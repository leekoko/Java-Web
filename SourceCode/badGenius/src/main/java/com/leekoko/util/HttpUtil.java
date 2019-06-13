package com.leekoko.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Http工具类
 * @author ilgoose
 *
 */
@Slf4j
public class HttpUtil {
	
	/**
	 * 编码格式
	 */
	private static final String CHARSET = "UTF-8";
	
	public static String getUrlContent4Get(String url) {
		return getUrlContent4Get(url, CHARSET);
	}
	
	/**
	 * 使用GET方式获取链接内容
	 * @param url
	 * @param charset
	 * @return
	 */
	public static String getUrlContent4Get(String url,String charset) {
		StringBuffer sb = new StringBuffer();
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		BufferedReader reader = null;
		
		try {
			//创建连接
			System.out.println("------"+url);
			URL getUrl = new URL(url);
			connection = (HttpURLConnection) getUrl.openConnection();
//			System.setProperty("weblogic.http.client.defaultConnectTimeout", "3000");
//			System.setProperty("weblogic.http.client.defaultReadTimeout", "3000");
//			System.setProperty("sun.net.client.defaultConnectTimeout", "3000");
//	        System.setProperty("sun.net.client.defaultReadTimeout", "3000");
			//请求方式
			connection.setRequestMethod("GET");
			connection.connect();
			int responseCode = connection.getResponseCode();
			System.out.print("------"+responseCode);

			//读取返回内容
			inputStream = connection.getInputStream();  
			reader = new BufferedReader(new InputStreamReader(inputStream, charset));
			String lines = null;
			while ((lines = reader.readLine()) != null) {
				sb.append(lines);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭
			if(null != reader){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != reader){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != connection){
				connection.disconnect();
			}
		}
		
		return sb.toString();
	}

	/**
	 * 使用GET方式获取链接内容
	 * @param path
	 * @param param
	 * @return
	 */
	public static String loadGET(String path,String param) throws Exception {
		URL restURL = new URL(path);
		/*
		 * 此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类 的子类HttpURLConnection
		 */
		HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		//请求方式
		conn.setRequestMethod("GET");
		//设置是否从httpUrlConnection读入，默认情况下是true; httpUrlConnection.setDoInput(true);
		conn.setDoOutput(true);
		//allowUserInteraction 如果为 true，则在允许用户交互（例如弹出一个验证对话框）的上下文中对此 URL 进行检查。
		conn.setAllowUserInteraction(false);

		PrintStream ps = new PrintStream(conn.getOutputStream());
		ps.print(param);
		ps.close();

		BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		String line,resultStr = "";
		while(null != (line = bReader.readLine())){
			resultStr += line;
		}
		bReader.close();
		return resultStr;
	}

	/**
	 * 使用Post方式获取链接内容
	 * @param path
	 * @param param
	 * @return
	 */
	public static String loadPost(String path ,String param) throws Exception {
		//使用之前写的方法创建httpClient实例
		CloseableHttpClient httpClient = createSSLClientDefault();

		String urlPath = new String(path);
		//建立连接
		URL url = new URL(urlPath);
		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
		//设置参数
		httpConn.setDoOutput(true);     //需要输出
		httpConn.setDoInput(true);      //需要输入
		httpConn.setUseCaches(false);   //不允许缓存
		httpConn.setRequestMethod("POST");      //设置POST方式连接
		//设置请求属性
		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
		httpConn.setRequestProperty("Charset", "UTF-8");
		//连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
		httpConn.connect();
		//建立输入流，向指向的URL传入参数
		DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
		dos.writeBytes(param);
		dos.flush();
		dos.close();
		//获得响应状态
		int resultCode = httpConn.getResponseCode();
		log.info("[HttpUtil-->使用Post方式获取链接内容],响应状态resultCode:"+resultCode);
		String line,resultStr = "";
		if(HttpURLConnection.HTTP_OK == resultCode){
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
			while(null != (line = responseReader.readLine())){
				log.info("[HttpUtil-->使用Post方式获取链接内容],line:"+line);
				resultStr += line;
			}
			responseReader.close();
		}
		log.info("[HttpUtil-->使用Post方式获取链接内容],返回值resultStr:"+resultStr);
		return resultStr;
	}



	/**
	 * 模拟发送 post 请求
	 */
	public static String doPost(String url, String data) {
		//构建POST请求   请求地址请更换为自己的。
		HttpPost post = new HttpPost(url);
		InputStream inputStream = null;
		String result="";
		try {
			//使用之前写的方法创建httpClient实例
			CloseableHttpClient httpClient = createSSLClientDefault();
			// 构造消息头
			post.setHeader("Content-type", "application/json; charset=utf-8");
			post.setHeader("Connection", "Close");
			if(StringUtils.isNotBlank(data)){
				// 构建消息实体
				StringEntity entity = new StringEntity(data, Charset.forName("UTF-8"));
				entity.setContentEncoding("UTF-8");
				// 发送Json格式的数据请求
				entity.setContentType("application/json");
				post.setEntity(entity);
			}
			//发送请求
			HttpResponse response = httpClient.execute(post);

			result = getResponseMessage(response);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 解析response
	 * @param response
	 * @return
	 */
	private static String getResponseMessage(HttpResponse response) {
		Header[] headers = response.getAllHeaders();
		String cookieResult = getCookieStr(headers);
		String result = "";
		result = result + getCookieStr(cookieResult, "route=");
		result = result + getCookieStr(cookieResult, "JSESSIONID=");
		return result;
	}

	private static String getCookieStr(String result, String s) {
		String routeStr = result.substring(result.indexOf(s), result.length());
		return routeStr.substring(0, routeStr.indexOf(";") + 1);
	}

	private static String getCookieStr(Header[] headers) {
		String result = "";
		for (Header header : headers) {
			result += header.getValue() + ";";
		}
		return result;
	}

	public static CloseableHttpClient createSSLClientDefault() {
		try {
			//使用 loadTrustMaterial() 方法实现一个信任策略，信任所有证书
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			//NoopHostnameVerifier类:  作为主机名验证工具，实质上关闭了主机名验证，它接受任何
			//有效的SSL会话并匹配到目标主机。
			HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();

	}
}

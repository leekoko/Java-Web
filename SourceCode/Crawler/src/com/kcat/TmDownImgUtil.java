package com.kcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 * @author leeyubin
 *
 */

public class TmDownImgUtil {
	/**
	 * 依据网址获取网站源码
	 * @param link
	 * @param encoding
	 * @return
	 */
	public static String htmlSource(String link,String encoding){
		StringBuilder stringBuilder=new StringBuilder();
		try {
			URL url =new URL(link);   //获取网络对象
			URLConnection uc=url.openConnection();   //建立网络链接  
			uc.setRequestProperty("User-Agent", "java");   //伪装
			InputStream inputStream=uc.getInputStream();  //获取文件输入流
			InputStreamReader in=new InputStreamReader(inputStream,encoding); //建立io流缓冲
			BufferedReader reader=new BufferedReader(in);   //下载代码缓冲区
			
			//开始下载源码
			//创建一个临时文件 
			String line=null;
			//循环
			while((line=reader.readLine())!=null){
				stringBuilder.append(line+"\r\n");
			}
			
			//关闭
			reader.close();
			in.close();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return stringBuilder.toString();
	}
	
}

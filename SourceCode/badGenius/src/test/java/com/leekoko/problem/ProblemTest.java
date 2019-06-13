package com.leekoko.problem;

import com.leekoko.util.HttpUtil;
import org.junit.Test;

public class ProblemTest {

    @Test
    public void loginTest(){
        String param = "j_username=440582199408010099&j_password=hnlg010099";
        String result = null;
        try {
//            result = HttpUtil.loadPost("https://wl.scutde.net:443/edu3/j_spring_security_check", param);
            result = HttpUtil.doPost("https://wl.scutde.net:443/edu3/j_spring_security_check", param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);

    }

    @Test
    public void cutCookie(){
        String result = "[Server: scutde/1.00.0, Date: Tue, 04 Jun 2019 17:09:38 GMT, Content-Length: 0, Connection: close, Set-Cookie: route=f11f636793262856edd9be756e0cc6c3; Path=/, P3P: CP=CAO PSA OUR, Set-Cookie: JSESSIONID=40B716374AF183CAC3DCD96E922C4A0F.tomcat_edu3_3; Path=/edu3; Secure, Set-Cookie: SPRING_SECURITY_REMEMBER_ME_COOKIE=\"\"; Expires=Thu, 01-Jan-1970 00:00:10 GMT; Path=/edu3, Location: https://wl.scutde.net/edu3/edu3/login.html;jsessionid=40B716374AF183CAC3DCD96E922C4A0F.tomcat_edu3_3?error=true]";
        result = result.substring(result.indexOf("route="),result.length());
        String route = result.substring(0,result.indexOf(";"));
        result = result.substring(result.indexOf("JSESSIONID="),result.length());
        String jessionID = result.substring(0,result.indexOf(";"));
        System.out.println(route + ";" + jessionID);
    }

    @Test
    public void cutTest(){
        String  urlStr = "ff8080813bb7dd34013bbc814b9d0025&pageNum=2&pageSize=10&scopeType=all&term=2&isPublished=Y&isAutoSave=N";
        int cutNum = urlStr.indexOf("&");
        if(cutNum > 0){
            urlStr = urlStr.substring(0,cutNum);
        }
        System.out.println(urlStr);
    }
}

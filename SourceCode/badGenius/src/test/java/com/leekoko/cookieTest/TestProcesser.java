package com.leekoko.cookieTest;

import com.leekoko.pipeline.MemoryPipeline;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * 测试cookie能不能用
 */
public class TestProcesser implements PageProcessor {


    private static String TARGET_URL = "https://wl.scutde.net/edu3/edu3/framework/index.html";
    String cookieStr = "JSESSIONID=9B9E8B67D9D6193E15C572205F64DEBD.tomcat_edu3_2;route=503e61637dd91cd98f986c297ebacc9f;";
    private Site site = Site.me().setDomain("wl.scutde.net");

    @Test
    public void cookieTest(){
        this.startCrawl();
    }

    public void startCrawl(){
        long startTime = System.currentTimeMillis();;
        MemoryPipeline pipeline = new MemoryPipeline();
        Spider.create(new TestProcesser()).addUrl(TARGET_URL).addPipeline(pipeline).thread(10).run();
        long endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }


    public void process(Page page) {
        //获取第一季度课程标题信息
        if (page.getUrl().regex(TARGET_URL).match()){
            //首页
            System.out.println("进入首页");
            getCourseTitleMessage(page);
        }

    }

    /**
     * 获取第一季度课程标题信息
     * @param page
     */
    private void getCourseTitleMessage(Page page) {
        Html html = page.getHtml();
//        System.out.println(html.toString());
        List<String> courseTitleList = html.xpath("//*[@id='tab']/tr/td[2]/a").all();
        if(courseTitleList.size() == 0){
            System.out.println("cookie可能过期了，需要更换一下cookie");
        }else {
            System.out.println("cookie访问成功");
        }
        return;
    }


    public Site getSite() {
        initCookie();
        return site;
    }

    /**
     * 初始化cookie信息
     */
    private void initCookie() {
        String[] cookieArr = cookieStr.split(";");
        for (int i = 0; i < cookieArr.length; i++) {
            String cookie = cookieArr[i];
            String[] strArr = cookie.split("=");
//            设置cookie的值
            setCookieValue(strArr);
        }
    }

    /**
     * 设置cookie的值
     * @param strArr
     */
    private void setCookieValue(String[] strArr) {
        if(strArr.length < 2){
            return;
        }
        site.addCookie(strArr[0],strArr[1]);
    }



}

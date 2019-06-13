package com.leekoko.crawlTest;

import us.codecraft.webmagic.Site;

//@TargetUrl("https://github.com/\\w+/\\w+")
//@HelpUrl("https://github.com/\\w+")
public class GithubRepo {

//    @ExtractBy(value = "//h1[@class='entry-title public']/strong/a/text()", notNull = true)
//    private String name;
//
//    @ExtractByUrl("https://github\\.com/(\\w+)/.*")
//    private String author;
//
//    @ExtractBy("//div[@id='readme']/tidyText()")
//    private String readme;
//
//    public static void main(String[] args) {
//        OOSpider.create(Site.me().setSleepTime(1000)
//                , new ConsolePageModelPipeline(), GithubRepo.class)
//                .addUrl("https://github.com/code4craft").thread(5).run();
//    }
}
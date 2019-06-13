package com.leekoko.util;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leekoko.pipeline.MemoryPipeline;
import com.leekoko.pojo.Chapter;
import com.leekoko.pojo.ChapterProblem;
import com.leekoko.pojo.Course;
import com.leekoko.pojo.Problem;
import com.leekoko.service.IChapterService;
import com.leekoko.service.IProblemService;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//注意线程问题
public class HGPageProcesser implements PageProcessor {

    private IProblemService problemService;

    private IChapterService chapterService;

    private HttpServletRequest request;


    public HGPageProcesser(IProblemService problemService, IChapterService chapterService, HttpServletRequest request){
        this.problemService = problemService;
        this.chapterService = chapterService;
        this.request = request;
    }
    private List<Chapter> chapterList = new ArrayList<Chapter>();
    private List<ChapterProblem> chapterProblemList = new ArrayList<ChapterProblem>();

    private static String TARGET_URL = "https://wl.scutde.net/edu3/edu3/framework/index.html";
    String cookieStr = "route=c70be6285cf639ed1c561ba730b3e730;JSESSIONID=D8D86DF0B9BC88150FCD91F2B6849F12.tomcat_edu3_4";
    String userName = "身份证内容异常";
    private Site site = Site.me().setDomain("wl.scutde.net");

    public void startCrawl(String cookie, String userName){
        System.out.println(cookie);
        if(StringUtils.isBlank(cookie) || StringUtils.isBlank(userName)){
            System.out.println("cookie或者userName是空的");
            return;
        }
        //初始化session信息
        initSession();
        long startTime = System.currentTimeMillis();
        HGPageProcesser newHgPageProcesser = new HGPageProcesser(problemService, chapterService, request);
        newHgPageProcesser.cookieStr = cookie;
        newHgPageProcesser.userName = userName;
        MemoryPipeline pipeline = new MemoryPipeline();
        Spider.create(newHgPageProcesser).addUrl(TARGET_URL).addPipeline(pipeline).thread(10).run();
        long endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }

    /**
     * 初始化session信息
     */
    private void initSession() {
        HttpSession session= request.getSession();
        session.setAttribute("chapterList", chapterList);
        session.setAttribute("chapterProblemList",chapterProblemList);
    }


    public void process(Page page) {
        //获取第一季度课程标题信息
        if (page.getUrl().regex(TARGET_URL).match()){
            //首页
            System.out.println("进入首页");
            getCourseTitleMessage(page);
        }else if(page.getUrl().regex("https://wl.scutde.net/edu3/edu3/learning/interactive/main.html").match()){
            //列表页
            System.out.println("进入课程列表页面");
            getChapterMessage(page);
        }else{
            //题目详情页   处理分页，获取题目进行存储
            System.out.println("进入题目页面");
            getProblemDetail(page);
        }
    }

    /**
     * 获取问题详情
     * @param page
     */
    private void getProblemDetail(Page page) {
        Html html = page.getHtml();
        List<String> problemIdList = html.css("input[name='activeCourseExamId']").all();
        List<String> problemAnswerList = html.xpath("//*[@id=\"StuActiveCourseExamForm\"]/table[1]/tbody/tr/td/div[3]/div[1]").all();
        List<String> titleList = html.xpath("//*[@id=\"StuActiveCourseExamForm\"]/table[1]/tbody/tr/td/div[1]").all();

        String chapterId = getChapterIdByUrl(page.getUrl().toString());
        //存储章节问题id
        saveChapterProblem(chapterId, problemIdList);



        if(problemAnswerList.size() == 0){
            System.out.println("没有答案");
            return;
        }

        //存储答案
        saveAnswer(problemIdList,problemAnswerList,titleList,chapterId);
        //添加下一页
        String nextPage = html.css(".sk_pagedown").toString();
        //获取下一页地址
        if(nextPage == null){
            return;
        }
        String nextPageResult = solveNextPage(nextPage, page);
        addSubjectRequest(page, nextPageResult);
    }

    /**
     * 存储章节问题id
     * @param chapterId
     * @param problemIdList
     */
    private void saveChapterProblem(String chapterId, List<String> problemIdList) {
        HttpSession session = request.getSession();
        List<ChapterProblem> chapterProblemTemp = (List<ChapterProblem>) session.getAttribute("chapterProblemList");
        //提取问题ID
        List<String> problemIdResultList = solveProblemId(problemIdList);
        for (String problemIdResult : problemIdResultList) {
            //存储章节题目对应数据
            ChapterProblem temp = new ChapterProblem();
            temp.setChapterId(chapterId);
            temp.setProblemId(problemIdResult);
            chapterProblemTemp.add(temp);
            session.setAttribute("chapterProblemList",chapterProblemTemp);
        }

    }

    private String getChapterIdByUrl(String url) {
        String urlStr = url.substring(url.indexOf("syllabusId=") + 11, url.length());
        int cutNum = urlStr.indexOf("&");
        if(cutNum > 0){
            urlStr = urlStr.substring(0,cutNum);
        }
        return urlStr;
    }

    /**
     * 获取题目页面下一页地址
     * @param nextPage
     * @return
     */
    private String solveNextPage(String nextPage, Page page) {
        String pageNum = nextPage.substring(nextPage.indexOf("goPage('")+8,nextPage.indexOf("')"));
        String preUrl = page.getUrl().toString();
        String url = preUrl + "&pageNum=" + pageNum + "&pageSize=10&scopeType=all&term=2&isPublished=Y&isAutoSave=N";
        return url;
    }

    /**
     * 存储答案
     * @param problemIdList
     * @param problemAnswerList
     */
    private void saveAnswer(List<String> problemIdList, List<String> problemAnswerList, List<String> titleList, String chapterId) {
        //提取问题ID
        List<String> problemIdResultList = solveProblemId(problemIdList);
        //提取答案值
        List<String> problemAnswerResultList = solveProblemAnswer(problemAnswerList);
        for (int i = 0; i < problemAnswerResultList.size(); i++) {
            //存储答案--值
            System.out.println(problemIdResultList.get(i)+"的答案：" + problemAnswerResultList.get(i));
            String id = problemIdResultList.get(i);
            //已有该答案，跳过
            if(problemService.getOne(new QueryWrapper<Problem>().eq("ID",id)) != null){
                continue;
            }
            Problem problem = new Problem();
            if(StringUtils.isEmpty(id)){
                id = UUID.randomUUID().toString();
            }
            problem.setId(id);
            problem.setAnswer(problemAnswerResultList.get(i));
            problem.setTitle(titleList.get(i));
            problemService.save(problem);
        }
    }

    /**
     * 提取答案值
     * @param problemAnswerList
     * @return
     */
    private List<String> solveProblemAnswer(List<String> problemAnswerList) {
        List<String> resultList = new ArrayList<String>();
        for (String answerEl : problemAnswerList) {
            int answerIndex = answerEl.indexOf("参考答案：");
            String answer = answerEl.substring(answerIndex+5,answerIndex+6);
            resultList.add(answer);
        }
        return resultList;
    }

    /**
     * 提取问题ID
     * @param problemIdList
     * @return
     */
    private List<String> solveProblemId(List<String> problemIdList) {
        List<String> resultList = new ArrayList<String>();
        for (String idEl : problemIdList) {
            String id = idEl.substring(idEl.indexOf("value=\"")+7,idEl.indexOf("\" name"));
            resultList.add(id);
        }
        return resultList;
    }

    /**
     * 获取课程ID
     * @param page
     */
    private void getChapterMessage(Page page) {
        String htmlStr = page.getHtml().toString();
        //获取ID节点json
        String zNodeStr = null;
        try {
            zNodeStr = getZNodeJ(htmlStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Chapter chapter = JSON.parseObject(zNodeStr, Chapter.class);
        //获取chapter列表
        List<String> courseUrlList = new ArrayList<String>();
        getChapterList(chapter, courseUrlList, "0");
        //添加题目页面url
        addSubjectRequest(page, courseUrlList);
    }

    /**
     * 保存用户与其Chapter信息到session
     * @param chapter
     */
    private void insertChapter2List(Chapter chapter, String parentCode) {
        //关联父子树关系
        Chapter temp = new Chapter();
        temp.setId(chapter.getId());
        temp.setParentCode(parentCode);
        temp.setIdCode(userName);
        temp.setName(chapter.getName());
        temp.setLevel(chapter.getLevel());

        HttpSession session = request.getSession();
        List<Chapter> chapterTemp = (List<Chapter>) session.getAttribute("chapterList");
        chapterTemp.add(temp);
        session.setAttribute("chapterList",chapterTemp);

    }

    /**
     * 添加题目页面请求
     * @param page
     */
    private void addSubjectRequest(Page page, List<String> courseUrlList) {
        if(courseUrlList == null || courseUrlList.size() == 0){
            return;
        }
        page.addTargetRequests(courseUrlList);
    }

    /**
     * 添加题目页面，单个url
     * @param page
     */
    private void addSubjectRequest(Page page, String courseUrl) {
        if(StringUtils.isBlank(courseUrl)){
            return;
        }
        List<String> urlList = new ArrayList<String>();
        urlList.add(courseUrl);
        page.addTargetRequests(urlList);
    }

    /**
     * 根据json对象获取chapter列表
     * @param chapter
     */
    private void getChapterList(Chapter chapter, List<String> courseUrlList, String parentCode) {
        //保存用户与其Chapter信息
        insertChapter2List(chapter, parentCode);
        if(chapter.getLevel().equals("1")){
            String idStr = chapter.getId();
            String[] idArr = idStr.split(",");
            String id = idArr[0];
            //存储一级课程id
            if(id.length() == 32){
                courseUrlList.add(id2Url(id));
            }
        }
        List<Chapter> nodes = chapter.getNodes();
        if(nodes == null || nodes.size() == 0){
            return;
        }
        for (int i = 0; i < nodes.size(); i++) {
            Chapter temp2 = nodes.get(i);
            getChapterList(temp2, courseUrlList, chapter.getId());
        }
    }

    /**
     * 详情页面url封装
     * @param id
     * @return
     */
    private String id2Url(String id) {
        return "https://wl.scutde.net/edu3/edu3/learning/interactive/activecourseexam/list.html?syllabusId="+id;
    }

    /**
     * 获取ID节点json
     * @param htmlStr
     */
    private String getZNodeJ(String htmlStr) throws IOException {
        InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(htmlStr.getBytes()));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        String result = "";
        while ((line = br.readLine()) != null) {
            if(line.contains("var zNodes")){
                result = line;
                break;
            }
        }
        result = result.replace("var zNodes =","").replace(";","").replace("\t","").trim();
        result = result.substring(1,result.length()-1);
        System.out.println(result);
        return result;
    }

    /**
     * 获取第一季度课程标题信息
     * @param page
     */
    private void getCourseTitleMessage(Page page) {
        Html html = page.getHtml();
        List<String> courseTitleList = html.xpath("//*[@id='tab']/tr/td[2]/a").all();
        if(courseTitleList.size() == 0){
            System.out.println("cookie可能过期了，需要更换一下cookie");
            return;
        }
        List<Course> list = new ArrayList<Course>();
        List<String> detailUrlList = new ArrayList<String>();
        for (Object courseTitle : courseTitleList){
            Course course = html2Model((String) courseTitle);
            list.add(course);
            String detailUrl = "https://wl.scutde.net/edu3/edu3/learning/interactive/main.html?planCourseId="+course.getCourseId()+"&courseId="+course.getPlanCourseId()+"&isNeedReExamination=";
            detailUrlList.add(detailUrl);
        }
        page.addTargetRequests(detailUrlList);
    }

    /**
     * 解析成Course对象
     * @param courseTitle
     * @return
     */
    private Course html2Model(String courseTitle) {
        Course course = new Course();
//        url转化为数组
        String[] arr = html2Arr(courseTitle);
        course.setPlanCourseId(arr[0]);
        course.setCourseId(arr[1]);
        course.setCourseTitle(arr[2]);
        return course;
    }

    /**
     * url转化为数组
     * @param courseTitle
     * @return
     */
    private String[] html2Arr(String courseTitle) {
        courseTitle = courseTitle.substring(courseTitle.indexOf("goInteractive")+15);
        courseTitle = courseTitle.replace("')\">","");
        courseTitle = courseTitle.replace("</a>","");
        return courseTitle.split("','");
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

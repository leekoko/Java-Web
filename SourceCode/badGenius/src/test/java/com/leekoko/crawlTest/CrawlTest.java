package com.leekoko.crawlTest;

import com.alibaba.fastjson.JSON;
import com.leekoko.pojo.Chapter;
import com.leekoko.util.HGPageProcesser;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CrawlTest {

    @Test
    public void startTest(){
//        HGPageProcesser processer = new HGPageProcesser();
//        processer.startCrawl();
    }

    /**
     * 切割测试
     */
    @Test
    public void cutTest(){
        String str = "<a href=\"javascript:void(0)\" onclick=\"goInteractive('ff808081129430cc0112da9881dc134f','ff808081685f902c016860d3db6220e8','')\">444</a>";
        str = str.substring(str.indexOf("goInteractive")+15);
        str = str.replace("')\">","");
        str = str.replace("</a>","");
        String[] split = str.split("','");
        System.out.println(split.length);
    }

    @Test
    public void jsCutTest() throws IOException {
        InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(LIST_PAGE_HTML.getBytes()));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();
        String result = "";
        while (line != null) {
            line = br.readLine(); // 一次读入一行数据
            if(line.contains("var zNodes")){
                result = line;
                break;
            }
        }
        result = result.replace("var zNodes =","").replace(";","").replace("\t","").trim();
        result = result.substring(1,result.length()-1);
        System.out.println(result);
    }

    /**
     * 解析json   loading
     */
    @Test
    public void resolveJson(){
        String json = "{\"name\":\"名师讲座\",\"id\":\"4aa66366326ce1eb013270e91114052c,\",\"open\":true,\"level\":0,\"nodes\":[{\"name\":\"人文社会科学类\",\"id\":\"4aa66366326ce1eb013270e991cd052d,1\",\"open\":true,\"level\":1,\"nodes\":[{\"name\":\"中国传统政治制度的整体认识\",\"id\":\"4aa66366326ce1eb013270ea16ec052f,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"中国古代科举制度的研究\",\"id\":\"4aa66366326ce1eb013270eeaf3e0531,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"哲学的性质与功能\",\"id\":\"4aa66366326ce1eb013270efdce50532,2\",\"open\":true,\"level\":2,\"nodes\":[{\"name\":\"矛盾法则与生活智慧\",\"id\":\"4aa663673436a33001343b3f8f65569b,3\",\"open\":true,\"level\":3,\"parentId\":\"4aa66366326ce1eb013270efdce50532\",\"exAttribute\":{\"mate\":\"Y\"}}],\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"管理的目的与任务\",\"id\":\"4aa66366326ce1eb013270f07de30535,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"企业运营的目的与机制等\",\"id\":\"4aa66366326ce1eb013270f0b0c60536,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"中国经济超越世界的投资战略选择\",\"id\":\"4aa66366326ce1eb013270f25d380539,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"经济学的思维方式\",\"id\":\"4aa66366326ce1eb013270f2811d053a,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"犯罪根源论\",\"id\":\"4aa66366326ce1eb013270f30353053d,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"我国刑法中的理论与实践问题\",\"id\":\"4aa66366326ce1eb013270f38973053e,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"文学与人生\",\"id\":\"4aa66366326ce1eb013270f427460542,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"心理学讲座\",\"id\":\"4aa663673696872a01369a583c763b53,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"形势与政策讲座（2012-10-28）\",\"id\":\"4aa663673b65444a013b6a2fd6ec7f76,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"大学——人生新起航\",\"id\":\"4aa66367413072a0014148bbecbe65aa,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"广东如何成为中国特色社会主义的排头兵\",\"id\":\"4aa663674346a51e014350b7344f0254,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}}],\"parentId\":\"4aa66366326ce1eb013270e91114052c\"},{\"name\":\"自然科学类\",\"id\":\"4aa66366326ce1eb013270f3e2310540,1\",\"level\":1,\"nodes\":[{\"name\":\"会计核算与涉税处理技巧\",\"id\":\"4aa66366326ce1eb013270f46b850543,2\",\"level\":2,\"parentId\":\"4aa66366326ce1eb013270f3e2310540\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"经济数学发展历史\",\"id\":\"4aa66366326ce1eb013270f48eb90544,2\",\"level\":2,\"parentId\":\"4aa66366326ce1eb013270f3e2310540\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"电子商务趋势与移动商务运用\",\"id\":\"4aa66366326ce1eb013270f4b2660545,2\",\"level\":2,\"parentId\":\"4aa66366326ce1eb013270f3e2310540\",\"exAttribute\":{\"mate\":\"Y\"}}],\"parentId\":\"4aa66366326ce1eb013270e91114052c\"},{\"name\":\"专利讲座\",\"id\":\"msjz-zhuanlijiangzuo,1\",\"level\":1,\"nodes\":[{\"name\":\"专利信息应用\",\"id\":\"msjz-zhuanlijiangzuo-1,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"专利权无效及专利侵权概述\",\"id\":\"msjz-zhuanlijiangzuo-2,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"专利法概述\",\"id\":\"msjz-zhuanlijiangzuo-3,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"企业知识产权管理规范解读\",\"id\":\"msjz-zhuanlijiangzuo-4,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"商标法概述及商标注册实务\",\"id\":\"msjz-zhuanlijiangzuo-5,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"外观设计专利申请实务\",\"id\":\"msjz-zhuanlijiangzuo-6,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"机械领域专利申请概述\",\"id\":\"msjz-zhuanlijiangzuo-7,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"生化领域专利申请实务\",\"id\":\"msjz-zhuanlijiangzuo-8,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"电学领域专利申请概述\",\"id\":\"msjz-zhuanlijiangzuo-9,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"知识产权对企业发展的重要意义\",\"id\":\"msjz-zhuanlijiangzuo-10,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"著作权法概述及著作权登记实务\",\"id\":\"msjz-zhuanlijiangzuo-11,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}}],\"parentId\":\"4aa66366326ce1eb013270e91114052c\"},{\"name\":\"创新与创业\",\"id\":\"msjz-shuangchuangkecheng,1\",\"level\":1,\"nodes\":[{\"name\":\"创新8大波\",\"id\":\"msjz-shuangchuangkecheng-1,2\",\"level\":2,\"parentId\":\"msjz-shuangchuangkecheng\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"创业8大波\",\"id\":\"msjz-shuangchuangkecheng-2,2\",\"level\":2,\"parentId\":\"msjz-shuangchuangkecheng\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"俞文辉校友创业事迹分享\",\"id\":\"ff80808163f347ec0163f80b5c882c02,2\",\"level\":2,\"parentId\":\"msjz-shuangchuangkecheng\",\"exAttribute\":{\"mate\":\"Y\"}}],\"parentId\":\"4aa66366326ce1eb013270e91114052c\"}]}";
        Chapter chapter = JSON.parseObject(JSON_OBJECT, Chapter.class);
        getChapterList(chapter);
        System.out.println(chapterList.size());
    }

    private List<Chapter> chapterList = new ArrayList<Chapter>();
    private List<Chapter> levelChapterList = new ArrayList<Chapter>();

    //根据json对象获取chapter列表
    private void getChapterList(Chapter chapter) {
        Chapter temp = new Chapter();
        temp.setId(chapter.getId());
        temp.setName(chapter.getName());
        temp.setLevel(chapter.getLevel());
        if(chapter.getLevel().equals("1")){
            levelChapterList.add(temp);
        }
        chapterList.add(temp);

        List<Chapter> nodes = chapter.getNodes();
        if(nodes == null || nodes.size() == 0){
            return;
        }
        for (int i = 0; i < nodes.size(); i++) {
            Chapter temp2 = nodes.get(i);
            getChapterList(temp2);
        }
    }

    @Test
    public void getLength(){
        System.out.println("4aa663663271a5ba013277d82e5203c3".length());
    }

    @Test
    public void cutId(){
        String idEl = "<input type=\"hidden\" value=\"ff80808167f882ad01680d356c016f03\" name=\"activeCourseExamId\" disabled>";
        String id = idEl.substring(idEl.indexOf("value=\"")+7,idEl.indexOf("\" name"));
        System.out.println(id);
    }

    @Test
    public void cutAnswer(){
        String answerEl = "<div style=\"color: green;\">\n" +
                " 参考答案：D\n" +
                "</div>";
        int answerIndex = answerEl.indexOf("参考答案：");
        String answer = answerEl.substring(answerIndex+5,answerIndex+6);
        System.out.println(answer);
    }

    @Test
    public void cutPageNum(){
        String pageEl = "<div class=\"sk_pagedown\">\n" +
                " <a href=\"javascript:goPage('2')\"></a>\n" +
                "</div>";
        String pageNum = pageEl.substring(pageEl.indexOf("goPage('")+8,pageEl.indexOf("')"));
        System.out.println(pageNum);

    }

    @Test
    public void getTitle(){

    }

    @Test
    public void getAnswerTitle(){

    }

    //列表页面html
    private static String LIST_PAGE_HTML = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            " <head> \n" +
            "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\"> \n" +
            "  <script type=\"text/javascript\">\n" +
            "\tvar baseUrl = \"https://wl.scutde.net:443/edu3\";\t\n" +
            "\t\n" +
            "</script>\n" +
            "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
            "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=7\">\n" +
            "  <title>华南理工大学网络教育平台V3.0 - 学习互动中心</title>\n" +
            "  <link rel=\"stylesheet\" href=\"https://wl.scutde.net:443/edu3/themes/default/style.css\">\n" +
            "  <link rel=\"stylesheet\" href=\"https://wl.scutde.net:443/edu3/themes/css/core.css\">\n" +
            "  <script type=\"text/javascript\" src=\"https://wl.scutde.net:443/edu3/jscript/jquery-1.8.3.min.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"https://wl.scutde.net:443/edu3/jscript/jquery.cookie.min.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"https://wl.scutde.net:443/edu3/jscript/framework/jquery.bgiframe.min.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"https://wl.scutde.net:443/edu3/jscript/framework/jquery.validate.min.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"https://wl.scutde.net:443/edu3/jscript/framework/framework.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"https://wl.scutde.net:443/edu3/jscript/jquery.uploadify/js/swfobject.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"https://wl.scutde.net:443/edu3/jscript/jquery.uploadify/js/jquery.uploadify.v2.1.0.min.js\"></script>\n" +
            "  <link rel=\"stylesheet\" href=\"https://wl.scutde.net:443/edu3/jscript/jquery.uploadify/css/uploadify.css\">\n" +
            "  <script type=\"text/javascript\" src=\"https://wl.scutde.net:443/edu3/jscript/editor/kindeditor.min.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"https://wl.scutde.net:443/edu3/jscript/autocomplete/jquery.flexselect.min.js\"></script>\n" +
            "  <link rel=\"stylesheet\" href=\"https://wl.scutde.net:443/edu3/jscript/autocomplete/flexselect.css\">\n" +
            "  <script type=\"text/javascript\" src=\"https://wl.scutde.net:443/edu3/jscript/ztree/jquery-ztree-2.4.min.js\"></script>\n" +
            "  <link rel=\"stylesheet\" href=\"https://wl.scutde.net:443/edu3/jscript/ztree/zTreeStyle.css\">\n" +
            "  <meta http-equiv=\"Cache-Control\" content=\"no-store\">\n" +
            "  <meta http-equiv=\"Pragma\" content=\"no-cache\">\n" +
            "  <meta http-equiv=\"Expires\" content=\"0\">\n" +
            "  <link type=\"text/css\" rel=\"stylesheet\" href=\"/edu3/themes/css/core.extends.css\">\n" +
            "  <style type=\"text/css\">\n" +
            "\t\t.scoretip { float: left; }\n" +
            "\t\t.optionbar { float: left; margin-right: 0.5em; border: 1px solid #183152; height: 8px;width: 25px; }\n" +
            "\t\t.optionbar div { float: left; height: 8px; overflow: hidden;background-color:#183152; background-repeat: repeat-x; background-position: 0 100%; }\n" +
            "\t</style>\n" +
            " </head>                        \n" +
            " <!--[if IE]>\n" +
            "\t\t<link href=\"https://wl.scutde.net:443/edu3/themes/css/ieHack.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
            "\t<![endif]-->    \n" +
            " <body> \n" +
            "  <div id=\"layout\"> \n" +
            "   <!-- 头部开始 --> \n" +
            "   <div id=\"header\" style=\"display:\"> \n" +
            "    <div class=\"headerNav\"> \n" +
            "     <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> \n" +
            "      <tbody>\n" +
            "       <tr> \n" +
            "        <td height=\"110\" colspan=\"2\" background=\"https://wl.scutde.net:443/edu3/style/default/learning/logo.png\"> \n" +
            "         <table width=\"100%\" height=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> \n" +
            "          <tbody>\n" +
            "           <tr> \n" +
            "            <td height=\"45\" colspan=\"3\" valign=\"top\"> \n" +
            "             <div style=\"float:right\"> \n" +
            "              <table width=\"748\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"stl1\"> \n" +
            "               <tbody>\n" +
            "                <tr> \n" +
            "                 <td height=\"24\" background=\"https://wl.scutde.net:443/edu3/style/default/learning/menu2.gif\"> \n" +
            "                  <ul class=\"nav\"> \n" +
            "                   <li>\n" +
            "                    <div>\n" +
            "                     欢迎\n" +
            "                     <b>李宇斌</b>同学,\n" +
            "                    </div></li> \n" +
            "                   <li>\n" +
            "                    <div class=\"scoretip\">\n" +
            "                     问答讨论：\n" +
            "                    </div>\n" +
            "                    <div class=\"optionbar\" title=\"0\">\n" +
            "                     <div id=\"askQuestionResults\"></div>\n" +
            "                    </div></li> \n" +
            "                   <li>\n" +
            "                    <div class=\"scoretip\">\n" +
            "                     随堂练习：\n" +
            "                    </div>\n" +
            "                    <div class=\"optionbar\" title=\"0\">\n" +
            "                     <div id=\"courseExamResults\"></div>\n" +
            "                    </div></li> \n" +
            "                   <li>\n" +
            "                    <div class=\"scoretip\">\n" +
            "                     作业：\n" +
            "                    </div>\n" +
            "                    <div class=\"optionbar\" title=\"0\">\n" +
            "                     <div id=\"exerciseResults\"></div>\n" +
            "                    </div></li> \n" +
            "                   <li>\n" +
            "                    <div class=\"scoretip\">\n" +
            "                     总评分：\n" +
            "                    </div>\n" +
            "                    <div class=\"optionbar\" title=\"0\">\n" +
            "                     <div id=\"usualResults\"></div>\n" +
            "                    </div></li> \n" +
            "                   <li><a target=\"dialog\" href=\"https://wl.scutde.net:443/edu3/edu3/student/feedback/input.html?from=interactive&amp;courseId=4aa66366326ce1eb013270e804010529\" style=\"color:blue\" width=\"800\" height=\"600\" rel=\"studentFeedback\" title=\"反馈\">反馈</a></li> \n" +
            "                   <li><a href=\"#\" style=\"color:blue\">帮助</a></li> \n" +
            "                  </ul> </td> \n" +
            "                </tr> \n" +
            "               </tbody>\n" +
            "              </table> \n" +
            "             </div> </td> \n" +
            "           </tr> \n" +
            "           <tr>\n" +
            "            <td height=\"30\" colspan=\"3\">&nbsp;\n" +
            "             <div>\n" +
            "              <div style=\"float: left;width: 80%;\">\n" +
            "               &nbsp;\n" +
            "              </div>\n" +
            "              <div>\n" +
            "               <span style=\"color: red\"> </span>\n" +
            "              </div>\n" +
            "             </div></td>\n" +
            "           </tr> \n" +
            "           <tr> \n" +
            "            <td width=\"1%\" height=\"46\" background=\"https://wl.scutde.net:443/edu3/style/default/learning/index_03.jpg\"> <img src=\"https://wl.scutde.net:443/edu3/style/default/learning/left.jpg\" width=\"22\" height=\"46\"> </td> \n" +
            "            <td width=\"98%\" height=\"46\" background=\"https://wl.scutde.net:443/edu3/style/default/learning/index_03.jpg\"> \n" +
            "             <ul id=\"learning_nav\"> \n" +
            "              <li> <a href=\"https://wl.scutde.net:443/edu3/edu3/framework/index.html\">返回我的主页</a> </li> \n" +
            "              <li> <a href=\"#\">课程介绍▼</a> \n" +
            "               <ul> \n" +
            "               </ul> </li> \n" +
            "              <li> <a href=\"#\" onclick=\"goQuizExercisesDistribution()\">随堂练习分布</a> </li> \n" +
            "              <li> <a href=\"#\" onclick=\"goExerciseBatch()\">平时作业</a> \n" +
            "               <!--<ul>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t<li><a href=\"#\" > 本课程作业</a></li>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t<li><a href=\"#\" onclick=\"goStudentExercise('isTypical','典型批改')\"> 典型批改</a></li>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t<li><a href=\"#\" onclick=\"goStudentExercise('isExcell','优秀作业')\"> 优秀作业</a></li>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t</ul>--> </li> \n" +
            "              <li> <a href=\"#\">复习总结▼</a> \n" +
            "               <ul> \n" +
            "                <li> <a href=\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/materevise/list.html?reviseCourseId=4aa66366326ce1eb013270e804010529\" target=\"navTab\" rel=\"mateRevise\" title=\"教师复习总结录像\">教师复习总结录像</a> </li> \n" +
            "                <!-- \n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t<li><a href=\"javascript:void(0)\" onclick=\"alertMsg.info('功能尚未开放，谢谢关注！');\"> 我的复习材料</a></li>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t --> \n" +
            "                <li><a href=\"#\" onclick=\"goCourseMockTest();\"> 模拟试题</a></li> \n" +
            "               </ul> </li> \n" +
            "              <!--20150303 新增：大作业--> \n" +
            "              <!----> \n" +
            "             </ul> </td> \n" +
            "            <td width=\"1%\" height=\"46\" align=\"right\" background=\"https://wl.scutde.net:443/edu3/style/default/learning/index_03.jpg\"> <img src=\"https://wl.scutde.net:443/edu3/style/default/learning/right.jpg\" width=\"18\" height=\"46\"> </td> \n" +
            "           </tr> \n" +
            "          </tbody>\n" +
            "         </table> </td> \n" +
            "       </tr> \n" +
            "       <tr> \n" +
            "        <td height=\"14\"> &nbsp; </td> \n" +
            "        <td width=\"100%\" valign=\"top\"></td> \n" +
            "       </tr> \n" +
            "      </tbody>\n" +
            "     </table> \n" +
            "    </div> \n" +
            "   </div> \n" +
            "   <!-- 头部结束 --> \n" +
            "   <!-- 左侧菜单开始 --> \n" +
            "   <div id=\"leftside\"> \n" +
            "    <!-- 分割 --> \n" +
            "    <div id=\"sidebar_s\" style=\"display:none;\"> \n" +
            "     <div class=\"collapse\"> \n" +
            "      <div class=\"toggleCollapse\"> \n" +
            "       <div></div> \n" +
            "      </div> \n" +
            "     </div> \n" +
            "    </div> \n" +
            "    <div id=\"sidebar\"> \n" +
            "     <div class=\"toggleCollapse\" style=\"height:46px;background:#ccc\"> \n" +
            "      <h2>名师讲座</h2> \n" +
            "      <div title=\"收缩\">\n" +
            "       收缩\n" +
            "      </div> \n" +
            "     </div> \n" +
            "     <div class=\"accordion\"> \n" +
            "      <div class=\"accordionContent\" style=\"display:block;height: 100%;width: 100%;\"> \n" +
            "       <ul id=\"syllabusTree\" class=\"ztree\"></ul> \n" +
            "      </div> \n" +
            "     </div> \n" +
            "    </div> \n" +
            "   </div> \n" +
            "   <!-- 左侧菜单结束 --> \n" +
            "   <!-- 主内容框架开始 --> \n" +
            "   <div id=\"container\"> \n" +
            "    <div id=\"navTab\" class=\"tabsPage\"> \n" +
            "     <div class=\"tabsPageHeader\" style=\"width:98%\"> \n" +
            "      <div class=\"tabsPageHeaderContent\"> \n" +
            "       <!-- 显示左右控制时添加 class=\"tabsPageHeaderMargin\" --> \n" +
            "       <ul class=\"navTab-tab\"> \n" +
            "        <li tabid=\"main\" class=\"main\"> <a href=\"#\"><span><span class=\"home_icon\">课程学习</span> </span> </a> </li> \n" +
            "       </ul> \n" +
            "      </div> \n" +
            "      <div class=\"tabsLeft\">\n" +
            "       left\n" +
            "      </div> \n" +
            "      <!-- 禁用只需要添加一个样式 class=\"tabsLeft tabsLeftDisabled\" --> \n" +
            "      <div class=\"tabsRight\">\n" +
            "       right\n" +
            "      </div> \n" +
            "      <!-- 禁用只需要添加一个样式 class=\"tabsRight tabsRightDisabled\" --> \n" +
            "      <div class=\"tabsMore\">\n" +
            "       more\n" +
            "      </div> \n" +
            "     </div> \n" +
            "     <div style=\"float:right;width:20px;margin-right:-4px;margin-top:-20px;\"> \n" +
            "      <img id=\"hiddenTop\" src=\"https://wl.scutde.net:443/edu3/themes/default/images/fullscreen.png\" title=\"全屏查看\" style=\"CURSOR: pointer\" onclick=\"fullScreen()\">\n" +
            "     </div> \n" +
            "     <ul class=\"tabsMoreList\"> \n" +
            "      <li><a href=\"javascript:void(0)\">课程学习</a></li> \n" +
            "     </ul> \n" +
            "     <!-- 内容显示 --> \n" +
            "     <div class=\"navTab-panel tabsPageContent\"> \n" +
            "      <div class=\"page\"> \n" +
            "       <div class=\"pageContent\" layouth=\"0\" style=\"margin:8px\"> \n" +
            "        <div class=\"tabs\" currentindex=\"0\" eventtype=\"click\" style=\"width:86%;float:left\"> \n" +
            "         <div class=\"tabsHeader\"> \n" +
            "          <div class=\"tabsHeaderContent\"> \n" +
            "           <ul> \n" +
            "            <li><a id=\"interactive_tab1\" href=\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/courselearningguid/list.html\" class=\"j-ajax\"><span>学习目标</span></a></li> \n" +
            "            <li><a id=\"interactive_tab2\" href=\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/materesource/list.html\" class=\"j-ajax\"><span>学习材料</span></a></li> \n" +
            "            <li><a id=\"interactive_tab3\" href=\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/activecourseexam/list.html\" class=\"j-ajax\"><span>随堂练习</span></a></li> \n" +
            "            <li> <a id=\"interactive_tab4\" href=\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/coursereference/list.html\" class=\"j-ajax\"><span>参考资料</span></a> </li> \n" +
            "            <!-- \t\t\t\t\t\t\t\t\n" +
            "\t\t\t\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\"><span>资料上传</span></a>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t</li> --> \n" +
            "           </ul> \n" +
            "          </div> \n" +
            "         </div> \n" +
            "         <div class=\"tabsContent\" layouth=\"40\" style=\"height:520px;background:#fff;\"> \n" +
            "          <div id=\"interactive_tab1Content\"> \n" +
            "          </div> \n" +
            "          <div id=\"interactive_tab2Content\" style=\"width: 100%;\"> \n" +
            "          </div> \n" +
            "          <div id=\"interactive_tab3Content\"> \n" +
            "          </div> \n" +
            "          <div id=\"interactive_tab4Content\"> \n" +
            "          </div> \n" +
            "          <div id=\"interactive_tab5Content\"> \n" +
            "          </div> \n" +
            "          <!-- \t\t\t\t\t\t\n" +
            "\t\t\t\t\t\t\t\t\t<div>\n" +
            "\t\t\t\t\t\t\t\t\t<p style=\"color:green\">该功能暂时还未开放...</p>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t --> \n" +
            "         </div> \n" +
            "         <div class=\"tabsFooter\"> \n" +
            "          <div class=\"tabsFooterContent\"></div> \n" +
            "         </div> \n" +
            "        </div> \n" +
            "        <!-- 右侧工具条 --> \n" +
            "        <div class=\"panel collapse\" defh=\"322\" style=\"float:left;width:10%;margin-left:6px;line-height:21px;\"> \n" +
            "         <h1>互动区</h1> \n" +
            "         <div style=\"background:#fff\"> \n" +
            "          <ul> \n" +
            "           <li style=\"padding-top:6px;text-align:center\"> <img src=\"https://wl.scutde.net:443/edu3/themes/default/images/learning/hd_notice.png\"><br> <a href=\"#\" onclick=\"openCourseNotice();\" title=\"老师发布的课程公告\">课程公告<span id=\"_courseNoticeNum\"></span></a> </li> \n" +
            "           <li style=\"padding-top:10px;text-align:center\"> <img src=\"https://wl.scutde.net:443/edu3/themes/default/images/learning/hd_tips.png\"><br> <a href=\"#\" onclick=\"openCourseTips();\" title=\"该课程的学习提醒\">温馨提示</a> </li> \n" +
            "           <li style=\"padding-top:10px;text-align:center\"> <img src=\"https://wl.scutde.net:443/edu3/themes/default/images/learning/hd_facebook.png\"><br> <a target=\"dialog\" href=\"https://wl.scutde.net:443/edu3/edu3/student/feedback/input.html?from=interactive&amp;courseId=4aa66366326ce1eb013270e804010529\" style=\"color:blue\" width=\"800\" height=\"600\" rel=\"studentFeedback\" title=\"反馈学习中的问题\"><font color=\"blue\">课件问题反馈</font></a> </li> \n" +
            "          </ul> \n" +
            "         </div> \n" +
            "        </div> \n" +
            "       </div> \n" +
            "      </div> \n" +
            "     </div> \n" +
            "    </div> \n" +
            "   </div> \n" +
            "   <!-- 页脚开始 --> \n" +
            "   <div id=\"taskbar\" style=\"left:0px; display:none;\"> \n" +
            "    <div class=\"taskbarContent\"> \n" +
            "     <ul></ul> \n" +
            "    </div> \n" +
            "    <div class=\"taskbarLeft taskbarLeftDisabled\" style=\"display:none;\">\n" +
            "      taskbarLeft \n" +
            "    </div> \n" +
            "    <div class=\"taskbarRight\" style=\"display:none;\">\n" +
            "      taskbarRight \n" +
            "    </div> \n" +
            "   </div> \n" +
            "   <div id=\"splitBar\"></div> \n" +
            "   <div id=\"splitBarProxy\"></div> \n" +
            "  </div> \n" +
            "  <div id=\"footer\">\n" +
            "    版权所有：广州华南教育科技发展有限公司 Version：3.3.4(#4224) (update:2019-05-10 ) Host：tomcat_edu3_4 \n" +
            "  </div> \n" +
            "  <!--拖动效果--> \n" +
            "  <div class=\"resizable\"></div> \n" +
            "  <!--阴影--> \n" +
            "  <div class=\"shadow\" style=\"width:508px; top:148px; left:296px;\"> \n" +
            "   <div class=\"shadow_h\"> \n" +
            "    <div class=\"shadow_h_l\"></div> \n" +
            "    <div class=\"shadow_h_r\"></div> \n" +
            "    <div class=\"shadow_h_c\"></div> \n" +
            "   </div> \n" +
            "   <div class=\"shadow_c\"> \n" +
            "    <div class=\"shadow_c_l\" style=\"height:296px;\"></div> \n" +
            "    <div class=\"shadow_c_r\" style=\"height:296px;\"></div> \n" +
            "    <div class=\"shadow_c_c\" style=\"height:296px;\"></div> \n" +
            "   </div> \n" +
            "   <div class=\"shadow_f\"> \n" +
            "    <div class=\"shadow_f_l\"></div> \n" +
            "    <div class=\"shadow_f_r\"></div> \n" +
            "    <div class=\"shadow_f_c\"></div> \n" +
            "   </div> \n" +
            "  </div> \n" +
            "  <!--遮盖屏幕--> \n" +
            "  <div id=\"alertBackground\" class=\"alertBackground\"></div> \n" +
            "  <div id=\"dialogBackground\" class=\"alertBackground\"></div> \n" +
            "  <div id=\"background\" class=\"background\"></div> \n" +
            "  <div id=\"progressBar\" class=\"progressBar\">\n" +
            "    数据加载中，请稍等... \n" +
            "  </div> \n" +
            "  <input type=\"hidden\" name=\"syllabusId\" id=\"syllabusId\" value=\"\"> \n" +
            "  <script type=\"text/javascript\">\n" +
            "\tif ($.browser.msie) {//如果是IE，则定时回收内存\n" +
            "\t\twindow.setInterval(\"CollectGarbage();\", 10000);\n" +
            "\t}\n" +
            "\t\n" +
            "\t$(document).ready(function(){\n" +
            "\t\tDWZ.init(\"https://wl.scutde.net:443/edu3/jscript/framework/dwz.plugin.xml\", {\t\t\t\t\n" +
            "\t\t\t\tcallback:function(){\n" +
            "\t\t\t\t\tinitEnv();\n" +
            "\t\t\t\t\t$(\"#themeList\").theme({themeBase:\"themes\"});\n" +
            "\t\t\t\t}\n" +
            "\t\t\t});\t\t\n" +
            "\t\t\tresizeLayout();\n" +
            "\t\t\t\n" +
            "\t\t\t//菜单\n" +
            "\t\t\tmenuFix();\n" +
            "\t\t\t//初始化Ztree\n" +
            "\t\t    var zTree1;\n" +
            "\t\t\tvar setting = {\tcheckable: false,expandSpeed:\"\",callback: {click:\tzTreeOnClick }\t};\n" +
            "\t\t\t\n" +
            "\t\t \tvar zNodes = [{\"name\":\"名师讲座\",\"id\":\"4aa66366326ce1eb013270e91114052c,\",\"open\":true,\"level\":0,\"nodes\":[{\"name\":\"人文社会科学类\",\"id\":\"4aa66366326ce1eb013270e991cd052d,1\",\"open\":true,\"level\":1,\"nodes\":[{\"name\":\"中国传统政治制度的整体认识\",\"id\":\"4aa66366326ce1eb013270ea16ec052f,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"中国古代科举制度的研究\",\"id\":\"4aa66366326ce1eb013270eeaf3e0531,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"哲学的性质与功能\",\"id\":\"4aa66366326ce1eb013270efdce50532,2\",\"open\":true,\"level\":2,\"nodes\":[{\"name\":\"矛盾法则与生活智慧\",\"id\":\"4aa663673436a33001343b3f8f65569b,3\",\"open\":true,\"level\":3,\"parentId\":\"4aa66366326ce1eb013270efdce50532\",\"exAttribute\":{\"mate\":\"Y\"}}],\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"管理的目的与任务\",\"id\":\"4aa66366326ce1eb013270f07de30535,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"企业运营的目的与机制等\",\"id\":\"4aa66366326ce1eb013270f0b0c60536,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"中国经济超越世界的投资战略选择\",\"id\":\"4aa66366326ce1eb013270f25d380539,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"经济学的思维方式\",\"id\":\"4aa66366326ce1eb013270f2811d053a,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"犯罪根源论\",\"id\":\"4aa66366326ce1eb013270f30353053d,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"我国刑法中的理论与实践问题\",\"id\":\"4aa66366326ce1eb013270f38973053e,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"文学与人生\",\"id\":\"4aa66366326ce1eb013270f427460542,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"心理学讲座\",\"id\":\"4aa663673696872a01369a583c763b53,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"形势与政策讲座（2012-10-28）\",\"id\":\"4aa663673b65444a013b6a2fd6ec7f76,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"大学——人生新起航\",\"id\":\"4aa66367413072a0014148bbecbe65aa,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"广东如何成为中国特色社会主义的排头兵\",\"id\":\"4aa663674346a51e014350b7344f0254,2\",\"open\":true,\"level\":2,\"parentId\":\"4aa66366326ce1eb013270e991cd052d\",\"exAttribute\":{\"mate\":\"Y\"}}],\"parentId\":\"4aa66366326ce1eb013270e91114052c\"},{\"name\":\"自然科学类\",\"id\":\"4aa66366326ce1eb013270f3e2310540,1\",\"level\":1,\"nodes\":[{\"name\":\"会计核算与涉税处理技巧\",\"id\":\"4aa66366326ce1eb013270f46b850543,2\",\"level\":2,\"parentId\":\"4aa66366326ce1eb013270f3e2310540\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"经济数学发展历史\",\"id\":\"4aa66366326ce1eb013270f48eb90544,2\",\"level\":2,\"parentId\":\"4aa66366326ce1eb013270f3e2310540\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"电子商务趋势与移动商务运用\",\"id\":\"4aa66366326ce1eb013270f4b2660545,2\",\"level\":2,\"parentId\":\"4aa66366326ce1eb013270f3e2310540\",\"exAttribute\":{\"mate\":\"Y\"}}],\"parentId\":\"4aa66366326ce1eb013270e91114052c\"},{\"name\":\"专利讲座\",\"id\":\"msjz-zhuanlijiangzuo,1\",\"level\":1,\"nodes\":[{\"name\":\"专利信息应用\",\"id\":\"msjz-zhuanlijiangzuo-1,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"专利权无效及专利侵权概述\",\"id\":\"msjz-zhuanlijiangzuo-2,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"专利法概述\",\"id\":\"msjz-zhuanlijiangzuo-3,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"企业知识产权管理规范解读\",\"id\":\"msjz-zhuanlijiangzuo-4,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"商标法概述及商标注册实务\",\"id\":\"msjz-zhuanlijiangzuo-5,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"外观设计专利申请实务\",\"id\":\"msjz-zhuanlijiangzuo-6,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"机械领域专利申请概述\",\"id\":\"msjz-zhuanlijiangzuo-7,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"生化领域专利申请实务\",\"id\":\"msjz-zhuanlijiangzuo-8,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"电学领域专利申请概述\",\"id\":\"msjz-zhuanlijiangzuo-9,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"知识产权对企业发展的重要意义\",\"id\":\"msjz-zhuanlijiangzuo-10,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"著作权法概述及著作权登记实务\",\"id\":\"msjz-zhuanlijiangzuo-11,2\",\"level\":2,\"parentId\":\"msjz-zhuanlijiangzuo\",\"exAttribute\":{\"mate\":\"Y\"}}],\"parentId\":\"4aa66366326ce1eb013270e91114052c\"},{\"name\":\"创新与创业\",\"id\":\"msjz-shuangchuangkecheng,1\",\"level\":1,\"nodes\":[{\"name\":\"创新8大波\",\"id\":\"msjz-shuangchuangkecheng-1,2\",\"level\":2,\"parentId\":\"msjz-shuangchuangkecheng\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"创业8大波\",\"id\":\"msjz-shuangchuangkecheng-2,2\",\"level\":2,\"parentId\":\"msjz-shuangchuangkecheng\",\"exAttribute\":{\"mate\":\"Y\"}},{\"name\":\"俞文辉校友创业事迹分享\",\"id\":\"ff80808163f347ec0163f80b5c882c02,2\",\"level\":2,\"parentId\":\"msjz-shuangchuangkecheng\",\"exAttribute\":{\"mate\":\"Y\"}}],\"parentId\":\"4aa66366326ce1eb013270e91114052c\"}]}];\t\n" +
            "\t\t\t$(document).ready(function(){\t\t\t\t\t\n" +
            "\t\t\t\tzTree1 = $(\"#syllabusTree\").zTree(setting, zNodes);\t\t\t\t\n" +
            "\t\t\t});\n" +
            "\t\t\t\t\n" +
            "\t\t\tfunction zTreeOnClick(event, treeId, treeNode) {\n" +
            "\t\t\t\tvar exAttribute = treeNode.exAttribute || {};\n" +
            "\t\t\t\t//学习目标\n" +
            "\t\t\t\t$(\"#interactive_tab1,#interactive_tab1Content\").toggle(exAttribute['guid']=='Y');\t\n" +
            "\t\t\t\t\n" +
            "\t\t\t\t//学习材料\n" +
            "\t\t\t\t$(\"#interactive_tab2,#interactive_tab2Content\").toggle(exAttribute['mate']=='Y');\t\t\t\t\n" +
            "\t\t\t\t\n" +
            "\t\t\t\t//随堂练习\n" +
            "\t\t\t\t$(\"#interactive_tab3,#interactive_tab3Content\").toggle(exAttribute['exam']=='Y');\n" +
            "\t\t\t\t\n" +
            "\t\t\t\t//参考资料\n" +
            "\t\t\t\t$(\"#interactive_tab4,#interactive_tab4Content\").toggle(exAttribute['ref']=='Y');\n" +
            "\t\t\t\t\t\t\t\t\n" +
            "\t\t\t\t//if(treeNode.parentId!=\"\"){\n" +
            "\t\t\t\t\t$(\"#syllabusId\").val(treeNode.id.split(\",\")[0]);\t\n" +
            "\t\t\t\t\t//if(treeNode.id.split(\",\")[1]==1){ \t\t\n" +
            "\t\t\t\t\t\t$(\"#interactive_tab1\").attr('href',\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/courselearningguid/list.html?syllabusId=\"+treeNode.id.split(\",\")[0]);\n" +
            "\t\t\t\t\t//}\n" +
            "\t\t\t\t\t$(\"#interactive_tab2\").attr('href',\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/materesource/list.html?syllabusId=\"+treeNode.id.split(\",\")[0]);\n" +
            "\t\t\t\t\t$(\"#interactive_tab3\").attr('href',\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/activecourseexam/list.html?syllabusId=\"+treeNode.id.split(\",\")[0]);\t\t\n" +
            "\t\t\t\t\t$(\"#interactive_tab4\").attr('href',\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/coursereference/list.html?syllabusId=\"+treeNode.id.split(\",\")[0]);\n" +
            "\t\t\t\t\t$(\"#interactive_tab5\").attr('href',\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/bbstopic/list.html?teachType=networkstudy&courseId=4aa66366326ce1eb013270e804010529&syllabusId=\"+treeNode.id.split(\",\")[0]);\t\t\n" +
            "\t\t\t\t\t$(\"[id^=interactive_tab]:visible:first\").click();\n" +
            "\t\t\t\t\tnavTab._switchTab(0);\t\n" +
            "\t\t\t\t\t$(\"#webContentFrame\").attr(\"src\",\"\");\t\t//清空视频\t\t\t\t\n" +
            "\t\t\t\t//} \n" +
            "\t\t\t\t\n" +
            "\t\t\t}\n" +
            "\t\t\t\n" +
            "\t\t\t//获取平时成绩积分\n" +
            "\t\t\tgetUsualResults();\t\t\t\n" +
            "\t\t\t\n" +
            "\t\t\twindow.setTimeout(function(){\t\t\n" +
            "\t\t\t\tif($(\"#syllabusTree_2_a\")){ //第一章\t\t\t\t\n" +
            "\t\t\t\t\t$(\"#syllabusTree_2_a\").click();\n" +
            "\t\t\t\t}\n" +
            "\t\t\t},500);\n" +
            "\t\t\t\n" +
            "\t\twindow.setTimeout(getCourseNotice,3000);//获取课程通知数量等信息\n" +
            "\t\t\n" +
            "\t\tgetOnlieExam();\n" +
            "\t\n" +
            "\t\t//20190304 新增 直接跳转随堂练习的页面\n" +
            "\t\tvar yh='';\t\t\t\n" +
            "\t\t//如果标识为1则表示是要转入做随堂练习。\n" +
            "\t\tif(yh==\"1\"){\n" +
            "\t\t\twindow.setTimeout(function(){\t\n" +
            "\t\t\tgoQuizExercisesDistribution();\n" +
            "\t\t\t},500);\n" +
            "\t      }else if(yh=='2'){\n" +
            "\t    \t  window.setTimeout(function(){\n" +
            "\t    \t\t  goExerciseBatch()();\n" +
            "\t    \t  },500);\n" +
            "\t      }else if(yh=='3'){\n" +
            "\t    \t  window.setTimeout(function(){\n" +
            "\t    \t\t  goExerciseBatch()();\n" +
            "\t    \t  },500);\n" +
            "\t      }\n" +
            "\t\t\n" +
            "\t   \t\n" +
            "\t});\t\n" +
            "\t//var sidebarh;\n" +
            "\t//全屏查看\n" +
            "\tfunction fullScreen(){\t\t\n" +
            "\t\tif($(\"#header\").height() == 118){\t\n" +
            "\t\t\t//sidebarh=$(\"#sidebar .accordionContent\").height();\n" +
            "\t\t\t$(\"#sidebar .toggleCollapse div\").click();\t\t\n" +
            "\t\t\t$(\"#header\").hide();\n" +
            "\t\t\tresizeLayout1();\n" +
            "\t\t\t$(\"#hiddenTop\").attr({ title: \"退出全屏查看\"} );\n" +
            "\t\t\t$(window).resize();\t\n" +
            "\t\t\t$(\"#webContentFrame\").height($(\".tabsContent\").height());\n" +
            "\t\t\t//$(\".zTreeDemoBackground\").height($(\"#sidebar_s\").height()-46);\t\t\n" +
            "\t\t}else{\n" +
            "\t\t\t$(\"#sidebar_s .toggleCollapse div\").click();\n" +
            "\t\t\t$(\"#header\").show();\t\t\t\t\t\t\t\t\n" +
            "\t\t\tresizeLayout();\t\t\t\t\t\t\t\t\n" +
            "\t\t\t$(\"#hiddenTop\").attr({ title: \"全屏查看\"} );\t\n" +
            "\t\t\t$(window).resize();\t\t\n" +
            "\t\t\t//$(\"#sidebar .accordionContent\").height(sidebarh);\n" +
            "\t\t\t//$(\".zTreeDemoBackground\").height($(\"#sidebar_s\").height()-46);\n" +
            "\t\t\t$(\"#sidebar .accordionContent\").height($(\"#sidebar_s\").height()-46);\n" +
            "\t\t\t$(\"#webContentFrame\").height($(\".tabsContent\").height());\n" +
            "\t\t}\t\n" +
            "\t\t$(\"#matesList\").height($(\".tabsContent\").height()-10);\n" +
            "\t}\n" +
            "\t\n" +
            "\tfunction resizeLayout(){//重新调整框架布局\n" +
            "\t\t$(\"#header\").height(\"118px\");\t\n" +
            "\t\t$(\"#sidebar\").css({width:250});\t\n" +
            "\t\t$(\"#leftside\").css({top:118});\n" +
            "\t\t$(\"#container\").css({top:118,left:260});\n" +
            "\t\t$(\"#splitBar, #splitBarProxy\").css({top:118,left:255});\t\n" +
            "\t\t\n" +
            "\t\t//$(\".zTreeDemoBackground\").height($(\"#container\").height()-46);\t\n" +
            "\t}\t\n" +
            "\t\n" +
            "\tfunction resizeLayout1(){//重新调整框架布局1\n" +
            "\t\t$(\"#header\").height(0);\t\n" +
            "\t\t$(\"#sidebar\").css({width:250});\t\n" +
            "\t\t$(\"#leftside\").css({top:0});\n" +
            "\t\t$(\"#container\").css({top:0,left:260});\n" +
            "\t\t$(\"#splitBar, #splitBarProxy\").css({top:0,left:255});\t\n" +
            "\t\t\n" +
            "\t\t//$(\".zTreeDemoBackground\").height($(\"#sidebar_s\").height()-46);\t\n" +
            "\t}\n" +
            "\tvar teachType = \"networkstudy\";\n" +
            "\t//重新登录\n" +
            "\tfunction relogin(){\t\t\n" +
            "\t\t$.pdialog.open('https://wl.scutde.net:443/edu3/edu3/relogin.html?user=440582199408010099','relogin','重新登录',{mask:true,width:400,height:180});\n" +
            "\t}\n" +
            "\t\n" +
            "\t//课程概况\n" +
            "\tfunction goCourseOverview(overviewId,typename){\n" +
            "\t\tvar url = \"https://wl.scutde.net:443/edu3/edu3/learning/interactive/courseoverview/view.html?overviewId=\"+overviewId;\n" +
            "\t\tnavTab.openTab(\"navTab\"+overviewId, url, typename);\n" +
            "\t}\n" +
            "\t//打开温馨提示\n" +
            "\tfunction openCourseTips(){\n" +
            "\t\tnavTab.openTab(\"navTabCourseTips\",\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/message/list.html?courseId=4aa66366326ce1eb013270e804010529\",\"温馨提示\");\n" +
            "\t}\t\n" +
            "\t//互动区-打开课程公告\n" +
            "\tfunction openCourseNotice(){\n" +
            "\t\tnavTab.openTab(\"navTabCourseNotice\",\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/coursenotice/list.html?courseId=4aa66366326ce1eb013270e804010529\",\"课程公告\");\n" +
            "\t}\n" +
            "\t//互动区-打开课程小组讨论\n" +
            "    function openCourseGroup(){\n" +
            "\t\tif(teachType!='facestudy'){\n" +
            "\t\t\twindow.open(\"https://wl.scutde.net:443/edu3/edu3/learning/bbs/section.html?courseId=4aa66366326ce1eb013270e804010529&sectionId=402881382c3a07b8012c3a09471e0002\",\"newwindow\");\n" +
            "\t\t}else{\n" +
            "\t\t\talertMsg.warn(\"当前课程部分学习内容需面授，不允许进入此功能。\");\n" +
            "\t\t}\n" +
            "\t\t\n" +
            "\t}\n" +
            "\t//互动区-打开课程论坛\n" +
            "\tfunction openCourseBbs(){\n" +
            "\t\tif(teachType!='facestudy'){\n" +
            "\t\t\twindow.open('https://wl.scutde.net:443/edu3/edu3/learning/bbs/index.html?courseId=4aa66366326ce1eb013270e804010529','course_bbs');\n" +
            "\t\t}else{\n" +
            "\t\t\talertMsg.warn(\"当前课程部分学习内容需面授，不允许进入此功能。\");\n" +
            "\t\t}\n" +
            "\t}\n" +
            "\t//互动区-打开我要提问\n" +
            "\tfunction openCourseAsk(){\t\n" +
            "\t\t//$(\"#interactive_tab5\").click();\n" +
            "\t\tif(teachType!='facestudy'){\n" +
            "\t\t\t//首先进行提问时间间隔校验\n" +
            "\t\t\tvar courseId = '4aa66366326ce1eb013270e804010529';\n" +
            "\t\t\t$.ajax({\n" +
            "\t            type:\"POST\",\n" +
            "\t            url:\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/bbstopic/checkTimeInterval.html\",\n" +
            "\t            data:{courseId:courseId },\n" +
            "\t            async:false,\n" +
            "\t            dataType:'json',         \n" +
            "\t            success:function(data){          \t   \t\t\n" +
            "         \t\t  if(data.statusCode == 200){\n" +
            "         \t\t\t  var isAllow = data.isAllow;\n" +
            "         \t\t\t  if(isAllow==true||isAllow==\"true\"){\n" +
            "         \t\t\t\t $.pdialog.open(baseUrl+\"/edu3/learning/interactive/bbstopic/ask.html?from=sidebar&courseId=4aa66366326ce1eb013270e804010529&syllabusId=\"+$('#syllabusId').val(), \"dialogQuestion\", \"我要提问\", {height:580,width:720});\n" +
            "         \t\t\t  }else{\n" +
            "         \t\t\t\t alertMsg.warn(data.message);\n" +
            "         \t\t\t  }\n" +
            "         \t\t  }else{\n" +
            "         \t\t\t  alertMsg.warn(data.message);\n" +
            "         \t\t  }         \n" +
            "\t          }            \n" +
            "\t\t\t});\n" +
            "\t\t}else{\n" +
            "\t\t\talertMsg.warn(\"当前课程部分学习内容需面授，不允许进入此功能。\");\n" +
            "\t\t}\n" +
            "\t}\n" +
            "\t//显示FAQ问题\n" +
            "\tfunction goFAQ(){\n" +
            "\t\tnavTab.openTab(\"FAQ\",\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/faq/list.html?courseId=4aa66366326ce1eb013270e804010529\",\"FAQ问题\");\n" +
            "\t}\n" +
            "\t//显示随堂练习分布以及完成情况\n" +
            "\tfunction goQuizExercisesDistribution(){\n" +
            "\t\tnavTab.openTab(\"exerciseBatch\",\"https://wl.scutde.net:443/edu3/edu3/learning/exercise/practice-list.html?courseId=4aa66366326ce1eb013270e804010529&flag=learn&examStatus=&isNeedexam=\",\"随堂练习分布\");\n" +
            "\t}\n" +
            "\t//显示课程作业\n" +
            "\tfunction goExerciseBatch(){\n" +
            "\t\tnavTab.openTab(\"exerciseBatch\",\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/exercisebatch/list.html?courseId=4aa66366326ce1eb013270e804010529\",\"课程作业\");\n" +
            "\t}\n" +
            "\t//典型批改和优秀作业\n" +
            "\tfunction goStudentExercise(type,name){\n" +
            "\t\tnavTab.openTab(type,\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/studentexercise/list.html?courseId=4aa66366326ce1eb013270e804010529&type=\"+type,name);\n" +
            "\t}\t\n" +
            "\t//显示大作业\n" +
            "\tfunction goBigHomeWork(){\n" +
            "\t\tnavTab.openTab(\"bigHomeWorkBatch\",\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/bigHomeWorkbatch/list.html?courseId=4aa66366326ce1eb013270e804010529\",\"大作业\");\n" +
            "\t}\n" +
            "\t//模拟试题\n" +
            "\tfunction goCourseMockTest(){\n" +
            "\t\tvar url = \"https://wl.scutde.net:443/edu3/edu3/learning/interactive/coursemocktest/view.html?courseId=4aa66366326ce1eb013270e804010529\";\n" +
            "\t\tnavTab.openTab(\"CouseMockTest\", url, \"模拟试题\");\n" +
            "\t}\n" +
            "\t//获取平时成绩积分\n" +
            "\tfunction getUsualResults(){\t\t\n" +
            "\t\t$.ajax({\n" +
            "\t\t   type: \"POST\",\n" +
            "\t\t   url: \"https://wl.scutde.net:443/edu3/edu3/learning/interactive/usualresults/ajax.html\",\t\n" +
            "\t\t   data: \"courseId=4aa66366326ce1eb013270e804010529&teachType=networkstudy\",\n" +
            "\t\t   dataType: \"json\",\t    \n" +
            "\t\t   global:false,\n" +
            "\t\t   success: function(data){\t \n" +
            "\t\t\t   //$(\"#bbsResults\").css(\"width\",data.bbsResults+\"%\").parent().attr(\"title\",data.bbsResults);\t\n" +
            "\t\t\t   $(\"#askQuestionResults\").css(\"width\",data.askQuestionResults+\"%\").parent().attr(\"title\",data.askQuestionResults);\n" +
            "\t\t\t   $(\"#exerciseResults\").css(\"width\",data.exerciseResults+\"%\").parent().attr(\"title\",data.exerciseResults);\n" +
            "\t\t\t   $(\"#courseExamResults\").css(\"width\",data.courseExamResults+\"%\").parent().attr(\"title\",data.courseExamResults);\n" +
            "\t\t\t   $(\"#usualResults\").css(\"width\",data.usualResults+\"%\").parent().attr(\"title\",data.usualResults);\n" +
            "\t\t   \t\t/*\n" +
            "\t\t\t   if(data.exerciseShow=='N'){\n" +
            "\t\t   \t\t $(\"#exerciseResults\").parent().parent().hide();\n" +
            "\t\t   \t\t} else {\n" +
            "\t\t   \t\t $(\"#exerciseResults\").parent().parent().show();\n" +
            "\t\t   \t\t}*/\n" +
            "\t\t   }\n" +
            "\t\t});\n" +
            "\t}\n" +
            "\t//下拉菜单\n" +
            "\tfunction menuFix() { \n" +
            "\t\tvar sfEls = document.getElementById(\"learning_nav\").getElementsByTagName(\"li\"); \n" +
            "\t\tfor (var i=0; i<sfEls.length; i++) { \n" +
            "\t\t\tsfEls[i].onmouseover=function() { \n" +
            "\t\t\tthis.className+=(this.className.length>0? \" \": \"\") + \"sfhover\"; \n" +
            "\t\t} \n" +
            "\t\tsfEls[i].onMouseDown=function() { \n" +
            "\t\t\tthis.className+=(this.className.length>0? \" \": \"\") + \"sfhover\"; \n" +
            "\t\t} \n" +
            "\t\tsfEls[i].onMouseUp=function() { \n" +
            "\t\t\tthis.className+=(this.className.length>0? \" \": \"\") + \"sfhover\"; \n" +
            "\t\t} \n" +
            "\t\tsfEls[i].onmouseout=function() { \n" +
            "\t\tthis.className=this.className.replace(new RegExp(\"( ?|^)sfhover\\\\b\"),\t\t\n" +
            "\t\t\"\"); \n" +
            "\t\t} \n" +
            "\t\t} \n" +
            "\t}\n" +
            "\t\n" +
            "\t//获取通知及其他数量\n" +
            "\tfunction getCourseNotice(){\n" +
            "\t\t$.ajax({\n" +
            "\t\t\t   type: \"POST\",\n" +
            "\t\t\t   url: \"https://wl.scutde.net:443/edu3/edu3/framework/learning/getmsgcount.html\",\t\n" +
            "\t\t\t   data: \"courseId=4aa66366326ce1eb013270e804010529\",\n" +
            "\t\t\t   dataType: \"json\",\t   \n" +
            "\t\t\t   global:false,\n" +
            "\t\t\t   success: function(data){\t \n" +
            "\t\t\t\t   $(\"#_courseNoticeNum\").html(\"(\"+data.couseNoticeNum+\")\");\n" +
            "\t\t\t   }\n" +
            "\t\t\t});\n" +
            "\t}\n" +
            "\tfunction getOnlieExam(){\n" +
            "\t\t$.ajax({\n" +
            "\t\t\t   type: \"POST\",\n" +
            "\t\t\t   url: \"https://wl.scutde.net:443/edu3/edu3/framework/learning/onlieexam/ajax.html\",\t\n" +
            "\t\t\t   data: \"courseId=4aa66366326ce1eb013270e804010529\",\n" +
            "\t\t\t   dataType: \"json\",\t    \n" +
            "\t\t\t   success: function(data){\t \n" +
            "\t\t\t\t   if(data.isOpen && data.isOpen=='Y'){\n" +
            "\t\t\t\t\t   var examName = \"在线考试\";\n" +
            "\t\t\t\t\t   if(data.isMachineExam=='Y'){\n" +
            "\t\t\t\t\t\t   examName = \"期末考试\";\n" +
            "\t\t\t\t\t   }\n" +
            "\t\t\t\t\t   if(data.isMachineExam!='Y'){//只保留网考\n" +
            "\t\t\t\t\t\t   $(\"#learning_nav\").append(\"<li><a href='javascript:void(0)' onclick=\\\"goOnlineExam('\"+data.examUrl+\"')\\\">\"+examName+\"</a></li>\");   \n" +
            "\t\t\t\t\t   }\t\t\t\t\t      \n" +
            "\t\t\t\t   }\t\t\t\t  \n" +
            "\t\t\t   }\n" +
            "\t\t});\n" +
            "\t}\n" +
            "\tfunction goOnlineExam(examUrl){\n" +
            "\t\t\n" +
            "\t\t\n" +
            "\t\n" +
            "\t\t\n" +
            "\t\t$.ajax({\n" +
            "\t\t\t   type: \"POST\",\n" +
            "\t\t\t   url: \"https://wl.scutde.net:443/edu3/edu3/framework/learning/onlieexam/findexamresult.html\",\t\n" +
            "\t\t\t   data: \"courseId=4aa66366326ce1eb013270e804010529\",\n" +
            "\t\t\t   dataType: \"json\",\t    \n" +
            "\t\t\t   success: function(data){\t \n" +
            "\t\t\t\t   if( data.status=='1'){\n" +
            "\t\t\t\t\t   alertMsg.info(data.msg);\t\t      \n" +
            "\t\t\t\t   }\t\n" +
            "\t\t\t\t   else\n" +
            "\t\t\t\t\t   {\n" +
            "\t\t\t\t\t   \n" +
            "\t\t\t\t\t   \n" +
            "\t\t\t\t\t   \n" +
            "\t\t\t\t\t   alertMsg.confirm(data.msg, {\n" +
            "\t\t\t\t\t\t\tokCall: function(){//执行\t\t\t\n" +
            "\t\t\t\t\t\t\t\t  var fromNet = 'pub';\n" +
            "\t\t\t\t\t\t\t\t\tvar url = \"http://\";\n" +
            "\t\t\t\t\t\t\t\t\turl += examUrl+\"/edu3-exam\";\n" +
            "\t\t\t\t\t\t\t\t\t//url+=\"localhost:8081/hnjk-webapp-exam/\";\n" +
            "\t\t\t\t\t\t\t\t\twindow.open(url+\"/exam/main.html?sid=ff808081697ac5fe016986f824413dd4&cid=4aa66366326ce1eb013270e804010529\",\"onlie_exam\",\"height=\"+screen.availHeight+\",width=\"+screen.availWidth+\",channelmode=yes,fullscreen=yes,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no\");\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t});\t\n" +
            "\t\t\t\t\t   \n" +
            "\t\t\t\t\t \n" +
            "\t\t\t\t\t\n" +
            "\t\t\t\t\t   \n" +
            "\t\t\t\t\t   }\n" +
            "\t\t\t   }\n" +
            "\t\t});\n" +
            "\t\t}\n" +
            "    </script>   \n" +
            " </body>\n" +
            "</html>";

    private static String ANSWER_HTML = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            " <head> \n" +
            "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\"> \n" +
            "  <script type=\"text/javascript\">\n" +
            "\tvar baseUrl = \"https://wl.scutde.net:443/edu3\";\t\n" +
            "\t\n" +
            "</script>\n" +
            "  <link type=\"text/css\" rel=\"stylesheet\" href=\"/edu3/style/default/mulitpage.css\">\n" +
            "  <script type=\"text/javascript\">\t\n" +
            "\tfunction _activeCourseExamValidateCallback(form){\n" +
            "\t\talertMsg.confirm(\"您确定要提交吗？\", {\n" +
            "\t\t\tokCall: function(){//执行\t\n" +
            "\t\t\t\t$(\"#ActiveExamSubmitButton\").attr(\"disabled\",\"disabled\");\n" +
            "\t\t\t\treturn validateCallback(form,getResult);\n" +
            "\t\t\t}\n" +
            "\t\t});\t\n" +
            "\t\treturn false;\n" +
            "\t}\n" +
            "\t\n" +
            "\tfunction showActiveExamAnswer(){\n" +
            "\t\t\n" +
            "\t\t$(\".correctAnswers\").toggle();\n" +
            "\t\t\n" +
            "\t}\n" +
            "\tfunction exchangeoption(resourceid){\n" +
            "\t\t$(\"#select_more_show_\"+resourceid).show();\n" +
            "\t\t$(\"#select_more_hide_\"+resourceid).hide();\n" +
            "\t}\n" +
            "\tfunction _activeCourseExamSubmit(obj,type){\n" +
            "\t\tvar msg = \"\";\n" +
            "\t\tvar page_now = \"\";\n" +
            "\t\tvar examNum = answerNum();\t\t//未做答题数\n" +
            "\t\tvar answerNuman = answerNumans();  //已做题数\n" +
            "\t//\talert(answerNuman);\n" +
            "\t\tif(answerNuman>0)\n" +
            "\t\t{if(type=='save'){\n" +
            "\t\t\tmsg += \"您确定要保存吗？\";\n" +
            "\t\t} else if(type=='submit_done'){\n" +
            "\t\t\tmsg += \"你将要提交所有已做的题目，提交后的题目不再允许修改，请确实是否要提交？\";\n" +
            "\t\t} else if(type=='submit'){\t\t\t\n" +
            "\t\t\ttype = 'submit_done';\n" +
            "\t\t\tmsg += \"你将要提交所有已做的题目，提交后本练习所有题目不再允许修改。\";\n" +
            "\t\t\t//var notDone = 0;\n" +
            "\t\t\tif(examNum>0){\n" +
            "\t\t\t\tmsg +=\"你还有\"+examNum+\"题尚未做或保存!\";\n" +
            "\t\t\t\t//return false;\n" +
            "\t\t\t}\n" +
            "\t\t\tmsg += \"请确认是否要提交？\";\t\t\t\n" +
            "\t\t} else {\n" +
            "\t\t\tpage_now = $('.sk_pagelist_now').text();\n" +
            "\t\t\tmsg += \"您确定现在就要提交本页的练习吗？\";\n" +
            "\t\t}\n" +
            "\t\talertMsg.confirm(msg, {\n" +
            "\t\t\tokCall: function(){//执行\t\n" +
            "\t\t\t\t$(obj).attr(\"disabled\",true);\n" +
            "\t\t\t\t$.ajax({\n" +
            "\t\t\t\t\ttype:'POST',\n" +
            "\t\t\t\t\turl:$('#StuActiveCourseExamForm').attr(\"action\")+\"?type=\"+type+\"&page_now=\"+page_now,\n" +
            "\t\t\t\t\tdata:$('#StuActiveCourseExamForm').serializeArray(),\n" +
            "\t\t\t\t\tdataType:\"json\",\n" +
            "\t\t\t\t\tcache: false,\n" +
            "\t\t\t\t\tsuccess: function (json){\n" +
            "\t\t\t\t\t\tDWZ.ajaxDone(json);\n" +
            "\t\t\t\t\t\tif (json.statusCode == 200){\t\t\t\t\t\n" +
            "\t\t\t\t\t\t\t//$(\"#interactive_tab3\").click();\n" +
            "\t\t\t\t\t\t\tlocalAreaPageBreak(\"interactive_tab3Content\",{pageNum:1});\n" +
            "\t\t\t\t\t\t} else {\n" +
            "\t\t\t\t\t\t\t$(obj).attr(\"disabled\",false);\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\n" +
            "\t\t\t\t\terror: DWZ.ajaxError\n" +
            "\t\t\t\t});\n" +
            "\t\t\t}\n" +
            "\t\t});\t\n" +
            "\t\t}\n" +
            "\t\telse\n" +
            "\t\t\t{\n" +
            "\t\t\talertMsg.error(\"您当前未选择答案，不允许保存（提交）!\");\n" +
            "\t\t\t}\n" +
            "\t}\n" +
            "\tfunction _activelocalAreaPageBreak(scopeType){\n" +
            "\t\tvar form = $(\"#interactive_tab3Content #pagerForm\").get(0);\n" +
            "\t\tif (form) {\n" +
            "\t\t\tform.scopeType.value = scopeType;\t\n" +
            "\t\t}\n" +
            "\t\tlocalAreaPageBreak(\"interactive_tab3Content\");\n" +
            "\t}\n" +
            "\tfunction _isAutoSaveChange(obj){\n" +
            "\t\tvar isAutoSave = \"N\";\n" +
            "\t\tif($(obj).attr(\"checked\")){\n" +
            "\t\t\tvar isAutoSave = \"Y\";\n" +
            "\t\t}\n" +
            "\t\tvar form = $(\"#interactive_tab3Content #pagerForm\").get(0);\n" +
            "\t\tif (form) {\n" +
            "\t\t\tform.isAutoSave.value = isAutoSave;\t\n" +
            "\t\t}\n" +
            "\t}\n" +
            "\tfunction _showStudentActiveCourseExam(syllabusId,title){\n" +
            "\t\tvar url = baseUrl+\"/edu3/learning/interactive/studentactivecourseexam/view.html?syllabusId=\"+syllabusId;\n" +
            "\t\t$.pdialog.open(url, \"viewStudentActiveCourseExam\"+syllabusId, title+\" 随堂练习答题情况\", {width:800,height:500});\n" +
            "\t}\n" +
            "\tfunction goPage(pageNo){\n" +
            "\t\tif($(\"#activeCourseExam_isAutoSave\").size()>0){\n" +
            "\t\t\tif($(\"#activeCourseExam_isAutoSave\").attr(\"checked\")){\n" +
            "\t\t\t\tif(answerChangeNum()>0){//答题变更\n" +
            "\t\t\t\t\t$.ajax({\n" +
            "\t\t\t\t\t\ttype:'POST',\n" +
            "\t\t\t\t\t\turl:$('#StuActiveCourseExamForm').attr(\"action\")+\"?type=save\",\n" +
            "\t\t\t\t\t\tdata:$('#StuActiveCourseExamForm').serializeArray(),\n" +
            "\t\t\t\t\t\tdataType:\"json\",\n" +
            "\t\t\t\t\t\tcache: false,\n" +
            "\t\t\t\t\t\tsuccess: function (json){\n" +
            "\t\t\t\t\t\t\tif (json.statusCode == 200){\t\t\t\t\t\n" +
            "\t\t\t\t\t\t\t\tlocalAreaPageBreak(\"interactive_tab3Content\",{pageNum:pageNo});\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t});\t\t\t\n" +
            "\t\t\t\t} else {\n" +
            "\t\t\t\t\tlocalAreaPageBreak(\"interactive_tab3Content\",{pageNum:pageNo});\n" +
            "\t\t\t\t}\t\t\n" +
            "\t\t\t} else {\n" +
            "\t\t\t\tvar examNum = answerChangeNum();\n" +
            "\t\t\t\tif(examNum>0){\n" +
            "\t\t\t\t\talertMsg.confirm(\"当前页有\"+examNum+\"题你尚未保存答案，请先保存答案再翻页。是否要继续翻页？\", {\n" +
            "\t\t\t\t\t\tokCall: function(){\t\n" +
            "\t\t\t\t\t\t\tlocalAreaPageBreak(\"interactive_tab3Content\",{pageNum:pageNo});\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t});\t\n" +
            "\t\t\t\t} else {\n" +
            "\t\t\t\t\tlocalAreaPageBreak(\"interactive_tab3Content\",{pageNum:pageNo});\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t} else {\n" +
            "\t\t\tlocalAreaPageBreak(\"interactive_tab3Content\",{pageNum:pageNo});\n" +
            "\t\t} \n" +
            "\t}\n" +
            "\t//答题而未保存的题目数\n" +
            "\tfunction answerChangeNum(){\n" +
            "\t\tvar num = 0;\n" +
            "\t\t$(\"input[id^='answer']\").each(function(){\n" +
            "\t\t\tif(!this.disabled)\n" +
            "\t\t\t{var answer = \"\";\n" +
            "\t\t\tif($(this).attr('rel')=='4'){\n" +
            "\t\t\t\tanswer = $(\"input[name='\"+this.id+\"']\").val();\n" +
            "\t\t\t} else {\n" +
            "\t\t\t\t$(\"input[name='\"+this.id+\"']\").each(function(){\n" +
            "\t\t\t\t\tif (this.checked) { \n" +
            "\t\t\t\t\t\tanswer += $(this).val();\n" +
            "\t\t\t\t\t\t} \n" +
            "\t\t\t\t\t\n" +
            "\t\t\t\t});\t\n" +
            "\t\t\t}\t\n" +
            "\t\t\tif(answer!=$(this).val()){\n" +
            "\t\t\t\tnum++;\n" +
            "\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t});\n" +
            "\t\treturn num;\n" +
            "\t}\n" +
            "\t//答题题目数\n" +
            "\tfunction answerNum(){\n" +
            "\t\tvar allnum = 0;\n" +
            "\t\tvar num = 0;\n" +
            "\t\t$(\"input[id^='answer']\").each(function(){\n" +
            "\t\t\tif(!this.disabled)\n" +
            "\t\t\t{\n" +
            "\t\t\tvar answer = \"\";\n" +
            "\t\t\tif($(this).attr('rel')=='4'){\n" +
            "\t\t\t\tanswer = $(\"input[name='\"+this.id+\"']\").val();\n" +
            "\t\t\t\tif(answer!=\"\")num++;\n" +
            "\t\t\t} else {\n" +
            "\t\t\t\t$(\"input[name='\"+this.id+\"']\").each(function(){\n" +
            "\t\t\t\t\tif (this.checked) { \n" +
            "\t\t\t\t\t\tanswer = $(this).val();\n" +
            "\t\t\t\t\t\tnum++;\n" +
            "\t\t\t\t\t\t} \n" +
            "\t\t\t\t});\t\n" +
            "\t\t\t}\t\n" +
            "\t\t\tallnum++;\n" +
            "\t\t\t}\n" +
            "\t\t});\n" +
            "\t\treturn allnum-num;\n" +
            "\t}\n" +
            "\t//已做题\n" +
            "\tfunction answerNumans(){\t\t\n" +
            "\t\tvar allnum = 0;\n" +
            "\t\tvar num = 0;\n" +
            "\t\t$(\"input[id^='answer']\").each(function(){\n" +
            "\t\t\tif(!this.disabled)\n" +
            "\t\t\t{\n" +
            "\t\t\tvar answer = \"\";\n" +
            "\t\t\tif($(this).attr('rel')=='4'){\n" +
            "\t\t\t\tanswer = $(\"input[name='\"+this.id+\"']\").val();\n" +
            "\t\t\t\tif(answer!=\"\")num++;\n" +
            "\t\t\t} else {\n" +
            "\t\t\t\t$(\"input[name='\"+this.id+\"']\").each(function(){\n" +
            "\t\t\t\t\tif (this.checked) { \n" +
            "\t\t\t\t\t\tanswer = $(this).val();\n" +
            "\t\t\t\t\t\tnum++;\n" +
            "\t\t\t\t\t\t} \n" +
            "\t\t\t\t});\t\n" +
            "\t\t\t}\t\n" +
            "\t\t\tallnum++;\n" +
            "\t\t\t}\n" +
            "\t\t});\n" +
            "\t\treturn num;\n" +
            "\t}\n" +
            "</script>\n" +
            "  <style>\n" +
            "<!--\n" +
            ".list {table-layout:fixed;word-wrap:break-word}\n" +
            ".list td div{line-height: 170%;font-size:10pt}\n" +
            ".list p{line-height: 170%;font-size:10pt;width:100%}\n" +
            "-->\n" +
            "</style>\n" +
            " </head>     \n" +
            " <body>\n" +
            "  <form id=\"StuActiveCourseExamForm\" method=\"post\" action=\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/studentactivecourseexam/save.html\" onsubmit=\"return false;\"> \n" +
            "   <input type=\"hidden\" value=\"4aa663663271a5ba013277d53bb103aa\" name=\"syllabusId\"> \n" +
            "   <h2 class=\"contentTitle\">1.华工人的自我修养</h2> \n" +
            "   <div style=\"line-height: 170%;text-align: center;font-weight: bolder;\">\n" +
            "    &nbsp;&nbsp;&nbsp;随堂练习提交截止时间：\n" +
            "    <span style=\"color:red;font-weight: bolder;\">2019-06-15 23:59:59</span>\n" +
            "   </div> \n" +
            "   <div style=\"color: blue;line-height: 170%;text-align: center;\"> \n" +
            "    <!-- \t<span>本次练习有题，你已做题，已提交题，其中答对题。</span> --> \n" +
            "    <br> \n" +
            "    <span>当前页有10题，你已做10题，已提交10题，其中答对9题。</span> \n" +
            "   </div> \n" +
            "   <table width=\"100%\" class=\"list\"> \n" +
            "    <tbody>\n" +
            "     <tr> \n" +
            "      <td> \n" +
            "       <div>\n" +
            "        <span style=\"font-weight: bold;\">1.</span>(单选题)&nbsp; 教育部批准华南理工大学实施开展现代远程教育试点工程的时间是（&nbsp; ） \n" +
            "        <br>&nbsp; A.&nbsp; 1998年&nbsp; B.&nbsp; 1999年&nbsp; C.&nbsp; 2000年&nbsp; D. 2001年&nbsp;\n" +
            "       </div> <input type=\"hidden\" value=\"ff80808167f882ad01680d356c016f03\" name=\"activeCourseExamId\" disabled> \n" +
            "       <div class=\"activeanswer\">\n" +
            "         答题： \n" +
            "        <input type=\"hidden\" value=\"C\" id=\"answerff80808167f882ad01680d356c016f03\" rel=\"1\" disabled> \n" +
            "        <input type=\"radio\" value=\"A\" name=\"answerff80808167f882ad01680d356c016f03\" disabled> A. \n" +
            "        <input type=\"radio\" value=\"B\" name=\"answerff80808167f882ad01680d356c016f03\" disabled> B. \n" +
            "        <input type=\"radio\" value=\"C\" name=\"answerff80808167f882ad01680d356c016f03\" checked disabled> C. \n" +
            "        <input type=\"radio\" value=\"D\" name=\"answerff80808167f882ad01680d356c016f03\" disabled> D. \n" +
            "        <span style=\"color: red;\">（已提交）</span> \n" +
            "       </div> \n" +
            "       <div class=\"correctAnswers\" style=\"display: none;\"> \n" +
            "        <div style=\"color: green;\">\n" +
            "         参考答案：C\n" +
            "        </div> \n" +
            "        <div style=\"color: green;\">\n" +
            "         问题解析：\n" +
            "        </div> \n" +
            "       </div> \n" +
            "       <div style=\"both:clear;height:12px\"></div> </td> \n" +
            "     </tr> \n" +
            "     <tr> \n" +
            "      <td> \n" +
            "       <div>\n" +
            "        <span style=\"font-weight: bold;\">2.</span>(单选题)&nbsp; 华南理工大学开办函授教育及夜大教育的时间是（&nbsp; ）\n" +
            "        <br>&nbsp; A.&nbsp; 1998年&nbsp; B. 1956年&nbsp; C. 2000年&nbsp; D. 1987年&nbsp;\n" +
            "       </div> <input type=\"hidden\" value=\"ff80808167f882ad01680d356c016f04\" name=\"activeCourseExamId\" disabled> \n" +
            "       <div class=\"activeanswer\">\n" +
            "         答题： \n" +
            "        <input type=\"hidden\" value=\"B\" id=\"answerff80808167f882ad01680d356c016f04\" rel=\"1\" disabled> \n" +
            "        <input type=\"radio\" value=\"A\" name=\"answerff80808167f882ad01680d356c016f04\" disabled> A. \n" +
            "        <input type=\"radio\" value=\"B\" name=\"answerff80808167f882ad01680d356c016f04\" checked disabled> B. \n" +
            "        <input type=\"radio\" value=\"C\" name=\"answerff80808167f882ad01680d356c016f04\" disabled> C. \n" +
            "        <input type=\"radio\" value=\"D\" name=\"answerff80808167f882ad01680d356c016f04\" disabled> D. \n" +
            "        <span style=\"color: red;\">（已提交）</span> \n" +
            "       </div> \n" +
            "       <div class=\"correctAnswers\" style=\"display: none;\"> \n" +
            "        <div style=\"color: green;\">\n" +
            "         参考答案：B\n" +
            "        </div> \n" +
            "        <div style=\"color: green;\">\n" +
            "         问题解析：\n" +
            "        </div> \n" +
            "       </div> \n" +
            "       <div style=\"both:clear;height:12px\"></div> </td> \n" +
            "     </tr> \n" +
            "     <tr> \n" +
            "      <td> \n" +
            "       <div>\n" +
            "        <span style=\"font-weight: bold;\">3.</span>(单选题)&nbsp; 华南理工大学校训（&nbsp; ）\n" +
            "        <br>&nbsp; A. 博学慎思 明辨笃行 \n" +
            "        <br>&nbsp; B. 厚德尚学 自强不息\n" +
            "        <br>&nbsp; C. 务实创新 追求卓越\n" +
            "        <br>&nbsp; D. 团结. 勤奋. 求实. 创新&nbsp;\n" +
            "       </div> <input type=\"hidden\" value=\"ff80808167f882ad01680d356c026f05\" name=\"activeCourseExamId\" disabled> \n" +
            "       <div class=\"activeanswer\">\n" +
            "         答题： \n" +
            "        <input type=\"hidden\" value=\"A\" id=\"answerff80808167f882ad01680d356c026f05\" rel=\"1\" disabled> \n" +
            "        <input type=\"radio\" value=\"A\" name=\"answerff80808167f882ad01680d356c026f05\" checked disabled> A. \n" +
            "        <input type=\"radio\" value=\"B\" name=\"answerff80808167f882ad01680d356c026f05\" disabled> B. \n" +
            "        <input type=\"radio\" value=\"C\" name=\"answerff80808167f882ad01680d356c026f05\" disabled> C. \n" +
            "        <input type=\"radio\" value=\"D\" name=\"answerff80808167f882ad01680d356c026f05\" disabled> D. \n" +
            "        <span style=\"color: red;\">（已提交）</span> \n" +
            "       </div> \n" +
            "       <div class=\"correctAnswers\" style=\"display: none;\"> \n" +
            "        <div style=\"color: green;\">\n" +
            "         参考答案：A\n" +
            "        </div> \n" +
            "        <div style=\"color: green;\">\n" +
            "         问题解析：\n" +
            "        </div> \n" +
            "       </div> \n" +
            "       <div style=\"both:clear;height:12px\"></div> </td> \n" +
            "     </tr> \n" +
            "     <tr> \n" +
            "      <td> \n" +
            "       <div>\n" +
            "        <span style=\"font-weight: bold;\">4.</span>(单选题)&nbsp; 现代远程教育（网络教育）的培养对象是各级各类（&nbsp; ）型专门人才。\n" +
            "        <br>&nbsp; A. 研究&nbsp; B. 开拓&nbsp; C. 专业&nbsp; D. 应用型&nbsp;\n" +
            "       </div> <input type=\"hidden\" value=\"ff80808167f882ad01680d356c026f06\" name=\"activeCourseExamId\" disabled> \n" +
            "       <div class=\"activeanswer\">\n" +
            "         答题： \n" +
            "        <input type=\"hidden\" value=\"D\" id=\"answerff80808167f882ad01680d356c026f06\" rel=\"1\" disabled> \n" +
            "        <input type=\"radio\" value=\"A\" name=\"answerff80808167f882ad01680d356c026f06\" disabled> A. \n" +
            "        <input type=\"radio\" value=\"B\" name=\"answerff80808167f882ad01680d356c026f06\" disabled> B. \n" +
            "        <input type=\"radio\" value=\"C\" name=\"answerff80808167f882ad01680d356c026f06\" disabled> C. \n" +
            "        <input type=\"radio\" value=\"D\" name=\"answerff80808167f882ad01680d356c026f06\" checked disabled> D. \n" +
            "        <span style=\"color: red;\">（已提交）</span> \n" +
            "       </div> \n" +
            "       <div class=\"correctAnswers\" style=\"display: none;\"> \n" +
            "        <div style=\"color: green;\">\n" +
            "         参考答案：D\n" +
            "        </div> \n" +
            "        <div style=\"color: green;\">\n" +
            "         问题解析：\n" +
            "        </div> \n" +
            "       </div> \n" +
            "       <div style=\"both:clear;height:12px\"></div> </td> \n" +
            "     </tr> \n" +
            "     <tr> \n" +
            "      <td> \n" +
            "       <div>\n" +
            "        <span style=\"font-weight: bold;\">5.</span>(单选题)&nbsp; 《网上学习指南》是网络教育学生的（&nbsp; ） \n" +
            "        <br>&nbsp; A.必修课&nbsp; B.&nbsp; 专业选修课&nbsp; C. 通识课&nbsp; D.专业限选课&nbsp;\n" +
            "       </div> <input type=\"hidden\" value=\"ff80808167f882ad01680d356c026f07\" name=\"activeCourseExamId\" disabled> \n" +
            "       <div class=\"activeanswer\">\n" +
            "         答题： \n" +
            "        <input type=\"hidden\" value=\"A\" id=\"answerff80808167f882ad01680d356c026f07\" rel=\"1\" disabled> \n" +
            "        <input type=\"radio\" value=\"A\" name=\"answerff80808167f882ad01680d356c026f07\" checked disabled> A. \n" +
            "        <input type=\"radio\" value=\"B\" name=\"answerff80808167f882ad01680d356c026f07\" disabled> B. \n" +
            "        <input type=\"radio\" value=\"C\" name=\"answerff80808167f882ad01680d356c026f07\" disabled> C. \n" +
            "        <input type=\"radio\" value=\"D\" name=\"answerff80808167f882ad01680d356c026f07\" disabled> D. \n" +
            "        <span style=\"color: red;\">（已提交）</span> \n" +
            "       </div> \n" +
            "       <div class=\"correctAnswers\" style=\"display: none;\"> \n" +
            "        <div style=\"color: green;\">\n" +
            "         参考答案：A\n" +
            "        </div> \n" +
            "        <div style=\"color: green;\">\n" +
            "         问题解析：\n" +
            "        </div> \n" +
            "       </div> \n" +
            "       <div style=\"both:clear;height:12px\"></div> </td> \n" +
            "     </tr> \n" +
            "     <tr> \n" +
            "      <td> \n" +
            "       <div>\n" +
            "        <span style=\"font-weight: bold;\">6.</span>(单选题)&nbsp; 我校网络教育人才培养方案坚持牢固树立（&nbsp; ）是教学活动主体的思想。\n" +
            "        <br>&nbsp; A.教师&nbsp; B.学生&nbsp; C.学校&nbsp; D.网络&nbsp;\n" +
            "       </div> <input type=\"hidden\" value=\"ff80808167f882ad01680d356c026f08\" name=\"activeCourseExamId\" disabled> \n" +
            "       <div class=\"activeanswer\">\n" +
            "         答题： \n" +
            "        <input type=\"hidden\" value=\"D\" id=\"answerff80808167f882ad01680d356c026f08\" rel=\"1\" disabled> \n" +
            "        <input type=\"radio\" value=\"A\" name=\"answerff80808167f882ad01680d356c026f08\" disabled> A. \n" +
            "        <input type=\"radio\" value=\"B\" name=\"answerff80808167f882ad01680d356c026f08\" disabled> B. \n" +
            "        <input type=\"radio\" value=\"C\" name=\"answerff80808167f882ad01680d356c026f08\" disabled> C. \n" +
            "        <input type=\"radio\" value=\"D\" name=\"answerff80808167f882ad01680d356c026f08\" checked disabled> D. \n" +
            "        <span style=\"color: red;\">（已提交）</span> \n" +
            "       </div> \n" +
            "       <div class=\"correctAnswers\" style=\"display: none;\"> \n" +
            "        <div style=\"color: green;\">\n" +
            "         参考答案：B\n" +
            "        </div> \n" +
            "        <div style=\"color: green;\">\n" +
            "         问题解析：\n" +
            "        </div> \n" +
            "       </div> \n" +
            "       <div style=\"both:clear;height:12px\"></div> </td> \n" +
            "     </tr> \n" +
            "     <tr> \n" +
            "      <td> \n" +
            "       <div>\n" +
            "        <span style=\"font-weight: bold;\">7.</span>(单选题)&nbsp; 远程教育学习的本质特征（&nbsp; ）\n" +
            "        <br>&nbsp; A. 自主性学习&nbsp; B. 自学.&nbsp; C. 面授学习&nbsp; D. 全日制学习&nbsp;\n" +
            "       </div> <input type=\"hidden\" value=\"ff80808167f882ad01680d356c026f09\" name=\"activeCourseExamId\" disabled> \n" +
            "       <div class=\"activeanswer\">\n" +
            "         答题： \n" +
            "        <input type=\"hidden\" value=\"A\" id=\"answerff80808167f882ad01680d356c026f09\" rel=\"1\" disabled> \n" +
            "        <input type=\"radio\" value=\"A\" name=\"answerff80808167f882ad01680d356c026f09\" checked disabled> A. \n" +
            "        <input type=\"radio\" value=\"B\" name=\"answerff80808167f882ad01680d356c026f09\" disabled> B. \n" +
            "        <input type=\"radio\" value=\"C\" name=\"answerff80808167f882ad01680d356c026f09\" disabled> C. \n" +
            "        <input type=\"radio\" value=\"D\" name=\"answerff80808167f882ad01680d356c026f09\" disabled> D. \n" +
            "        <span style=\"color: red;\">（已提交）</span> \n" +
            "       </div> \n" +
            "       <div class=\"correctAnswers\" style=\"display: none;\"> \n" +
            "        <div style=\"color: green;\">\n" +
            "         参考答案：A\n" +
            "        </div> \n" +
            "        <div style=\"color: green;\">\n" +
            "         问题解析：\n" +
            "        </div> \n" +
            "       </div> \n" +
            "       <div style=\"both:clear;height:12px\"></div> </td> \n" +
            "     </tr> \n" +
            "     <tr> \n" +
            "      <td> \n" +
            "       <div>\n" +
            "        <span style=\"font-weight: bold;\">8.</span>(单选题)&nbsp; 下述关于网络教育主要特色和优势的说法全面的是（&nbsp; ）\n" +
            "        <br>&nbsp; A. 不受时空限制&nbsp; B. 交互性强&nbsp; C. 以学习者为中心. 学习资源丰富&nbsp; D. 以上都是&nbsp;\n" +
            "       </div> <input type=\"hidden\" value=\"ff80808167f882ad01680d356c026f0a\" name=\"activeCourseExamId\" disabled> \n" +
            "       <div class=\"activeanswer\">\n" +
            "         答题： \n" +
            "        <input type=\"hidden\" value=\"D\" id=\"answerff80808167f882ad01680d356c026f0a\" rel=\"1\" disabled> \n" +
            "        <input type=\"radio\" value=\"A\" name=\"answerff80808167f882ad01680d356c026f0a\" disabled> A. \n" +
            "        <input type=\"radio\" value=\"B\" name=\"answerff80808167f882ad01680d356c026f0a\" disabled> B. \n" +
            "        <input type=\"radio\" value=\"C\" name=\"answerff80808167f882ad01680d356c026f0a\" disabled> C. \n" +
            "        <input type=\"radio\" value=\"D\" name=\"answerff80808167f882ad01680d356c026f0a\" checked disabled> D. \n" +
            "        <span style=\"color: red;\">（已提交）</span> \n" +
            "       </div> \n" +
            "       <div class=\"correctAnswers\" style=\"display: none;\"> \n" +
            "        <div style=\"color: green;\">\n" +
            "         参考答案：D\n" +
            "        </div> \n" +
            "        <div style=\"color: green;\">\n" +
            "         问题解析：\n" +
            "        </div> \n" +
            "       </div> \n" +
            "       <div style=\"both:clear;height:12px\"></div> </td> \n" +
            "     </tr> \n" +
            "     <tr> \n" +
            "      <td> \n" +
            "       <div>\n" +
            "        <span style=\"font-weight: bold;\">9.</span>(单选题)&nbsp; 我校网络教育实行的是计划指导的学分制。学生修满我校规定的相应专业学分，符合毕业条件，达到毕业标准，可以获得毕业文凭。其中高中起点专科. 专科起点本科理工类一般为（&nbsp; ）学分，文史经管类一般为（&nbsp; ）学分\n" +
            "        <br>&nbsp; A. 90，80&nbsp; B. 85，80&nbsp; C. 80，80&nbsp; D. 85，85&nbsp;\n" +
            "       </div> <input type=\"hidden\" value=\"ff80808167f882ad01680d356c026f0b\" name=\"activeCourseExamId\" disabled> \n" +
            "       <div class=\"activeanswer\">\n" +
            "         答题： \n" +
            "        <input type=\"hidden\" value=\"B\" id=\"answerff80808167f882ad01680d356c026f0b\" rel=\"1\" disabled> \n" +
            "        <input type=\"radio\" value=\"A\" name=\"answerff80808167f882ad01680d356c026f0b\" disabled> A. \n" +
            "        <input type=\"radio\" value=\"B\" name=\"answerff80808167f882ad01680d356c026f0b\" checked disabled> B. \n" +
            "        <input type=\"radio\" value=\"C\" name=\"answerff80808167f882ad01680d356c026f0b\" disabled> C. \n" +
            "        <input type=\"radio\" value=\"D\" name=\"answerff80808167f882ad01680d356c026f0b\" disabled> D. \n" +
            "        <span style=\"color: red;\">（已提交）</span> \n" +
            "       </div> \n" +
            "       <div class=\"correctAnswers\" style=\"display: none;\"> \n" +
            "        <div style=\"color: green;\">\n" +
            "         参考答案：B\n" +
            "        </div> \n" +
            "        <div style=\"color: green;\">\n" +
            "         问题解析：\n" +
            "        </div> \n" +
            "       </div> \n" +
            "       <div style=\"both:clear;height:12px\"></div> </td> \n" +
            "     </tr> \n" +
            "     <tr> \n" +
            "      <td> \n" +
            "       <div>\n" +
            "        <span style=\"font-weight: bold;\">10.</span>(单选题)&nbsp; 远程开放高等教育起源于19世纪中叶的（&nbsp; ）\n" +
            "        <br>&nbsp; A. 美国&nbsp; B. 法国&nbsp; C. 英国&nbsp; D. 德国&nbsp;\n" +
            "       </div> <input type=\"hidden\" value=\"ff80808167f882ad01680d356c026f0c\" name=\"activeCourseExamId\" disabled> \n" +
            "       <div class=\"activeanswer\">\n" +
            "         答题： \n" +
            "        <input type=\"hidden\" value=\"C\" id=\"answerff80808167f882ad01680d356c026f0c\" rel=\"1\" disabled> \n" +
            "        <input type=\"radio\" value=\"A\" name=\"answerff80808167f882ad01680d356c026f0c\" disabled> A. \n" +
            "        <input type=\"radio\" value=\"B\" name=\"answerff80808167f882ad01680d356c026f0c\" disabled> B. \n" +
            "        <input type=\"radio\" value=\"C\" name=\"answerff80808167f882ad01680d356c026f0c\" checked disabled> C. \n" +
            "        <input type=\"radio\" value=\"D\" name=\"answerff80808167f882ad01680d356c026f0c\" disabled> D. \n" +
            "        <span style=\"color: red;\">（已提交）</span> \n" +
            "       </div> \n" +
            "       <div class=\"correctAnswers\" style=\"display: none;\"> \n" +
            "        <div style=\"color: green;\">\n" +
            "         参考答案：C\n" +
            "        </div> \n" +
            "        <div style=\"color: green;\">\n" +
            "         问题解析：\n" +
            "        </div> \n" +
            "       </div> \n" +
            "       <div style=\"both:clear;height:12px\"></div> </td> \n" +
            "     </tr> \n" +
            "    </tbody>\n" +
            "   </table> \n" +
            "   <!--c:if test=\"false\" --> \n" +
            "   <table width=\"100%\" style=\"background-color: #EBF0F5;\"> \n" +
            "    <tbody>\n" +
            "     <tr>\n" +
            "      <td colspan=\"2\" style=\"color: green;\">&nbsp;</td>\n" +
            "     </tr> \n" +
            "     <tr> \n" +
            "      <td width=\"30%\"> \n" +
            "       <div style=\"margin-left: 5px;\">\n" +
            "        请选择查看范围： \n" +
            "        <select name=\"scopeType\" onchange=\"_activelocalAreaPageBreak(this.value)\"> <option value=\"all\">所有题目</option> <option value=\"unsave\">未做题目</option> <option value=\"unfinished\">未提交题目</option> </select> \n" +
            "        <br> \n" +
            "        <input type=\"checkbox\" id=\"activeCourseExam_isAutoSave\" onchange=\"_isAutoSaveChange(this)\" name=\"isAutoSave\" value=\"Y\">翻页时自动保存答案 \n" +
            "       </div> </td> \n" +
            "      <td> \n" +
            "       <div style=\"float: right;margin-left: 10px;margin-right: 50px;\"> \n" +
            "        <a class=\"button\" href=\"javascript:;\" onclick=\"showActiveExamAnswer()\"><span>参考答案</span></a> \n" +
            "        <a class=\"button\" href=\"javascript:void(0);\" onclick=\"_showStudentActiveCourseExam('4aa663663271a5ba013277d53bb103aa','1.华工人的自我修养')\"><span>查看答题详情</span></a> \n" +
            "       </div> </td> \n" +
            "     </tr> \n" +
            "     <tr>\n" +
            "      <td colspan=\"2\">&nbsp;</td>\n" +
            "     </tr> \n" +
            "    </tbody>\n" +
            "   </table> \n" +
            "  </form> \n" +
            "  <table width=\"100%\" border=\"0\"> \n" +
            "   <tbody>\n" +
            "    <tr> \n" +
            "     <td> \n" +
            "      <div id=\"pageBoxDivId\"> \n" +
            "       <div class=\"sk_pagelist_text\">\n" +
            "        共找到 10 条记录，分为 1 页\n" +
            "       </div> \n" +
            "      </div> </td> \n" +
            "    </tr> \n" +
            "   </tbody>\n" +
            "  </table> \n" +
            "  <input type=\"hidden\" name=\"pageNum\" value=\"1\"> \n" +
            "  <form id=\"pagerForm\" method=\"post\" action=\"https://wl.scutde.net:443/edu3/edu3/learning/interactive/activecourseexam/list.html\"> \n" +
            "   <input type=\"hidden\" name=\"pageNum\" value=\"1\"> \n" +
            "   <input type=\"hidden\" name=\"pageSize\" value=\"10\"> \n" +
            "   <!--动态创建查询条件--> \n" +
            "   <input type=\"hidden\" name=\"scopeType\" value=\"all\"> \n" +
            "   <input type=\"hidden\" name=\"term\" value=\"2\"> \n" +
            "   <input type=\"hidden\" name=\"isPublished\" value=\"Y\"> \n" +
            "   <input type=\"hidden\" name=\"yearid\" value=\"ff80808161dbc2fc0161e04cdd2c0db6\"> \n" +
            "   <input type=\"hidden\" name=\"isAutoSave\" value=\"N\"> \n" +
            "   <input type=\"hidden\" name=\"studentInfoId\" value=\"ff808081697ac5fe016986f824413dd4\"> \n" +
            "   <input type=\"hidden\" name=\"syllabusId\" value=\"4aa663663271a5ba013277d53bb103aa\"> \n" +
            "  </form> \n" +
            " </body>\n" +
            "</html>";

    String JSON_OBJECT = "{\n" +
            "        \"name\":\"离散数学\",\n" +
            "        \"id\":\"402881382ea3cffc012ea411e76f0242,\",\n" +
            "        \"open\":true,\n" +
            "        \"level\":0,\n" +
            "        \"nodes\":[\n" +
            "            {\n" +
            "                \"name\":\"第一章 命题逻辑\",\n" +
            "                \"id\":\"402881382f520988012f570fafdd0140,1\",\n" +
            "                \"open\":true,\n" +
            "                \"level\":1,\n" +
            "                \"nodes\":[\n" +
            "                    {\n" +
            "                        \"name\":\"第一节 命题与联结词\",\n" +
            "                        \"id\":\"402881382f520988012f571282680148,2\",\n" +
            "                        \"open\":true,\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f570fafdd0140\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"mate\":\"Y\",\n" +
            "                            \"exam\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第二节 命题公式\",\n" +
            "                        \"id\":\"402881382f520988012f5712a86b0149,2\",\n" +
            "                        \"open\":true,\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f570fafdd0140\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"exam\":\"Y\",\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第三节 命题公式的范式\",\n" +
            "                        \"id\":\"402881382f520988012f5712c8b1014a,2\",\n" +
            "                        \"open\":true,\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f570fafdd0140\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"exam\":\"Y\",\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第四节 联结词的功能完全集\",\n" +
            "                        \"id\":\"402881382f520988012f57134b8f014b,2\",\n" +
            "                        \"open\":true,\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f570fafdd0140\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"exam\":\"Y\",\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第五节 推理规则和证明方法\",\n" +
            "                        \"id\":\"402881382f520988012f57136aa4014c,2\",\n" +
            "                        \"open\":true,\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f570fafdd0140\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"mate\":\"Y\",\n" +
            "                            \"exam\":\"Y\"\n" +
            "                        }\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"parentId\":\"402881382ea3cffc012ea411e76f0242\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\":\"第二章 谓词逻辑\",\n" +
            "                \"id\":\"402881382f520988012f570fe4190141,1\",\n" +
            "                \"level\":1,\n" +
            "                \"nodes\":[\n" +
            "                    {\n" +
            "                        \"name\":\"第一节 谓词逻辑的基本概念\",\n" +
            "                        \"id\":\"402881382f520988012f5715ea250152,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f570fe4190141\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"exam\":\"Y\",\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第二节 谓词逻辑公式\",\n" +
            "                        \"id\":\"402881382f520988012f5716373e0153,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f570fe4190141\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"mate\":\"Y\",\n" +
            "                            \"exam\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第三节 谓词演算的推理规则\",\n" +
            "                        \"id\":\"402881382f520988012f5716788a0154,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f570fe4190141\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"mate\":\"Y\",\n" +
            "                            \"exam\":\"Y\"\n" +
            "                        }\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"parentId\":\"402881382ea3cffc012ea411e76f0242\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\":\"第三章 集合\",\n" +
            "                \"id\":\"402881382f520988012f57102a660142,1\",\n" +
            "                \"level\":1,\n" +
            "                \"nodes\":[\n" +
            "                    {\n" +
            "                        \"name\":\"第一节 集合的基本概念\",\n" +
            "                        \"id\":\"402881382f520988012f57198743015a,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f57102a660142\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"exam\":\"Y\",\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第二节 集合的运算\",\n" +
            "                        \"id\":\"402881382f520988012f5719a46a015b,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f57102a660142\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"exam\":\"Y\",\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第三节 归纳法与自然数\",\n" +
            "                        \"id\":\"402881382f520988012f5719d90d015c,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f57102a660142\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第四节 笛卡尔积\",\n" +
            "                        \"id\":\"402881382f520988012f571a52d9015d,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f57102a660142\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"exam\":\"Y\",\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第五节 可数与不可数集合\",\n" +
            "                        \"id\":\"402881382f520988012f571a8148015e,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f57102a660142\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第六节 集合基数的比较\",\n" +
            "                        \"id\":\"402881382f520988012f571a9cb2015f,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f57102a660142\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"parentId\":\"402881382ea3cffc012ea411e76f0242\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\":\"第四章 二元关系与函数\",\n" +
            "                \"id\":\"402881382f520988012f5710597e0143,1\",\n" +
            "                \"level\":1,\n" +
            "                \"nodes\":[\n" +
            "                    {\n" +
            "                        \"name\":\"第一节 二元关系的基本概念\",\n" +
            "                        \"id\":\"402881382f520988012f571e5fe30167,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f5710597e0143\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"exam\":\"Y\",\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第二节 关系的合成\",\n" +
            "                        \"id\":\"402881382f520988012f571eb9bb0168,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f5710597e0143\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"exam\":\"Y\",\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第三节 闭包\",\n" +
            "                        \"id\":\"402881382f520988012f571f31230169,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f5710597e0143\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第四节 偏序关系\",\n" +
            "                        \"id\":\"402881382f520988012f5720e35e016a,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f5710597e0143\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"mate\":\"Y\",\n" +
            "                            \"exam\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第五节 等价关系和划分\",\n" +
            "                        \"id\":\"ff8080816145c81801614aeb5c7000d8,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f5710597e0143\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"mate\":\"Y\",\n" +
            "                            \"exam\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第六节 函数的基本概念\",\n" +
            "                        \"id\":\"ff8080816145c81801614aeb7ba800d9,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f5710597e0143\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"mate\":\"Y\",\n" +
            "                            \"exam\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第七节 特殊 函数类\",\n" +
            "                        \"id\":\"ff8080816145c81801614aeeba3100da,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f5710597e0143\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第八节 逆函数\",\n" +
            "                        \"id\":\"ff8080816145c81801614aeed7c600db,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f5710597e0143\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"parentId\":\"402881382ea3cffc012ea411e76f0242\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\":\"第五章 图论简介\",\n" +
            "                \"id\":\"402881382f520988012f57108fc30144,1\",\n" +
            "                \"level\":1,\n" +
            "                \"nodes\":[\n" +
            "                    {\n" +
            "                        \"name\":\"第一节 有向图及无向图\",\n" +
            "                        \"id\":\"402881382f520988012f5722d3f6016c,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f57108fc30144\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"exam\":\"Y\",\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第二节 路径与回路\",\n" +
            "                        \"id\":\"402881382f520988012f5723d925016d,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f57108fc30144\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"mate\":\"Y\",\n" +
            "                            \"exam\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第三节 图的矩阵表示\",\n" +
            "                        \"id\":\"402881382f520988012f57240dc6016e,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f57108fc30144\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"exam\":\"Y\",\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"parentId\":\"402881382ea3cffc012ea411e76f0242\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\":\"第六章 特殊的图类\",\n" +
            "                \"id\":\"402881382f520988012f571126ca0145,1\",\n" +
            "                \"level\":1,\n" +
            "                \"nodes\":[\n" +
            "                    {\n" +
            "                        \"name\":\"第一节 二部图\",\n" +
            "                        \"id\":\"402881382f520988012f572864c10176,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f571126ca0145\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"mate\":\"Y\",\n" +
            "                            \"exam\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第二节 路径与回路\",\n" +
            "                        \"id\":\"402881382f520988012f5728848e0177,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f571126ca0145\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"exam\":\"Y\",\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\":\"第三节 树与有向树\",\n" +
            "                        \"id\":\"402881382f520988012f57289caa0178,2\",\n" +
            "                        \"level\":2,\n" +
            "                        \"parentId\":\"402881382f520988012f571126ca0145\",\n" +
            "                        \"exAttribute\":{\n" +
            "                            \"exam\":\"Y\",\n" +
            "                            \"mate\":\"Y\"\n" +
            "                        }\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"parentId\":\"402881382ea3cffc012ea411e76f0242\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\":\"课程考点\",\n" +
            "                \"id\":\"4aa6636742db04d30142eac40c5a5496,1\",\n" +
            "                \"level\":1,\n" +
            "                \"parentId\":\"402881382ea3cffc012ea411e76f0242\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\":\"随堂练习\",\n" +
            "                \"id\":\"4aa6636756e00aa70156e8dde9c86ec9,1\",\n" +
            "                \"level\":1,\n" +
            "                \"parentId\":\"402881382ea3cffc012ea411e76f0242\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }";
}

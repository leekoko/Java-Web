package com.leekoko;

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
        HGPageProcesser processer = new HGPageProcesser();
        processer.startCrawl();
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
        Chapter chapter = JSON.parseObject(json, Chapter.class);
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
}

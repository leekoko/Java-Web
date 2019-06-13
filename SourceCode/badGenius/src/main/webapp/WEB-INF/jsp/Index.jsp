<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" isELIgnored="false" pageEncoding="UTF-8"%>
<html>
<head>
    <title>登陆页面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- 包括所有已编译的插件 -->
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <h1>华工随堂助手</h1>
        <ul id="myTab" class="nav nav-tabs">
            <li class="active">
                <a href="#home" data-toggle="tab">
                    首页
                </a>
            </li>
        </ul>



        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="home">
                <p>华工随堂助手会扫描你的题库，匹配列出答案。由于扫描复杂性，耗时约3分钟，请勿刷新页面。菜单出来即加载成功！</p>
            </div>
            <div class="tab-pane fade" id="problemPage"><iframe frameBorder=0 src="" width="100%" height="100%" scrolling="none" id="problemIframe"></iframe></div>

        </div>



    </div>
</div>
</body>
<script>
var storage=window.localStorage;

$(function () {
    alert("题目答案获取时间约3分钟，请耐心等候，请勿重复刷新页面。点击确定开始扫描！");
    var data = "${data}";
    var account = "${account}";
    runTitle(data, account);



    //测试数据==============START
    // var a = {id:"4aa663673df35664013e0b805ee32574,",parentCode:"0",idCode:"440582199408010099",name:"人文素质修养",open:false,level:"0",nodes:null};
    // var b = {id:"402881382cc0a6d1012cc9d3d10f01e6,",parentCode:"0",idCode:"440582199408010099",name:"政治理论课",open:false,level:"0",nodes:null};
    // var c = {id:"4aa6636744dedef00144f33ab621095b,1",parentCode:"402881382f09d6ef012f13ce4aca031e,",idCode:"440582199408010099",name:"学位英语考前辅导",open:false,level:"1",nodes:null};
    // var d = {id:"402881382f09d6ef012f13ce4aca031e,",parentCode:"0",idCode:"440582199408010099",name:"大学英语B统考（三）",open:false,level:"0",nodes:null};
    // var e = {id:"402881382f09d6ef012f13ce4aca031e,",parentCode:"0",idCode:"440582199408010099",name:"大学英语B统考（三）",open:false,level:"0",nodes:null};
    // var chapterList = [];
    // chapterList.push(a);
    // chapterList.push(b);
    // chapterList.push(c);
    // chapterList.push(d);
    // showOneLevel(chapterList);
    //测试数据==============END
});

function runTitle(data, account) {
    $.ajax({
        url: '/problem/saveProblem',
        data: {cookie: data, userName: account},
        async: false,
        type: "POST",
        dataType: "json",
        success: function (result) {
            var chapterList = result.chapterList;
            var chapterProblemList = result.chapterProblemList;
            //存储到local Storage
            storage.setItem("chapterList",JSON.stringify(chapterList));
            storage.setItem("chapterProblemList",JSON.stringify(chapterProblemList));
            if(data !== ''){
                //显示页面数据
                showChapter(chapterList, chapterProblemList);
            }
        }
    });
}

/**
 * 显示页面数据
 */
function showChapter(chapterList) {
    showOneLevel(chapterList);

}

/**
 * 显示第一级内容
 */
function showOneLevel(chapterList) {
    var oneLevelArr = [];
    for(var i = 0 ; i < chapterList.length; i++){
        var curChapter = chapterList[i];
        if(curChapter.level === '0'){
            oneLevelArr.push(curChapter)
            var curId = curChapter.id.split(",");
            var tabHtml = "<li class=\"dropdown\"><a href=\"#\" id=\""+ curId[0] +"Drop\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+ curChapter.name +"<b class=\"caret\"></b></a><ul class=\"dropdown-menu\" role=\"menu\" aria-labelledby=\"myTabDrop1\" id=\""+ curId[0] +"\"></ul></li>";
            $("#myTab").append(tabHtml)
        }
    }
    //显示第二级内容
    showTwoLevel(chapterList, oneLevelArr);
}

function showTwoLevel(chapterList, oneLevelArr) {
    for(var i = 0 ; i < chapterList.length; i++){
        var curChapter2 = chapterList[i];
        if(curChapter2.level === '1' || curChapter2.level === '2'){
            var curId = curChapter2.id.split(",");
            var tabHtml2 = "<li><a href=\"#problemPage\" tabindex=\"-1\" data-toggle=\"tab\" onclick=\"goProblemPage('"+ curId[0] +"')\">"+ curChapter2.name +"</a></li>";
            var parentId = curChapter2.parentCode.split(",");
            console.log(parentId[0]);
            $("#"+parentId[0]).append(tabHtml2);
        }
    }
}

function goProblemPage(curId) {
    var problemUrl = "/page/problemPage?id="+curId;
    $("#problemIframe").attr("src",problemUrl);
}
</script>
</html>


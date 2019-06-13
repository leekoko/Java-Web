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
        <div id="problemContent">

        </div>

    </div>
</div>
</body>
<script>
var storage = window.localStorage;
var chapterId = "${chapterId}";

$(function () {
    //测试数据==============START
    // var a = {chapterId:"4aa6636744dedef00144f33ab621095b1,",problemId:"ff80808167f882ad01680d356c016f03"};
    // var b = {chapterId:"ff80808167f882ad01680d356c016f04,",problemId:"ff80808167f882ad01680d356c016f04"};
    // var temp = [];
    // temp.push(a);
    // temp.push(b);
    // storage.setItem("chapterProblemList",temp);
    //测试数据==============END

    var chapterProblemList = JSON.parse(localStorage.getItem("chapterProblemList"));
    initProblem(chapterProblemList);
});

function initProblem(chapterProblemList) {
    var isNull = true;
    for(var i = 0; i < chapterProblemList.length; i++){
        var chapterProblem = chapterProblemList[i];
        var tempChapterIdArr = chapterProblem.chapterId.split(",");
        if(tempChapterIdArr[0] === chapterId){
            var problemId = chapterProblem.problemId;
            $.ajax({
                url: '/problem/getDetailById',
                data: {problemId: problemId},
                async: false,
                type: "POST",
                dataType: "json",
                success: function (result) {
                    var problem = result.problem;
                    appendProblem(problem);
                    isNull = false;
                }
            });
        }
    }
    if(isNull){
        $("#problemContent").append("<h1>对不起，当前题目库中未收录此题答案，您明天来看看可能就有了。</h1>")
    }
}

/**
 * 显示答案内容
 */
function appendProblem(problem) {
    var insertContent = "<div class='problem'>" + problem.title + "<h3 style='color: red'>" + problem.answer + "</h3></div>";
    $("#problemContent").append(insertContent)
}

</script>
</html>


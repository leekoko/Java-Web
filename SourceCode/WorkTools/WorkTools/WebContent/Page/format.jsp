<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代码格式化</title>
<!-- 引入 Bootstrap -->
<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="https://code.jquery.com/jquery.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<ul id="myTab" class="nav nav-tabs">
	<li class="active">
		<a href="#htmlFormat" data-toggle="tab">
			 Html格式化
		</a>
	</li>
	<li>
		<a href="#xmlFormat" data-toggle="tab">
			xml格式化
		</a>
	</li>
</ul>
<style>
#htmldata,#fhtml{
	width:800px;
	height:300px;
}

</style>
<div id="myTabContent" class="tab-content">
	<!-- html格式化 -->
	<div class="tab-pane fade in active" id="htmlFormat">
		<form id="codeformat_form" action="../htmlFormat" method="POST">
<!-- 			<script type="text/javascript" src="/js/ZeroClipboard.min.js"></script>
			<script type="text/javascript" src="/js/copy_format.js"></script> -->
			<div class="topBar">
    			<div class="title">待格式化HTML：</div>
				<textarea name="html" id="htmldata" style="resize:none;" class="resizable"></textarea>
			</div>
			<div class="operateTB form-inline">
				<input class="btn btn-small btn-primary" data-loading-text="正在格式化HTML..." id="format"  type="submit" value="格式化"/>
				<input class="btn btn-small btn-success" id="copy_format" data-clipboard-target="fhtml" type="button" value="复制格式化代码"/>
			</div>
			<div class="bottomBar">
			    <div class="title">格式化HTML：</div>
				<textarea id="fhtml" name="fhtml" style="resize:none;" class="resizable"></textarea>
			</div>        
		</form>
		
	</div>
	<div class="tab-pane fade" id="xmlFormat">
		<p>BBBBB</p>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		$("#codeformat_form").ajaxForm({
			beforeSubmit:function(){
				$("#fhtml").val("");
				var data = $("#htmldata")
				if(data.val().length==0){
					alert("待格式化HTML为空");
					data.focus();
					return false;
				}
				$("#format").button('loading')
			},
			dataType:"json",
			success:function(json){
				$("#format").button('reset')
				if(json.msg){
					alert(json.msg);
					return;
				}
				$("#fhtml").val(json.fhtml);
			}
		});
		
	});
</script>



</body>
</html>
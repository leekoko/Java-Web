<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" />  
<!-- 引入 Bootstrap -->
<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="https://code.jquery.com/jquery.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>

</style>

<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<title>翻译</title>
</head>
<body>
	<div class="container">
			<div class="row clearfix">
			<div class="col-md-12 column">
				<ul class="nav nav-tabs">
					<li>
						 <a href="../index.jsp">首页</a>
					</li>
					<li class="active">
						<a href="Page/trans.jsp">翻译/驼峰式/转大写</a>
					</li>
					<li>
						<a href="Page/toBig.jsp">小写转大写</a>
					</li>
					<li>
						<a href="Page/format.jsp">代码格式化</a>		
					</li>
				</ul>
			</div>
		</div>
	
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h3>翻译</h3>
				<form role="form" style="width:500px;">
					<div class="form-group">
						<h6>1.翻译   2.驼峰式英文（属性） 3.全英文（字段名） </h6>
					</div>
					<div class="form-group">
						<label for="text">翻译汉字：</label><input id="changText" class="form-control" 
							name="changText" type="text"/>
						</div>
						<button type="button" class="btn btn-default" onclick="change();">翻译</button>
						<button type="button" class="btn btn-default" onclick="changeHump();">驼峰式</button>
						<button type="button" class="btn btn-default" onclick="changeBig();">全大写</button>
					<div class="checkbox">
						<textarea rows="3" cols="30" id="outText" name="outText" class="form-control"></textarea>
					</div>
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>

<script type="text/javascript">
	$(function(){
		String.prototype.replaceAll = function (FindText, RepText) { 
			regExp = new RegExp(FindText, "g"); 
			return this.replace(regExp, RepText); 
		}
	});

	var text=$("#changText");
	function change(){
 		$.ajax({
			type:"post",
			url:"../tranWords",        //需要到根目录进行访问
			data:{"text":text.val()},
			success:function(data){
				var StringJson=$.parseJSON(data);   //转化为json
				var trans_result=StringJson.trans_result[0].dst;
				if(trans_result.split(' ').length > 0){
					//trans_result = changeWord(trans_result);
					$("#outText").html(trans_result);
				}
			}
		}); 
	}
	
	//驼峰
 	function changeHump(){
		var trans_result = $("#outText").val();
 		var str = "";
		arr=trans_result.split(' ');
		arr[0] = arr[0].substr(0, 1).toLowerCase()+ arr[0].substr(1, arr[0].length);  //第一个单词首字母小写
		str = arr[0];
		for(var i=1 ; i < arr.length ; i++){
			arr[i] = arr[i].substr(0, 1).toUpperCase() + arr[i].substr(1, arr[0].length);  //其他单词首字母大写
			str += arr[i];
		}
		$("#outText").html(str);
	} 
	
	//大写
	function changeBig(){
		var trans_result = $("#outText").val();
		trans_result = trans_result.toUpperCase();
		trans_result = trans_result.replaceAll(" ", "_");		
		$("#outText").html(trans_result);
	}
	
	String.prototype.replaceAll = function (FindText, RepText) { 
		regExp = new RegExp(FindText, "g"); 
		return this.replace(regExp, RepText); 
	}
	
</script>
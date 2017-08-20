<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" />  
<style>
	*{
		margin: 0;
		padding: 0;   /*解决兼容性*/
	}
	body{
		background-color:#EBEBEB;
		font-size: 12px;
	}
	.b_body{
		overflow: auto;   /*添加滚动条*/
		width:100%;
		height: 90vh;
	}
	.rotWord{overflow:hidden;margin-top: 3px;}
	.rotWord span{
		width:40px; height:40px; background:url(img/rot.jpg);background-size:40px 40px; float:left; margin-top: 15px;  margin-right: 2px;
	    margin-left: 10px;
	}
	.rotWord p{
		float:left; padding:10px; background:white; margin-left:6px; border-radius:5px; word-break:break-all; max-width:186px; 
	    margin-top: 17px;
	}
	
	.myWord{overflow:hidden;margin-top: 3px;}
	.myWord span{
		width:40px; height:40px; background:url(img/my.jpg);background-size:40px 40px;  float:right; margin-top: 8px; 
	margin-right: 10px;
	}
	.myWord p{
		float:right; padding:10px; background:#9FE658; margin-right:7px; border-radius:5px; word-break:break-all; max-width:186px; 
		margin-top: 11px;
	}
	.b_footer{
		width:100%;
		height:10vh;
		background-color: white;
		float:left;
	    border-top: 1px solid #e4dede;
	}
	#input{
	    width: 231px;
	    height: 38px;
	    background: white;
	    text-indent: 10px;
	    float: left;
	    outline: none;
	    overflow: hidden;
	    margin-left: 10px;
	    margin-top: 6px;
	    border: none;
	    border-bottom: #e8e7e4 1px solid;
	}
	#btn{
	    width: 60px;
	    height: 41px;
	    background: #19AD17;
	    user-select: none;
	    text-align: center;
	    line-height: 43px;
	    font-size: 16px;
	    color: white;
	    margin-top: 8px;
	    margin-right: 7px;
	    float: right;
	    cursor: pointer;
	    border-radius: 5px;
	}
	
</style>

<title>团大大</title>
</head>
<body>
	<div class="b_body">
		<div class="rotWord">
			<span></span>
			<p>你好，好久不见啦，陪我聊聊天吧~</p>
		</div>
	</div>
	<div class="b_footer">
		<input id="input" type="text" autocoplete="off" />
		<div id="btn" onclick="query()">发送</div>
	</div>
	
	<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		var text=$("#input");
		function query(){
			if(text.val()==""||text.val==""){
				text.focus();
				return;
			}
			$(".b_body").append("<div class='myWord'><span></span><p>"+text.val()+"</p></div>");
			$(".b_body").scrollTop(10000000);
			$.ajax({
				type:"post",
				url:"robot",
				data:{"text":text.val()},
				success:function(data){
					var result=$.parseJSON(data).text;
					$(".b_body").append("<div class='rotWord'><span></span><p>"+result+"</p></div>");
					$(".b_body").scrollTop(10000000);
				}
			});
			text.val("");
			text.focus();
		}
		$(document).keydown(function(event){
			if(event.keyCode==13){
				query();
			}
		});
		
	</script>
	
</body>
</html>
# 5.JQuery  

## 1.jq常用方法    
### 案例1.选择对象，设置鼠标事件  
1. 鼠标悬停事件：  
```javascript
$("div[id='div']").mouseover(function(){
	$(this).css("background-color","red");
});
```  
2. 相似的还有鼠标离开事件：mouseout  
3. 鼠标变化事件：hover  
### 案例2.获取/设置属性值  
1. 获取css样式属性（带px）  
``$("#ht").val($("#div1").css("height"));``  
获取到的是“300px”的内容  

2. 获取css样式属性（不带px） 
``$("#ht").val($("#div1").height());``  

3. 设置css属性值  
``$("#div").css({height:$("#ht").val()});``  
设置属性的方式2：  
``$("div").width($("#wd").val());``  

### 案例3.变化动画  
1. 执行自定义动画  
``$("#div").animate({width:500},2000);``  
表示变化宽度  

2. 执行显示\隐藏方法  
显示：``$("#div1").show(8000);``  
隐藏：``$("#div1").hide(8000);``  

### 案例4.操作html代码  
1. 插入文字到div  
``$("#info").html("插入的内容");``  


---

## 2.异步执行    
类似于显示和隐藏是异步执行的  
### 1.解决异步执行：回调函数  
1. 回调函数  
为了解决程序过早被执行，形成按顺序运行，需要使用回调函数  
```javascript
$(function(){      //程序默认运行
	$("#btn").click(function(){    //点击触发事件
		$("div1").show(8000,function(){   //执行完该方法的回调函数
			$("#info").html("这是回调函数执行的语句");
		});
	});
});

```  












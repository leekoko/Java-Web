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

3. 鼠标变换移上离开事件：hover  

### 案例2.获取/设置属性值  
1. 获取css样式属性（带px）  
``$("#ht").val($("#div1").css("height"));``  
获取到的是“300px”的内容，修改为background-color获取的就是该元素的背景色  
 
获取容器的大小：  
width，height内容所占空间  
innerWidth,innerHeight内边框以内  
outerWidth，outerHeight外边框以内  

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
显示&隐藏：``$("#div").toggle();``  

3. 上滑\下滑事件  
上滑：``$("#div1").slideUp(8000);``  
下滑：``$("#div1").slideDown(8000);``  
上滑&下滑：``$("#div").slideToggle();``  

5. 淡入\淡出\淡到事件  
淡入：``$("#div1").fadeIn(8000);``  
淡出：``$("#div1").fadeOut(8000);``  
淡到0.5透明：``$("#div1").fadeTo(8000,0.5);``  

### 案例4.操作html代码  
1. 插入文字到div  
``$("#info").html("插入的内容");``  

2. 设置属性的值  
``$("#w3s").attr("href","http://www.baidu.com");``  

3. 获取内容  
text():获取的是纯文字（相当于innerText）  
html()：获取的是整个内容，包括标签（相当于innerHtml）  
val()：获取的是文本内容（value的值）  

4. 追加内容（选定内容里面添加）  
前面追加：``$("p").preappend("<b>被追加的内容</b>");``  
后面追加：``$("p").append("<b>被追加的内容</b>");``  

5. 追加内容（选定内容外面添加）  
后面添加：after（）  
前面添加：before（）  

6. 删除与清空  
删除：remove()  
清空：empty（）  

7. 添加/删除类选择器  
添加：``$("p").addClass("red");``  
删除：``$("p").removeClass("类名");``  
添加&删除：``$("p").toggleClass("red")；``  

---

## 2.异步执行    
类似于显示和隐藏是异步执行的  
### 1.解决异步执行：回调函数  
1. 为了解决程序过早被执行，形成按顺序运行，需要使用回调函数  
```javascript
$(function(){      //程序默认运行
	$("#btn").click(function(){    //点击触发事件
		$("div1").show(8000,function(){   //执行完该方法的回调函数
			$("#info").html("这是回调函数执行的语句");
		});
	});
});
```  

2. 还有带参数的回调函数  
当$(".info").html(function(index，old){形成循环体})，那么根据index可以判别同类的info的第几个元素，old表示当前循环到的对象的内容。  






---













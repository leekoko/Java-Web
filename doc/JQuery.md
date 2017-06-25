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

4. 鼠标点击事件：``$(":button").click(...);``(type的选择器前面加：)  

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

## 3.遍历元素  
### 1.父类遍历  
拿到a4所有的祖先：``$("#a4").parents().each(function(index,element){})；``  
这里是jq的语法，所以获取其元素需要用$(this).attr("id"),不能用element.attr("id")  

### 2.子类遍历  
1. 获取子元素：``$("#a4").children().each(function(index,element){})；``  
2. 获取标签名：e.tagName  
3. 获取第0个元素  $(this).get(0)  
4. 获取所有子孙后代find("*"),必须加参数，*表示所有子元素，也可以加div  

### 3.同胞遍历  
1. 获取同胞元素：``$("#a3").siblings().each(function(i,e){});``  
2. 获取第一个元素$("div p").first().each(...),获取最后一个元素$("div p").last().each(...),获取第n个$("div p").eq(n)  
3. 过滤器：只获取id=p2的内容：``$("div p").filter("#p2")``  
4. 反选过滤：获取id！=p2的内容：``$("div p").not("#p2")``  

---

## 4.Ajax  
Ajax可以网页局部刷新，通过子线程的异步更新进行数据更新。  

### 1.javascript检测用户存在的案例  
1. 做一个表单，输入用户名，点击运行checkit()方法  
新建项目的方式：新建一个project，选择tomcat，新建jsp文件（记得修改编码为utf-8）  

2. 判断浏览器的类型，生成访问服务器对象，用来联系服务器  
```javascript
	 if (window.XMLHttpRequest) {
	         req = new XMLHttpRequest();
	 }else if (window.ActiveXObject) {
	         req = new ActiveXObject("Microsoft.XMLHTTP");
	 }
```  
3. 访问服务器地址（使用GET）：  
``url="CheckUser?uname="+document.getElementById("uname").value ;``checkUser对应Servlet里面的注解  
``req.open("GET",url,true);``  
访问并且带参数过去  
4. 访问结束，回调函数：``req.onreadystatechange=show``(调用show)  
5. 启动子线程：``req.send(null);``
6. 回调函数判断是否访问到，访问到有没有结果  
```javscript
 function show(){
  if (req.readyState == 4) {   //判断是否访问到
       if (req.status == 200) {   //访问到有没有结果
          var city = req.responseText;   //获取servlet中response打印回来的数据
		  document.getElementById("info").innerHTML=city;
       }
   }
 }
```  
7. 新建一个servlet(注意注解跟调用地址一致)  
编写doGet()方法，获取带过来的参数：``String uname=request.getParameter("uname");``  
将拿到的参数跟数据库中的信息进行匹配，如果已注册则通过response打印信息:``response.getWriter().println("该用户名已注册");``  
(因为中文的存在，还需要在输出之前用response.setCharacterEncoding("UTF-8")来设置编码格式)  

**使用javascript的多线程，通过ajax访问servlet后台数据**  
register.html  
```html
<table>
	<tr><td>用户名：</td><td><input type="text" id="uname"/></td></tr>
	<tr><td><input type="button" value="验证" onclick="checkUser()"/></td><td><div id="info"></div></td></tr>
</table>
```

```javascript
function checkUser(){
	//生成访问服务器对象
	 if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }else if (window.ActiveXObject) {
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }
	var url="CheckUser?uname="+document.getElementById("uname").value;
	req.open("GET",url,true);
	req.onreadystatechange=show;   //回调show方法
	req.send(null);   //启动子线程
	
}
 function show(){
	 if (req.readyState == 4) {   //判断是否访问到
	      if (req.status == 200) {   //访问到有没有结果
			var city = req.responseText;   //获取servlet中response打印回来的数据
			document.getElementById("info").innerHTML=city;
	      }
	  }
 }
```

CheckUser.java  
```java
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname=request.getParameter("uname");
		response.setCharacterEncoding("UTF-8");
		//模拟数据库
		String[] arr={"小明","小黑","小白"};
		boolean flag=false;   //默认不重复
		for (int i = 0; i < arr.length; i++) {
			if(arr[i].equals(uname)){
				flag=true;
			}
		}
		if(flag){
			response.getWriter().println("该用户名已注册");
		}else{
			response.getWriter().println("注册成功");
		}
	}
```  
上方注解记得添加：``@WebServlet("/CheckUser")``  

### 2.jQuery检测用户存在的案例  
(引入jQuery：``<script src="jQuery/jquery-1.11.3.js"></script>``,必须为对标签)  
1. ajax访问url的方式：``obj=$.ajax({url:"CheckUser?uname="+$("uname").val(),async:false});``  
2. 将返回对象显示：``$("#info").html(obj.responseText);``  
3. 使用load简化ajax的url访问  
``$("#info").load("CheckUser?uname="+$("#uname").val());``(查询字符串的方式)  
``$("#info").load("CheckUser",{uname:$("#uname").val()});``(data的方式)    
load还可以加载文本文件的内容：``$("#div").load("text1.txt");``，加载完之后还可以执行回调函数  
4. 使用post访问并且执行回调函数：
```javascript
	$.post("CheckUser",{uname:$("#uname").val()},function(d,s){...});
```  
d为response返回的数据，s为执行的情况  
（当$和其他前端框架产生冲突，查询文档进行解决)  

**使用jQuery的多线程，通过ajax访问servlet后台数据**  
```javascript
<script src="jQuery/jquery-1.11.3.js"></script>
<script>
	$(function(){
		$(":button").click(function(){
			$.post("CheckUser",{uname:$("#uname").val()},function(d,s){
				$("#info").html(d);
			});
		});
	});

</script>
```  

---
**进入下一章：[6.javaweb快速入门](javaweb_ABC.md)**  
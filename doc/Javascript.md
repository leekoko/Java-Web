# 4.javascript   

## 1. 元素  
### 1.按钮  
分为功能按钮和普通按钮，功能按钮有submit,reset（只能放在表格中），普通按钮是button  
点击事件：用onclick添加  
### 2.选择框  
document.forms[0].ah[i].checked判断是否选中（ah是选择框的name）  
选中选择框：``document.getElementById("nan").checked=true;``  

### 3.输入框  
document.form[0].mm.focus()选中内容  

### 4.方法  
```javascript
function sayHello(){
	alert("你好"+document.getElementsByName("xm")[0].value);
}
```  
name有多个，所以Elements需要加s  
onload事件：页面加载的时候执行，写在body中  
parseFloat(value值)：转化为浮点数  

### 5.正则表达式  
1. 定义正则表达式  
方法1：var reName=new RegExp("正则表达式");    
方法2：var reName=/正则表达式/;  

2. 中文正则表达式  
/^[\u4e00-\u9fa5]+$/i   
该编码范围内，+表示出现1次以上  

3. 使用正则表达式  
```javascript
var str=/^[\u4e00-\u9fa5]+$/i;
if(!str.test(某个value)){
	...
}
```  

4. 数字正则表达式  
/^[1-9][0-9]?$/ 第一位1-9，第二位0-9，问号表示不一定会出现  
该正则表达式用来限制1-99的数字  

**使用正则表达式，判断输入框的名字是否为中文，岁数是否为1-99岁**  
```html
<html>
	<head>
		<meta charset="{CHARSET}">
		<title>正则表达式</title>
		<script>
			function yz(){
				var a=/^[\u4e00-\u9fa5]+$/;
				var b=/^[1-9][0-9]?$/;
				if(!a.test(document.getElementsByName("xm").item(0).value)){
					alert("请输入中文");
					return;
				}
				if(!b.test(document.getElementsByName("nl").item(0).value)){
					alert("请输入1-99的数字");
					return;
				}
				alert("验证成功");
			}
		</script>
	</head>
	
	<body>
		姓名：<input name="xm" /><br />
		年龄：<input name="nl" /><br />
		<button onclick="yz()">验证</button>
	</body>
</html>
```


---

## 2.DOM  
操作网页上的元素（文档对象模型）  
1. 隐藏元素  
``document.getElementsByName("nvhx").item(i).style.display="none";``
获取name必须添加item，从0开始计算    
style.visibility=visible/hidden 元素隐藏，空间留着  
style.display=block/none 元素和空间都会隐藏(为了防止排版出错，block改为table-row)  

2. 获取元素宽度  
``document.getElementBy("inner").style.width=document.getElementById("inner").offsetWidth+50+"px";``  
offsetWidth取出来的是纯粹数字，没有带px  

3. 打印元素内容  
document.write("<tr>");  
document.write(books[i].name);  
双引号内容用单引号代替  

**点击男女选框，表格中的输入框发生改变**
```html
<html>
	<head>
		<meta charset="{CHARSET}">
		<title>显示隐藏</title>
		<script>
			function nan(){
				document.getElementById("de").checked=true;
				document.getElementById("nan").style.display="block";
				document.getElementById("nv").style.display="none";
			}
			function nv(){
				document.getElementById("nan").style.display="none";
				document.getElementById("nv").style.display="block";
			}
		</script>
	</head>
	
	<body onload="nan()">
		男：<input type="radio" name="sex" id="de" onclick="nan()"/>
		女：<input type="radio" name="sex" onclick="nv()"/>		
		<div style="background: black; width: 20px; height: 20px;" id="nan"></div>
		<div style="background: red; width: 20px; height: 20px;" id="nv"></div>		
	</body>
</html>
```  

---

## 3.调试  
1.  DOM资源管理器  
可以查看javascript执行完之后的结果（跟直接查看源码不一样）  
可以修改显示的内容，右边为对应样式  
2.  DeBug  
在Debug页面可以调试断点  
Source中可以断点跟踪，查看属性  
Firefox可以添加插件firebug  
3. console  
可以在控制台打印：console.log(...width);  

---

## 4.定时器  
1. setInterval：多次定时器  
启动定时器：``timer=window.setInterval(show,50);``50毫秒执行一次show方法  
停止定时器：``window.clearInterval(timer);``  
2. setTimeout：单次定时器  

---

## 5.对象  
创建对象：  
```javascript
function Person(firstname,lastname,age,eyecolor){
	this.firstname=firstname;
	this.lastname=lastname;
	this.age=age;
	this.eyecolor=eyecolor;
}

var myFather=new Person("Bill","Gates",56,"blue");  
```

**新建对象存入数组，用js编写一个动态表单，将对象的信息显示出来,添加选框（有全选）**  
```html
<html>
	<head>
		<meta charset="UTF-8">
		<title>动态加载表单</title>
	</head>
	<body>
		<table>
			<tr>
				<td><input type="checkbox" name="op" onclick="quan()"/></td>
				<td>序号</td>
				<td>书名</td>
				<td>数量</td>
			</tr>
			<script>
				function Book(name,num){
					this.name=name;
					this.num=num;
				}
				var arr=new Array();
				arr[0]=new Book("java书",50);
				arr[1]=new Book("android书",20);
				arr[2]=new Book("C书",60);
				arr[3]=new Book("javascript书",40);
				arr[4]=new Book("jquery书",20);
				//新建完数组，进行输出
				for(var i=0;i<arr.length;i++){
					document.write("<tr>");
					
					document.write("<td>");
					document.write("<input type='checkbox' name='op'/>");
					document.write("</td>");
					
					document.write("<td>");
					document.write(i+1);
					document.write("</td>");
			
					document.write("<td>");
					document.write(arr[i].name);		
					document.write("</td>");
					
					document.write("<td>");
					document.write(arr[i].num);		
					document.write("</td>");
					
					document.write("</tr>");

				}
				//编写全选功能
				function quan(){
					for(var i=0;i<document.getElementsByName("op").length;i++){
						document.getElementsByName("op").item(i).checked=document.getElementsByName("op").item(0).checked;
					}
				}
			</script>
		</table>		
		
	</body>
</html> 
```

---









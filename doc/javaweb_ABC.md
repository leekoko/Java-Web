# 6.javaweb快速入门 

## 1.案例  
### 1.前端设计  
因为这一部分主要学的是jsp，所以前端代码直接提供,不予解释  
```html
<form name="f1" action="show.jsp">
	<table>
		<tr><td>用户名</td><td><input type="text" name="uname"></td></tr>
		<tr><td>密码</td><td><input type="password" name="pwd"></td></tr>
		<tr><td>重复密码</td><td><input type="password" name="pwd1"></td></tr>
		<tr>
			<td>所在省份</td>
			<td>
				<select name="prov">
					<option value="北京">北京</option>
					<option value="上海">上海</option>
					<option value="天津">天津</option>
					<option value="重庆">重庆</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>兴趣爱好</td>
			<td>
				<input type="checkbox" name="hobby" value="爬山">爬山
				<input type="checkbox" name="hobby" value="上网">上网
				<input type="checkbox" name="hobby" value="看书">看书
				<input type="checkbox" name="hobby" value="下棋">下棋
				<input type="checkbox" name="hobby" value="游泳">游泳
				<input type="checkbox" name="hobby" value="乒乓球">乒乓球
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="提交信息"></td>
		</tr>
	</table>
	
</form>	

```  
1. submit按钮要验证后才给予提交，在submit中添加onclick属性``onclick="return check()"``,在js中写return false还是true来确定是否提交    

### 2.jsp页面接受  
1. 在另一个jsp页面中用request接收  
``String uname=request.getParameter("uname");``  
当获取的是多个值得时候（多选框），用数组存储：``String[] arr=request.getParameter("hobby");``  
2. 在另一个页面线显示用``out.print("你的用户名是"+uname);``  
3. 运行测试，每次修改都得重新启动服务器  
> jsp表格提交    
show.jsp
```html
<%
	String uname=request.getParameter("uname");
	String pwd=request.getParameter("pwd");
	String prov=request.getParameter("prov");
	String[] hobby=request.getParameterValues("hobby");
	out.print("姓名："+uname+"\t");
	out.print("密码："+pwd+"\t");
	out.print("城市："+prov+"\t");
	out.print("爱好：");
	for(int i=0;i<hobby.length;i++){
		out.print(hobby[i]+" ");
	}
%>
``` 

### 3.用js验证数据  
1. 去除空格：``str=str.replace(/\s|/g,"");``  
2. 光标定位，focus()方法  
3. 用innnerHTML插入文字提示  
4. 检查非空，遍历多选数组，如果没内容则输出提示，return false  
5. 如果是普通按钮，没有提交功能，需要添加``document.f1.submit();``进行提交  
```javascript
function checkit(){
	var str=document.getElementsByName("uname").item(0).value;
	str=str.replace(/\s|/g,"");
	if(str.length<=0){
		document.getElementById("info").innerHTML="请输入名字";
		return false;
	}
	return true;
}
```

### 4.用jq验证数据  
1. $.trim()可以将内容进行去空格化  
2. 使用submit按钮，当jq返回的是fasle的时候，内容不会提交  
3. .each()方法可以对数组对象进行遍历，参数e表示当前对象  
4. 如果不是submit按钮，需要在jq中添加``$("form[name='f1']").submit()``进行事件提交  
```javascript
	$(function(){
		$("form[name='f1']").submit(function(){
			if($.trim($(":text[name='uname']").val()).length==0){
				$(":text[name='uname']").select();
				$(":text[name='uname']").focus();
				$("#info").html("用户名不为空！");
				return false;
			}
			if($.trim($(":password[name='pwd']").val()).length==0){
				$(":password[name='pwd']").select();
				$(":password[name='pwd']").focus();
				$("#info").html("密码不为空！");
				return false;
			}
			var pwd=$(":password[name='pwd']").val();
			var pwd1=$(":password[name='pwd1']").val();
			if(pwd!=pwd1){
				$("#info").html("两次输入的密码不一致！");
				return false;
			}
			//至少选择一种爱好
			var times=0;
			$(":checkbox[name='hobby']").each(function(i,e){
				if(e.checked){
					times++;
				}
			});
			if(times==0){
				$("#info").html("请至少选择一个爱好");
				return false;
			}
			return true;
		});
	});
```  

[案例源码](../SourceCode/javaWeb_ABC/)   

---

## 2.jsp构成  
jsp的运行原理：写的jsp文件会被翻译成java文件  
### 1.脚本元素  
1. **<%编写的是方法内的内容%>**...<%隔开的这些内容和上面那部分是同个方法中的，参数可以共享%>  
2. **<%!编写的是和方法同级的方法%>**  
3. **<%=getData()%>**可以直接访问jsp的代码  


# 6.javaweb快速入门 

## 1.案例  
### 1.前端设计  
因为这一部分主要学的是jsp，所以前端代码直接提供,不予解释  
```html
<form name="f1" action="show.jsp">
	<table>
		<tr><td>用户名</td><td><input type="text" id="uname"></td></tr>
		<tr><td>密码</td><td><input type="password" id="pwd"></td></tr>
		<tr><td>重复密码</td><td><input type="password" id="pwd1"></td></tr>
		<tr>
			<td>所在省份</td>
			<td>
				<select id="prov">
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
			<td colspan="2"><input type="button" value="提交信息"></td>
		</tr>
	</table>
	
</form>	

```  
1. submit按钮要验证后才给予提交，在submit中添加onclick属性``onclick="return check()"``  


### 2.jsp页面接受  
1. 在另一个jsp页面中用request接收  
``String uname=request.getParameter("uname");``  
当获取的是多个值得时候（多选框），用数组存储：``String[] arr=request.getParameter("hobby");``  
2. 在另一个页面线显示用``out.print("你的用户名是"+uname);``  
3. 运行测试，每次修改都得重新启动服务器  

### 3.用js验证数据  
1. 去除空格：``str=str.replace(/\s|/g,"");``  
2. 全选所有空格，设置该uname下的第0个元素的selectionStart=0，selectionEnd=value.length-1  
3. 光标定位，focus()方法  
4. 用innnerHTML插入文字提示  
5. 检查非空，遍历多选数组，如果没内容则输出提示，return false  
6. 如果是普通按钮，没有提交功能，需要添加``document.f1.submit();``进行提交  

**表格验证提交，用js做验证**  




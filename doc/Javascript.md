# 4.javascript   
## 1. 元素  
### 1.按钮  
分为功能按钮和普通按钮，功能按钮有submit,reset（只能放在表格中），普通按钮是button  
点击事件：用onclick添加  
### 2.选择框  
document.forms[0].ah[i].checked判断是否选中（ah是选择框的name）  
### 3.输入框  
document.form[0].mm.focus()选中内容  
### 4.方法  
```javascript
function sayHello(){
	alert("你好"+document.getElementsByName("xm")[0].value);
}
```  
name有多个，所以Elements需要加s  
### 5.正则表达式  




---

## 2.DOM  
操作网页上的元素（文档对象模型）  
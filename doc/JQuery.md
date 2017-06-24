# 5.JQuery  

## 1.jq常用方法    
### 案例1.选择对象，设置鼠标事件  
1. 鼠标悬停事件：  
```
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
设置属性的方式2：``$("div").width($("#wd").val());``  






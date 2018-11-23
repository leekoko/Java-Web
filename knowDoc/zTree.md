## zTree简单使用

#### 初始化树

Z：zTree前端：

1. zTree需要引入的文件

   ```html
   <link rel="stylesheet" href="${uiPath}/components/zTree-3.5.24/css/zTreeStyle/zTreeStyle.css" type="text/css">
   <link rel="stylesheet" href="${uiPath}/components/zTree-3.5.24/css/metroStyle/metroStyle.css" type="text/css">
   <script src="${uiPath}/components/zTree-3.5.24/js/jquery.ztree.core.min.js"></script>
   <script src="${uiPath}/components/zTree-3.5.24/js/jquery.ztree.excheck.js"></script>
   ```

2. 树渲染

   ```javascript
   var setting = {
       callback: {	
           onClick: onClick	//开启点击事件
       }
   };
   ```

   ```javascript
   $.fn.zTree.init($("#treeDemo"), setting, zNodes);   //初始化树
   ```

M：zNodes的内容为什么呢？

Z：节点信息，结构如下TreePojo对象

```java
//结点名
private String name;

private boolean open;
//非父选框   true非false否
private boolean nocheck;

private String code;

//子节点列表
private List<TreePojo> children;
```

children为子节点List，按照结构填充完属性之后返回为json数据，树将自动生成。

#### 点击事件

M：点击树事件怎么实现？

Z：开启setting的``setting.callback={'onClick':'onClick'}``属性  ，使用默认添加

因为点击是树的事件，要去调用父对象的方法，对父对象来说是被动接收

```javascript
    //结点点击事件
    function onClick(event, treeId, treeNode, clickFlag) {
        //刷新专家管理页面（父页面存在专家管理页面则对其刷新）
        if(parent.reflashPage){
            parent.reflashPage(treeNode);   //直接返回结点对象
        }
    }
```

#### 复选框

M：怎么开启树复选框功能？

Z：添加复选框设置，使用判断添加

```javascript
/*如果是addExpert子页面，使用checkbox多选*/
if("${typeMark}" == "checkbox"){
    setting.check = {'enable':true};
}
```

M：获取选中信息，提供获取信息的方法，对父对象来说是主动调用

```javascript
    /*返回所选复选框信息*/
    function getLeafStr(){
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        //获取被勾选结点
        var nodes = treeObj.getCheckedNodes(true);
        var leafCode = "";
        var leafName = "";
        for (var i = 0; i < nodes.length; i++) {
            leafCode += nodes[i].code + ",";
            leafName += nodes[i].name + ",";
        }
        leafCode = leafCode.substring(0,leafCode.length - 1);
        leafName = leafName.substring(0,leafName.length - 1);
        var obj = {'leafCode':leafCode,'leafName':leafName};
        return obj;
    }
```

因为是多选，使用拼接字符串返回的方式。
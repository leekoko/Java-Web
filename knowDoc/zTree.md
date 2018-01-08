# zTree的使用  

### 1.js方法1  

```javascript
    function loadTree() {
        if(entp_code==''){
            return
        }
        var zTree;
        var zTreeJson = "";
        var setting = {
            data : {
                simpleData : {
                    enable : true,
                    idKey : "code",
                    pIdKey : "parentCode",
                    rootPId : ""
                }
            },
            view: {
                //此目的是为了将查询出的树节点高亮显示 实际项目中可以不用
                fontCss: getFontCss
            },
            callback : {onClick : editMenuInfo}
        };
        $.ajax({
            type : "post",
            url : "${ctx}/entpStructure/getTreeData?entpCode="+entp_code,
            async : false,
            success : function(data) {
                zTreeJson = data.result;
            }
        });
        zTree = $.fn.zTree.init($("#menuTree"), setting,zTreeJson);
    }
```

1. ajax访问Controller，返回为zTree包装好的pojo对象。

   ```java
   public class EntpRele extends BaseEntity<EntpRele> {
   	private static final long serialVersionUID = 1L;
   	private String[] codes;//消息通知所有编码	
   	/** 数据唯一标识 */
   	private String code;
   	/** 企业CODE */
   	private String entpCode;
   	/** 关联CODE */
   	private String parentCode;
   	/** 名称 */
   	private String name;
   	/** 树节点是否展开 */
   	private String open;
   ...
   ```

2. ``zTree = $.fn.zTree.init($("#menuTree"), setting,zTreeJson);``：

   把数据传给zTree组件来生成树状结构：生成树结构显示的div，setting属性设置，Json数据    

3. setting设置：

   ```javascript
           simpleData: {
               enable: true,
               idKey: "c01id", //修改默认的ID为自己的ID
               pIdKey: "c01parentid",//修改默认父级ID为自己数据的父级ID
               rootPId: 000     //根节点的ID
           }
   ```

4. ``callback : {onClick : editMenuInfo}``：捕获点击后执行的方式，预加载。   

### 2.js方法2   

刷新树的方法	

```javascript
    function loadTree1(){
        loadTree();
        var treeObj = $.fn.zTree.getZTreeObj("menuTree");
        //treeObj.selectNode(selectTreeNode,false,true);
        editMenuInfo(null, null, selectTreeNode);
    }
```

1. 获取zTree对象   
2. ``treeObj.selectNode(selectTreeNode,false,true);``：选中节点操作，选中第一个节点、false设置不可多选节点、true设置选中节点时，会让节点自动滚到到可视区域内    

### 3.js方法3   

```javascript
    var menuCode_entp = "";    //企业code
    var menuCode_parent = "";  //节点code
    var selectTreeNode;
    /**
     *  编辑节点
     *  点击节点获取对应code
     * */
    function editMenuInfo(event, treeId, treeNode) {
        if (treeNode != null) {
            selectTreeNode = treeNode;
            menuCode_entp = treeNode.entpCode;
            menuCode_parent = treeNode.code;
            //$("#menuCode").val(menuCode);
            if(treeNode.level!=0){
	            //flag="update";
	            $('#update').show();    //添加或者修改节点iframe之间的切换
	            $('#add').hide();
	            $("#dataForm").attr("action", "${ctx}/entpStructure/addEntpFrom?deptName="+treeNode.name+"&code="+treeNode.code+"&tId="+treeNode.tId);
	            $("#dataForm").submit();
        	}
        }
    }
```

1. 点击节点的时候，切换显示的iframe。

2. 执行Controller方法：

   ```javascript
   	            $("#dataForm").attr("action", "${ctx}/entpStructure/addEntpFrom?deptName="+treeNode.name+"&code="+treeNode.code+"&tId="+treeNode.tId);
   	            $("#dataForm").submit();
   ```

   前端容器：

   ```html
                               <form id="dataForm" target="MenuInfo" method="post">
   				           </form>
   ```

### 4.调用的Controller方法

```java
    @RequestMapping(value = "addEntpFrom")
    public String addEntpFrom(HttpServletRequest request, HttpServletResponse response, String deptName,String code,String tId, Model model) {
        /**
         * 获取本节点CODE并将CODE传到修改本节点的页面
         * 由子节点使用建立关联关系
         */
        model.addAttribute("deptName", deptName);
        model.addAttribute("code", code);
        model.addAttribute("tId", tId);
        return "modules/supply/entpStructureFrom/addEntpFrom";
    }
```

跳转的前端页面    

```html
<form class="form-horizontal" id="dataForm">

        <!--从Model获取数据并加载到input-->
        <input type="hidden" id="deptName" name="deptName" value="${deptName}"/>
        <input type="hidden" id="code" name="code" value="${code}"/>
        <input type="hidden" id="tId" name="tId" value="${tId}"/>

        <div class="row">
            <div class="col-sm-12">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">部门名称</label>
                        <div class="col-sm-9">
                            <input id="name" name="name" value="${deptName}" class="form-control" type="text">
                        </div>
                    </div>
                </div>

            </div>
        </div>
</form>


<script>
    function saveOrUpdate(){     //保存的js方法
    	var rs="";
    	if($("#name").val()==''){
    		promptAlert("请输入部门名称！");
            return;
    	}
        var url = "${ctx}/entpStructure/update?code="+$("#code").val()+"&name="+$("#name").val();
        $.ajax({
            url : url,
            type : "POST",
            async : false,
            success:function(data){
                if(data.result == true){
                    parent.loadTree1();
                	parent.liClick($("#tId").val());
                	promptAlert("保存成功");
                	rs="yes";
                }else{
                	promptAlert("保存失败");
                	rs="no";
                }
            }
        });
        return rs;
    }

</script>
```

#### 主页面的iframe

```html
                        <div class="panel-body">
                            <form id="menuForm" target="editMenuInfo" method="post">
                                <input type="hidden" name="menuCode" id="menuCode" />
                                <input type="hidden" name="name" id="name" />
                            </form>
                            <form id="dataForm" target="MenuInfo" method="post">
				           </form>
                            <div id="add">
                                <iframe name="editMenuInfo" id="editMenuInfo" height="300px" width="100%" allowTransparency="true" frameBorder=0> </iframe>
                            </div>  
                            <div id="update">    
                                <iframe name="MenuInfo" id="MenuInfo" height="300px" width="100%" allowTransparency="true" frameBorder=0> </iframe>
				           </div>
                        </div>
```

跳转到指定页面，该页面在哪里显示呢？from的target进行了指定：

```html
                            <form id="dataForm" target="MenuInfo" method="post">
				           </form>
		...
                            <div id="update">    
                                <iframe name="MenuInfo" id="MenuInfo" height="300px" width="100%" allowTransparency="true" frameBorder=0> </iframe>
				           </div>
```

### 5.js方法4     

```javascript
    function initComplete() {
        loadTree1();
        var treeObj = $.fn.zTree.getZTreeObj("menuTree");
        var nodes = treeObj.getNodes();
        if (nodes.length > 0) {
            treeObj.selectNode(nodes[0]);
            editMenuInfo(null, null, nodes[0]);
        }
    }
```

调用上方js，默认选中第一个节点。    

---

### 6.删除节点js   

```javascript
    /*删除节点*/
    function deleteMenu() {
        if (!isNotEmpty(menuCode_parent)) {
            promptAlert("请选择菜单节点！");
            return;
        }
        promptConfirm("确认删除？", function() {
            $.ajax({
                type : "post",
                url : "${ctx}/entpStructure/delete?code="+menuCode_parent,
                async : false,
                success : function(data) {
                    if (data) {
                        promptAlert(DEL_SUCCESS_MSG);    
                        var treeObj = $.fn.zTree.getZTreeObj("menuTree");
                        var nodes = treeObj.getNodes();
                        if (nodes.length > 0) {
                            selectTreeNode = nodes[0];    //删除后还要选中第一个节点
                        }
                        loadTree1();    //重新加载树
                    } else {
                        promptAlert(DEL_FAIL_MSG);
                    }
                }
            })
        });
    }
```

---

### 7.查询节点   

```javascript
    /*根据某一条件查找节点 模糊查询*/
    function getFuzzy() {
        var parentNode;
        var value = $("#menuFind").val();
        if (value == "") {
            return;
        }
        var key = "name";
        var zTree = $.fn.zTree.getZTreeObj("menuTree");
        var nodes = zTree.getNodesByParamFuzzy(key, value, null);
        //取消之前的高亮显示
        highNodes(zTree, zTree.highlightNodeList, false);
        //高亮显示
        highNodes(zTree, nodes, true);
        zTree.highlightNodeList = nodes;
        //选中第一个
        if (null != nodes && nodes.length > 0) {
            zTree.selectNode(nodes[0]);
            editMenuInfo("select", null, nodes[0]);
        }
    }
```

1. ``var nodes = zTree.getNodesByParamFuzzy(key, value, null);``:key指定模糊搜索的属性，value指定搜索的属性值。    

2. 根据高亮显示的属性，对第一个进行编辑：

   ```javascript
           zTree.highlightNodeList = nodes;
           //选中第一个
           if (null != nodes && nodes.length > 0) {
               zTree.selectNode(nodes[0]);
               editMenuInfo("select", null, nodes[0]);
           }
   ```

#### 节点高亮显示：

```javascript
    /*高亮显示*/
    function highNodes(zTree, nodes, highlight) {
        if (null == nodes)
            return;
        for (var i = 0, l = nodes.length; i < l; i++) {
            nodes[i].highlight = highlight;
            zTree.updateNode(nodes[i]);      //更新节点属性
        }
    }
```

节点的高亮显示。

---

### 8.保存方法  

```javascript
    function saveOrUpdate(){
    	if($("select[name='entpCode']").val()==''){
            promptAlert("请选择企业！");
            return
        }
        if (!isNotEmpty(menuCode_parent)) {
            promptAlert("请选择菜单节点！");
            return;
        }
        
    	/* if ($("input[type='text'][id='name']").length == 0){ 
    		promptAlert("请增加子部门");
            return;
    	} */
    	
		if(flag=="update"){
			var status=document.getElementById('MenuInfo').contentWindow.saveOrUpdate();
		}else{
			var status=document.getElementById('editMenuInfo').contentWindow.saveOrUpdate();
		}
    }
```

选择不同页面的保存方法。   


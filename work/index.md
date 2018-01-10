# 首页显示逻辑  

## 1.工作台按钮   

### 1.Controller跳转代码      

首先根据图标内容创建字典   

```java
	@RequestMapping(value = "toWorkbenchIcon")
	public String toWorkbenchIcon(HttpServletRequest request,Model model){
		List<Map<String,Object>> workbenchIconList = new ArrayList<Map<String,Object>>();
		Integer workbenchLength = null;
		User user = UserUtils.getUser();
		if("minstone".equals(user.getId())){
			workbenchIconList = DictUtils.getDictListMap("th_user_workbenchIcon");
			workbenchLength = workbenchIconList.size();
		}else{
			String deptUnicode = DeptUtils.getDept(user.getDept().getCode()).getDeptUnicode();
			workbenchIconList = DictUtils.getDictListMap("th_"+deptUnicode);
			workbenchLength = workbenchIconList.size();
		}
		model.addAttribute("workbenchLength", workbenchLength);
		model.addAttribute("workbenchIconList", workbenchIconList);
		return "/modules/th/workbench/workbenchIcon";
	}
```

1. ``List<Map<String,Object>>``:从字典中获取到的是Map的集合，将其传到前台。     

### 2.html显示

```html
<c:forEach items="${workbenchIconList }" var="workbenchIcon" varStatus="list">
  <td >
    <div class="navIcon" onclick="iconOnclick('${workbenchIcon.value }');"
         style="width: 80px; height: 50px">
      <div class="navIcon_left"  align="center">
        <img    
             src=" ${ctxp}/static/modules/th/workbench/images/wb_${workbenchIcon.value}.png"  style="width: 50%;height: 50%;" />
      </div>
      <div class="clear"></div>
      <div align="center">${workbenchIcon.key }</div>
    </div>
  </td>
</c:forEach>
```

遍历传过来的Map数组，将其value作为图片名和传参，key作为显示名。   

#### 对应约定   



### 3.js控制跳转   

```javascript
	//add by huangnh
	function iconOnclick(mySign){
		//项目申报
		if(mySign == "th_project_application"){
			var diag = promptWin();
			diag.Title = "项目立项申请";
		    diag.ShowOkButton = false;
		    var url  = "${ctx}/thProjectInfo/toProjectApplication";
		    diag.URL = url;
		    diag.show();
		    diag.max();
		    diag.addButton("save", "保存", function(){
		    	diag.innerFrame.contentWindow.save("save");
		    	var saveflag = diag.innerFrame.contentWindow.document.getElementById("saveflag").value;
		    	if(saveflag == "true"){
		    		window.location.reload();
		    		diag.close();
		    	}
		    });
		    diag.addButton("start", "提交", function() {	
		    	diag.innerFrame.contentWindow.start();
		    	var startflag = diag.innerFrame.contentWindow.document.getElementById("startflag").value;
		    	if(startflag == "true"){
		    		window.location.reload();
		    		diag.close();
		    	}
		    });
		}else if(mySign == "th_task_info"){
			//待办图标
			window.location.href = "${ctx}/query/commonQuery?code=th_task_info";
		}else if(mySign == "th_task_his_info"){
			//已办图标
			window.location.href = "${ctx}/query/commonQuery?code=th_task_info_his";
		}else if(mySign == "th_project_application_proAttrach"){
			//项目附件
			var diag = new top.Dialog();
	    	diag.Title = "项目附件 ";
	    	diag.URL ="${ctx}/query/commonQuery?code=th_project_application_proAttrach";
			diag.Width = 1400;
			diag.Height = 700;
	    	diag.show();
		}
    }
```

根据传过来的参数选择跳转路径，跳转方式主要有两种：

1. 直接跳转    
2. 弹窗跳转（弹窗再区分有无保存提交按钮）      

## 2.下方左右iframe    

### 1.html显示   

```html
      <!--两列布局1开始-->
      <div class="fn-mt20 row-fluid">
        <!--左边内容1开始-->
        <div class="span6">
          <div class="ui-box ui-box-shadow">
            <div class="ui-box-head">
              <i class="icon-comment"></i>
              <ul class="ui-box-head-pills fn-right">
                <li><a onclick="iconOnclick('th_task_info');" class="ui-box-head-toggle">更多<i class="icon-chevron-right"></i>
                  </a>
                </li>
              </ul>
              <h3>个人待办</h3>
            </div>
            <div class="ui-box-container" style="height:290px;">
              <div class="ui-box-content" style="padding: 0;height:290px;">
                <iframe width="100%" height="100%" frameborder="no" name="urlFrame1" src="${ctx}/query/commonQuery?code=th_workspace_task_info"></iframe>
              </div>
            </div>
          </div>
        </div>
        <!--左边内容1结束-->

        <!--右边内容1开始-->
        <div class="span6">
          <div class="ui-box ui-box-shadow">
            <div class="ui-box-head">
              <i class="icon-info-sign"></i>
              <ul class="ui-box-head-pills fn-right">
                <li><a  onclick="moerBut()" class="ui-box-head-toggle">更多</a><i class="icon-chevron-right"></i>
                </li>
              </ul>

              <h3 ><a onclick="Jump('common')">普通通知</a></h3>&nbsp;&nbsp;丨&nbsp;&nbsp;<h3 ><a onclick="Jump('remind')">提醒通知<span id="myspan"></span></a></h3> 
            </div>
            <div class="ui-box-container" style="height:290px;">
              <div class="ui-box-content" style="padding: 0;">
                <div class="ui-box-content" style="padding: 0;height:290px;">
                  <iframe id="noticeiframe" width="100%" height="100%" frameborder="no" name="" noticeiframe="" src=""></iframe>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--右边内容1结束-->			
      </div>
      <!--两列布局1结束-->
```

前端参照模板     

### 2.js控制按钮跳转        

```javascript
	//选择通知类型
	function Jump(Str){
		if(Str == "common"){
			$("#noticeiframe").attr("src","${ctx}/query/commonQuery?code=th_notice_common_history");
		}else if(Str == "remind"){
			$("#noticeiframe").attr("src","${ctx}/query/commonQuery?code=th_project_historyList");
		}
	}
	
	//选择通知类型更多按钮
	function moerBut(){
		window.location.href = "${ctx}/query/commonQuery?code=th_notice_info";
	}
```


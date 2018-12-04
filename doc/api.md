# api结构

M：api的编写有什么结构要求吗？

Z：形式如下

 http://{IP}:{PORT}/{PROJECT_CONTEXT}/api/user/add

- /api：接口标识
- /user：类型
- /add：方法功能

要做到命名作用不重复

M：如果要在url上直接传参怎么做？

Z：如下代码所示，接收参数后赋值给形参

```java
    /**
     * 获取政策详情
     * @param response
     * @param id 主键code
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public void getPolicyDetail(HttpServletResponse response, @PathVariable String id){
        ...
```


package com.leekoko.mvcframework.servlet;

import com.leekoko.mvcframework.annotation.GPAutowired;
import com.leekoko.mvcframework.annotation.GPController;
import com.leekoko.mvcframework.annotation.GPRequestMapping;
import com.leekoko.mvcframework.annotation.GPService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class GPDispathServlet extends HttpServlet {

    private Properties contextConfig = new Properties();
    private List<String> classNames = new ArrayList<String>();
    private Map<String,Object> ioc = new HashMap<String,Object>();
    private Map<String,Method> handlerMapping = new HashMap<String,Method>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //任意调度和派遣
        try {
            doDispath(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500"+Arrays.toString(e.getStackTrace()));
        }


    }

    private void doDispath(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        //转化为相对路径
        url.replace(contextPath,"").replaceAll("/+","/");
        if(!this.handlerMapping.containsKey(url)){
            resp.getWriter().write("404 not Found!");
            return;
        }
        Method method = this.handlerMapping.get(url);
        //获取不到params
        Map<String, String[]> params = req.getParameterMap();
        System.out.println(method);
        //用反射调用方法
        String beanName = lowerFirstCase(method.getDeclaringClass().getSimpleName());
        method.invoke(ioc.get(beanName),req, resp, params.get("name")[0] );
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2.扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));

        //3.实例化所有的相关Class，放入到IOC容器
        doInstance();

        //4.依赖注入，将字段自动赋值
        doAutowired();

        //===============  spring实现完成  ==========================

        //5.初始化HandlerMapping(把Controller中的url和Method一对一关联)
        initHandlerMapping();

        System.out.println("GP Spring MVC is init");
    }

    private void initHandlerMapping() {
        if(ioc.isEmpty()){
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();

            if(!clazz.isAnnotationPresent(GPController.class)){
                continue;
            }
            String baseUrl = "";
            if(clazz.isAnnotationPresent(GPRequestMapping.class)){
                GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                baseUrl = requestMapping.value();
            }
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if(!method.isAnnotationPresent(GPRequestMapping.class)){
                    continue;
                }
                GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
                String url = requestMapping.value();
                url = (baseUrl + "/" +url).replaceAll("/+","/");
                handlerMapping.put(url, method);
                System.out.println("Mapped:" + url + "," + method);
            }

        }

    }

    private void doAutowired() {

        if(ioc.isEmpty()){
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if(!field.isAnnotationPresent(GPAutowired.class)){
                    continue;
                }
                GPAutowired autowired = field.getAnnotation(GPAutowired.class);
                String beanName = autowired.value();
                if("".equals(beanName)){
                    beanName = field.getType().getName();
                }
                //授权注入
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }

            }

        }

    }

    private void doInstance() {
        if(classNames.isEmpty()){
            return;
        }
        try {
            for (String className : classNames){
                Class<?> clazz = Class.forName(className);
                //只有加了注解的类才实例化
                if(clazz.isAnnotationPresent(GPController.class)){
                    Object instance = clazz.newInstance();
                    //1.ioc每一个Bean都有一个UD，beanName
                    String beanName = lowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName, instance);
                }else if(clazz.isAnnotationPresent(GPService.class)){
                    //2.beanName默认首字母小写
                    //3.beanName可以自定义，优先采用自定义的值
                    GPService service = clazz.getAnnotation(GPService.class);
                    String beanName = service.value();
                    if("".equals(beanName.trim())){
                        beanName = lowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                    //4.这里用接口的全名作为key，实现类的实例作为值
                    //接口存入ioc中
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        ioc.put(i.getName(),instance);
                    }

                }else{
                    continue;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private String lowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/"+ scanPackage.replaceAll("\\.","/"));
        File classDir = new File(url.getFile());
        for(File file : classDir.listFiles()){
            if(file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            }else {
                String className = (scanPackage + "." + file.getName().replace(".class",""));
                classNames.add(className);
            }
        }

    }

    private void doLoadConfig(String contextConfigLocation) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream is = classLoader.getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

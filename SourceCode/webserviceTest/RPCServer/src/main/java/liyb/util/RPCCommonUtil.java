package liyb.util;

import liyb.anno.Liyb;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RPCCommonUtil {

    public static void daScan(String packageName){
        String classPath = RPCCommonUtil.class.getResource("/").getPath();
        String packagePath = packageName.replace(".","//");
        classPath += packagePath;
        File file = new File(classPath);
        String names[] = file.list();

        for (String name : names) {
            //通过反射拿到类对象
            try {
                name = name.replaceAll(".class","");
                Class clazz = Class.forName(packageName+"."+name);
                if(clazz.isAnnotationPresent(Liyb.class)){
                    String clazzName = clazz.getName();
                    Map<String, List<Map<String, String>>> map = new HashMap<>();

                    Map<String, Object> methodInfo = new HashMap<>();

                    Method methods[] = clazz.getDeclaredMethods();
                    for (Method method : methods) {

                        methodInfo.put("mname",method.getName());

                        Class params[] = method.getParameterTypes();
                        for (Class param : params) {
                            System.out.println(param.getSimpleName());
                        }

                    }

                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        RPCCommonUtil.daScan("liyb.service");
    }

}

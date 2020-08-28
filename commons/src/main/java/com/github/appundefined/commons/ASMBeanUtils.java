package com.github.appundefined.commons;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ls
 */
@SuppressWarnings("Duplicates")
public class ASMBeanUtils {
    private static Map<Class, MethodAccess> methodMap = new HashMap<Class, MethodAccess>();
    private static Map<String, Integer> methodIndexMap = new HashMap<String, Integer>();

    private static Map<Class, List<String>> fieldMap = new HashMap<Class, List<String>>();
    /**
     *
     * @param desc 目标对象
     * @param orgi 需要修改的属性
     */
    public static void copyProperties(Object desc, Object orgi) {
        MethodAccess descMethodAccess = methodMap.get(desc.getClass());
        if (descMethodAccess == null) {
            descMethodAccess = cache(desc);
        }
        MethodAccess orgiMethodAccess = methodMap.get(orgi.getClass());
        if (orgiMethodAccess == null) {
            orgiMethodAccess = cache(orgi);
        }
        List<String> fieldList = fieldMap.get(orgi.getClass());
        for (String field : fieldList) {
            String getKey = orgi.getClass().getName() + "." + "get" + field;
            String setkey = desc.getClass().getName() + "." + "set" + field;
            Integer setIndex = methodIndexMap.get(setkey);
            if (setIndex != null) {
                int getIndex = methodIndexMap.get(getKey);
                // 参数一需要反射的对象
                // 参数二class.getDeclaredMethods 对应方法的index
                // 参数对三象集合
                //这里这里
                Object invoke = orgiMethodAccess.invoke(orgi, getIndex);
                if(invoke!=null) {
                    descMethodAccess.invoke(desc, setIndex.intValue(),orgiMethodAccess.invoke(orgi, getIndex));
                }
            }
        }
    }

    // 单例模式

    private static MethodAccess cache(Object orgi) {
        synchronized (orgi.getClass()) {
            MethodAccess methodAccess = MethodAccess.get(orgi.getClass());
            Field[] fields = orgi.getClass().getDeclaredFields();
            Class<?> superclass = orgi.getClass().getSuperclass();
            Field[] fields2 = superclass.getDeclaredFields();
            List<String> fieldList = new ArrayList<String>(fields.length+fields2.length);
            for (Field field : fields) {
                // 是否是私有的，是否是静态的
                if (Modifier.isPublic(field.getModifiers())||Modifier.isPrivate(field.getModifiers())&& !Modifier.isStatic(field.getModifiers())) {
                    // 非公共私有变量
                    // 获取属性名称
                    String fieldName = StringUtils.capitalize(field.getName());
                    if(fieldName.equals("Size")){continue;}
                    // 获取get方法的下标
                    int getIndex = methodAccess.getIndex("get" + fieldName);
                    // 获取set方法的下标
                    int setIndex = methodAccess.getIndex("set" + fieldName);
                    // 将类名get方法名，方法下标注册到map中
                    methodIndexMap.put(orgi.getClass().getName() + "." + "get"
                            + fieldName, getIndex);
                    // 将类名set方法名，方法下标注册到map中
                    methodIndexMap.put(orgi.getClass().getName() + "." + "set"
                            + fieldName, setIndex);
                    // 将属性名称放入集合里
                    fieldList.add(fieldName);
                }
            }
            if(fields2.length>0) {
                for (Field field : fields2) {
                    // 是否是私有的，是否是静态的
                    if (Modifier.isPublic(field.getModifiers()) || Modifier.isPrivate(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
                        // 非公共私有变量
                        // 获取属性名称
                        String fieldName = StringUtils.capitalize(field.getName());
                        if (fieldName.equals("Size")) {continue;}
                        // 获取get方法的下标
                        int getIndex = methodAccess.getIndex("get" + fieldName);
                        // 获取set方法的下标
                        int setIndex = methodAccess.getIndex("set" + fieldName);
                        // 将类名get方法名，方法下标注册到map中
                        methodIndexMap.put(orgi.getClass().getName() + "." + "get"
                                + fieldName, getIndex);
                        // 将类名set方法名，方法下标注册到map中
                        methodIndexMap.put(orgi.getClass().getName() + "." + "set"
                                + fieldName, setIndex);
                        // 将属性名称放入集合里
                        fieldList.add(fieldName);
                    }
                }
            }
            // 将类名，属性名称注册到map中
            fieldMap.put(orgi.getClass(), fieldList);
            methodMap.put(orgi.getClass(), methodAccess);
            return methodAccess;
        }
    }

}

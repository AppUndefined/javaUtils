package com.github.appundefined.annotation;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassFiledsUtils {
    public static List<String> getFileds(Object newInstance) {
        ArrayList<String> strings = new ArrayList<>();
        Field[] fields = newInstance.getClass().getDeclaredFields();
        for (Field field : fields) {
            String s = field.getType().toString();
            if(s.equals("class java.lang.String")){
                strings.add(field.getName());
            }
        }
        return strings;
    }

    /**
     * 获取map key为对象nameValue 为对象Filed
     * @param newInstance
     * @return
     */
    public static HashMap<String, Field> getFiledsMap(Object newInstance) {
        HashMap<String, Field> result = new HashMap<>();
        Field[] fields = newInstance.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            field.setAccessible(true);
            String s = field.getType().toString();
            if(s.equals("class java.lang.String")){
                result.put(field.getName(),field);
            }
        }
        Field[] declaredFields = newInstance.getClass().getSuperclass().getDeclaredFields();
        for (Field field : declaredFields)
        {
            field.setAccessible(true);
            String s = field.getType().toString();
            if(s.equals("class java.lang.String")){
                result.put(field.getName(),field);
            }
        }
        return result;
    }

}

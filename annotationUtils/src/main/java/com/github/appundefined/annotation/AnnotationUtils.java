package com.github.appundefined.annotation;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class AnnotationUtils implements LruInterface {
    /**
     * 缓存
     */
    private static Map<Object, Field[]> objectFields =  new HashMap<Object, Field[]>();
    /**
     * LRU算法
     */
    private static LruCache lruCache =  new LruCache(100);

    /**
     * 反射获取对象指定属性值
     * @param t
     * @param key
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    public static   <T>  Object getValue(T t,String key) throws IllegalAccessException {
        if(key==null||"".equals(key)){
            return null;
        }
        if(t==null){
            return null;
        }
        Field[] fields = getFields(t);
        for (Field field : fields) {
                if(key.equals(field.getName())){
                    Object o = field.get(t);
                    return o;
            }
        }
        return null;
    }

    /**
     * 反射获取对象属性与属性值对应的map
     * @param t
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    public static   <T> Map getMap(T t) throws IllegalAccessException {
        HashMap<String, Object> result = new HashMap<>();
        if(t==null){
            return null;
        }
        Field[] fields = getFields(t);
        for (Field field : fields) {
            result.put(field.getName(),field.get(t));
            return result;
        }
        return null;
    }
    /**
     * 反射获取类指定属性上的指定注解值
     * @param cla 指定一个类
     * @param annotationCla 该类上的注解的字节码对象
     * @param param 指定类的属性
     * @param annotationParam 指定注解的属性
     * @return
     * @throws IllegalAccessException
     */
    public static     Object getAnnotationValue(Class cla,Class annotationCla,String param,String annotationParam) throws IllegalAccessException, NoSuchFieldException {
        if(cla==null||annotationCla==null||param==null||annotationParam==null){
            return null;
        }
        Field[] fields = getFields(cla);
        for (Field field : fields) {
            if (field.getName().equals(param)) {
                Annotation annotation = field.getAnnotation(annotationCla);
                if (annotation != null) {
                    InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
                    Field memberValues = invocationHandler.getClass().getDeclaredField("memberValues");
                    memberValues.setAccessible(true);
                    Map map = (Map)memberValues.get(invocationHandler);
                    return map.get(annotationParam);
                }
            }
        }
        return null;
    }
    /**
     * 反射获取类属性上的所有注解值
     * @param cla 指定一个类
     * @param annotationCla 该类上的注解的字节码对象
     * @return
     * @throws IllegalAccessException
     */
    public static   HashMap getAnnotationValue(Class cla,Class annotationCla) throws IllegalAccessException, NoSuchFieldException {
        if(cla==null||annotationCla==null){
            return null;
        }
        Field[] fields = getFields(cla);
        HashMap<String, Object> result = new HashMap<>();
        for (Field field : fields) {
                Annotation annotation = field.getAnnotation(annotationCla);
                if (annotation != null) {
                    InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
                    Field memberValues = invocationHandler.getClass().getDeclaredField("memberValues");
                    memberValues.setAccessible(true);
                    Map map = (Map)memberValues.get(invocationHandler);
                    result.put(field.getName(),map);
                }
            }
        return result;
    }


    /**
     * 如果缓存中存在改对象的Field则直接返回
     * @param t
     * @param <T>
     * @return
     */
    private static <T> Field[] getFields(T t) {
        if(objectFields.get(t.getClass())!=null){
            lruCache.add(t.getClass(),new AnnotationUtils());
            return objectFields.get(t.getClass());
        }else {
            Field[] declaredFields = t.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
            }
            objectFields.put(t.getClass(),declaredFields);
            lruCache.add(t.getClass(),new AnnotationUtils());
            return declaredFields;
        }

    }
    /**
     * 如果缓存中存在改对象的Field则直接返回
     * @param cla
     * @param <T>
     * @return
     */
    private static <T> Field[] getFields(Class cla) {
        if(objectFields.get(cla)!=null){
            lruCache.add(cla,new AnnotationUtils());
            return objectFields.get(cla);
        }else {
            Field[] declaredFields = cla.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
            }
            objectFields.put(cla,declaredFields);
            lruCache.add(cla,new AnnotationUtils());
            return declaredFields;
        }

    }
    @Override
    public void remove(Object object) {
        System.out.println("****************************************************");
        objectFields.forEach((x,y)->{
            System.out.println(x.toString()+"=========="+y.toString());
        });
        objectFields.remove(object);
        System.out.println("---------------------------------------------------");
        objectFields.forEach((x,y)->{
            System.out.println(x.toString()+"=========="+y.toString());
        });
        System.out.println("****************************************************");
    }
}

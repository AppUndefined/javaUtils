package com.github.appundefined.annotation;


import java.lang.reflect.Field;
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

package com.github.appundefined.tree;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeUtils {
    /**
     * 根节点值/Root node value
     */
    static String rootNode = "0";
    /**
     * String class路径
     */
    static String stringType = "class java.lang.String";
    /**
     * Long class路径
     */
    static String longType = "class java.lang.Long";
    /**
     * Integer class路径
     */
    static String integerType = "class java.lang.Integer";
    /**
     * int
     */
    static String intType = "int";
    /**
     * 主键编号/Primary key id
     */
    static String id = "id";
    /**
     * 父节点ID/Parent node id
     */
    static String pid = "pid";
    /**
     * 子节点集合/Child node collection
     */
    static String children = "children";

    /**
     * 将列表转换为列表树/Convert List to List Tree
     * @param trees t
     * @param <T> t
     * @return result
     * @throws IllegalAccessException result
     */
    public  static   <T>  List<T>   ListToTree(List<T> trees) throws IllegalAccessException {
         //Root node collection
        List<T> rootList = new ArrayList<T>();
        HashMap<Object, List<T>> pidAndTrees= new HashMap<>();
        for (final T t : trees) {
            Object value = getValue(t, pid);
            if(value!=null){
                if(pidAndTrees.get(value)!=null) {
                    pidAndTrees.get(value).add(t);
                }else{
                    pidAndTrees.put(value,new ArrayList<T>(){{this.add(t);}});
                }
                Object type = getType(t, pid);
                if (stringType.equals(type)) {
                    if (rootNode.equals(value)) {
                        rootList.add(t);
                    }
                } else if (longType.equals(type)) {
                    if (((Long)Long.parseLong(rootNode)).equals(value)) {
                        rootList.add(t);
                    }
                }else if (integerType.equals(type)) {
                    if (((Integer)Integer.parseInt(rootNode)).equals(value)) {
                        rootList.add(t);
                    }
                }
                else if (intType.equals(type)) {
                    if (((Integer)Integer.parseInt(rootNode)).equals(value)) {
                        rootList.add(t);
                    }
                }

            }
        }
        buildChilTree(rootList,pidAndTrees);
        return rootList;
    }

    /**
     * 获取与对象的指定注释值对应的字段值
     * Get the field value corresponding to the specified annotation value of the object
     * @param t
     * @param key
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    private static   <T>  Object getValue(T t,String key) throws IllegalAccessException {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            TreeElement treeElement = field.getAnnotation(TreeElement.class);
            if(treeElement!=null){
                String name = treeElement.name();
                if(key.equals(name)){
                    Object o = field.get(t);
                    return o;
                }
            }
        }
        return null;
    }
    /**
     * 获取对象的指定字段对应的字段类型
     * Get the field type corresponding to the specified field of the object
     * @param t
     * @param key
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    public static   <T>  Object getType(T t,String key) throws IllegalAccessException {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (key.equals(field.getName())) {
                return field.getType().toString();
            }
        }
        return null;
    }

    /**
     * 递归构造/Recursive construction
     * @param currentTrees
     * @param trees
     * @param <T>
     * @throws IllegalAccessException
     */
    private static  <T> void  buildChilTree(List<T> currentTrees,    HashMap<Object, List<T>>  trees) throws IllegalAccessException {
        for (T t : currentTrees) {
            Object currentId = getValue(t, id);
            //Data exists with current id as pid
            if(trees.get(currentId)!=null){
                List list = (List) getValue(t, children);
                list.addAll(trees.get(currentId));
                buildChilTree(trees.get(currentId),trees);
            }
        }

    }

}

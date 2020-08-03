import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class TreeUtils {
    /**
     * 根节点数值
     */
    static String rootNode = "0";
    /**
     * 主键id
     */
    static String id = "id";
    /**
     * 父节点id
     */
    static String pid = "pid";
    /**
     * 子节点集合
     */
    static String children = "children";
    public  static   <T>  List<T>   ListToTree(List<T> trees) throws IllegalAccessException {
         //根节点集合
        List<T> rootList = new ArrayList<T>();
        HashMap<Object, List<T>> pidAndTrees= new HashMap<>();
        for (T t : trees) {
            Object value = getValue(t, pid);
            if(value!=null){
                if(pidAndTrees.get(value)!=null) {
                    pidAndTrees.get(value).add(t);
                }else{
                    pidAndTrees.put(value,new ArrayList<T>(){{this.add(t);}});
                }
                if(rootNode.equals(value)) {
                    rootList.add(t);
                }
            }
        }
        buildChilTree(rootList,pidAndTrees);
        return rootList;
    }

    /**
     * 获取对象指定注解值对应的字段值
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
    private static  <T> void  buildChilTree(List<T> currentTrees,    HashMap<Object, List<T>>  trees) throws IllegalAccessException {
        for (T t : currentTrees) {
            Object currentId = getValue(t, id);
            //以当前id为pid的数据存在
            if(trees.get(currentId)!=null){
                List list = (List) getValue(t, children);
                list.addAll(trees.get(currentId));
                buildChilTree(trees.get(currentId),trees);
            }
        }

    }

}

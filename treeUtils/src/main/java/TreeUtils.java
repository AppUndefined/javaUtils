import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeUtils {
    /**
     * Root node value
     */
    static String rootNode = "0";
    /**
     * Primary key id
     */
    static String id = "id";
    /**
     * Parent node id
     */
    static String pid = "pid";
    /**
     * Child node collection
     */
    static String children = "children";

    /**
     * Convert List to List Tree
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
                if(rootNode.equals(value)) {
                    rootList.add(t);
                }
            }
        }
        buildChilTree(rootList,pidAndTrees);
        return rootList;
    }

    /**
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
     * Recursive construction
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

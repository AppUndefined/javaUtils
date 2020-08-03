import java.lang.annotation.*;

/**
 * @program: javaUtils
 * @description: 树结构注解
 * @author: AppUndefined(LS)
 * @create: 2020-08-03 15:41
 **/
@Target(value= {ElementType.FIELD})
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface TreeElement {
    String name();
}

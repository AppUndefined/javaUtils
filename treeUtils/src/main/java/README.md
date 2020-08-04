#使用步骤：
##1. 添加依赖
        <dependency></br>
          <groupId>com.github.appundefined</groupId>
         <artifactId>treeUtils</artifactId>
         <version>1.0-RELEASE</version>
        </dependency>
##2. 在需要转换为树结构的对象字段上添加核心注解3个<br>
@TreeElement(name = "id")          
private String id;                 
@TreeElement(name = "pid")         
private String pid;                            
@TreeElement(name = "children")   
private List<Object> children = new ArrayList<>();（注：没有List集合需要添加一个）
##3. 调用
List trees = TreeUtils.ListToTree(objects);


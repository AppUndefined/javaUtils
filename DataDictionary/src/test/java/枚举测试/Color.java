package 枚举测试;

public enum Color {
    READ("红色","1"),ORANGE("橙色","2"),YELLOW("黄色","3"),GREEN("绿色","4");
    public String name;
    public String index;
    Color(String name, String index) {
        this.name = name;
        this.index = index;
    }
}

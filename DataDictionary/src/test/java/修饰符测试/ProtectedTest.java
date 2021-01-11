package 修饰符测试;

import lombok.Data;

@Data
public class ProtectedTest {
    protected String name;
    protected String phone;

    public ProtectedTest(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public ProtectedTest() {
    }

    public ProtectedTest(String name) {
        this.name = name;
    }

}

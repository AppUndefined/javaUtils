package 修饰符测试;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class DefaultTest implements Externalizable {
    String name;
    Integer a;
    char[] arr;


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Object o = in.readObject();
    }
}

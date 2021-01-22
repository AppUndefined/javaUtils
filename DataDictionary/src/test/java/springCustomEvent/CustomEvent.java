package springCustomEvent;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {
    public CustomEvent(Object source) {
        super(source);
    }
    public void event() {
        System.out.println("自定义event");
    }
}

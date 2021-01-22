package springCustomEvent;

import org.springframework.context.ApplicationListener;

public class CustomListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent customEvent) {
        customEvent.event();
    }
}

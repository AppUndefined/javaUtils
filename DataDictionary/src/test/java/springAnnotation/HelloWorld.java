package springAnnotation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class HelloWorld {
   private String message;
   public void setMessage(String message){
      this.message  = message;
   }
   public void getMessage(){
      System.out.println("Your Message : " + message);
   }

   @PreDestroy
   public void destroy() throws Exception {
      System.out.printf("bean销毁");
   }

   @PostConstruct
   public void afterPropertiesSet() throws Exception {
      System.out.printf("bean初始化");
   }
}
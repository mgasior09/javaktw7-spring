package pl.sdacademy.spring.car_dealer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        Application application = applicationContext.getBean("application", Application.class);
        application.start();
        applicationContext.close();
    }
}
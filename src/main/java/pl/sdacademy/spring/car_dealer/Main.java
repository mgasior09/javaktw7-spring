package pl.sdacademy.spring.car_dealer;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Application application = applicationContext.getBean("application", Application.class);
        application.start();
        applicationContext.close();
    }
}

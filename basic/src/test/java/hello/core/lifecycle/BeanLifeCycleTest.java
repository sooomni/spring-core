package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleTest.class);
        NetworkClient networkClient = ac.getBean(NetworkClient.class);
        ac.close();
    }
    @Configuration
    static class LifeCycleTest {
        @Bean
        //(initMethod = "init" ㅇestroyMethod = "close") -> 없어도 동작하는건 추론기능
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://naver.com");
            return networkClient;
        }
    }
}

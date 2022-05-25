package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class prototypeTest {
    @Test
    public void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        ac.close();
    }
    @Test
    public void singletonClientUserPrototype(){

        //주입 받는 시점에 각각 새로운 프로토타입 빈이 생성된다.
        //ClientBean이 singleton이기 때문에 생성 시점에만 의존관계를 주입받고,
        //PrototypeBean이 prototype이더라도 새로 생성되긴 하지만 싱글톤 빈과 함께 첫 의존관계 시 생성된 주기가 유지된다.

        //사실 PrototypeBean을 사용하려는 것은 PrototypeBean을 "사용할 때마다 새로 생성"하려는 것인데,
        //singleton과 함께 사용하면 싱글톤 주기에 맞게 유지되는 문제가 있다.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        assertThat(count2).isEqualTo(2);
    }


    @Scope("singleton")
    static class ClientBean{
        //private final PrototypeBean prototypeBean; //생성 시점에 주입

        @Autowired
        ApplicationContext applicationContext;

//        public ClientBean(PrototypeBean prototypeBean){
//            this.prototypeBean = prototypeBean;
//        }

        public int logic(){
            //아래처럼 하면 logic 호출 시마다 스프링 컨테이너에 PrototypeBean 생성해달라고 하는 것과 같다.
            //logic 호출 시마다 prototypeBean이 생성되기 때문에 count를 유지되게 사용할 수 없다.
            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count;

        public int getCount() {
            return count;
        }

        public void addCount() {
            count++;
        }
        @PostConstruct
        public void init(){
            System.out.println("init");
        }
        
        @PreDestroy
        public void destroy(){
            System.out.println("destroy");
        }
    }
}

package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }
    //임의의 스프링 빈 하나 생성
    static class TestBean{
        //@Autowired required false
        //의존관계가 없으면(자동 주입 대상이 없으면) 호출 자체가 되지 않음.
        @Autowired(required = false)
        public void setBean(Member noBean1){
            System.out.println("noBean1 "+noBean1);
        }

        //@Nullable
        @Autowired
        public void setBean2(@Nullable Member noBean2){
            System.out.println("noBean2 "+noBean2);
        }

        //@Optional
        @Autowired
        public void setBean3(Optional<Member> noBean3){
            System.out.println("noBean3 "+noBean3);
        }
    }
}

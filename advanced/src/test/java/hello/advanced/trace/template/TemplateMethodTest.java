package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0(){
        logic1();
        logic2();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();

        //비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime={}",resultTime);
    }

    private void logic2(){
        long startTime = System.currentTimeMillis();

        //비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime={}",resultTime);
    }

    /*
    * 템플릿 메서드 패턴 적용
    * */
    @Test
    void templateMethodV1(){
        AbstractTemplate templatet1 = new SubClassLogic1();
        templatet1.execute();
        AbstractTemplate templatet2 = new SubClassLogic2();
        templatet2.execute();
    }

    /*
     * 템플릿 메서드 패턴 적용
     * */
    @Test
    void templateMethodV2(){
        AbstractTemplate templatet1 = new AbstractTemplate(){
            @Override
            protected void call() {
                log.info("비즈니스 로직 실행1");
            }
        };
        log.info("클래스 이름={}",templatet1.getClass());
        templatet1.execute();

        AbstractTemplate templatet2 = new AbstractTemplate(){
            @Override
            protected void call() {
                log.info("비즈니스 로직 실행2");
            }
        };
        log.info("클래스 이름={}",templatet2.getClass());
        templatet2.execute();
    }
}

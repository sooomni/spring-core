package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    /* 이 빈의 scope이 http 요청이 들어와서 나갈 때 까지인데
     * http 요청 전에 스프링 컨테이너가 뜨는 시점에 의존관계 주입 하려고 해서 오류
     * ObjectProvider 사용하면 ObjectProvider.getObject() 사용하는 시점까지 request scope 빈 생성을 지연할 수 있다.
     */
    //private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;

    public void logic(String id) {
        //MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = "+ id);
    }
}

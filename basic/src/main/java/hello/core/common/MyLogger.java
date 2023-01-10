package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
/* 이 scope 선언으로 이 빈은 http 요청 당 하나씩 생성되고, Http 요청 끝나는 시점에 소멸
 * proxyMode 추가하면 MyLogger의 가짜 프록시 클래스를 만들어두고 Http request와 상관없이 가짜 프록시 클래스를 다른 빈에 미리 주입 가능
 * myLogger의 실제 기능 같은 걸 "사용하려고 접근할 때" 실제 객체 호출
 * 가짜 프록시 객체는 객체 내부에 진짜 객체를 찾는 방법을 알고 있다.(실제 요청이 오면 내부에 실제 빈을 요청하는 위임 로직이 있음)
 *
 * provider를 사용하든, 프록시 객체를 사용하든 핵심은 "진짜 객체 조회를 필요한 시점까지 지연처리 한다"는 것!
 * 어노테이션 설정만으로 원복 객체를 프록시 객체로 대체할 수 있음 -> 다형성, DI 컨테이너가 가진 장점
 */
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL){
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("["+uuid+"] "+"["+requestURL+"] "+message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] "+"request scope bean create : "+this);
    }

    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"] "+"request scope bean close : "+this);
    }
}


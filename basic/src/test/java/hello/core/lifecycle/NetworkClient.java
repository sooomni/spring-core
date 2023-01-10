package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient
//implements InitializingBean, DisposableBean
{
    private String url;


    public NetworkClient(){
        System.out.println("constructor 호출, url : "+url);
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public void connect(){
        System.out.println("connect url : "+url);
    }
    public void call(String msg){
        System.out.println("call : "+url+" messege : "+msg);
    }
    public void disconnect(){
        System.out.println("close : "+url);
    }
    @PostConstruct
    public void init() throws Exception {
        connect();
        call("초기화 연결");
    }
    @PreDestroy
    public void close() throws Exception {
        disconnect();
    }
}

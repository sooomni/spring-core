package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;


public abstract class AbstractTemplate<T> {
    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    //제네릭 : 타입에 대한 정보를 객체를 생성하는 시점으로 미룸
    public T execute(String message){
        TraceStatus status = null;
        try {
            status = trace.begin(message);
            //로직 호출
            T result = call();
            trace.end(status);
            return result;
        }catch (Exception e){
            trace.exception(status,e);
            throw e;
        }
    }
    protected abstract T call();
}

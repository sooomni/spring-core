package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//application에 실제 동작에 필요한 구현 객체를 생성한다.
//생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.

@Configuration
public class AppConfig {

    //DIP 완성 - MemberServiceImpl는 MemberRepository인 추상에만 의존하면 된다. 구체 클래스는 몰라도 된다.
    //appConfig 객체는 MemoryMemberRepository 객체를 생성하고 그 "참조값"을 MemberServiceImpl을 생성하면서 생성자로 전달한다.
    //클라이언트인 MemberServiceImpl 입장에서 보면 의존 관계를 마치 외부에서 주입해주는 것 같다고 해서 의존관계 주입, 의존성 주입이라고 한다.
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository()); //생성자 주입, memberRepository를 뺴서 따로 구현함으로서 memberRepository 역할을 더욱 명확하게
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(
                memberRepository()
                , discountPolicy()); //생성자 주입
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

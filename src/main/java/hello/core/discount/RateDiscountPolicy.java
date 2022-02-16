package hello.core.discount;

import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {
    @Override
    public int discount(Member meber, int price) {
        return 0;
    }
}

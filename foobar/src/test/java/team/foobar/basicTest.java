package team.foobar;

import org.junit.jupiter.api.Test;
import team.foobar.domain.Member;

import java.lang.reflect.Type;

public class basicTest {

    @Test
    void test() {
        Class<Member> memberClass = Member.class;
        System.out.println("memberClass.getName() = " + memberClass.getName());
        System.out.println("memberClass.getSimpleName() = " + memberClass.getSimpleName());
    }
}

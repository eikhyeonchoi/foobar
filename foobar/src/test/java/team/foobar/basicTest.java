package team.foobar;

import org.junit.jupiter.api.Test;
import team.foobar.domain.Member;

import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class basicTest {

    @Test
    void test() {
        Class<Member> memberClass = Member.class;
        System.out.println("memberClass.getName() = " + memberClass.getName());
        System.out.println("memberClass.getSimpleName() = " + memberClass.getSimpleName());
    }


    @Test
    void regexTest() {
        String s = "&lt;li&gt;content&lt;/li&gt;";
        String text = this.getText(s);
        System.out.println("text = " + text);
    }



    private String getText(String content) {
//        String text = "......";
//        String textWithoutTag = text.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");

        Pattern SCRIPTS = Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>",Pattern.DOTALL);
        Pattern STYLE = Pattern.compile("<style[^>]*>.*</style>",Pattern.DOTALL);
        Pattern TAGS = Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>");
        Pattern nTAGS = Pattern.compile("<\\w+\\s+[^<]*\\s*>");
        Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");
        Pattern WHITESPACE = Pattern.compile("\\s\\s+");

        Matcher m;

        m = SCRIPTS.matcher(content);
        content = m.replaceAll("");
        m = STYLE.matcher(content);
        content = m.replaceAll("");
        m = TAGS.matcher(content);
        content = m.replaceAll("");
        m = ENTITY_REFS.matcher(content);
        content = m.replaceAll("");
        m = WHITESPACE.matcher(content);
        content = m.replaceAll(" ");

        return content;
    }
}

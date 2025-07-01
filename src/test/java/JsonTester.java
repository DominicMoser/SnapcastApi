import com.dmoser.codyssey.bragi.snapcast.api.model.Server;
import com.dmoser.codyssey.bragi.snapcast.api.model.control.Command;
import com.dmoser.codyssey.bragi.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.bragi.snapcast.api.request.stream.Control;
import com.dmoser.codyssey.bragi.snapcast.api.service.UtilityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonTester {

    @Test
    void test() {
        A myA = new A();
        B b1 = new B();
        B b2 = new B();
        b1.t = "Hallo";
        b2.t = "Mars";
        myA.b = new ArrayList<>();
        myA.b.add(b1);
        myA.b.add(b2);

        myA.b.stream()
                .filter(b -> b.t.equals("Mars"))
                .forEach(b -> b.t = "Welt");

        myA.b.stream()
                .map(b -> b.t)
                .forEach(System.out::println);
    }

    @Test
    void testFullJson() throws IOException {
        ObjectMapper om = UtilityService.objectMapper();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("example.json");
        Server server = om.readValue(inputStream, Server.class);

        System.out.println(server.server.host().name());
    }

    @Test
    void testSomething() throws JsonProcessingException {
        ObjectMapper om = UtilityService.objectMapper();
        BaseRequest r = new Control.Request("swr3", Command.PLAY);
        System.out.println(om.writeValueAsString(r));
        r = new Control.Request("swr3", Command.PLAY);
        System.out.println(om.writeValueAsString(r));
    }

    class B {
        String t;
    }

    class A {
        List<B> b;
    }
}

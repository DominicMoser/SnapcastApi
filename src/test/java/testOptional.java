import com.dmoser.codyssey.bragi.snapcast.api.service.UtilityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class testOptional {


    @Test
    void testOptionalSerialization() throws JsonProcessingException {
        ObjectMapper om = UtilityService.objectMapper();
        abc abc = new abc("Hallo", Optional.of("Welt"));
        assertEquals("{\"a\":\"Hallo\",\"b\":\"Welt\"}", om.writeValueAsString(abc));
        abc = new abc("Hallo", Optional.empty());
        assertEquals("{\"a\":\"Hallo\"}", om.writeValueAsString(abc));
    }

    @Test
    void testOptionalDeserialization() throws JsonProcessingException {
        ObjectMapper om = UtilityService.objectMapper();
        String s1 = "{\"a\":\"Hallo\",\"b\":\"Welt\"}";
        String s2 = "{\"a\":\"Hallo\"}";
        abc o1 = new abc("Hallo", Optional.of("Welt"));
        abc o2 = new abc("Hallo", Optional.empty());

        assertEquals(om.readValue(s1, abc.class), o1);
        assertEquals(om.readValue(s2, abc.class), o2);
    }


}

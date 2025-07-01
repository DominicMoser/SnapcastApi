import com.dmoser.codyssey.bragi.snapcast.api.model.control.Command;
import com.dmoser.codyssey.bragi.snapcast.api.model.setProperty.PropertyType;
import com.dmoser.codyssey.bragi.snapcast.api.model.stream.properties.LoopStatus;
import com.dmoser.codyssey.bragi.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.bragi.snapcast.api.request.server.GetStatus;
import com.dmoser.codyssey.bragi.snapcast.api.request.stream.Control;
import com.dmoser.codyssey.bragi.snapcast.api.request.stream.SetProperty;
import com.dmoser.codyssey.bragi.snapcast.api.service.UtilityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD) // Optional, this is default
public class RequestTest {

    private static final ObjectMapper om = UtilityService.objectMapper();

    @Test
    void testRequestMethodGeneration() throws JsonProcessingException {
        BaseRequest<GetStatus.Params> testRequest1 = new GetStatus.Request();
        BaseRequest<Control.Params> testRequest2 = new Control.Request(null, null);
        assertTrue(testRequest1.id < testRequest2.id, "Id must increment!");
        assertEquals("2.0", testRequest1.jsonrpc, "Jsonrpc should be 2.0");
        assertNull(testRequest1.params);
        assertNotNull(testRequest2.params);

    }

    @Test
    void testStreamControlRequest() throws JsonProcessingException {
        BaseRequest testRequest = new Control.Request("test_id", Command.PLAY);
        var expected = om.readTree("{\"id\":" + testRequest.id + ",\"jsonrpc\":\"2.0\",\"method\":\"Stream.Control\"," +
                "\"params\":{\"id\":" +
                "\"test_id\", \"command\": \"play\"}}");
        var result = om.valueToTree(testRequest);
        assertEquals(expected, result);
    }

    @Test
    void testStreamSetPropertyRequest() throws JsonProcessingException {
        BaseRequest<SetProperty.Params> testRequest1 = new SetProperty.Request(
                "test_id",
                PropertyType.LOOP_STATUS,
                LoopStatus.NONE
        );
        BaseRequest<SetProperty.Params> testRequest2 = new SetProperty.Request(
                "test_id",
                PropertyType.RATE,
                10
        );
        
        var expected1 = om.readTree("{\"id\": " + testRequest1.id + ", \"jsonrpc\": \"2.0\", \"method\": \"Stream" +
                ".SetProperty\", " +
                "\"params\":" +
                " {\"id\": \"test_id\", \"property\": \"loopStatus\", \"value\": \"none\"}}");

        var expected2 = om.readTree("{\"id\": " + testRequest2.id + ", \"jsonrpc\": \"2.0\", \"method\": \"Stream" +
                ".SetProperty\", " +
                "\"params\":" +
                " {\"id\": \"test_id\", \"property\": \"rate\", \"value\": 10}}");

        var result1 = om.valueToTree(testRequest1);
        var result2 = om.valueToTree(testRequest2);

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertNotEquals(expected1, result2);

    }
}

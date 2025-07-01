package com.dmoser.codyssey.bragi.snapcast.api.service;

import com.dmoser.codyssey.bragi.snapcast.api.request.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.TreeMap;

public class ParsingService {

    // When a stream is added, there is no notification for that. Because of this we need to check the whole server
    // multiple times.

    static Map<Integer, Class<? extends Response>> awaitedResponses = new TreeMap<>();
    static Map<Integer, RequestParams> sendRequestHistory = new TreeMap<>();

    public static String parseRequestParams(BaseRequest<?> request) {
        sendRequestHistory.put(request.id, request.params);
        awaitedResponses.put(request.id, request.expectedResponse());

        ObjectMapper om = UtilityService.objectMapper();
        try {
            return om.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public static processable parseMsg(String msg) throws JsonProcessingException {
        ObjectMapper om = UtilityService.objectMapper();
        JsonNode messageNode = om.readTree(msg);
        if (messageNode.has("id")) {
            return parseResponse(messageNode);
        }
        if (messageNode.has("method")) {
            return parseNotification(messageNode);
        }
        return state -> {

        };
        //TODO What else
    }

    private static processable parseNotification(JsonNode notificationNode) throws JsonProcessingException {
        //TODO Method not found
        ObjectMapper om = UtilityService.objectMapper();
        NotificationMethod nM = NotificationMethod.fromMethod(notificationNode.get("method").asText());
        Notification notification = om.treeToValue(notificationNode, nM.notificationClass);
        return notification;
    }

    /**
     * Processes an incoming response message by performing the following steps:
     * 1. Determines the expected response type using the awaitedResponses map and the message's id field.
     * 2. Parses the JSON response into the expected class type.
     * 3. Retrieves and removes the corresponding request parameters from the appropriate map.
     * 4. Removes the expected response type from the awaitedResponses map.
     * 5. Calls the process() method on the parsed response object.
     *
     * @param responseNode the incoming response message as a JSON node
     * @throws JsonProcessingException if the message cannot be parsed
     */
    private static processable parseResponse(JsonNode responseNode) throws JsonProcessingException {
        //TODO Handle error responses
        ObjectMapper om = UtilityService.objectMapper();
        Integer responseId = responseNode.get("id").asInt();
        //Check if response is awaited
        Response response = om.treeToValue(responseNode, awaitedResponses.get(responseId));
        awaitedResponses.remove(responseId);
        RequestParams requestParams = sendRequestHistory.get(responseId);
        sendRequestHistory.remove(responseId);
        response.addRequestParams(requestParams);
        return response;
    }

}

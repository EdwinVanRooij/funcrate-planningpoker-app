package com.funcrate.funcrateplanningpoker;

import java.io.IOException;


/**
 * Author: eddy
 * Date: 17-1-17.
 */

public class Event {
    private final String name;
    private final JsonNode data;

    /**
     * Default constructor
     *
     * @param name name of the event
     * @param data Data in jsonnode format provided by the jackson library
     */
    public Event(String name, JsonNode data) {
        this.name = name;
        this.data = data;
    }

    /**
     * Returns the name that was sent previous to the ';' identifier
     *
     * @return Returns the name in String format
     */
    public String getName() {
        return name;
    }

    /**
     * Data associated with this event in jackson JsonNode format
     *
     * @return data in JsonNode format
     */
    public JsonNode getData() {
        return data;
    }

    /**
     * Inputs the raw event message and returns the associated event.
     *
     * @param message the raw event message
     * @return returns the event object
     */
    public static Event deserialize(String message) {
        int idx = message.indexOf(';');
        String eventName = message.substring(0, idx);
        String data = message.substring(idx + 1);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(data);
            return new Event(eventName, json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


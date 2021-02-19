package com.TechMojo.Top10TwitterHashtags.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Errors {
    public static final String SAVING_ERR_001 ="SAVING_ERR_001";
    public static final String FETCHING_ERR_001 ="FETCHING_ERR_001";


    public static final Map<String,String> messages = Collections.unmodifiableMap(initMap());

    private Errors(){

    }

    private static Map<String,String> initMap(){
        Map<String, String> messages = new HashMap<>();
        messages.put(SAVING_ERR_001, "Unable to post the tweet");
        messages.put(FETCHING_ERR_001, "Unable to fetch trending tweets");
        return messages;
    }
}

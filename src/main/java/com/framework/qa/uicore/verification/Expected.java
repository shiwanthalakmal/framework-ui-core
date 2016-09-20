package com.framework.qa.uicore.verification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Expected.java - class to handle Expected result
 * <p/>
 * Keeps track of expected element values to be compared with actual result element values
 * <p/>
 * key, value pairs would be in following format
 * txtUsername, Administrator
 *
 * @version 1.0-SNAPSHOT Last modified on 05_27_2016
 * @since 05/27/2016
 */
public class Expected {
    private static final Logger LOGGER = LoggerFactory.getLogger(Expected.class);

    private HashMap<String, Object> results = null;

    public Expected() {
        results = new HashMap<String, Object>();
    }

    public void setExpectedResult(String key, Object value) {
        results.put(key, value);
    }

    public void clearAndSetExpectedResult(String key, Object value) {
        results.clear();
        results.put(key, value);
    }

    public Map<String, Object> getResults() {
        return results;
    }

}



package com.framework.qa.uicore.verification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Actual.java - class to handle Actual result
 * <p/>
 * Keeps track of actual element values to be compared with expected result element values
 * <p/>
 * key, value pairs would be in following format
 * txtUsername, Administrator
 *
 * @version 1.0-SNAPSHOT Last modified on 05_27_2016
 * @since 05/27/2016
 */
public class Actual {
    private static final Logger LOGGER = LoggerFactory.getLogger(Actual.class);

    private HashMap<String, Object> results = null;

    public Actual() {
        results = new HashMap<String, Object>();
    }

    public void setActualResult(String key, Object value) {

        results.put(key, value);
    }

    public void clearAndSetActualResult(String key, Object value) {
        results.clear();
        results.put(key, value);
    }

    public Map<String, Object> getResults() {

        return results;
    }
}


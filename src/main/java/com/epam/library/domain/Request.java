package com.epam.library.domain;


import java.util.HashMap;
import java.util.Map;

public class Request {
    private static Map<String, Object> parameters = new HashMap<String, Object>();

    public Object getParameter(String key) {
        return parameters.get(key);
    }

    public void setParameter(String key, Object object) {
        parameters.put(key, object);
    }

}

package com.digdes.school;

import com.digdes.school.parser.DataParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class JavaSchoolStarter {
    private List<Map<String, Object>> data = new ArrayList<>();

    public List<Map<String, Object>> getData() {
        return data;
    }

    public JavaSchoolStarter() {
    }

    public DataParser parser = new DataParser();

    public List<Map<String, Object>> execute(String request) {


        if (request.matches("(?i).*insert.*")) {
            data = parser.requestInjection(request);
        } else if (request.matches("(?i).*update.*")) {
            data = parser.requestInjection(request);
        } else if (request.matches("(?i).*delete.*")) {
            data = parser.requestInjection(request);
        } else if (request.matches("(?i).*select.*")) {
            System.out.println(parser.requestInjection(request));

        }
        return getData();
    }


}

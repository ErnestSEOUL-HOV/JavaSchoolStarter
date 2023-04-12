package com.digdes.school;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        JavaSchoolStarter starter = new JavaSchoolStarter();

        try {

            List<Map<String, Object>> result1 = starter.execute("INSERT VALUES 'lastName' = 'Петров' , 'id'=13, 'age'=28, 'active'=true");
            List<Map<String, Object>> result2 = starter.execute("INSERT VALUES 'lastName' = 'Смирнов' , 'id'=212, 'age'=21, 'active'=false, 'cost' = 2.3");
            List<Map<String,Object>> result4 = starter.execute("DELETE 'lastName' WHERE 'id' > 2");
            List<Map<String,Object>> result5 = starter.execute("DELETE WHERE 'id' = 21");
            List<Map<String,Object>> result6 = starter.execute("UPDATE VALUES 'age' = 30 where 'id' = 13");
            List<Map<String, Object>> result8 = starter.execute("SELECT");


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}



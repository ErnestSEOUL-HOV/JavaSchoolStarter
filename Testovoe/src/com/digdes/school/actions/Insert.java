package com.digdes.school.actions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Insert {
    public Map<String, Object> functionInsert(String[] values) {
       //Наш map заполняется заданными значениями key, value заполняется null;
        Map<String, Object> row = new HashMap<>();
        row.put("id", null);
        row.put("lastName", null);
        row.put("age", null);
        row.put("cost", null);
        row.put("active", null);

        Iterator<Map.Entry<String, Object>> itr = row.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Object> entry = itr.next();
            //С помощью итератора entrySet получается ключ из Map, для сравнения с ключом запрос,
            //которму необходимо присвоить значение
                for (int i = 0; i < values.length; i++) {
                    if (values[i].equalsIgnoreCase(entry.getKey())) {
                        String value = values[i + 1];
                        row.put(entry.getKey(), value);
                        break;
                    }
                }
        }
        return row;
    }
}

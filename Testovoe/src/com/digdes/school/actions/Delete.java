package com.digdes.school.actions;


import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Delete {
    public List<Map<String, Object>> functionDelete(List<Map<String, Object>> data, String[] dataValues, String[] condition) {
        if (dataValues == null && condition == null) {
            return new ArrayList<>();
        } else if (dataValues == null && condition.length == 1) {
            return new ArrayList<>();
        } else if (dataValues == null && condition.length == 3) {
            String key = condition[0];
            String operator = condition[1];
            String valueOfKey = condition[2];
            try {
                data.removeIf(dataRow -> verificationData(dataRow.get(key), operator, valueOfKey));
            } catch (Exception e) {
                e.getMessage();
            }
        } else if (dataValues.length == 1) {
            String key = condition[0];
            String operator = condition[1];
            String valueOfKey = condition[2];

            for (Map<String, Object> dataRow : data) {
                if (verificationData(dataRow.get(key), operator, valueOfKey)) {
                    deleteDataRow(dataRow, dataValues);
                }
            }
        }
        return data;
    }
    //Проверка данных с оператором
    private boolean verificationData(Object key, String operator, String valueOfKey) {
        Double val = null;
        Double val1 = null;
        Long value = null;
        Long value1 = null;
        switch (operator) {
            case ("=") -> {
                return key.equals(valueOfKey);
            }
            case ("!=") -> {
                return !key.equals(valueOfKey);
            }
            case (">") -> {
                if (String.valueOf(key).contains(".")) {
                    val = Double.parseDouble(String.valueOf(key));
                } else {
                    value = Long.parseLong(String.valueOf(key));
                }
                if (valueOfKey.contains(".")) {
                    val1 = Double.parseDouble(valueOfKey);
                } else {
                    value1 = Long.parseLong(valueOfKey);
                }
                if (val == null && val1 == null)
                    return value > value1;
                if (value == null && value1 == null)
                    return val > val1;
                if (value == null && val1 == null)
                    return val > value1;
                if (val == null && value1 == null)
                    return value > val1;
            }
            case (">=") -> {
                if (String.valueOf(key).contains(".")) {
                    val = Double.parseDouble(String.valueOf(key));
                } else {
                    value = Long.parseLong(String.valueOf(key));
                }
                if (valueOfKey.contains(".")) {
                    val1 = Double.parseDouble(valueOfKey);
                } else {
                    value1 = Long.parseLong(valueOfKey);
                }
                if (val == null && val1 == null)
                    return value >= value1;
                if (value == null && value1 == null)
                    return val >= val1;
                if (value == null && val1 == null)
                    return val >= value1;
                if (val == null && value1 == null)
                    return value >= val1;
            }
            case ("<") -> {

                if (val == null && val1 == null)
                    return value < value1;
                if (value == null && value1 == null)
                    return val < val1;
                if (value == null && val1 == null)
                    return val < value1;
                if (val == null && value1 == null)
                    return value < val1;
            }
            case ("<=") -> {
                if (String.valueOf(key).contains(".")) {
                    val = Double.parseDouble(String.valueOf(key));
                } else {
                    value = Long.parseLong(String.valueOf(key));
                }
                if (valueOfKey.contains(".")) {
                    val1 = Double.parseDouble(valueOfKey);
                } else {
                    value1 = Long.parseLong(valueOfKey);
                }
                if (val == null && val1 == null)
                    return value <= value1;
                if (value == null && value1 == null)
                    return val <= val1;
                if (value == null && val1 == null)
                    return val <= value1;
                if (val == null && value1 == null)
                    return value <= val1;
            }
        }
        return true;
    }

    private static void deleteDataRow(Map<String, Object> map, String[] values) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            for (String value : values) {
                if (value.equalsIgnoreCase(entry.getKey())) {
                    map.put(entry.getKey(), null);
                    break;
                }
            }
        }
    }
}
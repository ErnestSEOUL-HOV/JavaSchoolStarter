package com.digdes.school.actions;

import java.util.*;
public class Update {

    public List<Map<String, Object>> functionUpdate(List<Map<String, Object>> data, String[] dataValues, String[] condition) {

        if (condition.length == 1) {
            for (Map<String, Object> dataRow : data) {
                updateDataRow(dataRow, dataValues);
            }
            return data;
        }

        String key = condition[0];
        String operator = condition[1];
        String valueOfKey = condition[2];
        if (condition.length < 4) {
            for (Map<String, Object> dataRow : data) {
                if (verificationData(dataRow.get(key), operator, valueOfKey)) {
                    updateDataRow(dataRow, dataValues);
                }
            }
        } else {
            String key1 = condition[4];
            String operator1 = condition[5];
            String valueOfKey1 = condition[6];
            if (Arrays.toString(condition).toLowerCase().contains("or")) {
                for (Map<String, Object> dataRow : data) {
                    if (verificationData(dataRow.get(key), operator, valueOfKey) || verificationData(dataRow.get(key1), operator1, valueOfKey1)) {
                        updateDataRow(dataRow, dataValues);
                    }
                }
            } else if (Arrays.toString(condition).toLowerCase().contains("and")) {
                for (Map<String, Object> dataRow : data) {
                    if (verificationData(dataRow.get(key), operator, valueOfKey) && verificationData(dataRow.get(key1), operator1, valueOfKey1)) {
                        updateDataRow(dataRow, dataValues);
                    }
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

    private static void updateDataRow(Map<String, Object> dataRow, String[] dataValues) {
        Iterator<Map.Entry<String, Object>> itr = dataRow.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Object> entry = itr.next();
            for (int i = 0; i < dataValues.length; i++) {
                if (dataValues[i].equalsIgnoreCase(entry.getKey())) {
                    String value = dataValues[i + 1];
                    dataRow.put(entry.getKey(), value);
                    break;
                }
            }
        }

    }
}

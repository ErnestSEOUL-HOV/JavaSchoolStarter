package com.digdes.school.parser;

import com.digdes.school.actions.Delete;
import com.digdes.school.actions.Insert;
import com.digdes.school.actions.Select;
import com.digdes.school.actions.Update;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {
    /**
     * Класс предназначенный для выполнения команд из запроса request,
     * но так же для экстракта значений и условий для последующего применения.
     **/

    private final Insert insert = new Insert();
    private final Update update = new Update();
    private final Delete delete = new Delete();
    private final Select select = new Select();
    Map<String, Object> newData = new HashMap<>();
    List<Map<String, Object>> data = new ArrayList<>();


    public List<Map<String, Object>> requestInjection(String request) {

        if (request.matches("(?i).*insert.*")) {
            newData = insert.functionInsert(getValues(request));
            data.add(newData);
        } else if (request.matches("(?i).*update.*")) {

            if ((request.toLowerCase()).contains("where")) {
                Pattern pattern = Pattern.compile(".*where");
                Matcher matcher = pattern.matcher(request.toLowerCase());

                if (matcher.find()) {
                    String output = matcher.group();
                    String output1 = output.substring(0, output.length() - 5);
                    data = update.functionUpdate(data, getValues(output1), getCondition(request));
                }
            } else {
                data = update.functionUpdate(data, getValues(request), getCondition(request));
            }

        } else if (request.matches("(?i).*delete.*")) {
            if ((request.toLowerCase()).contains("where")) {
                Pattern pattern = Pattern.compile(".*where");
                Matcher matcher = pattern.matcher(request.toLowerCase());

                if (matcher.find()) {
                    String output = matcher.group();
                    String output1 = output.substring(0, output.length() - 5);
                    data = delete.functionDelete(data, getValues(output1), getCondition(request));
                }

            }else if (request.equalsIgnoreCase("delete")){
                data = delete.functionDelete(data,null,null);
            }
        }else if (request.matches("(?i).*select.*")){
            if ((request.toLowerCase()).contains("where")) {
                Pattern pattern = Pattern.compile(".*where");
                Matcher matcher = pattern.matcher(request.toLowerCase());

                if (matcher.find()) {
                    String output = matcher.group();
                    String output1 = output.substring(0, output.length() - 5);
                   return select.functionSelect(data, getValues(output1), getCondition(request));
                }

            }else if (request.equalsIgnoreCase("select")){
                return select.functionSelect(data, null,null);
            }
        }
        return data;
    }

    private String[] getValues(String request) {
        if (request.equalsIgnoreCase("delete ")) {
            return null;
        } else if (request.contains("delete")) {
            String[] arr = request.split("'");
            String[] array = new String[1];
            System.arraycopy(arr, 1, array, 0, array.length);
            return array;
        }
        if (request.equalsIgnoreCase("select ")) {
            return null;
        } else if (request.contains("select")) {
            String[] arr = request.split("'");
            String[] array = new String[1];
            System.arraycopy(arr, 1, array, 0, array.length);
            return array;
        }
        //Создаем лист для хранения ключ-значение
        List<String> key_value = new ArrayList<>();
        //Разбиваем на действия, дата
        String[] arr1 = request.split(" ");
        //Копируем данные
        String[] arr2 = new String[arr1.length - 2];
        System.arraycopy(arr1, 2, arr2, 0, arr2.length);
        //Собираем строку из данных
        String stringData = String.join("", arr2);
        //Разделяем строку данных на под данные
        arr2 = stringData.split(",");
        //Цикл для разделения данных на ключ-значение
        // и добавление в наш лист как отдельные элементы
        for (String datum : arr2) {
            String[] kv = datum.split("=");
            key_value.add(kv[0].replaceAll("'", ""));
            key_value.add(kv[1].replaceAll("'", ""));
        }
        String[] resultArray = new String[key_value.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = key_value.get(i);
        }
        return resultArray;
    }

    private String[] getCondition(String request) {


        String where = null;
        if (request.contains("where")) {
            where = "where ";
        } else if (request.contains("WHERE")) {
            where = "WHERE ";
        } else if (!request.contains("where") && !request.contains("WHERE")) {
            System.out.println("!INCORRECT INPUT!");
            return null;
        }
        String[] resultArray = request.split(where);
        String[] afterWhere = new String[resultArray.length - 1];
        System.arraycopy(resultArray, 1, afterWhere, 0, afterWhere.length);
        String stringData = String.join("", afterWhere).replaceAll("'", "");
        afterWhere = stringData.split(" ");
        return afterWhere;

    }
}


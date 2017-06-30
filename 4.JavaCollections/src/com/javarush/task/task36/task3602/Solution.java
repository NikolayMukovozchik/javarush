package com.javarush.task.task36.task3602;

import java.lang.reflect.*;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        for (Class clazz : Collections.class.getDeclaredClasses()){
            // проверяем реализацию интерфейса List
            if (!List.class.isAssignableFrom(clazz))
                continue;
                Field[] fields = clazz.getDeclaredFields();
                for (Field field: fields){
                    // проверяем приватность и статичность
                    if (Modifier.isStatic(field.getModifiers()) && Modifier.isPrivate(field.getModifiers())){
                        try {
                            Constructor constructor = clazz.getDeclaredConstructor();
                            constructor.setAccessible(true);
                            List list = (List) constructor.newInstance();
                            list.get(0);
                        }catch (Exception error){
                            // проверяем нужное исключение
                            if(error.getClass().getSimpleName().equals("IndexOutOfBoundsException")){
                                return clazz;
                            }
                        }
                    }
                }
            }
        return null;
    }
}

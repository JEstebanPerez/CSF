package com.pastillasCreator.pill_box.herramientas;

import java.lang.reflect.Field;
import java.util.Objects;

public class ObjectComparator {

    public static <T> boolean compareObjects(Object object_1,Object object_2,Class<T> tClass) throws IllegalAccessException {
        Field[] fields = tClass.getDeclaredFields();
        for(Field field: fields){
            if(Objects.equals(field.get(object_1),field.get(object_2))) continue;
            return false;
        }
        return true;
    }
}

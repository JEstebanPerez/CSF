package com.pastillasCreator.pill_box.herramientas;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectComparator {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static <T> boolean compareObjects(Object object_1, Object object_2, Class<T> tClass) {
        Field[] fields = tClass.getDeclaredFields();
        List<Method> methods = Arrays.stream(tClass.getMethods())
                .filter(x -> x.getName().contains("get"))
                .filter(x -> x.getParameters().length == 0)
                .filter(x -> Modifier.isPublic(x.getModifiers())).collect(Collectors.toList());
        for(Method method: methods){
            try{
                if(Objects.equals(method.invoke(object_1),method.invoke(object_2))) continue;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
            return false;
        }
        return true;
    }
}

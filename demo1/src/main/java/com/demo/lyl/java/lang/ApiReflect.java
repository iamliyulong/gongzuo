package com.demo.lyl.java.lang;

import org.junit.Test;

import com.demo.lyl.java.Example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ApiReflect {

    @Test
    public void class1(){
        Class<Example> exampleClass = Example.class;
        try {
            Example example = exampleClass.newInstance();
            example.a();
            System.out.println(example.a);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void class2(){
        Class<Example> exampleClass = Example.class;
        try {
            Constructor<Example> constructor = exampleClass.getConstructor();
            try {
                Example example = constructor.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
  
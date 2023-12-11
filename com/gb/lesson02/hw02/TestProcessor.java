package com.gb.lesson02.hw02;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestProcessor {
    public static void runTest(Class<?> testClass) {
        final Constructor<?> declaredConstructor;
        try {
            declaredConstructor = testClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Для класса \"" + testClass.getName() + "\" не найден конструктор без оргументов");
        }

        final Object testObj;
        try {
            testObj = declaredConstructor.newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось создать объект класса \"" + testClass.getName() + "\"");
        }

        List<Method> methods = new ArrayList<>();
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class) || method.isAnnotationPresent(AfterEach.class) || method.isAnnotationPresent(BeforeEach.class)) {
                checkTestMethod(method);
                methods.add(method);
            }
        }

        for(int i =0; i < methods.size(); i++){
            if(!methods.get(i).isAnnotationPresent(Test.class)){
                if(methods.get(i).isAnnotationPresent(BeforeEach.class)){
                    Method temp = methods.get(0);
                    methods.set(0, methods.get(i));
                    methods.set(i, temp);
                }else if(methods.get(i).isAnnotationPresent(AfterEach.class)){
                    Method temp = methods.get(methods.size()-1);
                    methods.set(methods.size()-1, methods.get(i));
                    methods.set(i, temp);
                }
            }
        }

        for(int i = 1; i < methods.size()-2; i++){
            if(methods.get(i).isAnnotationPresent(Test.class)){
                for(int j = i; j < methods.size()-1; j++){
                    if(methods.get(j).isAnnotationPresent(Test.class)){
                        if(methods.get(i).getAnnotation(Test.class).order() >  methods.get(j).getAnnotation(Test.class).order()){
                            Method temp = methods.get(i);
                            methods.set(i, methods.get(j));
                            methods.set(j, temp);
                        }
                    }
                }
            }
        }

        methods.forEach(it -> runTest(it, testObj));
    }

    private static void checkTestMethod(Method method) {
        if (!method.getReturnType().isAssignableFrom(void.class) || method.getParameterCount() != 0) {
            throw new IllegalArgumentException("Method \"" + method.getName() + "\" must be void and have no arguments");
        }
    }


    private static void runTest(Method testMethod, Object testObj){
        try {
            testMethod.invoke(testObj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to run the test method \"" + testMethod.getName() + "\"");
        }

    }
}

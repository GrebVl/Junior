package com.gb.lesson01.hw01;


import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
 * 1.1 Найти максимальное
 * 2.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
 * 2.3 Найти количество чисел, квадрат которых меньше, чем 100_000
 */

public class Task01 {
    public static void main(String[] args) {
        //1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
        List<Integer> list = Stream.generate(() -> ThreadLocalRandom.current().nextInt(1_000_000))
                .limit(1000)
                .collect(Collectors.toList());

        list.sort(((o1, o2) -> o1-o2));
        //1.1 Найти максимальное
        int maxInt = list.stream().max(Integer::compare).get();

        //2.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
        int longSum = list.stream()
               .filter(it -> it > 500_000)
               .mapToInt(a -> a*5-150)
               .sum();

        //2.3 Найти количество чисел, квадрат которых меньше, чем 100_000
        int number = (int) list.stream()
                .filter(it -> it*it < 100_000 && it*it > 0)
                .count();


        System.out.println(list);
        System.out.println(maxInt);
        System.out.println(longSum);
        System.out.println(number);
        
    }



}

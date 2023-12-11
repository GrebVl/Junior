package com.gb.lesson02.hw02;

/**
 * Расширить пример с запуском тестов следующими фичами:
 *
 *  ,
 *  1. Добавить аннотации BeforeEach, AfterEach,
 * которые ставятся над методами void без аругментов
 * и запускаются ДО и ПОСЛЕ всех тестов соответственно.
 *
 * 2. В аннотацию Test добавить параметр order() со значением 0 по умолчанию.
 * Необходимо при запуске тестов фильтровать тесты по этому параметру (от меньшего к большему).
 * Т.е. если есть методы @Test(order = -2) void first, @Test void second,
 * Test(order = 5) void third,
 * то порядок вызовов first -> second -> third
 *
 * 3.* Добавить аннотацию @Skip, которую можно ставить над тест-методами. Если она стоит - то тест не запускается.
 *
 * 4.* При наличии идей, реализовать их и написать об этом в комментарии при сдаче.
 */

public class Homework {
    public static void main(String[] args) {
        TestProcessor.runTest(MyTest.class);
    }

    static class MyTest{
        @BeforeEach
        void thirdBeforeEach(){
            System.out.println("0 Third beforeEach start");
        }

        @AfterEach
        void fourthAfterEach(){
            System.out.println("4 Fourth AfterEach start");
        }

        @Test(order = -2)
        void firstTest(){
            System.out.println("1 FirstTest start");
        }

        @Test(order = 4)
        void fifthTest(){
            System.out.println("3 FifthTest start");
        }

        @Test(order = 2)
        void secondTest(){
            System.out.println("2 SecondTest start");
        }

    }
}

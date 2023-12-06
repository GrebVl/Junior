package com.gb.lesson01.hw01;

import java.util.List;
import java.util.TreeSet;


/**
 * 2. Создать класс Employee (Сотрудник) с полями:
 * String name, int age, double salary, String department
 * 2.1 Создать список из 10-20 сотрудников
 * 2.2 Вывести список всех различных отделов (department) по списку сотрудников
 * 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
 * 2.4 * Из списка сотрудников с помощью стрима создать
 * Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
 * 2.5 * Из списока сорудников с помощью стрима создать
 * Map<String, Double> с отделами и средней зарплатой внутри отдела
 */

public class Task02 {
    public static void main(String[] args) {
        //2.1 Создать список из 10-20 сотрудников
        List<Employee> employees = generateRandomEmployee();
        System.out.println(employees);

        //2.2 Вывести список всех различных отделов (department) по списку сотрудников
        TreeSet<String> departments = new TreeSet<>();
        for(int i = 0; i < employees.size(); i++){
            departments.add(employees.get(i).getDepartment());
        }
        departments.stream().distinct();
        System.out.println(departments);

        //2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
        employees.stream()
                .filter(it -> it.getSalary() < 10_000)
                .forEach(it -> it.setSalary(it.getSalary() * 1.20));
        System.out.println(employees);

    }

    private static List<Employee> generateRandomEmployee() {
        return List.of(
                new Employee("Вадим", 30, 30_000, "Испытательный"),
                new Employee("Евгений", 35, 30_000, "Наладки"),
                new Employee("Николай", 60, 40_000, "Управления"),
                new Employee("Михаил", 50, 40_000, "Испытательный"),
                new Employee("Степан", 30, 30_000, "Наладки"),
                new Employee("Дмитрий", 31, 40_000, "Управления"),
                new Employee("Натлья", 50, 7_000, "Обслуживание"),
                new Employee("Маргарита", 50, 7_000, "Обслуживание"),
                new Employee("Зинаида", 40, 37_000, "Бугалтерия"),
                new Employee("Ксения", 40, 37_000, "Бугалтерия")
        );
    }
}



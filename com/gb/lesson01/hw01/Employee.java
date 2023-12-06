package com.gb.lesson01.hw01;

 /**
 * 2. Создать класс Employee (Сотрудник) с полями:
 * String name, int age, double salary, String department
 */

public class Employee {
    private String name;
    private int age;
    private double salary;
    private String department;

     public Employee(String name, int age, double salary, String department) {
         this.name = name;
         this.age = age;
         this.salary = salary;
         this.department = department;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public int getAge() {
         return age;
     }

     public void setAge(int age) {
         this.age = age;
     }

     public double getSalary() {
         return salary;
     }

     public void setSalary(double salary) {
         this.salary = salary;
     }

     public String getDepartment() {
         return department;
     }

     public void setDepartment(String department) {
         this.department = department;
     }

     @Override
     public String toString() {
         return  name +
                 ", " + age +
                 ", " + salary +
                 ", " + department;
     }
 }

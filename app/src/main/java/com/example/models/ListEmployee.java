package com.example.models;

import java.util.ArrayList;

public class ListEmployee {
    private ArrayList<Employee> employees;

    public ListEmployee() {
        employees = new ArrayList<>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void gen_dataset() {
        Employee e1 = new Employee();
        e1.setId(1);
        e1.setName("John");
        e1.setEmail("John@gmail.com");
        e1.setPhone("0123456789");
        e1.setUsername("john");
        e1.setPassword("123");
        employees.add(e1);

        Employee e2 = new Employee();
        e2.setId(2);
        e2.setName("Peter");
        e2.setEmail("Peter@gmail.com");
        e2.setPhone("01234567890");
        e2.setUsername("peter");
        e2.setPassword("456");
        employees.add(e2);

        Employee e3 = new Employee();
        e3.setId(3);
        e3.setName("Tom");
        e3.setEmail("Tom@gmail.com");
        e3.setPhone("012345678901");
        e3.setUsername("tom");
        e3.setPassword("789");
        employees.add(e3);

        Employee e4 = new Employee();
        e4.setId(4);
        e4.setName("Anna");
        e4.setEmail("Anna@gmail.com");
        e4.setPhone("012345678902");
        e4.setUsername("anna");
        e4.setPassword("101");
        employees.add(e4);

        Employee e5 = new Employee();
        e5.setId(5);
        e5.setName("Mike");
        e5.setEmail("Mike@gmail.com");
        e5.setPhone("012345678903");
        e5.setUsername("mike");
        e5.setPassword("112");
        employees.add(e5);

        Employee e6 = new Employee();
        e6.setId(6);
        e6.setName("Lisa");
        e6.setEmail("Lisa@gmail.com");
        e6.setPhone("012345678904");
        e6.setUsername("lisa");
        e6.setPassword("113");
        employees.add(e6);

        Employee e7 = new Employee();
        e7.setId(7);
        e7.setName("David");
        e7.setEmail("David@gmail.com");
        e7.setPhone("012345678905");
        e7.setUsername("david");
        e7.setPassword("114");
        employees.add(e7);
    }
}
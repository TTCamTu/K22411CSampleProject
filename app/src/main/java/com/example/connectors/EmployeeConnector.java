package com.example.connectors;

import com.example.models.Employee;
import com.example.models.ListEmployee;

public class EmployeeConnector
{
    public Employee login (String username, String password)
    {
        ListEmployee le=new ListEmployee();
        le.gen_dataset();
        for (Employee emp:le.getEmployees())
        {
            if (emp.getUsername().equalsIgnoreCase(username) && emp.getPassword().equalsIgnoreCase(password)) {
                return emp;
            }
         }
        return null;
    }
}

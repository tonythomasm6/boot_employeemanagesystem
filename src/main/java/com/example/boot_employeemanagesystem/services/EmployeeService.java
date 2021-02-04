package com.example.boot_employeemanagesystem.services;

import com.example.boot_employeemanagesystem.model.Employee;
import com.example.boot_employeemanagesystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public void deleteEmployee(String empId){
        employeeRepository.deleteById(empId);
    }

    public Employee getEmployee(String empId){
        return employeeRepository.findById(empId).orElse(null);
    }
}

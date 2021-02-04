package com.example.boot_employeemanagesystem.controllers;

import com.example.boot_employeemanagesystem.model.Employee;
import com.example.boot_employeemanagesystem.repositories.EmployeeRepository;
import com.example.boot_employeemanagesystem.services.EmployeeService;
import com.example.boot_employeemanagesystem.services.OfficeService;
import org.dom4j.rule.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private OfficeService officeService;

    Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/employee")
    public String getAllEmployees(Model model){
        model.addAttribute("allEmployees",employeeService.getAllEmployees());
        return "Employee/employeeList";
    }

    @GetMapping("/employee/add")
    public String addEmployee(Model model){
        model.addAttribute("employee",new Employee());
        model.addAttribute("allOffices", officeService.getAllOffices());
        return "Employee/add";
    }

    @PostMapping("/employee")
    public String save(Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/delete/{employeeId}")
    public String delete(@PathVariable("EmployeeId") String employeeId){
        employeeService.deleteEmployee(employeeId);
        return "redirect:/employee";
    }

    @GetMapping("/employee/update/{employeeId}")
    public String update(@PathVariable("employeeId") String employeeId, Model model){
        Employee e = employeeService.getEmployee(employeeId);
        if(e== null){
            log.info("No employee found");
            return "Employee/update";
        }else{
            model.addAttribute("employee", e);
        }
        model.addAttribute("allOffices", officeService.getAllOffices());
        return "Employee/update";
    }
}

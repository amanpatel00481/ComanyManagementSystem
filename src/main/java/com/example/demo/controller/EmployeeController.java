package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.services.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {

	 @GetMapping("/employee")
	    public String employeeLogin() {
	        return "employee_login";
	    }
	    
	    @GetMapping("/employee/dashboard")
	    public String employeeDashboard() {
	        return "employee_dashboard";
	    }
}

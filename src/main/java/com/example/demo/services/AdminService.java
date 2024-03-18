package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repo.AdminRepository;
import com.example.demo.repo.EmployeeRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
    	return employeeRepository.save(employee);
    }
}

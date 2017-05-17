package com.springbootdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springbootdemo.dao.EmployeeDAOImpl;
import com.springbootdemo.dao.EmployeeDAO;
import com.springbootdemo.domain.Employee;
import com.springbootdemo.domain.EmployeeRepository;
import com.springbootdemo.exception.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAOImpl employeeDAO;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {		
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll().forEach(employees::add);
		return employees;
	}

	@Override
	public Employee getEmployee(String id) {
		Employee employee = employeeRepository.findOne(id);
		
		if(employee == null) {
			throw new ResourceNotFoundException(id, "Employee not found");
		}
		return employee;
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
		
	}

	@Override
	public void updateEmployee(String id, Employee employee) {
		employeeRepository.save(employee);
		
	}

	@Override
	public void deleteEmployee(String id) {
		employeeRepository.delete(id);			
	}

	@Override
	public Page<Object[]> dynamicSearch(String name, String id, String gender, String cellphone, String address,
			int age, int pageIx, int pageSize) {
		return employeeDAO.dynamicSearch(name, id, gender, cellphone, address, age, pageIx, pageSize);
	}
	
    @Override
    public boolean exists(Employee employee) {
        return getEmployee(employee.getId()) != null;
    }

}

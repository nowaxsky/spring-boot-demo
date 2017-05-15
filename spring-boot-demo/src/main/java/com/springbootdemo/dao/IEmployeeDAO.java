package com.springbootdemo.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springbootdemo.domain.Employee;

public interface IEmployeeDAO {
	
	public List<Employee> getAllEmployees();
	public Employee getEmployee(String id);
	public void addEmployee(Employee employee);
	public void updateEmployee(String id, Employee employee);
	public void deleteEmployee(String id);
	public Page<Object[]> dynamicSearch(String name, String id, String gender, 
			String cellphone, String address, int age, int pageIx, int pageSize);
}

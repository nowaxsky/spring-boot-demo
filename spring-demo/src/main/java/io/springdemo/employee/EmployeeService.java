package io.springdemo.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public List<Employee> getAllEmployees() {
		
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll().forEach(employees::add);
		return employees;
		
	}
	
	public Employee getEmployee(String number) {
		return employeeRepository.findOne(number);
	}

	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void updateEmployee(String number, Employee employee) {
		employeeRepository.save(employee);	
	}

	public void deleteEmployee(String number) {
		employeeRepository.delete(number);	
		
	}

	public List<EmployeeBrief> findByName(String name) {
		
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findByName(name).forEach(employees::add);
		
		List<EmployeeBrief> employeeBriefs = new ArrayList<>();
		for(int i = 0; i < employees.size(); i++) {
			
			EmployeeBrief temp = new EmployeeBrief();
			temp.setName(employees.get(i).getName());
			temp.setNumber(employees.get(i).getNumber());
			temp.setSex(employees.get(i).getSex());
			
			employeeBriefs.add(i, temp);			
		}
		
		return employeeBriefs;
		
		//return employees;
	}

	public List<EmployeeBrief> findByNumber(String number) {
		
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findByNumber(number).forEach(employees::add);
		
		List<EmployeeBrief> employeeBriefs = new ArrayList<>();
		for(int i = 0; i < employees.size(); i++) {
			
			EmployeeBrief temp = new EmployeeBrief();
			temp.setName(employees.get(i).getName());
			temp.setNumber(employees.get(i).getNumber());
			temp.setSex(employees.get(i).getSex());
			
			employeeBriefs.add(i, temp);			
		}
		
		return employeeBriefs;
		
		//return employees;
	}

	public List<EmployeeBrief> findByNameAndNumber(String name, String number) {
		
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findByNameAndNumber(name,number).forEach(employees::add);
		
		List<EmployeeBrief> employeeBriefs = new ArrayList<>();
		for(int i = 0; i < employees.size(); i++) {
			
			EmployeeBrief temp = new EmployeeBrief();
			temp.setName(employees.get(i).getName());
			temp.setNumber(employees.get(i).getNumber());
			temp.setSex(employees.get(i).getSex());
			
			employeeBriefs.add(i, temp);			
		}
		
		return employeeBriefs;
		
		//return employees;
	}
	
	

	
}

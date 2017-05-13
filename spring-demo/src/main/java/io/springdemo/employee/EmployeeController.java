package io.springdemo.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@RequestMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@RequestMapping("/employees/{number}")
	public Employee getEmployee(@PathVariable String number) {
		return employeeService.getEmployee(number);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employees")
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/employees/{number}")
	public void updateEmployee(@PathVariable String number, @RequestBody Employee employee) {
		employeeService.updateEmployee(number, employee);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/employees/{number}")
	public void deleteEmployee(@PathVariable String number) {
		employeeService.deleteEmployee(number);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/employees/search")
	public List<EmployeeBrief> findByName(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "number", defaultValue = "") String number) {
		
		if("".equals(number)) {
			return employeeService.findByName(name);
		} else if("".equals(name)) {
			return employeeService.findByNumber(number);
		} else {
			return employeeService.findByNameAndNumber(name,number);
		}
	}
	
	

}

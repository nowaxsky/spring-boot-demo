package com.springbootdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootdemo.domain.Employee;
import com.springbootdemo.exception.ExceptionResponse;
import com.springbootdemo.exception.ResourceNotFoundException;
import com.springbootdemo.service.EmployeeServiceImpl;

@Controller
@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@RequestMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		//return employeeService.getAllEmployees();
		
		List<Employee> employees = employeeService.getAllEmployees();
		if(employees.isEmpty()) {
			new ResourceNotFoundException("Database is empty", "Employee not found");
		}
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@RequestMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable String id) {
		//return employeeService.getEmployee(id);
		
		Employee employee = employeeService.getEmployee(id);
		if(employee == null) {
			new ResourceNotFoundException(id, "Employee not found");
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employees")
	public ResponseEntity<Void> addEmployee(@Valid @RequestBody Employee employee) {
		//employeeService.addEmployee(employee);
		//return new ResponseEntity<Void>(HttpStatus.CREATED);
		
		if(employeeService.exists(employee)) {
			new ResourceNotFoundException(employee.getId(), "Employee already exists");
		}
		employeeService.addEmployee(employee);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/employees/{id}")
	public void updateEmployee(@PathVariable String id, @Valid @RequestBody Employee employee) {
		//employeeService.updateEmployee(id, employee);
		
		Employee currentEmployee = employeeService.getEmployee(id);
		if(currentEmployee == null) {
			new ResourceNotFoundException(employee.getId(), "Employee not found");
		}
		
		employeeService.updateEmployee(id, employee);
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/employees/{id}")
	public void deleteEmployee(@PathVariable String id) {
		//employeeService.deleteEmployee(id);
		
		Employee employee = employeeService.getEmployee(id);
		if(employee == null) {
			new ResourceNotFoundException(employee.getId(), "Unable to delete. Employee not found");
		}
		
		employeeService.deleteEmployee(id);
	}
	
	/*
	@RequestMapping(method = RequestMethod.GET, value = "/employees/search")
	public List<Object[]> dynamicSearching(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "id", defaultValue = "") String id) {
		
		if("".equals(id)) {
			return employeeService.findByName(name);
		} else if("".equals(name)) {
			return employeeService.findById(id);
		} else {
			return employeeService.findByNameAndId(name,id);
		}
	}*/
	
	@RequestMapping(method = RequestMethod.GET, value = "/employees/search")
	public Page<Object[]> getDynamic(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "gender", defaultValue = "") String gender,
			@RequestParam(value = "cellphone", defaultValue = "") String cellphone,
			@RequestParam(value = "address", defaultValue = "") String address,
			@RequestParam(value = "age", defaultValue = "0") int age,
			@RequestParam(value = "pageix", defaultValue = "0") int pageIx,
			@RequestParam(value = "pagesize", defaultValue = "10") int pageSize) {
		return employeeService.dynamicSearch(name, id, gender, cellphone, address, age, pageIx, pageSize);
	}

	/*
	@RequestMapping(method = RequestMethod.GET, value = "/employees/paging")
	public Page<Employee> getEntryByPageable(@PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC) 
	    Pageable pageable, @RequestParam(value = "id", defaultValue = "") String id) {
	    return employeeRepository.findAll(pageable);
	}
	*//*
	@RequestMapping(method = RequestMethod.GET, value = "/employees/paging")
	public List<Employee> getPageSearching ( 
	     @RequestParam(value = "id", defaultValue = "") String id
	    , @RequestParam(value = "offset", defaultValue = "") int offset
	    , @RequestParam(value = "limit", defaultValue = "") int limit
	    ) {
	    return employeeService.findByIdPaging(id,offset,limit);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/employees/paging")
	public Page<Employee> getPageSearching2 (Pageable pageable
	    ,@RequestParam(value = "name", defaultValue = "") String name
	    ,@RequestParam(value = "offset", defaultValue = "") int offset
		,@RequestParam(value = "limit", defaultValue = "") int limit
	    ) {
	    return employeeService.findByNamePaging(name,offset,limit);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/employees/search")
	public List<Employee> dynamicSearching(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "id", defaultValue = "") String id) {
		return employeeService.getWhereClause(name, id);
	}*/

}

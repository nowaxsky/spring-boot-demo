package io.springdemo.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@RequestMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@RequestMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable String id) {
		return employeeService.getEmployee(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employees")
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/employees/{id}")
	public void updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
		employeeService.updateEmployee(id, employee);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/employees/{id}")
	public void deleteEmployee(@PathVariable String id) {
		employeeService.deleteEmployee(id);
	}
	
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
	}/*
	
	@RequestMapping(method = RequestMethod.GET, value = "/employees/search")
	public List<Employee> dynamicSearching(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "id", defaultValue = "") String id) {
		return employeeService.test(name, id);
	}*/

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
	}*/
	
	@RequestMapping(method = RequestMethod.GET, value = "/employees/paging")
	public Page<Employee> getPageSearching2 (Pageable pageable
	    ,@RequestParam(value = "name", defaultValue = "") String name
	    ,@RequestParam(value = "offset", defaultValue = "") int offset
		,@RequestParam(value = "limit", defaultValue = "") int limit
	    ) {
	    return employeeService.findByNamePaging(name,offset,limit);
	}
	
	
}

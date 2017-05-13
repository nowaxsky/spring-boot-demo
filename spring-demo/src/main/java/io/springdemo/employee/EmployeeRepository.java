package io.springdemo.employee;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
	
	//@Query("select name, number, gender from Employee where name = ?")
	public List<Employee> findByName(String name);
	
	public List<Employee> findByNumber(String number);
	
	public List<Employee> findByNameAndNumber(String name, String number);

}

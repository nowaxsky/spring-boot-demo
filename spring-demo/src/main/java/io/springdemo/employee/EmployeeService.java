package io.springdemo.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private EntityManager entityManager;
	
	
	public List<Employee> getAllEmployees() {
		
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll().forEach(employees::add);
		return employees;
		
	}
	
	public Employee getEmployee(String id) {
		return employeeRepository.findOne(id);
	}

	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void updateEmployee(String id, Employee employee) {
		employeeRepository.save(employee);	
	}

	public void deleteEmployee(String id) {
		employeeRepository.delete(id);	
		
	}

	public List<Object[]> findByName(String name) {
		
		List<Object[]> employees = new ArrayList<>();
		employeeRepository.findByName(name).forEach(employees::add);
		
		return employees;
	}
	
	public List<Object[]> findById(String id) {
		
		List<Object[]> employees = new ArrayList<>();
		employeeRepository.findById(id).forEach(employees::add);
		
		return employees;
	}
	
	public List<Object[]> findByNameAndId(String name, String id) {
		
		List<Object[]> employees = new ArrayList<>();
		employeeRepository.findByNameAndId(name,id).forEach(employees::add);
		
		return employees;
	}
	/*
	public List<Object[]> search(String name, String id) {
		 
		String sql = "(1=1)" ;
		if(name != null) {sql += " and name = " + name;}
		//if(id != null) {sql += " and id = " + id;}
		
		System.out.println(sql);
		
		List<Object[]> employees = new ArrayList<>();
		employeeRepository.search(name,id).forEach(employees::add);
		
		return employees;
	}*/

	public List<Employee> find(String name, String id) {
		
		List<Employee> employees = new ArrayList<>();
		employeeRepository.find(name,id).forEach(employees::add);
		
		return employees;
	}
	
	
	/*
	public List<Employee> findByIdPaging(String id, int offset, int limit) {
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findByIdPaging(id, new PageRequest(offset, limit)).forEach(employees::add);
		
		return employees;
	}*/
	
	public Page<Employee> findByNamePaging(String name, int offset, int limit) {
		
		Page<Employee> employees = employeeRepository.findByName(name, new PageRequest(offset, limit));
		
		return employees;
	}
	
	/*
    public List<Employee> test(String name, String id) {
   	 
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root<Employee> cust = query.from(Employee.class);
        query.select(cust);
     
        List<Predicate> predicateList = new ArrayList<Predicate>();
     
        Predicate firstNamePredicate, surnamePredicate;
     
        if ((name != null) && (!(name.isEmpty()))) {
            firstNamePredicate = builder.like(
                builder.upper(cust.<String>get("name")), "%"+name.toUpperCase()+"%");
            predicateList.add(firstNamePredicate);
        }
     
        if ((id != null) && (!(id.isEmpty()))) {
            surnamePredicate = builder.like(
                builder.upper(cust.<String>get("id")), "%"+id.toUpperCase()+"%");
            predicateList.add(surnamePredicate);
        }
     
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        query.where(predicates);
     
        return entityManager.createQuery(query).getResultList();
    }; */
    
	
	
}

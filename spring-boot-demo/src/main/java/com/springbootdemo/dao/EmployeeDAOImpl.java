package com.springbootdemo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springbootdemo.domain.Employee;
import com.springbootdemo.domain.EmployeeRepository;

@Transactional
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
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
		return employeeRepository.findOne(id);
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
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder(); 	
    	CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
    	Root<Employee> e = q.from(Employee.class);
    	q.select(cb.array(e.get("name"), e.get("id"), e.get("gender")));
    	
     
        List<Predicate> predicateList = new ArrayList<Predicate>();
     
        if((name != "") && (!(name.isEmpty()))){
        	predicateList.add(cb.equal(e.get("name"),name));
        }
     
        if((id != "") && (!(id.isEmpty()))){
        	predicateList.add(cb.equal(e.get("id"), id));
        }
        
        if((gender != "") && (!(gender.isEmpty()))){
        	predicateList.add(cb.equal(e.get("gender"), gender));
        }
        
        if((cellphone != "") && (!(cellphone.isEmpty()))){
        	predicateList.add(cb.equal(e.get("cellphone"), cellphone));
        }
        
        if((address != "") && (!(address.isEmpty()))){
        	predicateList.add(cb.equal(e.get("address"), address));
        }
        
        if(age != 0){
        	predicateList.add(cb.equal(e.get("age"), age));
        }
     
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        q.where(predicates);
        
        List<Object[]> resShow = entityManager.createQuery(q).setFirstResult(pageIx * pageSize).setMaxResults(pageSize).getResultList();
        List<Object[]> res = entityManager.createQuery(q).getResultList();
        
        Page<Object[]> pageRst = new PageImpl<Object[]>(resShow, new PageRequest(pageIx,pageSize), res.size());
        return pageRst;
    }

}

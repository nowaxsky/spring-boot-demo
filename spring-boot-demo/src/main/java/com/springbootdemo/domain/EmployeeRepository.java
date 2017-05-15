package com.springbootdemo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends CrudRepository<Employee, String>, JpaRepository<Employee, String>
		, PagingAndSortingRepository<Employee, String>, JpaSpecificationExecutor<Employee>{
	
	/*
	@Query(value = "select name,id,gender FROM employee where name = :name" , nativeQuery = true)
	public List<Object[]> findByName(@Param("name") String name);
	
	@Query(value = "select name,id,gender FROM employee where id = :id", nativeQuery = true)
	public List<Object[]> findById(@Param("id") String id);
	
	@Query(value = "select name,id,gender FROM employee where name = :name and id = :id", nativeQuery = true)
	public List<Object[]> findByNameAndId(@Param("name") String name, @Param("id") String id);

    @Query("SELECT e FROM Employee e WHERE e.name = :name and e.id = :id")
    public List<Employee> find(@Param("name") String name, @Param("id") String id);
    
    @Query("SELECT e FROM Employee e WHERE e.id = ?1")
    public Page<Employee> findByIdPaging(@Param("id") String id, Pageable pageable);
	
    @Query("SELECT e FROM Employee e WHERE e.id = ?1")
    public List<Employee> findByPaging(@Param("id") String id, Pageable pageable);
    */
    
}

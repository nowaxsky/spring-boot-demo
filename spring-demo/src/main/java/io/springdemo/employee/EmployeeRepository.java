package io.springdemo.employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends CrudRepository<Employee, String>, JpaRepository<Employee, String>
		, PagingAndSortingRepository<Employee, String>{
	
	@Query(value = "select name,id,gender FROM employee where name = :name" , nativeQuery = true)
	public List<Object[]> findByName(@Param("name") String name);
	
	@Query(value = "select name,id,gender FROM employee where id = :id", nativeQuery = true)
	public List<Object[]> findById(@Param("id") String id);
	
	@Query(value = "select name,id,gender FROM employee where name = :name and id = :id", nativeQuery = true)
	public List<Object[]> findByNameAndId(@Param("name") String name, @Param("id") String id);

    @Query("SELECT e FROM Employee e WHERE e.name = :name and e.id = :id")
    public List<Employee> find(@Param("name") String name, @Param("id") String id);
    
    /*
    @Query("SELECT e FROM Employee e WHERE e.id = ?1")
    public Page<Employee> findByIdPaging(@Param("id") String id, Pageable pageable);
	*/
    @Query("SELECT e FROM Employee e WHERE e.id = ?1")
    public List<Employee> findByPaging(@Param("id") String id, Pageable pageable);
    
    
    public Page<Employee> findByName(String name, Pageable pageable);
    
    

}

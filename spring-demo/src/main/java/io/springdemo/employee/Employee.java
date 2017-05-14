package io.springdemo.employee;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
/*@NamedStoredProcedureQuery (name = "dynamicQuery", procedureName = "dynamic_query",
							parameters={@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_name", type = String.class),
										@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_id", type = String.class)							
)*/
@Table (name="employee")
public class Employee {

	private String name;
	
	@Id
	private String id;
	private String gender;
	private String cellphone;
	private String address;
	private int age;
	
	//@CreationTimestamp
	@Column(updatable=false)
	@Temporal(TemporalType.TIME)
	private Date created;
	
	//@UpdateTimestamp
	@Temporal(TemporalType.TIME)
	private Date updated;
	
	@PrePersist
	protected void onCreate() {
		updated = created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}
	
	
	public Employee() {
		
	}	
	
	public Employee(String name, String id, String gender, String cellphone, String address, int age) {
		super();
		this.name = name;
		this.id = id;
		this.gender = gender;
		this.cellphone = cellphone;
		this.address = address;
		this.age = age;
		//this.created = created;
		//this.updated = updated;*/
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}
	
	
}

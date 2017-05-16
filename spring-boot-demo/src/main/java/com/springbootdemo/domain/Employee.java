package com.springbootdemo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table (name="employee")
public class Employee {
	
	@NotNull(message = "Name can not be null.")
	private String name;
	
	@Id
	@NotNull(message = "Id can not be null.")
	private String id;
	private String gender;
	private String cellphone;
	private String address;
	private int age;
	
	@CreationTimestamp
	@Column(updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
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

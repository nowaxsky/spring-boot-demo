package io.springdemo.employee;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name="EMPLOYEE")
public class Employee {

	private String name;
	
	@Id
	private String number;
	private String gender;
	private String cellphone;
	private String address;
	private int age;
	
	//@CreationTimestamp
	@Column(updatable=false)
	@Temporal(TemporalType.TIME)
	private Date buildTime;
	
	//@UpdateTimestamp
	@Temporal(TemporalType.TIME)
	private Date lastUpdateTime;
	
	@PrePersist
	protected void onCreate() {
		lastUpdateTime = buildTime = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		lastUpdateTime = new Date();
	}
	
	
	public Employee() {
		
	}	
	
	public Employee(String name, String number, String gender, String cellphone, String address, int age) {
		super();
		this.name = name;
		this.number = number;
		this.gender = gender;
		this.cellphone = cellphone;
		this.address = address;
		this.age = age;
		//this.buildTime = buildTime;
		//this.lastUpdateTime = lastUpdateTime;*/
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
	
	
	public Date getBuildTime() {
		return buildTime;
	}
	
	/*
	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}*/

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	/*
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}*/
	
	
}

package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Past;

@Entity(name="user_details")
public class User {
	@Id
private int id;

private String birthdate;
private String department;
@ManyToOne(cascade=CascadeType.ALL)
@JoinTable(name="join_table",
		joinColumns= {  @JoinColumn(name="customer_id")  },
		inverseJoinColumns= {  @JoinColumn(name="User_id")  })
private Employee emp;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getbirthdate() {
	return birthdate;
}


public void setbirthdate(String birthdate) {
	this.birthdate = birthdate;
}


public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

@Override
public String toString() {
	return "User [id=" + id + ", birthdate=" + birthdate + ", department=" + department + "]";
}
public Employee getEmp() {
	return emp;
}
public void setEmp(Employee emp) {
	this.emp = emp;
}



}

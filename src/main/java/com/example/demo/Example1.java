package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Example1 {
	private int id=0;
	private String name;
	private String dept;
	@Autowired
	@Qualifier("ex2")
	private Example2 ex2;
	@Autowired
	@Qualifier("ex2")
	private Example2 ex3;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public void show() {
		System.out.println("example1 working...."+(++id));
		ex2.compile();
		ex3.compile();
	}
	@Override
	public String toString() {
		return "Example1 [id=" + id + ", name=" + name + ", dept=" + dept + "]";
	}
	public Example2 getEx2() {
		return ex2;
	}
	public void setEx2(Example2 ex2) {
		this.ex2 = ex2;
	}
	public Example2 getEx3() {
		return ex3;
	}
	public void setEx3(Example2 ex3) {
		this.ex3 = ex3;
	}
	
}

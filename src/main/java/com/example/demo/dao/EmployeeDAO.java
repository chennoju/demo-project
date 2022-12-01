package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Employee;

//@RepositoryRestResource(path = "emp", collectionResourceRel = "employee")
public interface EmployeeDAO extends JpaRepository<Employee,Integer>{

}

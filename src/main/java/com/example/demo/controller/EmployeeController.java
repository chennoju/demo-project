package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.model.Employee;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeDAO repo;
	static int id=5;
	/*@RequestMapping("/home")
	public String show() {
		return "home";
	}
	@RequestMapping("/user")
	public ModelAndView user( Employee emp) {
		repo.save(emp);
		ModelAndView mv=new ModelAndView();
		mv.addObject("nums",emp);
		mv.setViewName("result");
		
		return mv;
	}
	@RequestMapping("/getuser")
	public ModelAndView getuser(@RequestParam("id")int nid) {
		Employee a=repo.findById(nid).orElse(new Employee());
		System.out.print(nid);
		ModelAndView mv=new ModelAndView();
		mv.addObject("nums",a);
		mv.setViewName("result");
		
		return mv;
	}
	
	@RequestMapping("/getname")
	public ModelAndView getname(@RequestParam("lname")String name) {
		System.out.print(name);
		System.out.println(repo.findByLname(name));
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("result");
		
		return mv;
	}
	@RequestMapping("/getuser")
	public ModelAndView getuser(@RequestParam("id")int nid) {
		Employee a=repo.findById(nid).orElse(new Employee());
		System.out.print(nid);
		ModelAndView mv=new ModelAndView();
		mv.addObject("nums",a);
		mv.setViewName("result");
		
		return mv;
	}*/
	
	/*@RequestMapping("/getuser")
	public ModelAndView getuser(@RequestParam("id")int nid) {
		repo.deleteById(nid);
		System.out.print(nid);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("result");
		return mv;
	}*/
	
	@GetMapping("/employee")
	public ResponseEntity<CollectionModel<EntityModel<Employee>>> getEmployees() {
		List<Employee> employees=repo.findAll();
		List<EntityModel<Employee>> entityemp=new ArrayList<EntityModel<Employee>>();
		
		for(Employee emp:employees) {
			EntityModel<Employee> entityModel=EntityModel.of(emp);
			WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).getEmployees());
			entityModel.add(link.withRel("all-collection"));
			
			entityModel.add(linkTo(methodOn(this.getClass()).getEmployeesbyid(emp.getId())).withRel("self"));
			entityemp.add(entityModel);
		}CollectionModel<EntityModel<Employee>> model=CollectionModel.of(entityemp);
		model.add(linkTo(methodOn(this.getClass()).getEmployees()).withSelfRel());
		return new ResponseEntity<>(model,HttpStatus.OK);
	}
	@GetMapping("/employee/{id}")
	public EntityModel<Employee> getEmployeesbyid(@PathVariable("id") int id) {
		Optional<Employee> emp=repo.findById(id);
		Employee employee=emp.get();
		EntityModel<Employee> entityModel=EntityModel.of(employee);
		WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).getEmployees());
		entityModel.add(link.withRel("all-Users"));
		
		entityModel.add(linkTo(methodOn(this.getClass()).getEmployeesbyid(employee.getId())).withSelfRel());
		return entityModel ;
	}
	@PostMapping("/employee")
	public ResponseEntity<Employee> postEmployees(@Valid @RequestBody Employee emp) {
		
		Employee employee=repo.save(emp);
		EntityModel<Employee> entityModel=EntityModel.of(employee);
		WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).getEmployees());
		entityModel.add(link.withRel("all-Users"));
		
		entityModel.add(linkTo(methodOn(this.getClass()).getEmployeesbyid(employee.getId())).withSelfRel());
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(emp.getId())
				.toUri();
		return  ResponseEntity.created(location).build() ;
	}
	@DeleteMapping("/employee/{id}")
	public List<Employee> deleteEmployees(@PathVariable int id) {
		Employee emp=repo.getOne(id);
		repo.delete(emp);
		return repo.findAll();
		
	}
	@PutMapping("/employee")
	public List<Employee> putEmployee(@RequestBody Employee emp) {
		repo.save(emp);
		return repo.findAll();
		}
	

	
}

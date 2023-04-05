package com.raghsonline.springboot.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raghsonline.springboot.demo.domain.Person;

@RestController
@RequestMapping("/api/")
public class HelloRestController 
{
	
	@GetMapping
	public String sayHello() //URI --> /api/ [http://localhost:8080/api/]
	{
		System.out.println("URI - '/' invoked!");
		
		return String.format("Hello from SpringBoot RestController, at %s", 
				LocalDateTime.now().toString());
	}
	
	@GetMapping("person") //URI -> /api/person [http://localhost:8080/api/person]
	public Person getPerson()
	{
		Person person = new Person("Raghavan", "Muthu", 41, "raghavan.muthu@gmail.com");
		
		return person;
	}
	
	@GetMapping("/persons")//URI -> /api/persons [http://localhost:8080/api/persons]
	public List<Person> getPersonList()
	{
		Person p1 = new Person("Raghavan", "Muthu", 41, "raghavan.muthu@gmail.com");
		Person p2 = new Person("Arun", "Prasad", 24, "arun.prasad@gmail.com");
		Person p3 = new Person("Rama", "Vemulapalli", 27, "rama.v@gmail.com");

		return List.of(p1, p2 , p3);
	}
}

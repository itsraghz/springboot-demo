package com.raghsonline.springboot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raghsonline.springboot.demo.domain.Person;
import com.raghsonline.springboot.demo.repository.PersonRepository;

@RestController
@RequestMapping("/api/persons/")
public class PersonRestController 
{
	@Autowired
	PersonRepository personRepository;
	
	public PersonRestController(PersonRepository personRepository)
	{
		System.out.println("PersonRestController() instantiated, personRepository="+personRepository);
		this.personRepository = personRepository;
	}
	
	@GetMapping("/person") //URI -> /api/persons [http://localhost:8080/api/persons]
	public Person getPerson()
	{
		Person person = new Person("Raghavan", "Muthu", 41, "raghavan.muthu@gmail.com");
		
		return person;
	}
	
	@GetMapping("/sample")//URI -> /api/persons/sample [http://localhost:8080/api/persons/sample]
	public List<Person> getPersonList()
	{
		Person p1 = new Person("Raghavan", "Muthu", 41, "raghavan.muthu@gmail.com");
		Person p2 = new Person("Arun", "Prasad", 24, "arun.prasad@gmail.com");
		Person p3 = new Person("Rama", "Vemulapalli", 27, "rama.v@gmail.com");

		return List.of(p1, p2 , p3);
	}
	
	@PostMapping
	public void addPerson(@RequestBody Person person)
	{
		System.out.println("POST - /person invoked!");
		System.out.println("Person object received : " + person);
		
		Person personInserted = personRepository.save(person);
		System.out.println("Person object Inserted : " + personInserted);
		
	}

	@GetMapping("/{id}")
	public Person getPersonById(@PathVariable int id)
	{
		System.out.println("GET - /api/person/{id} invoked!, id="+id);
		
		//Person person = personRepository.getReferenceById(id);
		Person person = personRepository.findById(id).get();
		System.out.println("Person object retrieved : " + person);
		
		return person;
	}
	
	@GetMapping
	public List<Person> getPersons()
	{
		System.out.println("GET - /api/persons invoked");
		
		List<Person> personList = personRepository.findAll();
		System.out.println("Persons List object retrieved : " + personList);
		
		return personList;
	}
	
	@GetMapping("/search/{email}")
	public List<Person> findByEmailLikeContaining(@PathVariable String email)
	{
		System.out.println("GET - /api/persons/search/{email} invoked");
		
		List<Person> personList = personRepository.findByEmailContaining(email);
		System.out.println("Persons List object retrieved : " + personList);
		
		return personList;
	}
}

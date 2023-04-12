package com.raghsonline.springboot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping("/addPersons")
	public void addPersons(@RequestBody List<Person> personList)
	{
		System.out.println("POST - /addPersons invoked!");
		System.out.println("Person List received : " + personList);
		List<Person> personsInserted = personRepository.saveAll(personList);
		System.out.println("Persons Inserted : " + personsInserted);
	}
	
	@PutMapping
	public void updatePerson(@RequestBody Person person)
	{
		System.out.println("Put - /person invoked!");
		System.out.println("Person object received : " + person);
		
		Person personUpdated = personRepository.save(person);
		System.out.println("Person object Updated : " + personUpdated);
		
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
	
	@GetMapping("/exists/{id}")
	public boolean existsById(@PathVariable int id)
	{
		System.out.println("\"GET - /api/persons/exists/{id} invoked\"");
		boolean person = personRepository.existsById(id);
		return person;
	}
	
	@GetMapping("/findAllById/{ids}")
	public List<Person> findByAllId(@PathVariable List<Integer> ids)
	{
		System.out.println("\"GET - /api/persons/findAllById - invoked\"");
		List<Person> personList = personRepository.findAllById(ids);
		return personList;
	}
	
	@GetMapping("/count")
	public long getPersonCount()
	{
		System.out.println("GET - /api/persons/count invoked!");
		
		long count = personRepository.count();
		System.out.println("Person object count : " + count);
		
		return count;
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletePersonById(@PathVariable int id)
	{
		System.out.println("Delete - /api/person/delete/{id} invoked!, id="+id);
		personRepository.deleteById(id);
	}
	
	@DeleteMapping("/deletePerson")
	public void deletePerson(@RequestBody Person person)
	{
		System.out.println("DELETE - /person invoked!");
		System.out.println("Person object received : " + person);
		personRepository.delete(person);

	}
	
	@DeleteMapping("/deleteAllById/{ids}")
	public void deleteAllById(@PathVariable List<Integer> ids)
	{
		System.out.println("\"Delete - /api/persons/deleteAllById - invoked\"");
		personRepository.deleteAllById(ids);
	}
	
	@DeleteMapping("/deleteAll")
	public void deleteAll()
	{
		System.out.println("Delete - /api/person/deleteAll invoked!");
		personRepository.deleteAll();
	}
	
}

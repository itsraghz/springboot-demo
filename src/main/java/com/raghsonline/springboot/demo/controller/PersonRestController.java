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
	
	@PutMapping
	public void updatePerson(@RequestBody Person person)
	{
		System.out.println("PUT - /person invoked!");
		System.out.println("Person object received : " + person);
		
		Person personUpdated = personRepository.save(person);
		System.out.println("Person object Inserted : " + personUpdated);
		
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
	
	@GetMapping("/searchById/{id}")
	public boolean checkPersonById(@PathVariable int id)
	{
		System.out.println("GET - /api/person//search/{id} invoked!, id="+id);
		boolean isPersonPresent = personRepository.existsById(id);
		System.out.println("isPersonPresent : " + isPersonPresent);
		return isPersonPresent;
	}
	
	@PostMapping("/addPersons")
	public void addPersons(@RequestBody List<Person> personList)
	{
		System.out.println("POST - /addPersons invoked!");
		System.out.println("Person List received : " + personList);
		List<Person> personsInserted = personRepository.saveAll(personList);
		System.out.println("Persons Inserted : " + personsInserted);
	}
	
	@GetMapping("/getPersonsById")
	public List<Person> findAllByIds()
	{
		System.out.println("GET - /api/person/getPersonsById invoked!");
		List<Integer> personIds = List.of(1, 2, 3, 4);
		List<Person> personList = personRepository.findAllById(personIds);
		return personList;
	}
	
	@GetMapping("/getCount")
	public long getCount()
	{
		System.out.println("GET - /api/person/getCount invoked!");
		long count = personRepository.count();
		return count;
	}
	
	@DeleteMapping("/{id}")
	public void deletePersonById(@PathVariable int id)
	{
		System.out.println("DELETE - /api/person/{id} invoked!, id="+id);
		personRepository.deleteById(id);
	}
	
	@DeleteMapping("/deletePerson")
	public void deletePerson(@RequestBody Person person)
	{
		System.out.println("DELETE - /person invoked!");
		System.out.println("Person object received : " + person);
		personRepository.delete(person);
		
	}
	
	@DeleteMapping("/deleteAllById")
	public void deleteAllById()
	{
		System.out.println("GET - /api/person/deleteAllById invoked!");
		List<Integer> personIds = List.of(2, 3);
		personRepository.deleteAllById(personIds);
	}
	
	@DeleteMapping("/deletePersons")
	public void deletePersons(@RequestBody List<Person> personList)
	{
		System.out.println("DELETE - /deletePersons invoked!");
		System.out.println("Person List received : " + personList);
		personRepository.deleteAll(personList);
	}
	
	@DeleteMapping
	public void deleteAll()
	{
		System.out.println("DELETE - /deleteAll invoked!");
		personRepository.deleteAll();
	}
	
}

package com.example.cassandra;

import com.datastax.oss.driver.shaded.guava.common.collect.ImmutableSet;
import com.example.cassandra.model.Person;
import com.example.cassandra.repository.PersonRepository;
import org.instancio.Instancio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableCassandraRepositories(basePackages = "com.example.cassandra")
public class CassandraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CassandraApplication.class, args);
	}

	@Autowired
	private PersonRepository personRepository;

	@Override
	public void run(String... args) throws Exception {
		personRepository.deleteAll();

		// 1. save
		Person person = Person.builder()
				.id(UUID.randomUUID())
				.title("Head First Java")
				.publisher("O'Reilly Media")
				.tags(ImmutableSet.of("Computer", "Software"))
				.build();
		personRepository.save(person);

		// 2. saveAll
		int count = 10;
		List<Person> persons = Instancio.ofList(Person.class)
				.size(count)
				.create();
		persons = personRepository.saveAll(persons);
		System.out.println("2. saveAll");
		persons.forEach(System.out::println);


		// 3. update
		person.setTitle("Head First Java Second Edition");
		person = personRepository.save(person);
		System.out.println("3. " + person);

		// 4. findAll
		persons = personRepository.findAll();
		System.out.println("4. findAll");
		persons.forEach(System.out::println);
	}
}

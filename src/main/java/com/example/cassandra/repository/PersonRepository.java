package com.example.cassandra.repository;

import com.example.cassandra.model.Person;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CassandraRepository<Person, Long> {
}

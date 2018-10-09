package com.ignite.repository;

import java.util.List;

import org.apache.ignite.springdata20.repository.IgniteRepository;
import org.apache.ignite.springdata20.repository.config.Query;
import org.apache.ignite.springdata20.repository.config.RepositoryConfig;

import com.ignite.model.Student;

@RepositoryConfig(cacheName = "StudentCache")
public interface StudentRepository extends IgniteRepository<Student, Long> {

	@Query("SELECT * FROM Student ORDER BY 1")
	public List<Student> findAll();

}

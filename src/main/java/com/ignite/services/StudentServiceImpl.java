package com.ignite.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignite.model.Student;
import com.ignite.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public List<Student> getAll() {
		List<Student> students = studentRepo.findAllByOrderById();
		return students;
	}

	@Override
	public Student getOne(Long id) {
		return studentRepo.findById(id).get();
	}
}

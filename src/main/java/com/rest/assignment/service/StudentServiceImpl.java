package com.rest.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.assignment.dao.StudentDAO;
import com.rest.assignment.entity.Course;
import com.rest.assignment.entity.Student;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;
	
	@Override
	public List<Student> findAll() {	
		return studentDAO.findAll();
	}

	@Override
	public Student findById(int id) {
		return studentDAO.findById(id);
	}

	@Override
	public void save(Student student) {
		studentDAO.save(student);
	}

	@Override
	public void deleteById(int id) {
		studentDAO.deleteById(id);
	}

	@Override
	public Student addCourseToStudent(Student student, Course course){
		return studentDAO.addCourseToStudent(student,course);
	}

	@Override
	public Student deleteCourseFromStudent(Student student, Course course) {
		return studentDAO.deleteCourseFromStudent(student,course);
	}
}
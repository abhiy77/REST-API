package com.rest.assignment.dao;

import java.util.List;

import com.rest.assignment.entity.Course;
import com.rest.assignment.entity.Student;

public interface StudentDAO {

	public List<Student> findAll();
	
	public Student findById(int id);
	
	public void save(Student student);
	
	public void deleteById(int id);

	public Student addCourseToStudent(Student student, Course course);

	public Student deleteCourseFromStudent(Student student, Course course);
}

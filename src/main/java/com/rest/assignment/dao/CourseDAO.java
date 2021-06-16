package com.rest.assignment.dao;

import java.util.List;

import com.rest.assignment.entity.Course;
import com.rest.assignment.entity.Student;

public interface CourseDAO {

	//public List<Course> findAll(PageRequest pageRequest);
	
	public Course findById(int id);
	
	public void save(Course course);
	
	public void deleteById(int id);

	public List<Course> findAll();

	public Course addStudentToCourse(Course course, Student student);

	public Course deleteStudentFromCourse(Course course, Student student);
}

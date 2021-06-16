package com.rest.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.assignment.dao.CourseDAO;
import com.rest.assignment.entity.Course;
import com.rest.assignment.entity.Student;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDAO courseDAO;
	
	@Override
	public List<Course> findAll() {
		return courseDAO.findAll();
	}

	@Override
	public Course findById(int id) {
		return courseDAO.findById(id);
	}

	@Override
	public void save(Course course) {
		courseDAO.save(course);
	}

	@Override
	public void deleteById(int id) {
		courseDAO.deleteById(id);
	}


	@Override
	public Course addStudentToCourse(Course course, Student student) {
		return courseDAO.addStudentToCourse(course,student);
	}

	@Override
	public Course deleteStudentFromCourse(Course course, Student student) {
		return courseDAO.deleteStudentFromCourse(course,student);
	}
	
//	@Override
//	public List<Course> findAll(PageRequest pageRequest) { 
//		return courseDAO.findAll(pageRequest);
//	}

	
}
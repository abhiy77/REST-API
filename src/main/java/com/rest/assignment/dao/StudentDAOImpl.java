package com.rest.assignment.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.assignment.entity.Student;
import com.rest.assignment.entity.Course;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Student> findAll() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Student> query = currentSession.createQuery("from Student",Student.class);
		List<Student> students = query.getResultList();
		return students;
	}

	@Override
	public Student findById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Student student = currentSession.get(Student.class,id);
		return student;
	}

	@Override
	public void save(Student student) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(student);

	}

	@Override
	public void deleteById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Student> query = currentSession.createQuery("delete from Student where id=:studentId");
		query.setParameter("studentId", id);
		query.executeUpdate();

	}

	@Override
	public Student addCourseToStudent(Student student, Course course) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		List<Course> studentCourses = student.getCourses();
		if(!studentCourses.contains(course)) {
			studentCourses.add(course);
		}
		currentSession.saveOrUpdate(student);
		
		return student;
	}

	@Override
	public Student deleteCourseFromStudent(Student student, Course course) {
		Session currentSession = sessionFactory.getCurrentSession();

		List<Course> studentCourses = student.getCourses();
		if(studentCourses.contains(course)) {
			studentCourses.remove(course);
		}
		currentSession.saveOrUpdate(student);
		
		return student;
	}

}

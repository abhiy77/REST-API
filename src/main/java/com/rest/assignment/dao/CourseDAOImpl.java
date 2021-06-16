package com.rest.assignment.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.assignment.entity.Course;
import com.rest.assignment.entity.Student;
import com.rest.assignment.service.StudentService;

@Repository
public class CourseDAOImpl implements CourseDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private StudentService studentService;

//	@Override
//	public List<Course> findAll(PageRequest pageRequest) {
//		Session currentSession = sessionFactory.getCurrentSession();
//		
//		Query<Course> query = currentSession.createQuery("from Course",Course.class);
//		
//		query.setFirstResult(pageRequest.getPageNumber());
//		query.setMaxResults(pageRequest.getPageSize());
//		List<Course> courses = query.getResultList();
//		return courses;
//	}
	
	
	public List<Course> findAll() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Course> query = currentSession.createQuery("from Course",Course.class);
		
		List<Course> courses = query.getResultList();
		return courses;
	}

	@Override
	public Course findById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Course course = currentSession.get(Course.class,id);
		return course;
	}

	@Override
	public void save(Course course) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(course);
	}

	@Override
	public void deleteById(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Course> query = currentSession.createQuery("delete from Course where id=:courseId");
		query.setParameter("courseId", id);
		query.executeUpdate();
	}

	@Override
	public Course addStudentToCourse(Course course, Student student) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		List<Student> studentsEnrolled = course.getStudents();
		if(!studentsEnrolled.contains(student)) {
			studentsEnrolled.add(student);
		}
		currentSession.saveOrUpdate(course);
		
		return course;
	}

	@Override
	public Course deleteStudentFromCourse(Course course, Student student) {
		Session currentSession = sessionFactory.getCurrentSession();
			
		List<Student> studentsEnrolled = course.getStudents();
		if(studentsEnrolled.contains(student)) {
			studentsEnrolled.remove(student);
		}
		currentSession.saveOrUpdate(course);
		
		return course;
	}



	

}

package com.rest.assignment.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.assignment.entity.Course;
import com.rest.assignment.entity.Student;
import com.rest.assignment.exceptionhandling.CourseNotFoundException;
import com.rest.assignment.exceptionhandling.StudentNotFoundException;
import com.rest.assignment.service.CourseService;
import com.rest.assignment.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {
	
	@Autowired 
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	
	@GetMapping("/")
	public List<Student> getStudents(){
		return studentService.findAll();
	}
	
	@GetMapping("/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		Student student = studentService.findById(studentId);
		
		if(student == null) {
			throw new StudentNotFoundException("Student not found : " + studentId);
		}
		
		return student;
	}
	
	@PostMapping("/")
	public Student createStudent(@RequestBody Student student) {
		
		student.setId(0);

		studentService.save(student);
		return student;
	}
	
	@PutMapping("/")
	public Student updateStudent(@RequestBody Student student) {
		studentService.save(student);
		return student;
	}
	
	@DeleteMapping("/{studentId}")
	public String deleteStudent(@PathVariable int studentId) {
		
		Student student = studentService.findById(studentId);
		
		if(student == null) {
			throw new StudentNotFoundException("Student not found : " + studentId);
		}
		
		studentService.deleteById(studentId);
		return "Deleted Student id: " + studentId;
	}
	
	@GetMapping("/{studentId}/courses")
	public List<Course> getStudentCourses(@PathVariable int studentId){
		Student student = studentService.findById(studentId);
		
		if(student == null) {
			throw new StudentNotFoundException("Student not found : " + studentId);
		}
		
		return student.getCourses();
	}
	
	@PostMapping("/{studentId}/courses/{courseId}")
	public List<Course> addCourseToStudent(@PathVariable int studentId,@PathVariable int courseId){
		
		Student student = studentService.findById(studentId);
		Course course = courseService.findById(courseId);
		
		if(student == null) {
			throw new StudentNotFoundException("Student not found : " + studentId);
		}
		
		if(course == null) {
			throw new CourseNotFoundException("Course not found : " + courseId);
		}
		
		Student updatedStudent = studentService.addCourseToStudent(student,course);
		return updatedStudent.getCourses();
	}
	
	@DeleteMapping("/{studentId}/courses/{courseId}")
	public List<Course> deleteCourseFromStudent(@PathVariable int studentId,@PathVariable int courseId){
		
		Student student = studentService.findById(studentId);
		Course course = courseService.findById(courseId);
		
		if(student == null) {
			throw new StudentNotFoundException("Student not found : " + studentId);
		}
		
		if(course == null) {
			throw new CourseNotFoundException("Course not found : " + courseId);
		}
		
		Student updatedStudent = studentService.deleteCourseFromStudent(student,course);
		return updatedStudent.getCourses();
	}
	
}
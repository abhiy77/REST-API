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
@RequestMapping("/api/courses")
public class CourseRestController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	public List<Course> getCourses(){
		return courseService.findAll();
	}
	
	
	@GetMapping("/{courseId}")
	public Course getCourse(@PathVariable int courseId) {
		Course course = courseService.findById(courseId);
		
		if(course == null) {
			throw new CourseNotFoundException("Course not found : " + courseId);
		}
			
		return course;
	}
	
	@PostMapping("/")
	public Course addCourse(@RequestBody Course course) {
		course.setId(0);
		
//		List<Student> students = course.getStudents();
//		for(Student s: students)
//			studentService.save(s);
		
		courseService.save(course);
		return course;
	}
	
	@PutMapping("/")
	public Course updateCourse(@RequestBody Course course) {
		courseService.save(course);
		return course;
	}
	
	@DeleteMapping("/{courseId}")
	public String deleteCourse(@PathVariable int courseId) {
		Course course = courseService.findById(courseId);
		
		if(course == null) {
			throw new CourseNotFoundException("Course not found : " + courseId);
		}
			
		courseService.deleteById(courseId);
		
		return "Deleted Course id : " + courseId;
	}
	
	@GetMapping("/{courseId}/students")
	public List<Student> getStudentsEnrolledInCourse(@PathVariable int courseId){
		Course course = courseService.findById(courseId);
		
		if(course == null) {
			throw new CourseNotFoundException("Course not found : " + courseId);
		}
		
		return course.getStudents();
	}
	
	@PostMapping("/{courseId}/students/{studentId}")
	public List<Student> addStudentToCourse(@PathVariable int courseId,@PathVariable int studentId){
		
		Student student = studentService.findById(studentId);
		Course course = courseService.findById(courseId);
		
		if(course == null) {
			throw new CourseNotFoundException("Course not found : " + courseId);
		}
		
		if(student == null) {
			throw new StudentNotFoundException("Student not found : " + studentId);
		}
		
		Course updatedCourse = courseService.addStudentToCourse(course,student);
		return updatedCourse.getStudents();
	}
	
	@DeleteMapping("/{courseId}/students/{studentId}")
	public List<Student> deleteStudentFromCourse(@PathVariable int courseId,@PathVariable int studentId){
		
		Student student = studentService.findById(studentId);
		Course course = courseService.findById(courseId);
		
		if(course == null) {
			throw new CourseNotFoundException("Course not found : " + courseId);
		}
		
		if(student == null) {
			throw new StudentNotFoundException("Student not found : " + studentId);
		}
		
		Course updatedCourse = courseService.deleteStudentFromCourse(course,student);
		return updatedCourse.getStudents();
	}
	
//	@GetMapping("/courses")
//	public List<Course> getCourses(@RequestParam int page, @RequestParam int limit,
//			@RequestParam Optional<String> sortBy){
//		return courseService.findAll(PageRequest.of(page,limit,Sort.Direction.ASC,sortBy.orElse("id")));
//	}
	
	
	
	
}

package com.rest.assignment.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;


@JsonIgnoreProperties(value= {"students"})
@Entity
@Table(name="course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	

	@NotNull
	@Column(name="course_name")
	private String courseName;
	
	@Column(name="course_rating")
	private int courseRating;
	
	
	@ManyToMany(fetch=FetchType.EAGER,
			cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
			name="course_student",
			joinColumns=@JoinColumn(name="course_id"),
			inverseJoinColumns=@JoinColumn(name="student_id")
			)
	private List<Student> students;
	
	
	
	public Course(int id, String courseName, int courseRating, List<Student> students) {
		this.id = id;
		this.courseName = courseName;
		this.courseRating = courseRating;
		this.students = students;
	}
	

	public Course() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public int getCourseRating() {
		return courseRating;
	}


	public void setCourseRating(int courseRating) {
		this.courseRating = courseRating;
	}


	public List<Student> getStudents() {
		return students;
	}


	public void setStudents(List<Student> students) {
		this.students = students;
	}


	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", courseRating=" + courseRating + ", students="
				+ students + "]";
	}
	
}

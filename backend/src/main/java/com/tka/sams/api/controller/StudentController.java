package com.tka.sams.api.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.sams.api.entity.Student;
import com.tka.sams.api.service.StudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin("http://localhost:4200")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/get-all-students")
	@PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
	public List<Student> getAllStudents() {
	    return studentService.getAllStudents();
	}

	@PostMapping("/add-student")
	@PreAuthorize("hasRole('FACULTY')")
	public Student createStudent(@RequestBody Student student) {
	    return studentService.createStudent(student);
	}

	@GetMapping("/get-student-by-id/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
	public Student getStudentById(@PathVariable Long id) {
	    return studentService.getStudentById(id);
	}

	@PutMapping("/update-student")
	@PreAuthorize("hasRole('FACULTY')")
	public Student updateStudent(@RequestBody Student studentDetails) {
	    return studentService.updateStudent(studentDetails);
	}

	@DeleteMapping("/delete-student/{id}")
	@PreAuthorize("hasRole('FACULTY')")
	public String deleteStudent(@PathVariable long id) {
	    return studentService.deleteStudent(id);
	}
}

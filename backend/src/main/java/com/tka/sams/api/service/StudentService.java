package com.tka.sams.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tka.sams.api.exceptions.ResourceNotFoundException;

import com.tka.sams.api.dao.StudentDao;
import com.tka.sams.api.entity.Student;

@Service
public class StudentService {

	@Autowired
	private StudentDao dao;

	public List<Student> getAllStudentsById(List<Long> studentIds) {
		return dao.getAllStudentsById(studentIds);
	}

	public List<Student> getAllStudents() {
		return dao.getAllStudents();
	}

	public Student createStudent(Student student) {
		return dao.createStudent(student);
	}

	public Student getStudentById(long id) {

	    Student student = dao.getStudentsById(id);

	    if (student == null) {
	        throw new ResourceNotFoundException("Student not found with id : " + id);
	    }

	    return student;
	}

	public Student updateStudent(Student studentDetails) {

	    Student student = dao.getStudentsById(studentDetails.getId());

	    if (student == null) {
	        throw new ResourceNotFoundException(
	                "Student not found with id : " + studentDetails.getId());
	    }

	    return dao.updateStudent(studentDetails);
	}

	public String deleteStudent(long id) {

	    Student student = dao.getStudentsById(id);

	    if (student == null) {
	        throw new ResourceNotFoundException("Student not found with id : " + id);
	    }

	    return dao.deleteStudent(id);
	}
}

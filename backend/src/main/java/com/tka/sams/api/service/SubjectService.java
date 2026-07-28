package com.tka.sams.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.sams.api.dao.SubjectDao;
import com.tka.sams.api.entity.Subject;
import com.tka.sams.api.exceptions.ResourceNotFoundException;

@Service
public class SubjectService {
	@Autowired
	private SubjectDao dao;

	public Subject getSubjectById(long id) {

	    Subject subject = dao.getSubjectById(id);

	    if (subject == null) {
	        throw new ResourceNotFoundException(
	                "Subject not found with id : " + id);
	    }

	    return subject;
	}

	public List<Subject> getAllSubjects() {
		
		return dao.getAllSubjects();
	}

	public Subject createSubject(Subject subject) {
		
		return dao.createSubject(subject);
	}

	public Subject updateSubject(Subject subjectDetails) {

	    Subject subject = dao.getSubjectById(subjectDetails.getId());

	    if (subject == null) {
	        throw new ResourceNotFoundException(
	                "Subject not found with id : " + subjectDetails.getId());
	    }

	    return dao.updateSubject(subjectDetails);
	}

	public String deleteSubject(long id) {

	    Subject subject = dao.getSubjectById(id);

	    if (subject == null) {
	        throw new ResourceNotFoundException(
	                "Subject not found with id : " + id);
	    }

	    return dao.deleteSubject(id);
	}
}

package com.dynamicform.app.grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dynamicform.app.util.Response;

@Service
public class GradeService {

	@Autowired
	GradeRepository gradeRepository;

	public Response save(String reqObj) {
		return gradeRepository.save(reqObj);
	}

	public Response update(String reqObj) {
		return gradeRepository.update(reqObj);
	}

	public Response delete(long reqId) {
		return gradeRepository.delete(reqId);
	}

	public Response list(String reqObj) {
		return gradeRepository.list(reqObj);
	}

	public Response getAll() {
		return gradeRepository.getAll();
	}

	public GradeEntity updateGrade(GradeEntity gradeEntity) {
		return gradeRepository.updateGrade(gradeEntity);
	}

	public GradeEntity findById(Long id) {
		return gradeRepository.findById(id);
	}

}

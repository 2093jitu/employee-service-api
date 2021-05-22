package com.dynamicform.app.grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dynamicform.app.util.Response;

@RestController
@RequestMapping("api/grade")
public class GradeController {

	@Autowired
	private GradeService gradeService;

	@PostMapping("/create")
	public Response create(@RequestBody String reqObj) {
		return gradeService.save(reqObj);
	}

	@PostMapping("/update")
	public Response update(@RequestBody String reqObj) {
		return gradeService.update(reqObj);
	}

	@DeleteMapping("/delete")
	public Response delete(@RequestParam("id") long reqId) {
		return gradeService.delete(reqId);
	}

	@GetMapping("/list")
	public Response getAll(@RequestBody(required = false) String reqObj) {
		return gradeService.list(reqObj);
	}
}

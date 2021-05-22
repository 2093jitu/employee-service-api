package com.dynamicform.app.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.dynamicform.app.util.Response;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public Response save(String reqObj) {
		return employeeRepository.save(reqObj);
	}

	public Response update(String reqObj) {
		return employeeRepository.update(reqObj);
	}

	public Response delete(Long reqId) {
		return employeeRepository.detele(reqId);
	}

	public Response list() {
		return employeeRepository.list();
	}

}

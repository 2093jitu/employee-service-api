package com.dynamicform.app.employeeAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dynamicform.app.employee.EmployeeRepository;
import com.dynamicform.app.util.Response;

@Service
public class EmployeeAccountService {

	@Autowired
	EmployeeAccountRepository employeeAccountRepository;

	public Response save(String reqObj) {
		return employeeAccountRepository.save(reqObj);
	}

	public Response update(String reqObj) {
		return employeeAccountRepository.update(reqObj);
	}

	public Response delete(Long reqId) {
		return employeeAccountRepository.detele(reqId);
	}

	public Response list(String reqObj) {
		return employeeAccountRepository.list(reqObj);
	}

}

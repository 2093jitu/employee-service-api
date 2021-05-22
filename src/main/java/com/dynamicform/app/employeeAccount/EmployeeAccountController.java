package com.dynamicform.app.employeeAccount;

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
@RequestMapping("api/employee-account")
public class EmployeeAccountController {

	@Autowired
	private EmployeeAccountService employeeAccountService;

	@PostMapping("/create")
	public Response create(@RequestBody String reqObj) {
		return employeeAccountService.save(reqObj);
	}

	@PutMapping("/update")
	public Response update(@RequestBody String reqObj) {
		return employeeAccountService.update(reqObj);
	}

	@DeleteMapping("/delete")
	public Response delete(@RequestParam("id") Long reqId) {
		return employeeAccountService.delete(reqId);
	}

	@GetMapping("/list")
	public Response getAll(@RequestBody(required = false) String reqObj) {
		return employeeAccountService.list(reqObj);
	}

}

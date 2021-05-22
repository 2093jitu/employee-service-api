package com.dynamicform.app.transaction;

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
@RequestMapping("api/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/create")
	public Response create(@RequestBody String reqObj) {
		return transactionService.save(reqObj);
	}

	@PutMapping("/update")
	public Response update(@RequestBody String reqObj) {
		return transactionService.update(reqObj);
	}

	@DeleteMapping("/delete")
	public Response delete(@RequestParam("id") Long reqId) {
		return transactionService.delete(reqId);
	}

	@GetMapping("/list")
	public Response getAll(@RequestBody(required = false) String reqObj) {
		return transactionService.list(reqObj);
	}

	@GetMapping("/find-by-com-id")
	public Response getByComId(@RequestBody(required = false) String reqObj) {
		return transactionService.getByComId(reqObj);
	}

}

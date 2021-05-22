package com.dynamicform.app.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.dynamicform.app.util.Response;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public Response save(String reqObj) {
		return transactionRepository.save(reqObj);
	}

	public Response update(String reqObj) {
		return transactionRepository.update(reqObj);
	}

	public Response delete(Long reqId) {
		return transactionRepository.detele(reqId);
	}

	public Response list(String reqObj) {
		return transactionRepository.list(reqObj);
	}

	public Response getByComId(String reqObj) {
		return transactionRepository.getByComId(reqObj);
	}

}

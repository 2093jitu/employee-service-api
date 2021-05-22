package com.dynamicform.app.companyAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dynamicform.app.util.Response;

@Service
public class CompanyAccountService {

	@Autowired
	CompanyAccountRepository companyAccountRepository;

	public Response save(String reqObj) {
		return companyAccountRepository.save(reqObj);
	}

	public Response update(String reqObj) {
		return companyAccountRepository.update(reqObj);
	}

	public Response delete(Long reqId) {
		return companyAccountRepository.detele(reqId);
	}

	public Response list(String reqObj) {
		return companyAccountRepository.list(reqObj);
	}

}

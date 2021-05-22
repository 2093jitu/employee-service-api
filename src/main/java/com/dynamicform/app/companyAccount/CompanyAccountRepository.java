package com.dynamicform.app.companyAccount;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.dynamicform.app.base.BaseRepository;
import com.dynamicform.app.transaction.TransactionEntity;
import com.dynamicform.app.transaction.TransactionService;
import com.dynamicform.app.util.Def;
import com.dynamicform.app.util.Response;

@Repository
@Transactional
public class CompanyAccountRepository extends BaseRepository  {

	@Autowired
	TransactionService transactionService;

	public Response save(String reqObj) {

		JSONObject json = new JSONObject(reqObj);
		String companyAccountStr = Def.getString(json, "companyAccount");
		String transactionStr = Def.getString(json, "transaction");

		CompanyAccountEntity companyAccountEntity = objectMapperReadValue(companyAccountStr,
				CompanyAccountEntity.class);
		TransactionEntity transactionEntity = objectMapperReadValue(transactionStr, TransactionEntity.class);

		Response res = baseOnlySave(companyAccountEntity);
		CompanyAccountEntity afterSave = getValueFromObject(res.getObj(), CompanyAccountEntity.class);
		transactionEntity.setComAccount(afterSave);

		Response tranRes = transactionService.save(objectToJson(transactionEntity));

		if (afterSave != null && tranRes.isSuccess()) {
			return getSuccessResponse("Saved Successfully");
		}

		return getErrorResponse("Save fail !!");

	}

	public Response update(String reqObj) {

		CompanyAccountEntity companyAccountEntity = objectMapperReadValue(reqObj, CompanyAccountEntity.class);
		CompanyAccountEntity obj = findById(companyAccountEntity.getId());
		Response response = new Response();

		if (obj == null) {
			return getErrorResponse("Record Not Found!!.");
		}

		response = baseUpdate(companyAccountEntity);
		if (response.isSuccess()) {
			return getSuccessResponse("Update Successfully");
		}

		return getErrorResponse("Update fail !!");
	}

	public Response detele(Long id) {
		CompanyAccountEntity companyAccountEntity = findById(id);
		if (companyAccountEntity == null) {
			return getErrorResponse("Record not found!");
		}
		return baseDelete(companyAccountEntity);
	}

	public Response list(String reqObj) {
		CompanyAccountEntity companyAccountEntity = null;
		if (null != reqObj) {
			companyAccountEntity = objectMapperReadValue(reqObj, CompanyAccountEntity.class);
		}
		return baseList(criteriaQuery(companyAccountEntity));
	}

	public CompanyAccountEntity findById(Long id) {
		CompanyAccountEntity companyAccountEntity = new CompanyAccountEntity();
		companyAccountEntity.setId(id);
		Response response = baseFindById(criteriaQuery(companyAccountEntity));
		if (response.isSuccess()) {
			return getValueFromObject(response.getObj(), CompanyAccountEntity.class);
		}
		return null;
	}

	// Non API
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private CriteriaQuery criteriaQuery(CompanyAccountEntity filter) {
		init();

		List<Predicate> p = new ArrayList<Predicate>();
		p = criteriaCondition(filter, null, null);

		if (!CollectionUtils.isEmpty(p)) {
			Predicate[] pArray = p.toArray(new Predicate[] {});
			Predicate predicate = builder.and(pArray);
			criteria.where(predicate);
		}
		return criteria;
	}

	@SuppressWarnings({ "unchecked" })
	private List<Predicate> criteriaCondition(CompanyAccountEntity filter, CriteriaBuilder builder,
			Root<CompanyAccountEntity> root) {
		if (builder == null) {
			builder = super.builder;
		}
		if (root == null) {
			root = super.root;
		}
		List<Predicate> p = new ArrayList<Predicate>();
		if (filter != null) {

			if (filter.getId() != null && filter.getId() > 0) {
				Predicate condition = builder.equal(root.get("id"), filter.getId());
				p.add(condition);
			}
		}
		return p;
	}

	@SuppressWarnings("rawtypes")
	private void init() {
		initEntityManagerBuilderCriteriaQueryRoot(CompanyAccountEntity.class);
		@SuppressWarnings("unused")
		CriteriaBuilder builder = super.builder;
		@SuppressWarnings("unused")
		CriteriaQuery criteria = super.criteria;
		@SuppressWarnings("unused")
		Root root = super.root;
	}

}

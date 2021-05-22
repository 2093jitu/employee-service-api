package com.dynamicform.app.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.dynamicform.app.base.BaseRepository;
import com.dynamicform.app.util.Response;

@Repository
@Transactional
public class TransactionRepository extends BaseRepository {

	public Response save(String reqObj) {
		TransactionEntity transactionEntity = objectMapperReadValue(reqObj, TransactionEntity.class);

		Response res = baseOnlySave(transactionEntity);

		if (res.isSuccess()) {
			return getSuccessResponse("Saved Successfully");
		}

		return getErrorResponse("Save fail !!");

	}

	public Response update(String reqObj) {

		TransactionEntity transactionEntity = objectMapperReadValue(reqObj, TransactionEntity.class);
		TransactionEntity obj = findById(transactionEntity.getId());
		Response response = new Response();

		if (obj == null) {
			return getErrorResponse("Record Not Found!!.");
		}

		response = baseUpdate(transactionEntity);
		if (response.isSuccess()) {
			return getSuccessResponse("Update Successfully");
		}

		return getErrorResponse("Update fail !!");
	}

	public Response detele(Long id) {
		TransactionEntity transactionEntity = findById(id);
		if (transactionEntity == null) {
			return getErrorResponse("Record not found!");
		}
		return baseDelete(transactionEntity);
	}

	public Response list(String reqObj) {
		TransactionEntity transactionEntity = null;
		if (null != reqObj) {
			transactionEntity = objectMapperReadValue(reqObj, TransactionEntity.class);
		}
		return baseList(criteriaQuery(transactionEntity));
	}

	public Response getByComId(String reqObj) {
		TransactionEntity transactionEntity = null;
		if (null != reqObj) {
			transactionEntity = objectMapperReadValue(reqObj, TransactionEntity.class);
		}
		return baseList(criteriaQuery(transactionEntity));
	}

	public TransactionEntity findById(Long id) {
		TransactionEntity transactionEntity = new TransactionEntity();
		transactionEntity.setId(id);
		Response response = baseFindById(criteriaQuery(transactionEntity));
		if (response.isSuccess()) {
			return getValueFromObject(response.getObj(), TransactionEntity.class);
		}
		return null;
	}

	// Non API
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private CriteriaQuery criteriaQuery(TransactionEntity filter) {
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
	private List<Predicate> criteriaCondition(TransactionEntity filter, CriteriaBuilder builder,
			Root<TransactionEntity> root) {
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
			if (filter.getComAccount() != null) {
				Predicate condition = builder.equal(root.get("comAccount"), filter.getComAccount());
				p.add(condition);
			}
		}
		return p;
	}

	@SuppressWarnings("rawtypes")
	private void init() {
		initEntityManagerBuilderCriteriaQueryRoot(TransactionEntity.class);
		@SuppressWarnings("unused")
		CriteriaBuilder builder = super.builder;
		@SuppressWarnings("unused")
		CriteriaQuery criteria = super.criteria;
		@SuppressWarnings("unused")
		Root root = super.root;
	}

}

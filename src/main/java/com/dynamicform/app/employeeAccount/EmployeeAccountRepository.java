package com.dynamicform.app.employeeAccount;

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
public class EmployeeAccountRepository extends BaseRepository {

	public Response save(String reqObj) {
		EmployeeAccountEntity employeeAccountEntity = objectMapperReadValue(reqObj, EmployeeAccountEntity.class);

		Response res = baseOnlySave(employeeAccountEntity);

		if (res.isSuccess()) {
			return getSuccessResponse("Saved Successfully");
		}

		return getErrorResponse("Save fail !!");

	}

	public Response update(String reqObj) {

		EmployeeAccountEntity employeeAccountEntity = objectMapperReadValue(reqObj, EmployeeAccountEntity.class);
		EmployeeAccountEntity obj = findById(employeeAccountEntity.getId());
		Response response = new Response();

		if (obj == null) {
			return getErrorResponse("Record Not Found!!.");
		}

		response = baseUpdate(employeeAccountEntity);
		if (response.isSuccess()) {
			return getSuccessResponse("Update Successfully");
		}

		return getErrorResponse("Update fail !!");
	}

	public Response detele(Long id) {
		EmployeeAccountEntity employeeAccountEntity = findById(id);
		if (employeeAccountEntity == null) {
			return getErrorResponse("Record not found!");
		}
		return baseDelete(employeeAccountEntity);
	}

	public Response list(String reqObj) {
		EmployeeAccountEntity employeeAccountEntity = null;
		if (null != reqObj) {
			employeeAccountEntity = objectMapperReadValue(reqObj, EmployeeAccountEntity.class);
		}
		return baseList(criteriaQuery(employeeAccountEntity));
	}

	public EmployeeAccountEntity findById(Long id) {
		EmployeeAccountEntity employeeAccountEntity = new EmployeeAccountEntity();
		employeeAccountEntity.setId(id);
		Response response = baseFindById(criteriaQuery(employeeAccountEntity));
		if (response.isSuccess()) {
			return getValueFromObject(response.getObj(), EmployeeAccountEntity.class);
		}
		return null;
	}

	// Non API
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private CriteriaQuery criteriaQuery(EmployeeAccountEntity filter) {
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
	private List<Predicate> criteriaCondition(EmployeeAccountEntity filter, CriteriaBuilder builder,
			Root<EmployeeAccountEntity> root) {
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
		initEntityManagerBuilderCriteriaQueryRoot(EmployeeAccountEntity.class);
		@SuppressWarnings("unused")
		CriteriaBuilder builder = super.builder;
		@SuppressWarnings("unused")
		CriteriaQuery criteria = super.criteria;
		@SuppressWarnings("unused")
		Root root = super.root;
	}

}

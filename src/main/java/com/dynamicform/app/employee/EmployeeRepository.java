package com.dynamicform.app.employee;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.dynamicform.app.base.BaseRepository;
import com.dynamicform.app.grade.GradeService;
import com.dynamicform.app.util.Response;

@Repository
@Transactional
public class EmployeeRepository extends BaseRepository {

	public Response save(String reqObj) {

		EmployeeEntity employeeEntity = objectMapperReadValue(reqObj, EmployeeEntity.class);
		Response responseForDuplicateCheck = duplicateChecEmployeeId(employeeEntity);

		if (!responseForDuplicateCheck.isValid()) {
			return getErrorResponse("Duplicate Not Allow !!");
		}

		if (employeeEntity.getEmpId() != null && employeeEntity.getEmpId().length() != 4) {
			return getErrorResponse("Employee Id must be 4 digit");
		}

		Response res = baseOnlySave(employeeEntity);

		if (res.isSuccess()) {
			return getSuccessResponse("Saved Successfully");
		}

		return getErrorResponse("Save fail !!");

	}

	public Response update(String reqObj) {

		EmployeeEntity employeeEntity = objectMapperReadValue(reqObj, EmployeeEntity.class);
		EmployeeEntity obj = findById(employeeEntity.getId());

		Response response = new Response();
		employeeEntity.setId(null);

		employeeEntity.setIdNotEqual(obj.getId());
		Response responseForDuplicateCheck = duplicateChecEmployeeId(employeeEntity);

		if (!responseForDuplicateCheck.isValid()) {
			return getErrorResponse("Duplicate Not Allow !!");
		}

		if (employeeEntity.getEmpId() != null && employeeEntity.getEmpId().length() != 4) {
			return getErrorResponse("Employee Id must be 4 digit");
		}

		if (obj == null) {
			return getErrorResponse("Record Not Found!!.");
		}

		employeeEntity.setId(employeeEntity.getIdNotEqual());
		response = baseUpdate(employeeEntity);
		if (response.isSuccess()) {
			return getSuccessResponse("Update Successfully");
		}

		return getErrorResponse("Update fail !!");
	}

	public Response detele(Long id) {
		EmployeeEntity employeeEntity = findById(id);
		if (employeeEntity == null) {
			return getErrorResponse("Record not found!");
		}
		return baseDelete(employeeEntity);
	}

	public Response list() {
		EmployeeEntity billsBToBLabPolicyObj = new EmployeeEntity();
		String data = objectToJson(baseList(criteriaQuery(billsBToBLabPolicyObj)));
		return baseList(criteriaQuery(billsBToBLabPolicyObj));
	}

	public EmployeeEntity findById(Long id) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setId(id);
		Response response = baseFindById(criteriaQuery(employeeEntity));
		if (response.isSuccess()) {
			System.out.println(objectToJson(response.getObj()));
			return getValueFromObject(response.getObj(), EmployeeEntity.class);
		}
		return null;
	}

	private Response duplicateChecEmployeeId(EmployeeEntity employeeEntity) {
		Response responce = new Response();

		if (employeeEntity == null) {
			return getErrorResponse("Search Id not found!");
		}

		Long totalCount = totalCountWithDConjunction(employeeEntity);

		if (totalCount.longValue() > 0) {
			responce.setValid(false);
			return responce;
		} else {
			responce.setValid(true);
			return responce;
		}

	}

	private Long totalCountWithDConjunction(EmployeeEntity filter) {

		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = longCriteriaQuery(builder);
		Root<EmployeeEntity> root = from(EmployeeEntity.class, criteriaQuery);

		return totalCount(builder, criteriaQuery, root, criteriaCondition(filter, builder, root));
	}

	// Non API
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private CriteriaQuery criteriaQuery(EmployeeEntity filter) {
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
	private List<Predicate> criteriaCondition(EmployeeEntity filter, CriteriaBuilder builder,
			Root<EmployeeEntity> root) {
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

			if (filter.getIdNotEqual() != null) {
				Predicate condition = builder.notEqual(root.get("id"), filter.getIdNotEqual());
				p.add(condition);
			}

			if (filter.getEmpId() != null) {
				Predicate condition = builder.equal(root.get("empId"), filter.getEmpId());
				p.add(condition);
			}

		}
		return p;
	}

	@SuppressWarnings("rawtypes")
	private void init() {
		initEntityManagerBuilderCriteriaQueryRoot(EmployeeEntity.class);
		@SuppressWarnings("unused")
		CriteriaBuilder builder = super.builder;
		@SuppressWarnings("unused")
		CriteriaQuery criteria = super.criteria;
		@SuppressWarnings("unused")
		Root root = super.root;
	}

}

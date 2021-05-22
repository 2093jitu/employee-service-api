package com.dynamicform.app.grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.dynamicform.app.base.BaseRepository;
import com.dynamicform.app.util.Response;

@Repository
@Transactional
public class GradeRepository extends BaseRepository {

	private final Long ADD_AMOUNT = 5000l;
	private final Long HOUSE_RENT = 20l;
	private final Long MEDICAL_ALLOWANCE = 15l;

	public String updateGradeStaement() {

		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE grade SET ");
		sql.append(" id = ?, name = ?, MAX_POST = ?, ");
		sql.append(" basic = ?, HOUSE_RENT = ?, medical = ?, salary =?");
		sql.append(" WHERE id = ?");

		return sql.toString();

	}

	public GradeEntity updateGrade(GradeEntity gradeEntity) {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		con = getOraConnection();

		try {

			pstm = con.prepareStatement(updateGradeStaement());
			pstm.setLong(1, gradeEntity.getId());
			pstm.setString(2, gradeEntity.getName());
			pstm.setLong(3, gradeEntity.getMaxPost());

			pstm.setDouble(4, gradeEntity.getBasic());
			pstm.setDouble(5, gradeEntity.getHouseRent());
			pstm.setDouble(6, gradeEntity.getMedical());
			pstm.setDouble(7, gradeEntity.getSalary());
			pstm.setLong(8, gradeEntity.getId());

			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			finalyConPstmRs(con, pstm, rs);
		}

		return gradeEntity;
	}

	public Response save(String reqObj) {
		GradeEntity GradeEntity = objectMapperReadValue(reqObj, GradeEntity.class);

		GradeEntity lastSavedGrade = new GradeEntity();

		for (int i = 6; i >= 1; i--) {

			GradeEntity grade = new GradeEntity();
			Double basicSalary = i == 6 ? GradeEntity.getBasic() : lastSavedGrade.getBasic() + ADD_AMOUNT;
			Long maxPost = (i == 1 || i == 2 ? 1l : 2l);

			grade.setMaxPost(maxPost);
			grade.setBasic(basicSalary);
			grade.setName("Grade/Rank - " + i);

			Double houseRent = (basicSalary / 100) * HOUSE_RENT;
			Double medical = (basicSalary / 100) * MEDICAL_ALLOWANCE;
			Double salary = basicSalary + houseRent + medical;

			grade.setHouseRent(houseRent);
			grade.setMedical(medical);
			grade.setSalary(salary);

			Response res = baseOnlySave(grade);

			lastSavedGrade = getValueFromObject(res.getObj(), GradeEntity.class);

		}

		return getSuccessResponse("Update Successfully");

	}

	public Response update(String reqObj) {

		GradeEntity gradeEntity = objectMapperReadValue(reqObj, GradeEntity.class);
		GradeEntity lastSavedGrade = new GradeEntity();

		Response listRes = getAll();
		List<GradeEntity> grades = (List<GradeEntity>) getValueFromObject(listRes.getItems(), GradeEntity.class);
		Collections.sort(grades, (o1, o2) -> o1.getId().compareTo(o2.getId()));

		if (grades.get(0).getId() == gradeEntity.getId()) {
			for (int i = 0; i < grades.size(); i++) {
				GradeEntity dbGrade = grades.get(i);
				Double basicSalary = i == 0 ? gradeEntity.getBasic() : lastSavedGrade.getBasic() + ADD_AMOUNT;

				dbGrade.setBasic(basicSalary);

				Double houseRent = (basicSalary / 100) * HOUSE_RENT;
				Double medical = (basicSalary / 100) * MEDICAL_ALLOWANCE;
				Double salary = basicSalary + houseRent + medical;

				dbGrade.setHouseRent(houseRent);
				dbGrade.setMedical(medical);
				dbGrade.setSalary(salary);
				lastSavedGrade = updateGrade(dbGrade);
			}
		} else {
			GradeEntity bdObj = findById(gradeEntity.getId());
			updateGrade(bdObj);
			return getSuccessResponse("Update Successfully");
		}

		return getSuccessResponse("Update Successfully");
	}

	public Response list(String reqObj) {
		GradeEntity billsBToBLabPolicyObj = null;
		if (null != reqObj) {
			billsBToBLabPolicyObj = objectMapperReadValue(reqObj, GradeEntity.class);
		}

		Response res = baseList(criteriaQuery(billsBToBLabPolicyObj));

		if (res.isSuccess() && !CollectionUtils.isEmpty(res.getItems())) {
			List<GradeEntity> grades = (List<GradeEntity>) getValueFromObject(res.getItems(), GradeEntity.class);
			Collections.sort(grades, (o1, o2) -> o1.getId().compareTo(o2.getId()));
			Response finalRes = new Response();
			finalRes.setItems(grades);
			return getSuccessResponse("Data Found !.", finalRes);
		}

		return getErrorResponse("Data Not Found!");
	}

	public Response getAll() {
		GradeEntity billsBToBLabPolicyObj = new GradeEntity();
		return baseList(criteriaQuery(billsBToBLabPolicyObj));
	}

	public Response delete(Long id) {

		GradeEntity billsBToBLabPolicyObj = findById(id);
		if (billsBToBLabPolicyObj == null) {
			return getErrorResponse("Record not found!");
		}
		return baseDelete(billsBToBLabPolicyObj);

	}

	public GradeEntity findById(Long id) {
		GradeEntity GradeEntity = new GradeEntity();
		GradeEntity.setId(id);
		Response response = baseFindById(criteriaQuery(GradeEntity));
		if (response.isSuccess()) {
			return getValueFromObject(response.getObj(), GradeEntity.class);
		}
		return null;
	}

	// Non API
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private CriteriaQuery criteriaQuery(GradeEntity filter) {
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
	private List<Predicate> criteriaCondition(GradeEntity filter, CriteriaBuilder builder, Root<GradeEntity> root) {
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
		initEntityManagerBuilderCriteriaQueryRoot(GradeEntity.class);
		@SuppressWarnings("unused")
		CriteriaBuilder builder = super.builder;
		@SuppressWarnings("unused")
		CriteriaQuery criteria = super.criteria;
		@SuppressWarnings("unused")
		Root root = super.root;
	}

}

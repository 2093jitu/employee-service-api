package com.dynamicform.app.employee;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.dynamicform.app.grade.GradeEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "employee")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "EMP_ID", length = 5)
	private String empId;

	@Column(name = "EMP_NAME", length = 50)
	private String empName;

	@Column(name = "ADDRESS", length = 100)
	private String address;

	@Column(name = "MOBILE", length = 15)
	private String mobile;

	@OneToOne
	@JoinColumn(name = "GRADE_ID", referencedColumnName = "ID")
	private GradeEntity grade;

	@Transient
	private Long idNotEqual;
	
	@Transient
	private List<EmployeeEntity> employeeList = new ArrayList<EmployeeEntity>();
	
	public List<EmployeeEntity> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<EmployeeEntity> employeeList) {
		this.employeeList = employeeList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public GradeEntity getGrade() {
		return grade;
	}

	public void setGrade(GradeEntity grade) {
		this.grade = grade;
	}

	public Long getIdNotEqual() {
		return idNotEqual;
	}

	public void setIdNotEqual(Long idNotEqual) {
		this.idNotEqual = idNotEqual;
	}

}

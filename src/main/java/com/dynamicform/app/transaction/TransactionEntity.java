package com.dynamicform.app.transaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dynamicform.app.companyAccount.CompanyAccountEntity;
import com.dynamicform.app.employeeAccount.EmployeeAccountEntity;

@Entity
@Table(name = "TRANSACTION")
public class TransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "DES", length = 100)
	private String des;

	@Column(name = "CR_AMOUNT")
	private Double crAmount;

	@Column(name = "DR_AMOUNT")
	private Double drAmount;

	@OneToOne
	@JoinColumn(name = "COM_ACC_ID", referencedColumnName = "ID")
	private CompanyAccountEntity comAccount;

	@OneToOne
	@JoinColumn(name = "EMP_ACC_ID", referencedColumnName = "ID")
	private EmployeeAccountEntity empAccount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Double getCrAmount() {
		return crAmount;
	}

	public void setCrAmount(Double crAmount) {
		this.crAmount = crAmount;
	}

	public Double getDrAmount() {
		return drAmount;
	}

	public void setDrAmount(Double drAmount) {
		this.drAmount = drAmount;
	}

	public CompanyAccountEntity getComAccount() {
		return comAccount;
	}

	public void setComAccount(CompanyAccountEntity comAccount) {
		this.comAccount = comAccount;
	}

	public EmployeeAccountEntity getEmpAccount() {
		return empAccount;
	}

	public void setEmpAccount(EmployeeAccountEntity empAccount) {
		this.empAccount = empAccount;
	}

}

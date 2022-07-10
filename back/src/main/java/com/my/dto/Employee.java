package com.my.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class Employee { 
	private int employeeId;
	private String employeePassword;
	private String employeeNameKr;
	private String employeeNameEng;
	private Date employeeHiredate;
	private int employeePhonenumber;
	private String employeeEmail;
	private String employeeAddress;
	private String employeeGender;
	private Date employeeBirthday;
	private int employeeResign;
	private int employeeAuthority;
	
	private Department department;
	//private List<Department> department;
	
	private HolidayInfo holidayInfo;
	
	public Employee() {
		
	}
	
	public Employee(int employeeId) {
		super();
		this.employeeId = employeeId;
	}

	public Employee(int employeeId, String employeeNameKr) {
		super();
		this.employeeId = employeeId;
		this.employeeNameKr = employeeNameKr;
	}


	public Employee(int employeeId, String employeePassword, String employeeNameKr, String employeeNameEng,
			Date employeeHiredate, int employeePhonenumber, String employeeEmail, String employeeAddress,
			String employeeGender, Date employeeBirthday, Department department) {
		super();
		this.employeeId = employeeId;
		this.employeePassword = employeePassword;
		this.employeeNameKr = employeeNameKr;
		this.employeeNameEng = employeeNameEng;
		this.employeeHiredate = employeeHiredate;
		this.employeePhonenumber = employeePhonenumber;
		this.employeeEmail = employeeEmail;
		this.employeeAddress = employeeAddress;
		this.employeeGender = employeeGender;
		this.employeeBirthday = employeeBirthday;
		this.department = department;
	}

	public Employee(int employeeId, String employeePassword, String employeeNameKr, String employeeNameEng,
			Date employeeHiredate, int employeePhonenumber, String employeeEmail, String employeeAddress,
			String employeeGender, Date employeeBirthday, int employeeResign, int employeeAuthority,
			Department department) {
		super();
		this.employeeId = employeeId;
		this.employeePassword = employeePassword;
		this.employeeNameKr = employeeNameKr;
		this.employeeNameEng = employeeNameEng;
		this.employeeHiredate = employeeHiredate;
		this.employeePhonenumber = employeePhonenumber;
		this.employeeEmail = employeeEmail;
		this.employeeAddress = employeeAddress;
		this.employeeGender = employeeGender;
		this.employeeBirthday = employeeBirthday;
		this.employeeResign = employeeResign;
		this.employeeAuthority = employeeAuthority;
		this.department = department;
	}


	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	public String getEmployeeNameKr() {
		return employeeNameKr;
	}

	public void setEmployeeNameKr(String employeeNameKr) {
		this.employeeNameKr = employeeNameKr;
	}

	public String getEmployeeNameEng() {
		return employeeNameEng;
	}

	public void setEmployeeNameEng(String employeeNameEng) {
		this.employeeNameEng = employeeNameEng;
	}

	public Date getEmployeeHiredate() {
		return employeeHiredate;
	}

	public void setEmployeeHiredate(Date employeeHiredate) {
		this.employeeHiredate = employeeHiredate;
	}

	public int getEmployeePhonenumber() {
		return employeePhonenumber;
	}

	public void setEmployeePhonenumber(int employeePhonenumber) {
		this.employeePhonenumber = employeePhonenumber;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}

	public Date getEmployeeBirthday() {
		return employeeBirthday;
	}

	public void setEmployeeBirthday(Date employeeBirthday) {
		this.employeeBirthday = employeeBirthday;
	}

	public int getEmployeeResign() {
		return employeeResign;
	}

	public void setEmployeeResign(int employeeResign) {
		this.employeeResign = employeeResign;
	}

	public int getEmployeeAuthority() {
		return employeeAuthority;
	}

	public void setEmployeeAuthority(int employeeAuthority) {
		this.employeeAuthority = employeeAuthority;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public HolidayInfo getHolidayInfo() {
		return holidayInfo;
	}

	public void setHolidayInfo(HolidayInfo holidayInfo) {
		this.holidayInfo = holidayInfo;
	}



	
	
}

package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.my.dto.Department;
import com.my.dto.Employee;
import com.my.exception.SelectException;
import com.my.exception.UpdateException;
import com.my.sql.MyConnection;

public class EmployeeOracleRepository implements EmployeeRepository {

	@Override
	public Employee selectByIdAndPwd(int employeeId, String employeePassword) throws SelectException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectByIdAndPwdSQL = "SELECT * FROM employee WHERE employee_id= ? AND employee_password= ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectByIdAndPwdSQL);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String employeeNameKr = rs.getString("employee_naame_kr");
				String employeeNameEng = rs.getString("employee_name_eng");
				java.sql.Date employeeHiredate = rs.getDate("employee_hiredate");
				int employeePhonenumber = rs.getInt("employee_phonenumber");
				String employeeEmail = rs.getString("employee_email");
				String employeeAddress = rs.getString("employee_address");
				String employeeGender = rs.getString("employee_gender");
				java.sql.Date employeeBirthday = rs.getDate("employee_birthday"); // util?? MyWeb의
																				  //ProductOracleRepository 참조
				int employeeResign = rs.getInt("employee_resign");
				int employeeAuthority = rs.getInt("employee_authority");

				String departmentName = rs.getString("department_name");
				int departmentId = rs.getInt("employeeId");

				Department d = new Department(departmentId, departmentName);
				Employee em = new Employee(employeeId, employeePassword, employeeNameKr, employeeNameEng,
						employeeHiredate, employeePhonenumber, employeeEmail, employeeAddress, employeeGender,
						employeeBirthday, employeeResign, employeeAuthority, d);

				return em;
			} else {
				throw new SelectException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SelectException();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}

	}

	@Override
	public void update(int employeeId, String employeePassword) throws UpdateException {
		Connection con = null; // private?
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String updateSQL = "UPDATE employee SET employee_password = ? WHERE employee_id = ?";
		Employee employee = new Employee();
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setString(1, employee.getEmployeePassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();

		}finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public Employee selectMyPage(int employeeId) throws SelectException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectMyPageSQL = "SELECT e.employee_name_kr, e.employee_name_eng, e.employee_birthday, "
				+ "e.employee_gender, e.employee_address, e.employee_id, e.employee_password, d.department_name, "
				+ "e.employee_phonenumber, e.employee_hiredate, e.employee_email \r\n"
				+ "FROM employee e JOIN department d ON e.department_id=d.department_id\r\n"
				+ "WHERE e.employee_id = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectMyPageSQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String employeeNameKr = rs.getString("employee_naame_kr");
				String employeeNameEng = rs.getString("employee_name_eng");
				java.sql.Date employeeBirthday = rs.getDate("employee_birthday"); // util??
				String employeeGender = rs.getString("employee_gender");
				String employeeAddress = rs.getString("employee_address");
				String employeePassword = rs.getString("employee_password");
				int employeePhonenumber = rs.getInt("employee_phonenumber");
				java.sql.Date employeeHiredate = rs.getDate("employee_hiredate");
				String employeeEmail = rs.getString("employee_email");

				int departmentId = rs.getInt("employeeId");
				String departmentName = rs.getString("department_name");

				Department d = new Department(departmentId, departmentName);
				Employee em = new Employee(employeeId, employeePassword, employeeNameKr, employeeNameEng,
						employeeHiredate, employeePhonenumber, employeeEmail, employeeAddress, employeeGender,
						employeeBirthday, d);
				return em;

			} else {
				throw new SelectException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SelectException();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}

	}

}

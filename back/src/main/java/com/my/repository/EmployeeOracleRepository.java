package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.my.dto.Department;
import com.my.dto.Employee;
import com.my.exception.SelectException;
import com.my.exception.UpdateException;
import com.my.sql.MyConnection;

public class EmployeeOracleRepository implements EmployeeRepository {



	@Override
	public Employee selectMyPage(int employeeId) throws SelectException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectSQL = "SELECT * \r\n"
				+ "FROM employee e JOIN department d ON e.department_id=d.department_id\r\n"
				+ "WHERE e.employee_id = ? ";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, employeeId );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String employeeNameKr = rs.getString("employee_name_kr");
				String employeeNameEng = rs.getString("employee_name_eng");
				java.sql.Date employeeBirthday = rs.getDate("employee_birthday"); // util??
				String employeeGender = rs.getString("employee_gender");
				String employeeAddress = rs.getString("employee_address");
				String employeePassword = rs.getString("employee_password");
				int employeePhonenumber = rs.getInt("employee_phonenumber");
				java.sql.Date employeeHiredate = rs.getDate("employee_hiredate");
				String employeeEmail = rs.getString("employee_email");

				int departmentId = rs.getInt("department_id");
				String departmentName = rs.getString("department_name");

				Department department = new Department(departmentId, departmentName);
				Employee employee = new Employee(employeeId, employeePassword, employeeNameKr, employeeNameEng,
						employeeHiredate, employeePhonenumber, employeeEmail, employeeAddress, employeeGender,
						employeeBirthday, department);
				return employee;

			} else {
				throw new SelectException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SelectException();
		} finally {
			MyConnection.close(rs, pstmt, con);
			//throws 처리
		}

	}

}

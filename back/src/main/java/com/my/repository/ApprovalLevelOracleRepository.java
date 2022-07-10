package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.ApprovalLevel;
import com.my.exception.SelectException;
import com.my.sql.MyConnection;
import DefinePackage.DefineOption;

public class ApprovalLevelOracleRepository implements ApprovalLevelRepository {

	//결재자를 반환한다
	@Override
	public List<ApprovalLevel> selectApprovalLevel() throws SelectException {
		// TODO Auto-generated method stub
		List<ApprovalLevel> approvalLevels = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT e.* FROM approval_level a JOIN employee e ON a.employee_id = e.employee_id";

		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int departmentId = rs.getInt("department_id");
				String employeePassword = rs.getString("employee_password");
				String employeeNameKr = rs.getString("employee_name_kr");
				String employeeNameEng = rs.getString("employee_name_eng");
				java.sql.Date employeeHiredate = rs.getDate("employee_hiredate");
				int employeePhonenumber = rs.getInt("employee_phonenumber");
				String employeeEmail = rs.getString("employee_email");
				String employeeAddress = rs.getString("employee_address");
				String employeeGender = rs.getString("employee_gender");
				java.sql.Date employeeBirthday = rs.getDate("employee_birthday");
				int employeeResign = rs.getInt("employee_resign");
				int employeeAuthority = rs.getInt("employee_authority");

				// employee 값들 받을 것
//				ApprovalLevel approval_level = new ApprovalLevel(id);
//				approvalLevels.add(approval_level);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return approvalLevels;
	}

}

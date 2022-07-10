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

	@Override
	public List<ApprovalLevel> selectApprovalLevel() throws SelectException {
		// TODO Auto-generated method stub
		List<ApprovalLevel> approvalLevel = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String level = "SELECT e.* FROM approval_level a JOIN employee e ON a.employee_id = e.employee_id";

		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(level);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("employee_id");
				ApprovalLevel approval_level = new ApprovalLevel(id);
				return (List<ApprovalLevel>) approval_level;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return null;
	}

}

package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.my.dto.HolidayInfo;
import com.my.exception.SelectException;
import com.my.exception.UpdateException;
import com.my.sql.MyConnection;

public class HolidayInfoOracleRepository implements HolidayInfoRepository {

	@Override
	public HolidayInfo selectHolidayInfoByEmployeeId(int employeeId) throws SelectException {
		// TODO Auto-generated method stub
		
		Connection con = null;	// DB와 연결
		PreparedStatement pstmt = null;	// SQL 송신
		ResultSet rs = null;  // 송신결과 -> 반드시 executeQuery()로 받아야 한다
		
		try {
			String remainholiday = "SELECT holiday_total, holiday_using FROM holiday_info WHERE employee_id = ?";

			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(remainholiday);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int id = rs.getInt("employee_id");  // dto에 들어갈 파라미터?
				
				HolidayInfo info = new HolidayInfo(id);  // dto주소
				return info;
			} else {
				System.out.println("해당하는 사원이 없습니다");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			MyConnection.close(rs, pstmt, con);// DB와의 연결 닫기 - finally에 넣는다
		}
		return null;    // 제대로 돌아가면 String이 리턴되는데, 그게 싫은가 보다
	}

	@Override
	public void update(int employeeId) throws UpdateException {
		// TODO Auto-generated method stub

		Connection con = null;	// DB와 연결
		PreparedStatement pstmt = null;	// SQL 송신
		ResultSet rs = null;  // 송신결과 -> 반드시 executeQuery()로 받아야 한다
		try {
			String changeholiday = "UPDATE holiday_using FROM holiday_info WHERE employee_id=?";
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(changeholiday);
			int id = rs.getInt("employee_id");
			pstmt.setInt(employeeId, id);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			MyConnection.close(rs, pstmt, con);// DB와의 연결 닫기
		}
	}

}

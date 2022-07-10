package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.my.dto.HolidayInfo;
import com.my.exception.SelectException;
import com.my.exception.UpdateException;
import com.my.sql.MyConnection;

public class HolidayInfoOracleRepository implements HolidayInfoRepository {

	// 로그인한 사원의 휴가 일수를 반환한다
	@Override
	public HolidayInfo selectHolidayInfoByEmployeeId(int employeeId) throws SelectException {
		// TODO Auto-generated method stub
		
		Connection con = null;	// DB와 연결
		PreparedStatement pstmt = null;	// SQL 송신
		ResultSet rs = null;  // 송신결과 -> 반드시 executeQuery()로 받아야 한다
		
		try {
			String selectSQL = "SELECT holiday_total, holiday_using FROM holiday_info WHERE employee_id = ? ";

			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int holidayTotal = rs.getInt("holiday_total");
				int holidayUsing = rs.getInt("holiday_using");
				
				HolidayInfo info = new HolidayInfo(employeeId, holidayTotal, holidayUsing);
				return info;
			} else {
				System.out.println("해당하는 사원이 없습니다");
				throw new SelectException();
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new SelectException();
		} finally{
			MyConnection.close(rs, pstmt, con);// DB와의 연결 닫기 - finally에 넣는다
		}
	}

	// 로그인한 사원 휴가일수를 변경한다
	@Override
	public void update(int employeeId) throws UpdateException {
		// TODO Auto-generated method stub

		Connection con = null;	// DB와 연결
		PreparedStatement pstmt = null;	// SQL 송신
		ResultSet rs = null;  // 송신결과 -> 반드시 executeQuery()로 받아야 한다
		HolidayInfo info = new HolidayInfo();
		try {
			String updateSQL = "UPDATE holiday_info SET holiday_using WHERE employee_id = ? ";
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setInt(1, info.getHolidayUsing());
//			int holidayUsing = rs.getInt("holiday_using");
//			pstmt.setInt(1, info.getInt("holiday_using"));
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			MyConnection.close(rs, pstmt, con);// DB와의 연결 닫기
		}
	}

}

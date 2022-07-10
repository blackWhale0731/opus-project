package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.my.dto.Calendar;
import com.my.exception.InsertException;
import com.my.exception.SelectException;

import sql.MyConnection;

public class CalendarOracleRepository implements CalendarRepository {

	@Override
	public void insert(Calendar calendar) throws InsertException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = MyConnection.getConnection();
			String insertSQL = "INSERT INTO calendar(cal_no, employee_id, cal_type, cal_revealed, cal_cnt, cal_start, cal_end) values(seq_cal_no.NEXTVAL ,?,?,?,?,?,?)";
		pstmt = con.prepareStatement(insertSQL);
		pstmt.setInt(1, calendar.getCalNo());
		pstmt.setInt(2, calendar.getEmployee().getEmployeeId());
		pstmt.setInt(3, calendar.getCalType().getCalType());
		pstmt.setInt(4, calendar.getCalRevealed());
		pstmt.setString(5, calendar.getCalCnt());
		pstmt.setDate(6, new java.sql.Date(calendar.getCalStart().getTime()));
		pstmt.setDate(7, new java.sql.Date(calendar.getCalEnd().getTime()));
		pstmt.executeUpdate();
		
		} catch (SQLException e) {
			throw new InsertException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyConnection.close( pstmt, con);
		}
		
	}

	@Override
	public List<Calendar> selectCalByEmployeeId(Calendar calendar) throws SelectException {
			
		List<Calendar> calendars = new ArrayList<Calendar>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
			String selectSQL = "select employee_id, cal_revealed, cal_type, cal_cnt, cal_start, cal_end\r\n"
					+ "from calendar\r\n"
					+ "where employee_id = ?\r\n"
					+ "AND (TO_CHAR(add_months(?, ?), 'MM') = TO_CHAR(cal_start, 'MM') OR TO_CHAR(add_months(?,0), 'MM') = TO_CHAR(cal_end, 'MM'));\r\n";
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, calendar.getEmployee().getEmployeeId());
			pstmt.setInt(2, calendar.getCalRevealed());
			pstmt.setInt(3, calendar.getCalType().getCalType());
			pstmt.setString(4, calendar.getCalCnt());
			pstmt.setDate(5, new java.sql.Date(calendar.getCalStart().getTime()));
			pstmt.setDate(6, new java.sql.Date(calendar.getCalEnd().getTime()));
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return calendars;
	}

	@Override
	public List<Calendar> selectCalByDepartmentId(Calendar calendar) throws SelectException {

		List<Calendar> calendars = new ArrayList<Calendar>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
			String selectSQL = "select c.employee_id, c.cal_revealed, c.cal_type, c.cal_cnt, c.cal_start, c.cal_end \r\n"
					+ "from calendar c JOIN employee e ON(c.employee_id = e.employee_id)\r\n"
					+ "WHERE cal_revealed=1 AND  e.department_id = (SELECT department_id FROM employee WHERE employee_id = ?)\r\n"
					+ "AND(TO_CHAR(add_months(?, 0), 'MM') = TO_CHAR(cal_start, 'MM') OR\r\n"
					+ "TO_CHAR(add_months(?, 0), 'MM') = TO_CHAR(cal_end, 'MM'));\r\n";
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, calendar.getEmployee().getEmployeeId());
			pstmt.setInt(2, calendar.getCalRevealed());
			pstmt.setInt(3, calendar.getCalType().getCalType());
			pstmt.setString(4, calendar.getCalCnt());
			pstmt.setDate(5, new java.sql.Date(calendar.getCalStart().getTime()));
			pstmt.setDate(6, new java.sql.Date(calendar.getCalEnd().getTime()));
			
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		return calendars;
	}

}

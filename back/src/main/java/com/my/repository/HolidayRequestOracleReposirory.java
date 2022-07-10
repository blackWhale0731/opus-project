package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.my.dto.HolidayRequest;
import com.my.exception.InsertException;
import com.my.exception.SelectException;
import com.my.exception.UpdateException;
import com.my.sql.MyConnection;

public class HolidayRequestOracleReposirory implements HolidayRequestRepository {

	@Override
	public void insertHolidayRequest(HolidayRequest holidayrequest) throws InsertException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = MyConnection.getConnection();
			String insertHolidayRequestSQL =
					"INSERT INTO holiday_request(holiday_number, employee_id, holiday_type, \r\n"
							+ "    holiday_start, holiday_end, holiday_desc,\r\n"
							+ "    holiday_approval_first,\r\n"
							+ "    holiday_approval_second,\r\n"
							+ "    holiday_approval_third,\r\n"
							+ "    holiday_request_status)\r\n"
							+ " VALUES(seq_holiday_no.NEXTVAL,  \r\n"
							+ "    <로그인된 사번>, <선택된holiday_type>,\r\n"
							+ "    <선택된holiday_start>, <선택된holiday_end>,\r\n"
							+ "    <입력된holiday_desc>,<선택된 holiday_approval_first>, \r\n"
							+ "    <선택된holiday_approval_second>, \r\n"
							+ "    <선택된 holiday_approval_third>, 3);\r\n";

			pstmt = con.prepareStatement(insertHolidayRequestSQL);
			pstmt.setInt(1, holidayrequest.getHolidayNumber());
			pstmt.setInt(2, holidayrequest.getEmployee().getEmployeeId());
			pstmt.setInt(3, holidayrequest.getHolidayType().getHolidayType());
			pstmt.setDate(4,new java.sql.Date(holidayrequest.getHolidayStart().getTime()));
			pstmt.setDate(5,new java.sql.Date(holidayrequest.getHolidayEnd().getTime()));
			pstmt.setString(6, holidayrequest.getHolidayDesc());
			pstmt.setInt(7, holidayrequest.getHolidayApprovalFirst().getEmployee().getEmployeeId());
			pstmt.setInt(8, holidayrequest.getHolidayApprovalSecond().getEmployee().getEmployeeId());
			pstmt.setInt(9, holidayrequest.getHolidayApprovalThird().getEmployee().getEmployeeId());
			pstmt.setInt(10, holidayrequest.getHolidayApprovalStatus());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	@Override
	public HolidayRequest selectHolidayRequestByHolidayNumber(int holidayNumber) throws SelectException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectHolidayRequestByHolidayNumberSQL = "SELECT r.holiday_start, r.holiday_end, t.holiday_type_name,\r\n"
				+ "(SELECT e.employee_name_kr\r\n"
				+ "FROM approval_level l\r\n"
				+ "JOIN employee e ON(l.employee_id = e.employee_id)\r\n"
				+ "JOIN holiday_approval a ON (l.employee_id = a.holiday_approval_first)\r\n"
				+ "WHERE (a.HOLIDAY_APPROVAL_FIRST = e.employee_id) and a.holiday_number = ?), \r\n"
				+ "(SELECT e.employee_name_kr\r\n"
				+ "FROM approval_level l\r\n"
				+ "JOIN employee e ON(l.employee_id = e.employee_id)\r\n"
				+ "JOIN holiday_approval a ON (l.employee_id = a.holiday_approval_second)\r\n"
				+ "WHERE (a.holiday_approval_second = e.employee_id) and a.holiday_number =?), \r\n"
				+ "(SELECT e.employee_name_kr\r\n"
				+ "FROM approval_level l\r\n"
				+ "JOIN employee e ON(l.employee_id = e.employee_id)\r\n"
				+ "JOIN holiday_approval a ON (l.employee_id = a.holiday_approval_third)\r\n"
				+ "WHERE (a.holiday_approval_third = e.employee_id) and a.holiday_number = ?),\r\n"
				+ "a.holiday_approval_status\r\n"
				+ "FROM holiday_request r\r\n"
				+ "JOIN holiday_type t ON(r.holiday_type = t.holiday_type)\r\n"
				+ "JOIN holiday_approval a ON (r.holiday_number = a.holiday_number) \r\n"
				+ "JOIN employee e ON(r.employee_id = e.employee_id)\r\n"
				+ "WHERE r.holiday_number = ?;\r\n";

		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectHolidayRequestByHolidayNumberSQL);

			if(rs.next()) {
				int holidayNmuber = rs.getInt("holiday_number");
			}pstmt.executeQuery();
			rs = pstmt.executeQuery();
		}catch (SQLException e) {
			throw new SelectException();
		}return null;
	}

	@Override
	public List<HolidayRequest> selectHolidayByEmployeeId(int startRow, int endRow, int employeeId) throws SelectException {
		List<HolidayRequest> holidayRequest = new ArrayList<HolidayRequest>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT *\r\n"
				+ "FROM (\r\n"
				+ "    SELECT rownum as rn, a.*\r\n"
				+ "    FROM(   \r\n"
				+ "        SELECT hr.holiday_number, hr.holiday_type, hr.holiday_desc, hr.holiday_start, hr.holiday_end,\r\n"
				+ "              ha.holiday_approval_status, \r\n"
				+ "              ht.holiday_type_name\r\n"
				+ "        FROM holiday_request hr JOIN holiday_approval ha ON (hr.holiday_number=ha.holiday_number) \r\n"
				+ "                            JOIN HOLIDAY_TYPE ht ON (hr.holiday_type=ht.holiday_type)\r\n"
				+ "        where EMPLOYEE_ID = ?\r\n"
				+ "        ORDER BY holiday_number DESC\r\n"
				+ "    ) a\r\n"
				+ ")\r\n"
				+ "WHERE rn BETWEEN ? AND ?; \r\n"
				+ "\r\n"
				+ "SELECT *\r\n"
				+ "FROM (\r\n"
				+ "    SELECT rownum as rn, a.*\r\n"
				+ "    FROM(   \r\n"
				+ "        SELECT hr.holiday_number, hr.holiday_type, hr.holiday_desc, hr.holiday_start, hr.holiday_end,\r\n"
				+ "              hr.holiday_request_status, \r\n"
				+ "              ht.holiday_type_name\r\n"
				+ "        FROM holiday_request hr JOIN HOLIDAY_TYPE ht ON (hr.holiday_type=ht.holiday_type)\r\n"
				+ "        where EMPLOYEE_ID = ?\r\n"
				+ "        ORDER BY holiday_number DESC\r\n"
				+ "    ) a\r\n"
				+ ")\r\n"
				+ "WHERE rn BETWEEN ? AND ?; \r\n";

		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, employeeId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			pstmt.setInt(4, employeeId);
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, endRow);
			pstmt.execute();

			if(rs.next()) {
				int holidayNumber = rs.getInt("holiday_number");
				int employId = rs.getInt("employeeId");
				int holidayType = rs.getInt("holiday_type");
				int holiday_approval_first = rs.getInt("employId");
				int holiday_approval_second = rs.getInt("employId");
				int holiday_approval_third= rs.getInt("employId");
				String holiday_desc = rs.getString("holidayDesc");
				Date holiday_start = rs.getDate("holidayStart");
				Date holiday_end = rs.getDate("holidayEnd");
				int holiday_request_status = rs.getInt("holidayRequestStatus");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}return null;
	}



	@Override
	public void update(int holidayNumber) throws UpdateException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String updateSQL = "UPDATE holidayRequest SET holiday_Number = ?";
		HolidayRequest holidayRequest = new HolidayRequest();
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setInt(1, holidayRequest.getHolidayNumber());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
}




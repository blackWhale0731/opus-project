package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.my.dto.ApprovalLevel;
import com.my.dto.Employee;
import com.my.dto.HolidayRequest;
import com.my.dto.HolidayType;
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
							+ "    ? , ? ,\r\n"
							+ "    ? , ? ,\r\n"
							+ "    ? , ? , \r\n"
							+ "    ? , \r\n"
							+ "    ? , 3);\r\n";

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
		}finally {
			MyConnection.close(pstmt, con);			
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
				+ "WHERE (a.HOLIDAY_APPROVAL_FIRST = e.employee_id) and a.holiday_number = ? ), \r\n"
				+ "(SELECT e.employee_name_kr\r\n"
				+ "FROM approval_level l\r\n"
				+ "JOIN employee e ON(l.employee_id = e.employee_id)\r\n"
				+ "JOIN holiday_approval a ON (l.employee_id = a.holiday_approval_second)\r\n"
				+ "WHERE (a.holiday_approval_second = e.employee_id) and a.holiday_number = ? ), \r\n"
				+ "(SELECT e.employee_name_kr\r\n"
				+ "FROM approval_level l\r\n"
				+ "JOIN employee e ON(l.employee_id = e.employee_id)\r\n"
				+ "JOIN holiday_approval a ON (l.employee_id = a.holiday_approval_third)\r\n"
				+ "WHERE (a.holiday_approval_third = e.employee_id) and a.holiday_number = ? ),\r\n"
				+ "a.holiday_approval_status\r\n"
				+ "FROM holiday_request r\r\n"
				+ "JOIN holiday_type t ON(r.holiday_type = t.holiday_type)\r\n"
				+ "JOIN holiday_approval a ON (r.holiday_number = a.holiday_number) \r\n"
				+ "JOIN employee e ON(r.employee_id = e.employee_id)\r\n"
				+ "WHERE r.holiday_number = ? \r\n";

		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectHolidayRequestByHolidayNumberSQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int holidayNmuber = rs.getInt("holiday_number");
				
				HolidayRequest hr = new HolidayRequest();
				return hr;
			}else {
				throw new SelectException();				
			}
		}catch (SQLException e) {
		}finally {
			MyConnection.close(rs, pstmt, con);
		}throw new SelectException();
	}

	@Override
	public List<HolidayRequest> selectHolidayByEmployeeId(int startRow, int endRow, int employeeId) throws SelectException {
		List<HolidayRequest> holidayRequests = new ArrayList<>();
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
				+ "        where EMPLOYEE_ID = ? \r\n"
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
				+ "        where EMPLOYEE_ID = ? \r\n"
				+ "        ORDER BY holiday_number DESC\r\n"
				+ "    ) a\r\n"
				+ ")\r\n"
				+ "WHERE rn BETWEEN ? AND ?; \r\n";

		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			pstmt.setInt(1, employeeId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			pstmt.setInt(4, employeeId);
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, endRow);
			pstmt.execute();
			while(rs.next()) {
                int holidayNumber = rs.getInt("holiday_number");
                int holidayApprovalStatus = rs.getInt("holiday_approval_status");
                String holidayDesc = rs.getString("holiday_desc");
                Date holidayStart = rs.getDate("holiday_start");
                Date holidayEnd = rs.getDate("holiday_end");
                int holidayType = rs.getInt("holiday_type");


                int empId = rs.getInt("employee_id");
               Employee em =new Employee(empId);
                ApprovalLevel a = new ApprovalLevel(em);//생성자만들기
                ApprovalLevel holidayApprovalFirst = a; // 생성자
                ApprovalLevel holidayApprovalSecond = a;
                ApprovalLevel holidayApprovalThird = a;


                HolidayType ht = new HolidayType(holidayType);
                HolidayRequest hr = new HolidayRequest(holidayNumber,
                        holidayApprovalStatus, 
                        holidayApprovalFirst,
                        holidayApprovalSecond,
                        holidayApprovalThird,
                        holidayDesc,
                        holidayStart,
                        holidayEnd,
                        ht);
                holidayRequests.add(hr);
            }
			return holidayRequests;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SelectException();
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
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




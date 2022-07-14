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

public class HolidayRequestOracleRepository implements HolidayRequestRepository {

	@Override
	public void insertHolidayRequest(HolidayRequest holidayrequest) throws InsertException {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = MyConnection.getConnection();
			String insertHolidayRequestSQL =
					"INSERT INTO holiday_request(holiday_number, employee_id, holiday_type,"
							+ "    holiday_start, holiday_end, holiday_desc,"
							+ "    holiday_approval_first,"
							+ "    holiday_approval_second,"
							+ "    holiday_approval_third,"
							+ "    holiday_approval_status)"
							+ " VALUES(seq_holiday_no.NEXTVAL,"
							+ " ? , ? ,  ? , ? , ? , ? , ? , ? , ? )";

			pstmt = con.prepareStatement(insertHolidayRequestSQL);
//			pstmt.setInt(1, holidayrequest.getHolidayNumber());
			pstmt.setInt(1, holidayrequest.getEmployee().getEmployeeId());
			pstmt.setInt(2, holidayrequest.getHolidayType().getHolidayType());
			pstmt.setDate(3,new java.sql.Date(holidayrequest.getHolidayStart().getTime()));
			pstmt.setDate(4,new java.sql.Date(holidayrequest.getHolidayEnd().getTime()));
			pstmt.setString(5, holidayrequest.getHolidayDesc());
			pstmt.setInt(6, holidayrequest.getHolidayApprovalFirst().getEmployee().getEmployeeId());
			pstmt.setInt(7, holidayrequest.getHolidayApprovalSecond().getEmployee().getEmployeeId());
			pstmt.setInt(8, holidayrequest.getHolidayApprovalThird().getEmployee().getEmployeeId());
			pstmt.setInt(9, holidayrequest.getHolidayApprovalStatus());
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
		
		

		try {
			con = MyConnection.getConnection();
			String selectHolidayRequestByHolidayNumberSQL = "SELECT  r.holiday_start, r.holiday_end, t.holiday_type_name,\r\n"
					+ "(SELECT e.employee_name_kr\r\n"
					+ "FROM approval_level l\r\n"
					+ "JOIN employee e ON(l.employee_id = e.employee_id)\r\n"
					+ "JOIN holiday_request r ON (l.employee_id = r.holiday_approval_first)\r\n"
					+ "WHERE (r.holiday_approval_first = e.employee_id) and r.holiday_number =  ?),\r\n"
					+ "(SELECT e.employee_name_kr\r\n"
					+ "FROM approval_level l\r\n"
					+ "JOIN employee e ON(l.employee_id = e.employee_id)\r\n"
					+ "JOIN holiday_request r ON (l.employee_id = r.holiday_approval_second)\r\n"
					+ "WHERE (r.holiday_approval_second = e.employee_id) and r.holiday_number =  ?),\r\n"
					+ "(SELECT e.employee_name_kr\r\n"
					+ "FROM approval_level l\r\n"
					+ "JOIN employee e ON(l.employee_id = e.employee_id)\r\n"
					+ "JOIN holiday_request r ON (l.employee_id = r.holiday_approval_third)\r\n"
					+ "WHERE (r.holiday_approval_third = e.employee_id) and r.holiday_number =  ?),\r\n"
					+ "r.holiday_approval_status\r\n"
					+ "FROM holiday_request r\r\n"
					+ "JOIN holiday_type t ON(r.holiday_type = t.holiday_type)\r\n"
					+ "JOIN employee e ON(r.employee_id = e.employee_id)\r\n"
					+ "WHERE r.holiday_number = ?";
					
			pstmt = con.prepareStatement(selectHolidayRequestByHolidayNumberSQL);
			pstmt.setInt(1,holidayNumber);
			pstmt.setInt(2, holidayNumber);
			pstmt.setInt(3, holidayNumber);
			pstmt.setInt(4, holidayNumber);
			rs = pstmt.executeQuery();
			if(rs.next()) {
//				int holidayNumber = rs.getInt("holiday_nunber");
				Date holidayStart = rs.getDate("holiday_start");
				Date holidayEnd = rs.getDate("holiday_end");
				String employeeNameKr = rs.getString("employee_name_kr");
				int holidayApprovalFirst = rs.getInt("holiday_approval_first");
				int holidayApprovalSecond = rs.getInt("holiday_approval_second");
				int holidayApprovalThird = rs.getInt("holiday_approval_third");
				int holidayApprovalStatus = rs.getInt("holiday_approval_status");
				
				
//				Employee em = new Employee(employeeNameKr);
//				HolidayType ht = new HolidayType(holidayTypeName);
//				ApprovalLevel al = new ApprovalLevel(holidayApprovalFirst,holidayApprovalSecond,holidayApprovalThird);
//				HolidayRequest hr = new HolidayRequest(holidayStart,holidayEnd,holidayRequestStatus,em,ht,al);
//				return hr;
				
				//int holidayNmuber = rs.getInt("holiday_number");
				
//				HolidayRequest hr = new HolidayRequest();
//				hr.setHolidayNumber(holidayNumber);
//				hr.setHolidayStart();
//				hr.setHolidayEnd(holidayNumber);
//				hr.setHolidayType(holidayNumber);
				//:
//				return hr;
				
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
				+ "        SELECT hr.holiday_number, hr.holiday_approval_status, hr.holiday_type, hr.holiday_desc, hr.holiday_start, hr.holiday_end, \r\n"
				+ "              ht.holiday_type_name\r\n"
				+ "        FROM holiday_request hr JOIN HOLIDAY_TYPE ht ON (hr.holiday_type=ht.holiday_type)\r\n"
				+ "        where EMPLOYEE_ID = ?\r\n"
				+ "        ORDER BY holiday_number DESC\r\n"
				+ "    ) a\r\n"
				+ ")\r\n"
				+ "WHERE rn BETWEEN ? AND ?";
			
				

		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, employeeId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
//			pstmt.setInt(4, employeeId);
//			pstmt.setInt(5, startRow);
//			pstmt.setInt(6, endRow);
		
			rs = pstmt.executeQuery();
			while(rs.next()) {
                int holidayNumber = rs.getInt("holiday_number");

                int holidayType = rs.getInt("holiday_type");
                int holidayApprovalStatus = rs.getInt("holiday_approval_status");
                String holidayDesc = rs.getString("holiday_desc");
                Date holidayStart = rs.getDate("holiday_start");
                Date holidayEnd = rs.getDate("holiday_end");
                String holidayTypeName = rs.getString("holiday_type_name");

//                int empId = rs.getInt("employee_id");
               Employee em =new Employee(employeeId);
                ApprovalLevel a = new ApprovalLevel();//생성자만들기
                ApprovalLevel holidayApprovalFirst = a; // 생성자
                ApprovalLevel holidayApprovalSecond = a;
                ApprovalLevel holidayApprovalThird = a;


                HolidayType ht = new HolidayType(holidayType, holidayTypeName);
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


	
	public void update(int holidayNumber, int employeeId) throws UpdateException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String updateSQL = "UPDATE holiday_request  SET holiday_Approval_status = -2 WHERE holiday_number = ? AND employee_id = ?";
		HolidayRequest holidayRequest = new HolidayRequest();
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setInt(1, holidayNumber);
//			pstmt.setInt(2, employeeId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}
	@Override
	public HolidayRequest selectHolidayRequestByHolidayNumber(int holidayNumber, int employeeId) throws UpdateException, SelectException {
		return null;
	}
}

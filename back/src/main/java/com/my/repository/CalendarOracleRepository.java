package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.my.dto.CalType;
import com.my.dto.Calendar;
import com.my.dto.Department;
import com.my.dto.Employee;
import com.my.exception.InsertException;
import com.my.exception.SelectException;
import com.my.sql.MyConnection;

public class CalendarOracleRepository implements CalendarRepository {

	@Override
	public void insert(Calendar calendar) throws InsertException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = MyConnection.getConnection();
			String insertSQL = "INSERT INTO calendar(cal_no, employee_id, cal_type, cal_revealed, cal_cnt, cal_start, cal_end) "
					+ "values(seq_cal_no.NEXTVAL , ? , ? , ? , ? , ? , ? )";
			//int rowcnt = pstmt.executeUpdate(insertSQL);
			//System.out.println(rowcnt+"건이 추가되었습니다");
			
			pstmt = con.prepareStatement(insertSQL);
//			pstmt.setInt(1, calendar.getCalNo());
			pstmt.setInt(1, calendar.getEmployee().getEmployeeId());
			pstmt.setInt(2, calendar.getCalType().getCalType());
			pstmt.setInt(3, calendar.getCalRevealed());
			pstmt.setString(4, calendar.getCalCnt());
			pstmt.setDate(5, new java.sql.Date(calendar.getCalStart().getTime())); //Date확인필요
			pstmt.setDate(6, new java.sql.Date(calendar.getCalEnd().getTime()));
			pstmt.executeUpdate();
			System.out.println("INSERT CALENDAR OK");
		} catch (SQLException e) {
			throw new InsertException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyConnection.close( pstmt, con);
		}
		
	}

	@Override
	public List<Calendar> selectCalMyExcludeDept(String ym, int employeeId) throws SelectException {
			
		List<Calendar> calendars = new ArrayList<Calendar>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String selectSQL = 
					"SELECT c.employee_id, "
					+"c.cal_revealed, "
					+ "c.cal_type, "
					+ "t.cal_type_name, "
					+ "c.cal_cnt, "
					+ "c.cal_start, "
					+ "c.cal_end\r\n"
					+ "FROM calendar c JOIN employee e ON(c.employee_id = e.employee_id)\r\n"
					+ "                	      JOIN cal_type t ON(c.cal_type = t.cal_type)\r\n"
					+ "WHERE c.employee_id = ? \r\n"
					+ "AND(? = TO_CHAR(cal_start, 'MM') \r\n"
					+ "OR  ? = TO_CHAR(cal_end, 'MM'))";
			
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, employeeId);
			pstmt.setString(2, ym);
			pstmt.setString(3, ym);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("in rs next");
				Employee em = new Employee(employeeId);
				int calRevealed = rs.getInt("cal_revealed");
				
				int calType = rs.getInt("cal_type");
				String calTypeName = rs.getString("cal_type_name");
				
				CalType ct = new CalType(calType, calTypeName);
				String calCnt = rs.getString("cal_cnt");
				Date calStart = rs.getDate("cal_start");
				Date calEnd = rs.getDate("cal_end");
				
				Calendar cal = new Calendar(em, calRevealed, ct, calCnt, calStart, calEnd);
				calendars.add(cal);
				
			}
			return calendars;
		} catch (SQLException e){
			e.printStackTrace();
			throw new SelectException();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		
	}
	
	@Override
	public List<Calendar> selectCalBoth(String ym, int employeeId) throws SelectException {

		List<Calendar> calendars = new ArrayList<Calendar>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			String selectSQL = " SELECT * "
					+ "FROM calendar c JOIN employee e ON(c.employee_id = e.employee_id)"
					+ "JOIN cal_type t ON(c.cal_type = t.cal_type)\r\n"
					+ "WHERE c.cal_revealed=1 AND  e.department_id = (SELECT e.department_id FROM employee e WHERE e.employee_id =  ?)"
					+ "OR (c.cal_revealed=0 AND c.employee_id = ?) "
					+ "AND(? = TO_CHAR(cal_start, 'MM') OR (? = TO_CHAR(cal_end, 'MM')))";
			
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			
			pstmt.setInt(1, employeeId);
			pstmt.setInt(2, employeeId);
			pstmt.setString(3,  ym);
			pstmt.setString(4,  ym);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				 //테스트
	        	System.out.println("in rs next()");
	        	 
				String employeeNameKr = rs.getString("employee_name_kr");
				
				int calRevealed = rs.getInt("cal_revealed");
				String calCnt = rs.getString("cal_cnt");
				Date calStart = rs.getDate("cal_start");
				Date calEnd = rs.getDate("cal_end");
				
				int calType = rs.getInt("cal_type");
				String calTypeName = rs.getString("cal_type_name");
				
				int departmentId = rs.getInt("department_id");
				Department department = new Department(departmentId);
				CalType CalType = new CalType(calType, calTypeName);
				Employee employee = new Employee(employeeId, employeeNameKr, department);
				Calendar cal = new Calendar(employee, calRevealed, CalType, calCnt, calStart, calEnd  );
				calendars.add(cal);
				
			}
			return calendars;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SelectException(e.getMessage());
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
		
	}

	 //부서일정 반환
	   @Override
	   public List<Calendar> selectCalDeptExcludeMy(String ym, int employeeId) throws SelectException {
	      List<Calendar> calendars = new ArrayList<Calendar>();
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         	         String selectSQL = "SELECT * "
	               + "FROM calendar c JOIN employee e ON (c.employee_id = e.employee_id)\r\n"
	               + "                JOIN cal_type t ON (c.cal_type = t.cal_type)\r\n"
	               + "WHERE c.cal_revealed =1 "
	               + "              AND e.department_id = (SELECT department_id FROM employee WHERE employee_id = ?) "
	               + "              AND e.employee_id != ? \r\n"
	               + "              AND(? = TO_CHAR(cal_start, 'MM') \r\n"
	               + "              OR ?  = TO_CHAR(cal_end, 'MM'))";
	        
	         con = MyConnection.getConnection();
	         pstmt = con.prepareStatement(selectSQL);
	         pstmt.setInt(1,  employeeId);
	         pstmt.setInt(2,  employeeId);
			 pstmt.setString(3, ym);
			 pstmt.setString(4, ym);
	         rs = pstmt.executeQuery();
	         while(rs.next()) {
	        	
	        	 
	            int calRevealed = rs.getInt("cal_revealed");
	            
	            int calType = rs.getInt("cal_type");
	            String calTypeName = rs.getString("cal_type_name");
	            
	            String calCnt = rs.getString("cal_cnt");
	            Date calStart = rs.getDate("cal_start");
	            Date calEnd = rs.getDate("cal_end");
	           
	            int departmentId = rs.getInt("department_id");
	            
	            String employeeNameKr = rs.getString("employee_name_kr");
	 
	            
	            CalType CalType = new CalType(calType, calTypeName); 
	            Department department = new Department(departmentId);
	            Employee employee = new Employee(employeeId, employeeNameKr, department);
	            Calendar cal = new Calendar(employee, calRevealed, CalType, calCnt, calStart, calEnd);
	            calendars.add(cal);
	            
	            
	            
	         }
	         return calendars;
	      } catch (SQLException e) {
	    	  e.printStackTrace();
	         throw new SelectException(e.getMessage());
	      }finally {
	         MyConnection.close(rs, pstmt, con);
	      }
	      
	   }

}


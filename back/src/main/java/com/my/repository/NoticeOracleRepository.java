<<<<<<< HEAD
package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.my.dto.Notice;
import com.my.exception.SelectException;
import com.my.sql.MyConnection;

public class NoticeOracleRepository implements NoticeRepository {
	
	@Override
	public List<Notice> selectNoticeId(int startRow, int endRow) throws SelectException {
		List<Notice> notices = new ArrayList<Notice>();
		Connection con = null;
		PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    String selectSQL = "select notice_title, notice_id from notice where rownum between ? AND ?";
		
	    try { 
				con = MyConnection.getConnection();	
				pstmt = con.prepareStatement(selectSQL);
			    pstmt.setInt(1, startRow);
			    pstmt.setInt(2, endRow);
			    rs = pstmt.executeQuery();
			    
				if (rs.next()) {
					int noticeId = rs.getInt("notice_id");
					int employeeId = rs.getInt("employee_id");
					String noticeCnt = rs.getString("notice_cnt");
					String noticeTitle = rs.getString("notice_title");
					java.sql.Date noticeTime = rs.getDate("notice_time");
					
//					Notice n = new Notice(noticeId, employeeId, noticeCnt, noticeTitle, noticeTime);
				  //List? 
					
					return notices;
				}else {
					throw new SelectException();
				}
			     
			 } catch (SQLException e) {
				 e.printStackTrace();
				 throw new SelectException(e.getMessage());
			 } finally {
			      MyConnection.close(rs, pstmt, con);
			    }    
	}
	

	@Override
	public List<Notice> selectByNoticeTitle(int startRow, int endRow, String noticeTitle) throws SelectException {
		List<Notice> notices = new ArrayList<Notice>();
		Connection con = null;
		PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    String selectSQL = "SELECT * FROM(\r\n"
	    		+ "    SELECT rownum as rn, n.notice_id, n.notice_title,n.notice_time,e.employee_name_kr\r\n"
	    		+ "        FROM notice n join employee e on(n.employee_id=e.employee_id)\r\n"
	    		+ "        WHERE notice_title like '%[검색된 값]%'\r\n"
	    		+ "        ORDER BY notice_id DESC\r\n"
	    		+ "        )\r\n"
	    		+ "    WHERE rn BETWEEN ? AND ?";

		try { 
				con = MyConnection.getConnection();
				pstmt = con.prepareStatement(selectSQL);
			    pstmt.setInt(1, startRow);
			    pstmt.setInt(2, endRow);
			    pstmt.setString(3, noticeTitle);
			    pstmt.executeQuery();
			    
			    if (rs.next()) {
					int noticeId = rs.getInt("notice_id");
					int employeeId = rs.getInt("employee_id");
					String noticeCnt = rs.getString("notice_cnt");
//					String noticeTitle = rs.getString("notice_title");
					Date noticeTime = rs.getDate("notice_time");
					
//					Notice n = new Notice(noticeId, employeeId, noticeCnt, noticeTitle, noticeTime);
				  //List? 
					
					return notices;
				}else {
					throw new SelectException("검색어를 포함한 제목의 공지사항이 없습니다");
				}
			     
			 } catch (SQLException e) {
				 e.printStackTrace();
				 throw new SelectException(e.getMessage());
			 } finally {
			      MyConnection.close(rs, pstmt, con);
			    }    
	}

	
	@Override
	public Notice selectByNoticeId(int noticeId) throws SelectException {
		Connection con = null;
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    String selectSQL = "SELECT *\r\n"
	    		+ "FROM (\r\n"
	    		+ "    SELECT rownum as rn, a.*\r\n"
	    		+ "    FROM(   \r\n"
	    		+ "        SELECT n.notice_id, n.notice_title, e.employee_id, n.notice_time\r\n"
	    		+ "        FROM notice n JOIN employee e ON (n.employee_id=e.employee_id) \r\n"
	    		+ "        ORDER BY notice_time DESC\r\n"
	    		+ "    ) a\r\n"
	    		+ ")\r\n"
	    		+ "WHERE rn BETWEEN ? AND ?";

		try { 
				con = MyConnection.getConnection();
				pstmt = con.prepareStatement(selectSQL);
			    pstmt.setInt(1, noticeId);
			    pstmt.executeQuery();
			    
			    if (rs.next()) {
//					int noticeId = rs.getInt("notice_id");
					int employeeId = rs.getInt("employee_id");
					String noticeCnt = rs.getString("notice_cnt");
					String noticeTitle = rs.getString("notice_title");
					Date noticeTime = rs.getDate("notice_time");
			    
			    Notice n = new Notice(noticeId, employeeId, noticeCnt, noticeTitle, noticeTime);
			
					
				return n;
				}else {
					throw new SelectException();
				}
			     
			 } catch (SQLException e) {
				 e.printStackTrace();
				 throw new SelectException(e.getMessage());
			 } finally {
			      MyConnection.close(rs, pstmt, con);
			    }    
	}
}
	
	
	
	
	
	
	
=======
package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.my.dto.Employee;
import com.my.dto.Notice;
import com.my.exception.SelectException;
import com.my.sql.MyConnection;

public class NoticeOracleRepository implements NoticeRepository {
	
	@Override
	public List<Notice> selectNoticesByWritingtime(int startRow, int endRow) throws SelectException {
		List<Notice> notices = new ArrayList<Notice>();
		Connection con = null;
		PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    String selectSQL = "select * from notice n\r\n"
	    		+ "join employee e on (e.employee_id = n.employee_id)\r\n"
	    		+ "where rownum between ? AND ? ";
	    		// sql 보류
		
	    try { 
				con = MyConnection.getConnection();	
				pstmt = con.prepareStatement(selectSQL);
			        pstmt.setInt(1, startRow);
			        pstmt.setInt(2, endRow);
			        rs = pstmt.executeQuery();
			    
				while(rs.next()== true)  {
					Notice notice = new Notice();
					Employee employee = new Employee();
			        	notice.setNoticeId(rs.getInt("notice_id"));
					employee.setEmployeeId(rs.getInt("employee_id"));
					notice.setEmployee(employee);
					notice.setNoticeCnt(rs.getString("notice_cnt"));
					notice.setNoticeTitle(rs.getString("notice_title"));
					notice.setNoticeTime(rs.getDate("notice_time"));
					
					notices.add(notice); // list에 방금 생성한 객체 넣기
					
				}
					
				
			     
			 } catch (SQLException e) {
				 e.printStackTrace();
				 throw new SelectException(e.getMessage());
			 } finally {
			      MyConnection.close(rs, pstmt, con);
			    }    
	    return notices;
	}
	

	@Override
	public List<Notice> selectByNoticeTitle(int startRow, int endRow, String noticeTitle) throws SelectException {
		List<Notice> notices = new ArrayList<Notice>();
		Connection con = null;
		PreparedStatement pstmt = null;
	    	ResultSet rs = null;

	    String selectSQL = "SELECT * FROM(\r\n"
	    		+ "    SELECT rownum as rn, n.notice_id, n.notice_title,n.notice_time,e.employee_name_kr\r\n"
	    		+ "        FROM notice n join employee e on(n.employee_id=e.employee_id)\r\n"
	    		+ "        WHERE notice_title like '% ? %'\r\n"
	    		+ "        ORDER BY notice_id DESC\r\n"
	    		+ "        )\r\n"
	    		+ "    WHERE rn BETWEEN ? AND ? ";

		try { 
			    con = MyConnection.getConnection();
			    pstmt = con.prepareStatement(selectSQL);
			    pstmt.setInt(1, startRow);
			    pstmt.setInt(2, endRow);
			    pstmt.setString(3, noticeTitle);
			    rs = pstmt.executeQuery();
			    
			    while(rs.next())  {
					Notice notice = new Notice();
					Employee employee = new Employee();
			        	notice.setNoticeId(rs.getInt("notice_id"));
					employee.setEmployeeId(rs.getInt("employee_id"));
					notice.setEmployee(employee);
					notice.setNoticeCnt(rs.getString("notice_cnt"));
					notice.setNoticeTitle(rs.getString("notice_title"));
					notice.setNoticeTime(rs.getDate("notice_time"));
					
					notices.add(notice); // list에 방금 생성한 객체 넣기
					
				}
		 
			} catch (SQLException e) {
				 e.printStackTrace();
				 throw new SelectException(e.getMessage());
			 } finally {
			      MyConnection.close(rs, pstmt, con);
			    }    
	    return notices;
	}
	
	@Override
	public Notice selectByNoticeId(int noticeId) throws SelectException {
		Connection con = null;
		PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    
	    String selectSQL = "SELECT notice_title, employee_name_kr, notice_time, notice_cnt\r\n"
	    		+ "FROM notice n JOIN employee e ON (n.employee_id = e.employee_id)\r\n"
	    		+ "WHERE notice_id = ? ;\r\n"
	    		+ "";

		try { 
			    con = MyConnection.getConnection();
			    pstmt = con.prepareStatement(selectSQL);
			    pstmt.setInt(1, noticeId);
			    rs = pstmt.executeQuery();
			    
			    if (rs.next()) {
//					int noticeId = rs.getInt("notice_id");
					String noticeCnt = rs.getString("notice_cnt");
					String noticeTitle = rs.getString("notice_title");
					java.sql.Date noticeTime = rs.getDate("notice_time");
					String employeeNameKr = rs.getString("employee_name_kr");
					int employeeId =  rs.getInt("employee_id");
					Employee employee = new Employee(employeeId, employeeNameKr);
					
					
			    Notice n = new Notice(noticeId, noticeCnt, noticeTitle, noticeTime, employee);
			
					
				return n;
				}else {
					throw new SelectException();
				}
			     
			 } catch (SQLException e) {
				 e.printStackTrace();
				 throw new SelectException(e.getMessage());
			 } finally {
			      MyConnection.close(rs, pstmt, con);
			    }    
	}
}
	
>>>>>>> a8a07c25d0df4833f23054c367935878cff1314c

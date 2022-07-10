package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.CalType;
import com.my.dto.Calendar;
import com.my.exception.SelectException;

import sql.MyConnection;

public class CaltypeOracleRepository implements CaltypeRepository {

	@Override
	public List<CalType> selectCaltype() throws SelectException {

		List<CalType> caltype = new ArrayList<CalType>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
			String selectSQL = "SELECT cal_type, cal_type_name FROM cal_type";
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int calType = rs.getInt("cal_type");
				String calTypeName = rs.getString("cal_type_name");
			}
		} catch (SQLException e) {
			throw new SelectException();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		
		return caltype;
	}

}

package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.HolidayType;
import com.my.exception.SelectException;
import com.my.sql.MyConnection;

public class HolidayTypeOracleRepository implements HolidayTypeRepository {

	// 휴가유형 반환
	@Override
	public List<HolidayType> selectHolidayType() throws SelectException {
		// TODO Auto-generated method stub
		List<HolidayType> holidaytypes = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT holiday_type_name, holiday_type FROM holiday_type";
		
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			// 무조건 휴가유형만 가져오면 되는데 이게 없을리는 없으니 없는 상황을 가정하지 않아도 될 것 같다
			while(rs.next()) {
				int type = rs.getInt("holiday_type");
				String name = rs.getString("holiday_type_name");
				
				HolidayType ht = new HolidayType(type, name);
				holidaytypes.add(ht);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
		return holidaytypes;
	}

}

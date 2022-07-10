package com.my.repository;

import java.util.ArrayList;
import java.util.List;

import com.my.dto.Notice;
import com.my.exception.SelectException;

public class Test {

	public static void main(String[] args) {
		NoticeRepository r = new NoticeOracleRepository(); //리포지토리 객체 생성
		List<Notice> notices = new ArrayList<Notice>();
		try {
			notices = r.selectNoticesByWritingtime(3, 5);
		} catch (SelectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < notices.size(); i++) {
			System.out.println(notices.get(i));
		}
	}

}

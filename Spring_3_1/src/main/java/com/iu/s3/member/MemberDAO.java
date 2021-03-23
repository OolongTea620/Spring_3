package com.iu.s3.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.iu.s3.bankbook.BankBookDTO;

public class MemberDAO {
	
	public void menberSelect()throws Exception{
		//조회 아무거나
	}
	
	public void memberUpdate()throws Exception {
		//id룰 제외하고 나머지 수정
		
	}
	
	//memberJoin 데이터를 바다엇 DB에 insert하는 메소드
	public void memberJoin(MemberDTO memberDTO)throws Exception {
		
		//1.oracleDB 접속하기
		String user="user01";
		String password="user01";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";

		//2. 클래스 로딩
		Class.forName(driver);

		//3. 로그인 Connection
		Connection con = DriverManager.getConnection(url, user, password);

		String sql ="select * from bankbook";

		PreparedStatement st = con.prepareStatement(sql);

		ResultSet rs = st.executeQuery();
		System.out.println("executeQuery----------");
		while(rs.next()) {
			System.out.println("count");
			MemberDTO memberDTO1 = new MemberDTO();
			memberDTO1.setId(rs.getString("memberID"));
			memberDTO1.setPw(rs.getString("memberPw"));
			memberDTO1.setName(rs.getString("memberName"));
			memberDTO1.setPhone(rs.getString("memberPhone"));
			memberDTO1.setEmail(rs.getString("memberEmail"));
		}

		rs.close();
		st.close();
		con.close();

	}
}

package com.iu.s3.bankbook;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BankBookDAO {
	
	@Autowired 
	private SqlSession sqlSession;
	//패키지와그 클래스 명을 가리키기 위한 상수(고정) 문자열.
	private final String NAMESPACE = "com.iu.s3.bankbook.BankBookDAO"; 
	
	
	public int setUpdate(BankBookDTO bankBookDTO)throws Exception{
		return sqlSession.update(NAMESPACE+".setUpdate",bankBookDTO);
	}
	
	public int setDelete(BankBookDTO bankBookDTO)throws Exception{
		return sqlSession.delete(NAMESPACE+".setUpdate",bankBookDTO);
	}
	
	public BankBookDTO getSelect(BankBookDTO bankBookDTO)throws Exception{
		long num = 1L;
		sqlSession.selectOne(NAMESPACE+".getSelect", num);
		//없으면 자동으로 0이 들어간다.
		
		return bankBookDTO;
	}	
	
	public int setWrite(BankBookDTO bankBookDTO)throws Exception{
		
		//실행해야하는sql문의 위치를 알려준다.
		int result = sqlSession.insert(NAMESPACE+".setWrite",bankBookDTO);
		return result;

	}
	
	//getList
	//bankbook table의 모든 데이트 조회 후 리턴
	public List<BankBookDTO> getList()throws Exception{
		ArrayList<BankBookDTO> ar = new ArrayList<BankBookDTO>();

		//1. 로그인 정보 
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
			BankBookDTO bankBookDTO = new BankBookDTO();
			bankBookDTO.setBookNumber(rs.getLong("bookNumber"));
			bankBookDTO.setBookName(rs.getString("bookName"));
			bankBookDTO.setBookRate(rs.getDouble("bookRate"));
			bankBookDTO.setBookSale(rs.getString("bookSale"));
			ar.add(bankBookDTO);
		}

		rs.close();
		st.close();
		con.close();

		return ar;
	}

}

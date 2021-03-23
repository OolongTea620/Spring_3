package com.iu.s3.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class MemberDAO {
	
	@Autowired 
	private SqlSession sqlSession; //조회 XMl문서 관련
	
	//패키지와그 클래스 명을 가리키기 위한 상수(고정) 문자열.
	private final String NAMESPACE = "com.iu.s3.member.MemberDAO"; 
	
	
	public int setUpdate(MemberDTO memberDTO)throws Exception{
		return sqlSession.update(NAMESPACE+".setUpdate",memberDTO);
	}
	
	public int setDelete(MemberDTO memberDTO)throws Exception{
		return sqlSession.delete(NAMESPACE+".setUpdate",memberDTO);
	}
	
	public MemberDTO getSelect(MemberDTO memberDTO)throws Exception{
		long num = 1L;
		sqlSession.selectOne(NAMESPACE+".getSelect", num);
		//없으면 자동으로 0이 들어간다.
		
		return memberDTO;
	}	
	
	public int setWrite(MemberDTO memberDTO)throws Exception{
		
		//실행해야하는sql문의 위치를 알려준다.
		int result = sqlSession.insert(NAMESPACE+".setWrite",memberDTO);
		return result;

	}
	
	//memberJoin 데이터를 받아서 DB에 insert 하는 메서드
	public int memberJoin(MemberDTO memberDTO)throws Exception{
		//1. 로그인 정보 
		String user="user01";
		String password="user01";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		//2. 클래스 로딩
		Class.forName(driver);
		
		//3. 로그인 Connection
		Connection con = DriverManager.getConnection(url, user, password);
		
		//4. SQL
		String sql ="insert into member values(?,?,?,?,?)";
		
		//5. 미리 전송
		PreparedStatement st = con.prepareStatement(sql);
		
		//6. ? 세팅
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		st.setString(3, memberDTO.getName());
		st.setString(4, memberDTO.getEmail());
		st.setString(5, memberDTO.getPhone());
		
		//7. 최종 전송 후 처리
		int result = st.executeUpdate();
		
		//8. 해제
		st.close();
		con.close();
		
		return result;
		
	}
	
	
	//login - id pw를 받아서 조회
	public MemberDTO memberLogin(MemberDTO memberDTO)throws Exception{
		
		//1. 로그인 정보 
		String user="user01";
		String password="user01";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		//2. 클래스 로딩
		Class.forName(driver);
		
		//3. 로그인 Connection
		Connection con = DriverManager.getConnection(url, user, password);
		
		//4. SQL문 생성
		String sql ="SELECT * FROM member WHERE id=? and pw=?";
		
		//5. 미리 보내기
		PreparedStatement st = con.prepareStatement(sql);
		
		//6. ? 세팅
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		
		//7. 최종 전송 후 처리
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			memberDTO.setName(rs.getString("name"));
			memberDTO.setEmail(rs.getString("email"));
			memberDTO.setPhone(rs.getString("phone"));
		}else {
			memberDTO = null;
		}
		
		//8. 해제
		rs.close();
		st.close();
		con.close();
		
		return memberDTO;
	}
}

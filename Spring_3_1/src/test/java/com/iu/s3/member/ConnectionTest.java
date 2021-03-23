package com.iu.s3.member;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ConnectionTest extends MyAbstractTest{
	
	@Autowired 
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void connectionTest() throws Exception {
		
		//primitive type,String
		//asserEquals(0,0);
		
		//reference type
		assertNotNull(sqlSession);
		
	
	}
		
}

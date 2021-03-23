package com.iu.s3.bankbook;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.s3.MyAbstractTest;

public class BankBookDAOTest extends MyAbstractTest{
	
	@Autowired
	private BankBookDAO bankBookDAO;
	
	public void setUpdateTest()throws Exception{
		BankBookDTO bankBookDTO = new BankBookDTO();
		bankBookDTO.setBookNumber(5);
		bankBookDTO.setBookName("change");
		bankBookDTO.setBookRate(0.001);
		bankBookDTO.setBookSale("N");
		int result = bankBookDAO.setUpdate(bankBookDTO);
		assertEquals(1, result);
	}
	
	//@Test
	public void getListTest () throws Exception{
		List<BankBookDTO> ar = bankBookDAO.getList();
		assertNotEquals(0,ar.size());
	}
	
	//@Test
	public void getSelectTest()throws Exception{
		BankBookDTO bankBookDTO = bankBookDAO.getSelect(null);
		assertNotNull(bankBookDAO);
	}
	
	//@Test
	public void setWriteTest() throws Exception {
		
		BankBookDTO bankBookDTO = new BankBookDTO();
		 bankBookDTO.setBookName("Test");
		 bankBookDTO.setBookRate(0.12);
		 bankBookDTO.setBookSale("Y");
		 
		 int result = bankBookDAO.setWrite(bankBookDTO);
		 assertEquals(1, result);
	}
}

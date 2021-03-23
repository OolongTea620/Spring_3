package com.iu.s3.bankbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankbookService {
	
	@Autowired
	private BankBookDAO bankBookDAO;
	
	public BankbookService() {
		//결합도가 높다, 강하다 : BankbookService 수정시,여기도 수정해야 한다.
		bankBookDAO = new BankBookDAO();
	} 
	
	
	
	public void setBankBookDAO(BankBookDAO bankBookDAO) {
		//결합도가 낮다, 느슨하다.: bankbookDAO와 독립적인 연결을 가지고 있다.
		
		this.bankBookDAO = bankBookDAO;
	}


	public BankBookDTO getSelect(BankBookDTO bankBookDTO)throws Exception{
		return bankBookDAO.getSelect(bankBookDTO);
	}
	
	public List<BankBookDTO> getList()throws Exception{
		return bankBookDAO.getList();
	}

}
package com.iu.s3.bankbook;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BankbookController {
	
	@Autowired
	private BankbookService bankbookService;
	
	//상품 리스트
	@RequestMapping(value = "/bankbook/bankbookList")
	public void bankbookList(Model model)throws Exception{
		
		List<BankBookDTO> ar=  bankbookService.getList();
		model.addAttribute("list", ar);
		model.addAttribute("kind", "BankBook El Test");
	}
	
	//상품 상세
	@RequestMapping(value = "/bankbook/bankbookSelect")
	public ModelAndView bankbookSelect(BankBookDTO bankBookDTO, ModelAndView modelAndView) throws Exception {
		System.out.println("Num : "+bankBookDTO.getBookNumber());
		bankBookDTO = bankbookService.getSelect(bankBookDTO);
		modelAndView.addObject("dto", bankBookDTO);
		modelAndView.setViewName("bankbook/bankbookSelect");
		return modelAndView;
	}
	
	//상품등록
	@RequestMapping(value = "/bankbook/bankbookAdd")
	public void bankbookAdd()throws Exception{}
	
	@RequestMapping(value = "/bankbook/bankbookAdd", method = RequestMethod.POST)
	public ModelAndView bankbookAdd(BankBookDTO bankBookDTO, ModelAndView modelAndView)throws Exception{
		modelAndView.setViewName("");
		return modelAndView;
	}
	
	
	//상품 수정
	@RequestMapping(value = "/bankbook/bankbookUpdate")
	public void bankbookUpdate(BankBookDTO bankBookDTO)throws Exception{
		
	}
	
	@RequestMapping(value = "/bankbook/bankbookUpdate", method = RequestMethod.POST)
	public ModelAndView bankbookUpdate(BankBookDTO bankBookDTO, ModelAndView modelAndView)throws Exception{
		return modelAndView;
	}
	
	//상품 삭제
	@RequestMapping(value = "/bankbook/bankbookDelete")
	public ModelAndView bankbookDelete(BankBookDTO bankBookDTO, ModelAndView modelAndView)throws Exception{
		return modelAndView;
	}
	
}

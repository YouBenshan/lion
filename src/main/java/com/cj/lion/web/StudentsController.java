package com.cj.lion.web;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cj.lion.domain.StudentInfo;
import com.cj.lion.repository.StudentInfoRepository;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
@RequestMapping(value = "/user")
public class StudentsController {
	public static final String FINISH_PAGE="/resources/site/personalFinish.html";
	
	@Autowired
	private StudentInfoRepository studentInfoRepository;
	
	@RequestMapping(value = "/studentInfo", method = RequestMethod.GET)
	public String infoForm(@ModelAttribute StudentInfo studentInfo) {
		return "user/studentInfo";
	}
	
	@RequestMapping(value = "/studentInfo", method = RequestMethod.POST)
	public String infoSave(@Valid StudentInfo info, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return infoForm(info);
		}
		studentInfoRepository.save(info);
		return "redirect:"+FINISH_PAGE;
	}
	
	
}

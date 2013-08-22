package com.cj.web.admin;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cj.domain.received.ReceivedMessage;
import com.cj.repository.received.ReceivedMessageRepository;
import com.cj.utils.query.Filter;
import com.cj.utils.query.Search;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class AdminController {
	
	@Autowired
	private ReceivedMessageRepository receivedMessageRepository;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin() {
		return "admin";
	}
	
	@RequestMapping("/template/**")
	public String templates(HttpServletRequest request){
		String uri=request.getRequestURI();
		return uri.substring(uri.indexOf("/template/"));
	}

	@RequestMapping(value = "/admin/receivedMessage", method = RequestMethod.GET)
	public @ResponseBody
	Page<ReceivedMessage> receivedMessage(Pageable pageable) {
		log.info(pageable+"");
		Page<ReceivedMessage> result = receivedMessageRepository.findAll(pageable);
		return result;
	}


	
	@RequestMapping(value = "/admin/receivedMessage/search", method = RequestMethod.POST)
	public @ResponseBody
	Page<ReceivedMessage> search(@Search(ReceivedMessage.class) Filter<ReceivedMessage> filter,Pageable pageable) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		Page<ReceivedMessage> result = receivedMessageRepository.findAll(filter.getSpecification(), pageable);
		return result;
	}
}

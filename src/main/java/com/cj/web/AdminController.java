package com.cj.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cj.domain.received.EventReceivedMessage;
import com.cj.domain.received.ReceivedMessage;
import com.cj.repository.received.ActiveInfo;
import com.cj.repository.received.EventReceivedMessageRepository;
import com.cj.repository.received.ReceivedMessageRepository;
import com.cj.utils.query.Search;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class AdminController {
	
	@Autowired
	private ReceivedMessageRepository receivedMessageRepository;
	
	@Autowired
	private EventReceivedMessageRepository eventReceivedMessageRepository;
	
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


	
	@RequestMapping(value = "/admin/receivedMessage/search", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public @ResponseBody
	Page<ReceivedMessage> search(@Search(ReceivedMessage.class) Specification<ReceivedMessage> specification,Pageable pageable){

		Page<ReceivedMessage> result = receivedMessageRepository.findAll(specification, pageable);
		return result;
	}
	
	@RequestMapping(value = "/admin/eventReceivedMessage/countSubscribe", method = RequestMethod.GET)
	public @ResponseBody
	Page<EventReceivedMessage> countSubscribe(Pageable pageable) {
		int OneDayBefore= (int) (new Date().getTime()/1000) -10*24*60*60;
		return eventReceivedMessageRepository.findByEventAndCreateTimeGreaterThan(EventReceivedMessage.SUBSCRIBE, OneDayBefore , pageable);
	}
	
	@RequestMapping(value = "/admin/receivedMessage/topActive", method = RequestMethod.GET)
	public @ResponseBody
	Page<ActiveInfo> topActive(Pageable pageable) {
		return receivedMessageRepository.findActive(pageable);
	}
	

	
}

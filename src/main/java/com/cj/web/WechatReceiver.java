package com.cj.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.crypto.hash.Sha1Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import com.cj.config.AppProperties;
import com.cj.domain.TencentIp;
import com.cj.domain.received.ReceivedMessage;
import com.cj.replyer.MessageSender;
import com.cj.repository.TencentIpRepository;
import com.cj.repository.received.ReceivedMessageRepository;
import com.cj.utils.ReceivedMessageParser;

@Slf4j
@Controller
@RequestMapping(value = WechatReceiver.WECHAT_RECEIVER_URL)
public class WechatReceiver {
	public final static String WECHAT_RECEIVER_URL = "/wechatReceiver";
	private final Set<String> ips = new HashSet<String>();
	
	@Autowired
	private AppProperties appProperties;
	
	@Autowired
	private TencentIpRepository tencentIpRepository;

	@Autowired
	private MessageSender messageSender;

	@Autowired
	private ReceivedMessageRepository receivedMessageRepository;

	@RequestMapping(method = RequestMethod.GET)
	public void connect(ServletRequest request, Writer writer,
			HttpServletResponse response, @RequestParam String signature,
			@RequestParam String timestamp, @RequestParam String nonce,
			@RequestParam String echostr) throws IOException {
		String ip = request.getRemoteAddr();
		if (validate(timestamp, nonce, signature)) {
			if(!ips.contains(ip)){
				saveAndCache(ip);
			}
			writer.write(echostr);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN,
					"That means goodbye forever!");
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public void reply(ServletRequest request, InputStream inputStream,
			Writer writer) throws ParserConfigurationException, SAXException,
			IOException {
		String ip = request.getRemoteAddr();
		if (!ips.contains(ip)) {
			return;
		}

		ReceivedMessage receivedMessage = ReceivedMessageParser
				.parse(inputStream);
		log.info(receivedMessage.getOther() + " " +receivedMessage.getCreateTime());

		receivedMessageRepository.save(receivedMessage);

		String sentMessage = messageSender.generate(receivedMessage);
		writer.write(sentMessage);
	}
	


	private boolean validate(String timestamp, String nonce, String signature) {
		Set<String> set = new TreeSet<String>();
		set.add(appProperties.getServerTocken());
		set.add(timestamp);
		set.add(nonce);

		StringBuilder sb = new StringBuilder();
		for (String s : set) {
			sb.append(s);
		}

		String s = new Sha1Hash(sb.toString()).toHex();
		return signature.equals(s);
	}
	
	private void saveAndCache(String ip){
		ips.add(ip);
		log.info(ip);
		TencentIp tencentIp=new TencentIp();
		tencentIp.setIp(ip);
		tencentIpRepository.save(tencentIp);
	}
	
	@PostConstruct
	public void initIpCache(){
		List<TencentIp> tencentIps=tencentIpRepository.findAll();
		for(TencentIp tencentIp: tencentIps){
			ips.add(tencentIp.getIp());
		}
	}
	
}

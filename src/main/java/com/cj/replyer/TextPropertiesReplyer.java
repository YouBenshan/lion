package com.cj.replyer;

import java.util.Properties;

import com.cj.domain.received.ReceivedMessage;
import com.cj.domain.received.TextReceivedMessage;
import com.cj.domain.sent.SentContent;
import com.cj.domain.sent.TextSentContent;
import com.cj.utils.PropertiesUtil;

public class TextPropertiesReplyer implements Replyer {
	private Properties properties;
	private final static String propertiesFilePath = "/textReply.properties";
	
	public TextPropertiesReplyer() {
		this(propertiesFilePath);
	}
	
	public TextPropertiesReplyer(String propertiesFilePath) {
		properties = PropertiesUtil.read(propertiesFilePath);
	}

	@Override
	public SentContent reply(ReceivedMessage receivedMessage) {
		if (!(receivedMessage instanceof TextReceivedMessage)) {
			return null;
		}
		String incomingContent = ((TextReceivedMessage) receivedMessage)
				.getContent();
		String replyContent = properties.getProperty(incomingContent);
		if (replyContent == null) {
			return null;
		}
		TextSentContent textSentContent = new TextSentContent();
		textSentContent.setContent(replyContent);
		return textSentContent;
	}

}

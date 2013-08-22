package com.cj.replyer;

import lombok.Setter;

import com.cj.domain.received.EventReceivedMessage;
import com.cj.domain.received.ReceivedMessage;
import com.cj.domain.sent.SentContent;

@Setter
public class SubscribeReplyer implements Replyer{

	private static final String SUBSCRIBE="subscribe";
	private SentContent sentContent;

	@Override
	public SentContent reply(ReceivedMessage receivedMessage) {
		if (receivedMessage instanceof EventReceivedMessage) {
			String event = ((EventReceivedMessage) receivedMessage).getEvent();
			if(SUBSCRIBE.equalsIgnoreCase(event)){
				return sentContent;
			}
		}
		return null;
	}


}

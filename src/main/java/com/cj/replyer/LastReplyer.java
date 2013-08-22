package com.cj.replyer;

import lombok.Setter;

import com.cj.domain.received.ReceivedMessage;
import com.cj.domain.sent.SentContent;

@Setter
public class LastReplyer implements Replyer {

	private SentContent sentContent;

	@Override
	public SentContent reply(ReceivedMessage receivedMessage) {
		return sentContent;
	}

}

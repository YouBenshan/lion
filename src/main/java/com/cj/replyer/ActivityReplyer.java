package com.cj.replyer;

import java.sql.Date;

import lombok.Setter;

import com.cj.domain.received.ReceivedMessage;
import com.cj.domain.sent.SentContent;

@Setter
public class ActivityReplyer implements Replyer {

	private String startSign;
	private String endSign;
	private int expirySeconds;
	private Date startDate;
	private Date endDate;

	@Override
	public SentContent reply(ReceivedMessage receivedMessage) {
		// TODO Auto-generated method stub
		return null;
	}
}

package com.cj.replyer;

import com.cj.domain.received.ReceivedMessage;
import com.cj.domain.sent.SentContent;

public interface Replyer {
	SentContent reply(ReceivedMessage receivedMessage);
}

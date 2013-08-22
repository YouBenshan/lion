package com.cj.replyer;

import lombok.Setter;

import com.cj.domain.received.ReceivedMessage;
import com.cj.domain.sent.SentContent;

@Setter
public class ReplyerChain implements Replyer {
	private LastReplyer lastReplyer;
	private SubscribeReplyer subscribeReplyer;
	private TextPropertiesReplyer textPropertiesReplyer;
	private NewsPropertiesReplyer newsPropertiesReplyer;
	private ActivityReplyer activityReplyer;

	@Override
	public SentContent reply(ReceivedMessage receivedMessage) {
		SentContent sentContent=null;
		if(activityReplyer!=null){
			sentContent= activityReplyer.reply(receivedMessage);
		}
		if (sentContent != null) {
			return sentContent;
		}
		if(newsPropertiesReplyer!=null){
			sentContent= newsPropertiesReplyer.reply(receivedMessage);
		}
		if (sentContent != null) {
			return sentContent;
		}
		if(textPropertiesReplyer!=null){
			sentContent= textPropertiesReplyer.reply(receivedMessage);
		}
		if (sentContent != null) {
			return sentContent;
		}
		if(subscribeReplyer!=null){
			sentContent= subscribeReplyer.reply(receivedMessage);
		}
		if (sentContent != null) {
			return sentContent;
		}
		if(lastReplyer!=null){
			sentContent= lastReplyer.reply(receivedMessage);
		}
		return sentContent;
	}
}
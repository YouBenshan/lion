package com.cj.replyer;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

import com.cj.domain.received.ReceivedMessage;
import com.cj.domain.sent.SentContent;

@Setter
public class ReplyerChain implements Replyer {
	private LastReplyer lastReplyer;
	private SubscribeReplyer subscribeReplyer;
	private TextPropertiesReplyer textPropertiesReplyer;
	private NewsPropertiesReplyer newsPropertiesReplyer;
	
	private final List<Replyer> customReplyers=new ArrayList<Replyer>();
	public void addCustomReplyer(Replyer replyer){
		customReplyers.add(replyer);
	}

	@Override
	public SentContent reply(ReceivedMessage receivedMessage) {
		SentContent sentContent=null;
		for(Replyer replyer:customReplyers){
			sentContent=replyer.reply(receivedMessage);
			if(sentContent!=null){
				return sentContent;
			}
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
package com.cj.replyer;

import lombok.Setter;

import com.cj.domain.received.ReceivedMessage;
import com.cj.domain.sent.SentContent;
import com.cj.utils.XmlUtil;

@Setter
public class MessageSender {
	private ReplyerChain replyerChain;
	private String wechatId;

	public String generate(ReceivedMessage receivedMessage) {
		SentContent sentContent = replyerChain.reply(receivedMessage);
		StringBuilder sb = new StringBuilder();
		XmlUtil.appendTagPre(sb, "xml");
		XmlUtil.appendCDdataElement(sb, "ToUserName",
				receivedMessage.getOther());
		XmlUtil.appendCDdataElement(sb, "FromUserName", wechatId);
		XmlUtil.appendTextElement(sb, "CreateTime",
				"" + System.currentTimeMillis() / 1000);
		sb.append(sentContent.toXml());
		XmlUtil.appendTagPost(sb, "xml");
		return sb.toString();

	}

}

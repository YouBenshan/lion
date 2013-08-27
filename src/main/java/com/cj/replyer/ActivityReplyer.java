package com.cj.replyer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Setter;

import org.springframework.web.util.UriComponentsBuilder;

import com.cj.domain.received.ImageReceivedMessage;
import com.cj.domain.received.ReceivedMessage;
import com.cj.domain.received.TextReceivedMessage;
import com.cj.domain.sent.Article;
import com.cj.domain.sent.NewsSentContent;
import com.cj.domain.sent.SentContent;
import com.cj.domain.sent.TextSentContent;
import com.cj.lion.config.LionRootConfig;

@Setter
public class ActivityReplyer implements Replyer {

	private Date startDate;
	private Date endDate;
	private static final String FLAG = "新生新体验";
	private static final String INTRODUCE = "欢迎参加“改变，从我的细齿洁牙刷开始”活动，即日起至10月7日，北上广深四地高校新生，只要晒出小细送出的细齿洁牙刷靓照，即有机会赢取好礼！请用微信上传相片";
	private final TextSentContent textSentContent;

	private static final String TITLE = "请提交联系方式来抽奖";
	private static final String DESCRIPTION = "我们将选出最美牙齿";
	private static final String PIC_URL = LionRootConfig.WEB_BASE_LINK
			+ "resources/site/student/student.jpg";
	private static final String URL = LionRootConfig.WEB_BASE_LINK
			+ "/user/studentInfo";

	public ActivityReplyer() {
		textSentContent = new TextSentContent();
		textSentContent.setContent(INTRODUCE);
	}

	@Override
	public SentContent reply(ReceivedMessage receivedMessage) {
//		Date now = new Date();
//		if (now.before(startDate) || now.after(endDate)) {
//			return null;
//		}
		if (receivedMessage instanceof TextReceivedMessage) {
			String incomingContent = ((TextReceivedMessage) receivedMessage)
					.getContent();
			if (FLAG.equals(incomingContent)) {
				return textSentContent;
			}
			;
		}
		if (receivedMessage instanceof ImageReceivedMessage) {
			NewsSentContent newsSentContent = new NewsSentContent();
			List<Article> articles = new ArrayList<Article>();
			newsSentContent.setArticles(articles);

			Article article = new Article();
			article.setTitle(TITLE);
			article.setDescription(DESCRIPTION);
			article.setPicUrl(PIC_URL);

			String url = UriComponentsBuilder.fromHttpUrl(URL)
					.queryParam("wechatId", receivedMessage.getOther()).build()
					.toUriString();
			article.setUrl(url);

			return newsSentContent;
		}
		return null;
	}
}

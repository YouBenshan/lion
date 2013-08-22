package com.cj.domain.sent;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.cj.utils.XmlUtil;

@Setter
@Getter
public class NewsSentContent implements SentContent {

	private List<Article> articles;

	@Override
	public StringBuilder toXml() {
		StringBuilder sb = new StringBuilder();
		XmlUtil.appendCDdataElement(sb, "MsgType", "news");
		int articleCount = articles.size();
		XmlUtil.appendCDdataElement(sb, "ArticleCount",
				String.valueOf(articleCount));
		XmlUtil.appendTagPre(sb, "Articles");
		for (Article article : articles) {
			article.appendXml(sb);
		}
		XmlUtil.appendTagPost(sb, "Articles");
		return sb;
	}
}

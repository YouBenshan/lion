package com.cj.replyer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.cj.domain.received.ReceivedMessage;
import com.cj.domain.received.TextReceivedMessage;
import com.cj.domain.sent.Article;
import com.cj.domain.sent.NewsSentContent;
import com.cj.domain.sent.SentContent;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

public class NewsPropertiesReplyer implements Replyer {
	private final String SEPERATOR = "|";
	private final int ARTICLE_MAX_SIZE = 10;
	private final String TITLE = "title";
	private final String DESCRIPTION = "description";
	private final String PIC_URL = "picUrl";
	private final String URL = "url";

	Map<String, NewsSentContent> map = new HashMap<String, NewsSentContent>();
	private final static String propertiesFileFolder = "/newsReply";

	public NewsPropertiesReplyer() throws FileNotFoundException, IOException {
		File folder = new File(this.getClass()
				.getResource(propertiesFileFolder).getPath());
		File[] propertiesFiles = folder.listFiles();
		Set<Properties> propertiesSet = new HashSet<Properties>();

		for (File file : propertiesFiles) {
			Properties properties = new Properties();
			properties.load(new FileReader(file));
			propertiesSet.add(properties);
		}
		for (Properties properties : propertiesSet) {
			String[] keywords = StringUtils.split(
					properties.getProperty("keywords"), SEPERATOR);
			NewsSentContent sentContent = new NewsSentContent();
			for (String keyword : keywords) {
				map.put(keyword, sentContent);
			}
			properties.remove("keywords");

			List<Article> articles = new ArrayList<Article>(ARTICLE_MAX_SIZE);
			sentContent.setArticles(articles);

			Table<Integer, String, String> articleTable = HashBasedTable
					.create();

			for (Entry<Object, Object> e : properties.entrySet()) {
				String key = (String) e.getKey();
				String[] keyArray = StringUtils.split(key, SEPERATOR);
				Integer row = Integer.valueOf(keyArray[0]);
				String collow = keyArray[1];
				String value = (String) e.getValue();
				articleTable.put(row, collow, value);
			}
			List<Integer> order = Lists.newArrayList(articleTable.rowKeySet());
			Collections.sort(order);
			for (Integer row : articleTable.rowKeySet()) {
				Article article = new Article();
				article.setTitle(articleTable.get(row, TITLE));
				article.setDescription(articleTable.get(row, DESCRIPTION));
				article.setPicUrl(articleTable.get(row, PIC_URL));
				article.setUrl(articleTable.get(row, URL));
				articles.add(article);
			}
		}
	}

	@Override
	public SentContent reply(ReceivedMessage receivedMessage) {
		if (!(receivedMessage instanceof TextReceivedMessage)) {
			return null;
		}
		String incomingContent = ((TextReceivedMessage) receivedMessage)
				.getContent();
		NewsSentContent newsSentContent = map.get(incomingContent);
		if (newsSentContent == null) {
			return null;
		}
		List<Article> articles = newsSentContent.getArticles();
		for (Article article : articles) {
			String url = UriComponentsBuilder.fromHttpUrl(article.getUrl())
					.queryParam("other", receivedMessage.getOther()).build()
					.toUriString();
			article.setUrl(url);
		}

		return newsSentContent;
	}

}

package com.cj.replyer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.cj.domain.received.ReceivedMessage;
import com.cj.domain.received.TextReceivedMessage;
import com.cj.domain.sent.Article;
import com.cj.domain.sent.NewsSentContent;
import com.cj.domain.sent.SentContent;
import com.cj.utils.PropertiesUtil;

public class NewsPropertiesReplyer implements Replyer{
	private final int MAX_ITEM=10;
	private final String TITLE="title";
	private final String DESCRIPTION="description";
	private final String PIC_URL="picUrl";
	private final String URL="url";
	
	Map<String,NewsSentContent> map=new HashMap<String,NewsSentContent>();
	private final static String propertiesFilePath = "/newsReply.properties";
	
	public NewsPropertiesReplyer() {
		this(propertiesFilePath);
	}
	
	public NewsPropertiesReplyer(String... propertiesFilePaths) {
		Set<Properties> propertiesSet=new HashSet<Properties>();
		for(String propertiesFilePath: propertiesFilePaths){
			Properties properties = PropertiesUtil.read(propertiesFilePath);
			propertiesSet.add(properties);
		}
		for(Properties properties:propertiesSet){
			String[] keywords=StringUtils.split(properties.getProperty("keywords"), ",");
			NewsSentContent sentContent=new NewsSentContent();
			for(String keyword:keywords){
				map.put(keyword, sentContent);
			}
			List<Article> articles=new ArrayList<Article>();
			sentContent.setArticles(articles);
			for(Entry<Object, Object> e: properties.entrySet()){
				String[] titles=new String[MAX_ITEM];
				String[] descriptions=new String[MAX_ITEM];
				String[] picUrls=new String[MAX_ITEM];
				String[] urls=new String[MAX_ITEM];
				
				String key=(String)e.getKey();
				String[] keyArray=StringUtils.split(key, ".");
				int index=0;
				if(keyArray.length==2){
					index=Integer.valueOf(keyArray[1]);
				}
				if(TITLE.equals(keyArray[0])){
					titles[index]=(String)e.getValue();
				}
				else if(DESCRIPTION.equals(keyArray[0])){
					descriptions[index]=(String)e.getValue();
				}
				else if(PIC_URL.equals(keyArray[0])){
					picUrls[index]=(String)e.getValue();
				}
				else if(URL.equals(keyArray[0])){
					urls[index]=(String)e.getValue();
				}
				for(int i=0;i<MAX_ITEM;i++){
					if(StringUtils.isNotBlank(titles[i])){
						Article article = new Article();
						article.setTitle(titles[i]);
						article.setDescription(descriptions[i]);
						article.setPicUrl(picUrls[i]);
						article.setUrl(urls[i]);
						articles.add(article);
					}
				}
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
		NewsSentContent newsSentContent=map.get(incomingContent);
		if(newsSentContent==null){
			return null;
		}
		List<Article> articles=newsSentContent.getArticles();
		for(Article article:articles){
			String newUrl=article.getUrl()+"?wechatId="+receivedMessage.getOther();
			article.setUrl(newUrl);
		}
		
		
		return map.get(incomingContent);
	}

}

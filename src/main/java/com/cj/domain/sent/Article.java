package com.cj.domain.sent;

import lombok.Getter;
import lombok.Setter;

import com.cj.utils.XmlUtil;

@Setter
@Getter
public class Article {

	private String title;

	private String description;

	private String picUrl;
	private String url;

	public void appendXml(StringBuilder sb) {
		XmlUtil.appendTagPre(sb, "item");
		XmlUtil.appendCDdataElement(sb, "Title", title);
		XmlUtil.appendCDdataElement(sb, "Description", description);
		XmlUtil.appendCDdataElement(sb, "PicUrl", picUrl);
		XmlUtil.appendCDdataElement(sb, "Url", url);
		XmlUtil.appendTagPost(sb, "item");
	}
}

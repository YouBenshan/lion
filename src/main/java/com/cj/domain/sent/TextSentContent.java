package com.cj.domain.sent;

import lombok.Getter;
import lombok.Setter;

import com.cj.utils.XmlUtil;

@Setter
@Getter
public class TextSentContent implements SentContent {
	private String content;

	@Override
	public StringBuilder toXml() {
		StringBuilder sb = new StringBuilder();
		XmlUtil.appendCDdataElement(sb, "MsgType", "text");
		XmlUtil.appendCDdataElement(sb, "Content", content);
		return sb;
	}
}

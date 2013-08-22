package com.cj.domain.sent;

import lombok.Getter;
import lombok.Setter;

import com.cj.utils.XmlUtil;

@Setter
@Getter
public class MusicSentContent implements SentContent {

	private String title;

	private String description;

	private String musicUrl;

	private String hqMusicUrl;

	@Override
	public StringBuilder toXml() {
		StringBuilder sb = new StringBuilder();
		XmlUtil.appendCDdataElement(sb, "MsgType", "music");
		XmlUtil.appendTagPre(sb, "Music");
		XmlUtil.appendCDdataElement(sb, "Title", title);
		XmlUtil.appendCDdataElement(sb, "Description", description);
		XmlUtil.appendCDdataElement(sb, "MusicUrl", musicUrl);
		XmlUtil.appendCDdataElement(sb, "HQMusicUrl", hqMusicUrl);
		XmlUtil.appendTagPost(sb, "Music");
		return sb;
	}
}

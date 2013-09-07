package com.cj.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XmlUtil {
	public static void appendCDdataElement(StringBuilder sb, String tagName,
			String value) {
		appendTagPre(sb, tagName);
		sb.append("<![CDATA[").append(value).append("]]>");
		appendTagPost(sb, tagName);
	}

	public static void appendTagPost(StringBuilder sb, String tagName) {
		sb.append("</").append(tagName).append(">");
	}

	public static void appendTagPre(StringBuilder sb, String tagName) {
		sb.append("<").append(tagName).append(">");
	}

	public static void appendTextElement(StringBuilder sb, String tagName,
			String value) {
		appendTagPre(sb, tagName);
		sb.append(value);
		appendTagPost(sb, tagName);
	}

	public static Node getNode(Document doc, String tagName) {
		return doc.getElementsByTagName(tagName).item(0);
	}

	public static String getText(Document doc, String tagName) {
		return getNode(doc, tagName).getTextContent();
	}
}

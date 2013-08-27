package com.cj.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.cj.domain.received.EventReceivedMessage;
import com.cj.domain.received.ImageReceivedMessage;
import com.cj.domain.received.LinkReceivedMessage;
import com.cj.domain.received.LocationReceivedMessage;
import com.cj.domain.received.ReceivedMessage;
import com.cj.domain.received.TextReceivedMessage;

public class ReceivedMessageParser {

	private static DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
			.newInstance();

	private static ReceivedMessage parse(Document doc) {
		ReceivedMessage receivedMessage;
		String msgType = XmlUtil.getText(doc, "MsgType");
		switch (msgType) {
		case "text":
			receivedMessage = new TextReceivedMessage();
			TextReceivedMessage textreceivedMessage = (TextReceivedMessage) receivedMessage;
			textreceivedMessage.setContent(XmlUtil.getText(doc, "Content").trim());
			textreceivedMessage.setMsgId(Long.parseLong(XmlUtil.getText(doc,
					"MsgId")));
			break;
		case "image":
			receivedMessage = new ImageReceivedMessage();
			ImageReceivedMessage imagereceivedMessage = (ImageReceivedMessage) receivedMessage;
			imagereceivedMessage.setPicUrl(XmlUtil.getText(doc, "PicUrl"));
			imagereceivedMessage.setMsgId(Long.parseLong(XmlUtil.getText(doc,
					"MsgId")));
			break;
		case "location":
			receivedMessage = new LocationReceivedMessage();
			LocationReceivedMessage locationreceivedMessage = (LocationReceivedMessage) receivedMessage;
			locationreceivedMessage.setLabel(XmlUtil.getText(doc, "Label"));
			locationreceivedMessage.setLocationX(Float.parseFloat(XmlUtil
					.getText(doc, "Location_X")));
			locationreceivedMessage.setLocationY(Float.parseFloat(XmlUtil
					.getText(doc, "Location_Y")));
			locationreceivedMessage.setScale(Integer.parseInt(XmlUtil.getText(
					doc, "Scale")));
			locationreceivedMessage.setMsgId(Long.parseLong(XmlUtil.getText(
					doc, "MsgId")));
			break;
		case "link":
			receivedMessage = new LinkReceivedMessage();
			LinkReceivedMessage linkreceivedMessage = (LinkReceivedMessage) receivedMessage;
			linkreceivedMessage.setTitle(XmlUtil.getText(doc, "Title"));
			linkreceivedMessage.setDescription(XmlUtil.getText(doc,
					"Description"));
			linkreceivedMessage.setUrl(XmlUtil.getText(doc, "Url"));
			linkreceivedMessage.setMsgId(Long.parseLong(XmlUtil.getText(doc,
					"MsgId")));
			break;
		case "event":
			receivedMessage = new EventReceivedMessage();
			EventReceivedMessage eventreceivedMessage = (EventReceivedMessage) receivedMessage;
			eventreceivedMessage.setEvent(XmlUtil.getText(doc, "Event"));
			eventreceivedMessage.setEventKey(XmlUtil.getText(doc, "EventKey"));
			break;
		default:
			throw new IllegalArgumentException(
					"Invalid type of the received message: " + msgType);
		}
		receivedMessage.setCreateTime(Integer.parseInt(XmlUtil.getText(doc,
				"CreateTime")));
		receivedMessage.setOther(XmlUtil.getText(doc, "FromUserName"));
		return receivedMessage;
	}

	public static ReceivedMessage parse(InputStream is)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document dom = docBuilder.parse(is);
		ReceivedMessage receivedMessage = parse(dom);
		return receivedMessage;
	}
}

package com.murkhies.zombiegame.utils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {

	String path, tagName;
	Document doc;

	public void build() {
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(getClass().getResourceAsStream(path));
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getValue(String tag) {

		Node node = doc.getElementsByTagName(tagName).item(0);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
			Node value = (Node) nodeList.item(0);
			return value.getNodeValue();
		}
		return null;

	}

	public ArrayList<String> getAllValue(String tag) {

		ArrayList<String> stringList = new ArrayList<String>();
		int i = 0;

		Node node = doc.getElementsByTagName(tagName).item(0);
//		while (node.getNodeType() == Node.ELEMENT_NODE) {
//			Element element = (Element) node;
//			NodeList nodeList = element.getElementsByTagName(tag).item(i).getNodeName();
//			stringList.add(nodeList.item(0).toString());
//			System.out.println(nodeList.item(i).toString());
//			i++;
//		}
		return stringList;

	}

	public void saveScore(String cad, int points) {

		setPath(new Strings().FILE_TO_XML_HIGHSCORE);
		build();
		setTagName("player");
		System.out.println(getAllValue("name").toString());

		System.out.println(cad + " " + points);
	}

}

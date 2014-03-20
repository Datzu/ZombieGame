package com.murkhies.zombiegame.utils;

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

	public String getValue(String etiqueta) {

		Node node = doc.getElementsByTagName(tagName).item(0);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			NodeList llistat= element.getElementsByTagName(etiqueta).item(0).getChildNodes();
			Node valor = (Node) llistat.item(0);
			return valor.getNodeValue();
		}
		return null;

	}

}

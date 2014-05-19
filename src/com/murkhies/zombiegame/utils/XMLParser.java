package com.murkhies.zombiegame.utils;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
			NodeList nodeList = element.getElementsByTagName(tag).item(0)
					.getChildNodes();
			Node value = (Node) nodeList.item(0);
			return value.getNodeValue();
		}
		return null;

	}

	public NodeList getAllValue(String tag) {
		return doc.getElementsByTagName(tag);
	}

	public void saveScore(String name, int points) {
		
		ArrayList<Score> scoreList = new ArrayList<Score>();
		
		scoreList.add(new Score(name, points));

		setPath(new Strings().FILE_TO_XML_HIGHSCORE);
		build();
		NodeList nodeList = getAllValue("player");
		
		for (int temp = 0; temp < nodeList.getLength(); temp++) {
			Node nNode = nodeList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				scoreList.add(new Score(eElement.getElementsByTagName("name").item(0).getTextContent(), Integer.parseInt(eElement.getElementsByTagName("score").item(0).getTextContent())));
			}
		}
		
		for (int i = 0; i < scoreList.size(); i++) {
			for (int j = 0; j < i; j++) {
				if (scoreList.get(i).getPoints() > scoreList.get(j).getPoints()) {
					Score scoreI = scoreList.get(i);
					Score scoreJ = scoreList.get(j);
					scoreList.remove(scoreI);
					scoreList.remove(scoreJ);
					scoreList.add(j, scoreI);
					scoreList.add(i, scoreJ);
				 }
			}
		}
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("highScores");
			doc.appendChild(rootElement);
			
			for (int i = 0; i < 4; i++) {
				Element player = doc.createElement("player");
				rootElement.appendChild(player);
				
				Element nameElement = doc.createElement("firstname");
				nameElement.appendChild(doc.createTextNode("yong"));
				player.appendChild(nameElement);
				
				Element scoreElement = doc.createElement("firstname");
				scoreElement.appendChild(doc.createTextNode("yong"));
				player.appendChild(scoreElement);
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));
			
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

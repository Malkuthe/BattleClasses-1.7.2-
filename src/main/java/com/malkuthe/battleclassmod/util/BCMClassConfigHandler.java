package com.malkuthe.battleclassmod.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.malkuthe.battleclassmod.BattleClassMod;

public class BCMClassConfigHandler {
	
	public static File classConfig = new File(BattleClassMod.confPath + BattleClassMod.classConfigPath);
	
	public static void ClassCreate(){
		
		try {
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			//root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("BCMCustomClasses");
			Comment classComment = doc.createComment("Add Classes if you want. Make sure each one has a unique id");
			doc.appendChild(rootElement);
			rootElement.getParentNode().insertBefore(classComment, rootElement);
			
			rootElement.appendChild(getClasses(doc, "1", "Squire", "red", "strength"));
			rootElement.appendChild(getClasses(doc, "2", "Apprentice", "blue", "insight"));
			rootElement.appendChild(getClasses(doc, "3", "Thief", "green", "haste"));
			rootElement.appendChild(getClasses(doc, "4", "Archer", "aqua", "acuity"));
			rootElement.appendChild(getClasses(doc, "5", "Knight", "red", "strength"));
			rootElement.appendChild(getClasses(doc, "6", "Mage", "blue", "insight"));
			rootElement.appendChild(getClasses(doc, "7", "Rogue", "green", "haste"));
			rootElement.appendChild(getClasses(doc, "8", "Hunter", "aqua", "acuity"));
			
			 Transformer transformer = TransformerFactory.newInstance().newTransformer();
	         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(classConfig);
	         transformer.transform(source, result);
	 
	         System.out.println("\nXML DOM Created Successfully..");
			
			
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	public static Node getClasses(Document doc, String id, String name, String colour, String tag){
		Element bcmClasses = doc.createElement("Class");
		bcmClasses.setAttribute("id", id);
		bcmClasses.appendChild(getClassesElements(doc, bcmClasses, "Name", name));
		bcmClasses.appendChild(getClassesElements(doc, bcmClasses, "Colour", colour));
		bcmClasses.appendChild(getClassesElements(doc, bcmClasses, "Tag", tag));
		return bcmClasses;
	}
	
	public static Node getClassesElements(Document doc, Element element, String name, String value){
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
	
	public static void Init(){

	}

}

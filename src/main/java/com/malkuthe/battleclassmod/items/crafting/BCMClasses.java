package com.malkuthe.battleclassmod.items.crafting;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.malkuthe.battleclassmod.config.Configs;
import com.malkuthe.battleclassmod.items.BCMItems;
import com.malkuthe.battleclassmod.util.BCMClassConfigHandler;

public class BCMClasses {
	
	private static final int CLASSES_NUMBER = Configs.classNumber;
	public static final int SONGS_NUMBER = 12;
	public static final File classConfig = BCMClassConfigHandler.classConfig;
	
	public BCMClasses(){
	}
	
	public static void readConfig(){
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			DefaultHandler handler = new DefaultHandler() {
				
				boolean bClassName = false;
				boolean bColour = false;
				boolean bTag = false;
				
				public void startElement(String uri, String localName,String qName, 
		                Attributes attributes) throws SAXException{
					 
					if (qName.equalsIgnoreCase("NAME")) {
						bClassName = true;
					}
			 
					if (qName.equalsIgnoreCase("COLOUR")) {
						bColour = true;
					}
			 
					if (qName.equalsIgnoreCase("TAG")) {
						bTag = true;
					}
					
				}
				
				public void endElement(String uri, String localName,
						String qName) throws SAXException {
				 
					}
				
				public void characters(char ch[], int start, int length) throws SAXException {
					 
					if (bClassName) {
						System.out.println("Class : " + new String(ch, start, length));
						bClassName = false;
					}
			 
					if (bColour) {
						System.out.println("Colour : " + new String(ch, start, length));
						bColour = false;
					}
			 
					if (bTag) {
						System.out.println("Tag : " + new String(ch, start, length));
						bTag = false;
					}
			 
				}
			};
			
			saxParser.parse(classConfig, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object[] songs = {
		new ItemStack(BCMItems.songsItem,1,0),
		new ItemStack(BCMItems.songsItem,1,1),
		new ItemStack(BCMItems.songsItem,1,2),
		new ItemStack(BCMItems.songsItem,1,3),
		new ItemStack(BCMItems.songsItem,1,4),
		new ItemStack(BCMItems.songsItem,1,5),
		new ItemStack(BCMItems.songsItem,1,6),
		new ItemStack(BCMItems.songsItem,1,7),
		new ItemStack(BCMItems.songsItem,1,8),
		new ItemStack(BCMItems.songsItem,1,9),
		new ItemStack(BCMItems.songsItem,1,10),
		new ItemStack(BCMItems.songsItem,1,11)
	};
	
	/*
	 * 0 - Song of Virtue
	 * 1 - Song of Courage
	 * 2 - Song of Rage
	 * 3 - Song of Healing
	 * 4 - Song of Wisdom
	 * 5 - Song of Knowledge
	 * 6 - Song of Celerity
	 * 7 - Song of Acuity
	 * 8 - Song of Agility
	 * 9 - Song of Strength
	 * 10 - Song of Might
	 * 11 - Song of Divinity
	 */
	
	public static final String[] defaultClasses = {
		"Squire", "Apprentice", "Thief", "Archer",
		"Knight", "Mage", "Rogue", "Hunter"
	};
	
	public static final String[] tierTwoClasses = {
		"Knight", "Mage", "Rogue", "Hunter"
	};
	
	public static final String[] HasteClasses ={
		"Thief", "Rogue"
	};

	public static final Object[][] classRecipes = {
		//Squire
		{songs[0],songs[0],songs[1],songs[1]},
		//Apprentice
		{songs[0],songs[0],songs[5],songs[5]},
		//Thief
		{songs[0],songs[0],songs[6],songs[6]},
		//Archer
		{songs[0],songs[0],songs[7],songs[7]},
		//Knight
		{songs[0],songs[1],songs[10],songs[11]},
		//Mage
		{songs[0],songs[4],songs[5],songs[11]},
		//Rogue
		{songs[0],songs[6],songs[8],songs[11]},
		//Hunter
		{songs[0],songs[7],songs[10],songs[11]}
	};
	
	Item boonItem = BCMItems.boonItem;
}

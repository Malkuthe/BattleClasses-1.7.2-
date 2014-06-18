package com.malkuthe.battleclassmod.config;

import java.io.File;
import javax.xml.parsers.*;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

	public static void Init(File file){
		Configuration config = new Configuration(file);
		
		config.load();
		
		/*
		 * Item Configs
		 */
		Configs.boonID = config.get(Configs.CATEGORY_ITEMS, Configs.boonID_NAME, Configs.boonID_DEFAULT).getInt() - 256;
		
		/*
		 * Message Configs
		 */
		Configs.noviceJoin = config.get(Configs.CATEGORY_MESSAGES, Configs.noviceJoin_NAME, Configs.noviceJoin_DEFAULT, Configs.noviceJoin_DESC).getString();
		Configs.noviceJoinCont = config.get(Configs.CATEGORY_MESSAGES, Configs.noviceJoinCont_NAME, Configs.noviceJoinCont_DEFAULT, Configs.noviceJoinCont_DESC).getString();
		
		Configs.classChangeErr = config.get(Configs.CATEGORY_MESSAGES, Configs.classChangeErr_NAME, Configs.classChangeErr_DEFAULT, Configs.classChangeErr_DESC).getString();
		Configs.classChangeErrCont = config.get(Configs.CATEGORY_MESSAGES, Configs.classChangeErrCont_NAME, Configs.classChangeErrCont_DEFAULT, Configs.classChangeErrCont_DESC).getString();
		
		Configs.classChange = config.get(Configs.CATEGORY_MESSAGES, Configs.classChange_NAME, Configs.classChange_DEFAULT, Configs.classChange_DESC).getString();
		Configs.classChangeCont = config.get(Configs.CATEGORY_MESSAGES, Configs.classChangeCont_NAME, Configs.classChangeCont_DEFAULT, Configs.classChangeCont_DESC).getString();
		
		Configs.classless = config.get(Configs.CATEGORY_MESSAGES, Configs.classless_NAME, Configs.classless_DEFAULT, Configs.classless_DESC).getString();
		Configs.classlessCont = config.get(Configs.CATEGORY_MESSAGES, Configs.classlessCont_NAME, Configs.classlessCont_DEFAULT, Configs.classlessCont_DESC).getString();
		
		Configs.classCheck = config.get(Configs.CATEGORY_MESSAGES, Configs.classCheck_NAME, Configs.classCheck_DEFAULT, Configs.classCheck_DESC).getString();
		Configs.classCheckCont = config.get(Configs.CATEGORY_MESSAGES, Configs.classCheckCont_NAME, Configs.classCheckCont_DEFAULT, Configs.classCheckCont_DESC).getString();
		
		/*
		 * Miscellaneous Configs
		 */
		Configs.defaultClass = config.get(Configs.CATEGORY_MISCELLANEOUS, Configs.defaultClass_NAME, Configs.defaultClass_DEFAULT, Configs.defaultClass_DESC).getString();
		
		config.save();
	}
	
}

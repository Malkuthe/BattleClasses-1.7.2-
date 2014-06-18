package com.malkuthe.battleclassmod.items;

import com.malkuthe.battleclassmod.BCMInfo;
import com.malkuthe.battleclassmod.config.Configs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BCMItems{

	public static Item boonItem;
	public static Item songsItem;
	
	public static void init(){
		boonItem = new BoonItem().setTextureName(BCMInfo.ID + ":" + ItemInfo.boonItemUnlocalized);
		songsItem = new SongsItem();
	}
	
	public static void addNames() {

	}
	
}

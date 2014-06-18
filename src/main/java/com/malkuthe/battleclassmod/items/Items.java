package com.malkuthe.battleclassmod.items;

import com.malkuthe.battleclassmod.BCMInfo;
import com.malkuthe.battleclassmod.config.Configs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items{

	public static Item boonItem;
	public static Item songsItem;
	
	public static void init(){
		boonItem = new BoonItem(Configs.boonID).setTextureName(BCMInfo.ID + ":" + ItemInfo.boonItemUnlocalized);
		songsItem = new SongsItem(Configs.songsID);
	}
	
	public static void addNames() {
		LanguageRegistry.addName(boonItem, ItemInfo.boonItemLocalized);
		for (int i = 0; i < ItemInfo.songsItemLocalized.length; ++i){
			LanguageRegistry.addName( new ItemStack(songsItem, 1, i), ItemInfo.songsItemLocalized[i]);
		}
	}
	
	
}

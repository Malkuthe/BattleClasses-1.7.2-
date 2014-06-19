package com.malkuthe.battleclassmod.items;

import net.minecraft.item.Item;

public class BCMItems{

	public static Item boonItem;
	public static Item songsItem;

	public static void init(){
		boonItem = new BoonItem();
		songsItem = new SongsItem();
	}

	public static void addNames() {

	}

}

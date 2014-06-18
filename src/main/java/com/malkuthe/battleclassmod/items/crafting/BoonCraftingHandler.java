package com.malkuthe.battleclassmod.items.crafting;

import java.util.HashMap;

import com.malkuthe.battleclassmod.items.BCMItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BoonCraftingHandler {
	
	public static void Init(){
		for (int i = 0; i < 8; ++i){
			ClassHandler.BoonClass(i);
		}
	}

}

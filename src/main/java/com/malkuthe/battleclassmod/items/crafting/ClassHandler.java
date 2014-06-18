package com.malkuthe.battleclassmod.items.crafting;

import java.util.HashMap;

import com.malkuthe.battleclassmod.config.Configs;
import com.malkuthe.battleclassmod.items.ItemInfo;
import com.malkuthe.battleclassmod.items.Items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClassHandler {
	
	private static final int CLASSES_NUMBER = Configs.classNumber;
	private static final int INGREDIENTS_NUMBER = 4;
	
	static ItemStack gold = new ItemStack(Item.ingotGold);
	static ItemStack heart = new ItemStack(Items.songsItem,1,0);
	private static Object[][] classRecipe = BCMClasses.classRecipes;
	private static String[] boonClass;
	
	public static void BoonClass(int classnum){
		boonClass = BCMClasses.defaultClasses;
		String bcmBoonClass = boonClass[classnum];
		
		for (int i = 0; i < INGREDIENTS_NUMBER; ++i){
			classRecipe[classnum][i] = BCMClasses.classRecipes[classnum][i];
		}
		
		ItemStack boonItem = new ItemStack(Items.boonItem);
		boonItem.setTagCompound(new NBTTagCompound());
		NBTTagCompound properties = boonItem.stackTagCompound;
		properties.setString("Class", bcmBoonClass);
		properties.setString("Owner", "none");
		properties.setInteger("Level", 1);
		properties.setInteger("Tributes", 0);
		
		GameRegistry.addRecipe(boonItem, "xax", "boc", "xdx", 'x', gold, 'o', heart, 'a', classRecipe[classnum][0],
				'b', classRecipe[classnum][1], 'c', classRecipe[classnum][2], 'd',classRecipe[classnum][3]);
		System.out.println("Added Recipe");
	}

}

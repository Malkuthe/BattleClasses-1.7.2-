package com.malkuthe.battleclassmod.items.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.malkuthe.battleclassmod.config.Configs;
import com.malkuthe.battleclassmod.items.BCMItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class ClassHandler {
	
	private static final int CLASSES_NUMBER = Configs.classNumber;
	private static final int INGREDIENTS_NUMBER = 4;
	
	static ItemStack gold = new ItemStack(Items.gold_ingot);
	static ItemStack heart = new ItemStack(BCMItems.songsItem,1,0);
	private static Object[][] classRecipe = BCMClasses.classRecipes;
	private static String[] boonClass;
	
	public static void BoonClass(int classnum){
		boonClass = BCMClasses.defaultClasses;
		String bcmBoonClass = boonClass[classnum];
		
		for (int i = 0; i < INGREDIENTS_NUMBER; ++i){
			classRecipe[classnum][i] = BCMClasses.classRecipes[classnum][i];
		}
		
		ItemStack boonItem = new ItemStack(BCMItems.boonItem);
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

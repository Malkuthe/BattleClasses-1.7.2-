package com.malkuthe.battleclassmod.items.crafting;

import com.malkuthe.battleclassmod.items.BoonItem;
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
		boonItem.stackTagCompound = generateNBTTagCompoundWithClass(bcmBoonClass);

		GameRegistry.addRecipe(boonItem, "xax", "boc", "xdx", 'x', gold, 'o', heart, 'a', classRecipe[classnum][0], 'b', classRecipe[classnum][1], 'c', classRecipe[classnum][2], 'd',
				classRecipe[classnum][3]);

	}

	private static NBTTagCompound generateNBTTagCompoundWithClass(String playerclass)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("Owner", "none");
		nbt.setString("Class", playerclass);
		nbt.setInteger("Level", 1);
		nbt.setInteger("Tributes", 0);
		return nbt;
	}
}

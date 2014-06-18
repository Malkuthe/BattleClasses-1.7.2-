package com.malkuthe.battleclassmod;

import java.util.List;

import com.malkuthe.battleclassmod.config.Configs;
import com.malkuthe.battleclassmod.items.BCMItems;
import com.malkuthe.battleclassmod.items.crafting.BCMClasses;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BCMCreativeTab extends CreativeTabs{

	public BCMCreativeTab(int id, String unlocalizedname) {
		super(id, unlocalizedname);
	}
	
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return BCMItems.boonItem;
	}
	
	@SideOnly(Side.CLIENT)
	public void displayAllReleventItems(List itemList){
		String[] defaultclasses = BCMClasses.defaultClasses;
		for (int i = 0; i < 8; ++i){
			if (i == 8){
				ItemStack boonStack = new ItemStack(BCMItems.boonItem, 1, 0);
				boonStack.setTagCompound(new NBTTagCompound());
				boonStack.stackTagCompound.setString("Owner", "none");
				boonStack.stackTagCompound.setString("Class", Configs.defaultClass);
				boonStack.stackTagCompound.setInteger("Level", 1);
				boonStack.stackTagCompound.setInteger("Tributes", 0);
				itemList.add(boonStack);
			} else if (i < 8) {
				ItemStack boonStack = new ItemStack(BCMItems.boonItem, 1, 0);
				boonStack.setTagCompound(new NBTTagCompound());
				boonStack.stackTagCompound.setString("Owner", "none");
				boonStack.stackTagCompound.setString("Class", defaultclasses[i]);
				boonStack.stackTagCompound.setInteger("Level", 1);
				boonStack.stackTagCompound.setInteger("Tributes", 0);
				itemList.add(boonStack);
			}
		}
		
		addMetaDataItems(itemList, BCMItems.songsItem, 12);
	}
	
	public void addMetaDataItems(List itemList, Item item, int range){
		for (int i = 0; i < range; ++i){
			itemList.add(new ItemStack(item, 1, i));
		}
	}

}

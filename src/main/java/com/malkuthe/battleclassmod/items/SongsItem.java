package com.malkuthe.battleclassmod.items;

import java.util.List;

import com.malkuthe.battleclassmod.BCMInfo;
import com.malkuthe.battleclassmod.BattleClassMod;
import com.malkuthe.battleclassmod.items.crafting.BCMClasses;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SongsItem extends Item{
	
	Icon[] icons;
	
	public SongsItem(int id){
		super(id);
		setCreativeTab(BattleClassMod.tabCustom);
		setMaxStackSize(16);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack){
		return ItemInfo.songsItemUnlocalized[itemstack.getItemDamage()];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon){
		icons  = new Icon[BCMClasses.SONGS_NUMBER];
		
		for (int i = 0; i < icons.length; ++i){
			icons[i] = icon.registerIcon(BCMInfo.ID + ":" + ItemInfo.songsItemUnlocalized[i]);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int damage){
		return icons[damage];
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list){
		for (int i = 0; i < icons.length; ++i){
			ItemStack itemstack = new ItemStack(id, 1, i);
			list.add(itemstack);
		}
	}
	

}

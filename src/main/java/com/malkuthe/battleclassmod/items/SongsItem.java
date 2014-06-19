package com.malkuthe.battleclassmod.items;

import java.util.List;

import com.malkuthe.battleclassmod.BCMInfo;
import com.malkuthe.battleclassmod.BattleClassMod;
import com.malkuthe.battleclassmod.items.crafting.BCMClasses;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SongsItem extends Item{
	
	IIcon[] icons;
	
	public SongsItem(){
		setCreativeTab(BattleClassMod.tabCustom);
		setMaxStackSize(16);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack){
		int i = MathHelper.clamp_int(itemstack.getItemDamage(), 0, BCMClasses.SONGS_NUMBER - 1);
		return ItemInfo.songsItemUnlocalized[i];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon){
		icons  = new IIcon[BCMClasses.SONGS_NUMBER];
		
		for (int i = 0; i < icons.length; ++i){
			icons[i] = icon.registerIcon(BCMInfo.ID + ":" + ItemInfo.songsItemUnlocalized[i]);
			System.out.println(icons[i]);
		}
	}
	/*
	 * This is the method causing the NPE
	 * Again, it's the return that's triggering it
	 * 
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage){
		int j = MathHelper.clamp_int(damage, 0, BCMClasses.SONGS_NUMBER - 1);
		return icons[j];
	}
	*/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list){
		for (int i = 0; i < icons.length; ++i){
			ItemStack itemstack = new ItemStack(item, 1, i);
			list.add(itemstack);
		}
	}

}

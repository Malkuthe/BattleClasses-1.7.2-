package com.malkuthe.battleclassmod.items;

import com.malkuthe.battleclassmod.BCMInfo;
import com.malkuthe.battleclassmod.BattleClassMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import java.util.List;

public class SongsItem extends Item
{
	public static final String[] names = new String[] {
			"virtue", "courage", "rage", "healing", "wisdom", "knowledge", "celerity", "acuity", "agility", "strength", "might", "divinity"
			// I think that the calls you made to ItemInfo.songsItemUnlocalized were made BEFORE that class was even initialized, and so it was returning null
			// Best to make the list inside the class like I have done here
			// I may be wrong though about it being before ItemInfo was initialized... Oh well. This works, what you had before doesn't
	};
	public static final String[] textures = new String[] {
			"bcmSongVirtue", "bcmSongCourage", "bcmSongRage", "bcmSongHealing", "bcmSongWisdom", "bcmSongKnowledge",
			"bcmSongCelerity", "bcmSongAcuity", "bcmSongAgility", "bcmSongStrength", "bcmSongMight", "bcmSongDivinity"
			// Refer to comments on lines 21-23
	};
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public SongsItem()
	{
		this.setMaxStackSize(16);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(BattleClassMod.tabCustom);
		GameRegistry.registerItem(this, "item_song"); // One thing you forgot to do was register your items. No wonder the registerIcons wasn't being called.
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		int i = MathHelper.clamp_int(itemstack.getItemDamage(), 0, ItemInfo.songsItemUnlocalized.length - 1);
		return "item." + names[i];
	}

	/**
	 * Gets an icon index based on an item's damage value
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		int j = MathHelper.clamp_int(par1, 0, names.length - 1);
		return this.icons[j];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < names.length; ++i)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.icons = new IIcon[textures.length];

		for (int i = 0; i < textures.length; ++i)
		{
			this.icons[i] = par1IconRegister.registerIcon(BCMInfo.ID + ":" + textures[i]);
		}
	}
}

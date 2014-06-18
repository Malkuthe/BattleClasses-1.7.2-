package com.malkuthe.battleclassmod.inventories.slots;

import com.malkuthe.battleclassmod.BCMInfo;
import com.malkuthe.battleclassmod.items.BoonItem;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SlotBoon extends Slot{

	public SlotBoon(IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemStack){
		return itemStack.getItem() instanceof BoonItem;
	}
	
	private static final ResourceLocation iconLocation = new ResourceLocation(BCMInfo.ID, "textures/gui/boonslotIcon.png");
	
	@SideOnly(Side.CLIENT)
	public void setBackgroundIconTexture(ResourceLocation texture){
		this.texture = iconLocation;
	}

}

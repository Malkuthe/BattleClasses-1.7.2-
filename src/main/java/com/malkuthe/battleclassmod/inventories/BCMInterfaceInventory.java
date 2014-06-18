package com.malkuthe.battleclassmod.inventories;

import com.malkuthe.battleclassmod.items.BoonItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class BCMInterfaceInventory implements IInventory{
	
	/*
	 * Variables
	 */
	//Inventory Name
	private final String invName = "Class Interface";
	
	//key to store and retrieve data from NBTTag
	private final String invTagName = "ClassInterfaceTag";
	
	//Defining inventory size
	public static final int invSize = 1;
	
	//Defining which slots are which
	public static final int boonSlot = 0;
	
	private ItemStack[] inventory = new ItemStack[invSize];
	
	public BCMInterfaceInventory(){
		
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		
		if (stack != null){
			
			if (stack.stackSize > amount){
				stack = stack.splitStack(amount);
				
				if (stack.stackSize == 0){
					setInventorySlotContents(slot, null);
				}
				
			} else {
				setInventorySlotContents(slot, null);
			}

			this.onInventoryChanged();
			
		}
		
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		
		if (stack != null){
			setInventorySlotContents(slot,null);
		}
		
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		this.inventory[slot] = itemstack;
		
		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()){
			itemstack.stackSize = this.getInventoryStackLimit();
		}
		
		this.onInventoryChanged();
		
	}

	@Override
	public String getInvName() {
		return invName;
	}

	@Override
	public boolean isInvNameLocalized() {
		return invName.length() > 0;
	}

	//Makes custom slots like armour. Only one item per slot.
	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void onInventoryChanged() {
		for (int i = 0; i < this.getSizeInventory(); ++i) {
			if (this.getStackInSlot(i) != null && this.getStackInSlot(i).stackSize == 0){
				this.setInventorySlotContents(i, null);
			}
		}
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {
		
	}

	@Override
	public void closeChest() {
		
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		return (
				(slot == boonSlot && itemstack.getItem() instanceof BoonItem)
				);
	}
	
	public void writeToNBT(NBTTagCompound compound){
		NBTTagList items = new NBTTagList();
		
		for (int i = 0; i < getSizeInventory(); ++i ){
			if (getStackInSlot(i) != null){
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte) i);
				getStackInSlot(i).writeToNBT(item);
				items.appendTag(item);
			}
		}
		
		compound.setTag(invTagName, items);
	}
	
	public void readFromNBT(NBTTagCompound compound){
		NBTTagList items = compound.getTagList(invTagName);
		
		for (int i = 0; i < items.tagCount(); ++i){
			NBTTagCompound item = (NBTTagCompound) items.tagAt(i);
			byte slot = item.getByte("Slot");
			if ( slot >= 0 && slot < getSizeInventory() ){
				inventory[slot] = ItemStack.loadItemStackFromNBT(item);
			}
		}
	}

}

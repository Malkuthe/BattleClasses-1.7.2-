package com.malkuthe.battleclassmod;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {
	
	//used to temporarily store playerClass NBT on death
	private static final Map<String, NBTTagCompound> 
	extendedEntityData = new HashMap<String, NBTTagCompound>();
	
	public void registerRenderers () {}
	
	@Override
	public Object getServerGuiElement( int guiId, EntityPlayer player, World world, int x, int y, int z ){
		return null;
	}
	
	@Override
	public Object getClientGuiElement( int guiId, EntityPlayer player, World world, int x, int y, int z ){
		return null;
	}
	
	//adding entity's custom data to hashmap for temp storage.
	//@param compound is an NBTTagCompound that stores the IExtendedEntityProperties data only
	public static void storeEntityData( String name, NBTTagCompound compound ){
		extendedEntityData.put(name, compound);
	}
	
	//removes compound from hashmap and returns NBT Tag stored for name, or none if null
	public static NBTTagCompound getEntityData(String name){
		return extendedEntityData.remove(name);
	}

}

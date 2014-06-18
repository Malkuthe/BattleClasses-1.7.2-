package com.malkuthe.battleclassmod.guis;

import com.malkuthe.battleclassmod.BattleClassMod;
import com.malkuthe.battleclassmod.PlayerClass;
import com.malkuthe.battleclassmod.inventories.containers.BCMInterfaceContainer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class BCMGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == BattleClassMod.GUI_CLASS_INTERFACE_INV){
			return new BCMInterfaceContainer(player, player.inventory, PlayerClass.get(player).inventory);
		} else {
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == BattleClassMod.GUI_CLASS_INTERFACE_INV){
			return new BCMInterfaceInventoryGui(player, player.inventory, PlayerClass.get(player).inventory);
		} else {
			return null;
		}
	}

}

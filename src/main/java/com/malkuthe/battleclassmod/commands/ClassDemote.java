package com.malkuthe.battleclassmod.commands;

import com.malkuthe.battleclassmod.PlayerClass;
import com.malkuthe.battleclassmod.config.Configs;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.EnumChatFormatting;

public class ClassDemote extends IBCMCommands {

	@Override
	public String getCommandName() {
		return "bcmdemote";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "bcmdemote <user>";
	}
	
	@Override
	public int getRequiredPermissionLevel(){
		return 3;
	}

	@Override
	public void ProcessPlayer(EntityPlayer player, String[] params) {
		
		if (params.length != 1){
			player.addChatMessage(EnumChatFormatting.RED + "[ERROR] bcmdemote <user>");
		} else if (params.length == 1){
			EntityPlayer entityplayermp = getPlayer(player, params[0]);
			if (entityplayermp == player){
				PlayerClass props = PlayerClass.get(entityplayermp);
				props.Demote(entityplayermp);
			} else {
				PlayerClass props = PlayerClass.get(entityplayermp);
				props.DemoteDiff(player, entityplayermp);
			}
		}
		
	}

	@Override
	public void ProcessCommandBlock(TileEntityCommandBlock commandBlock,
			String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ProcessServerConsole(ICommandSender console, String[] params) {
		// TODO Auto-generated method stub
		
	}
	

}

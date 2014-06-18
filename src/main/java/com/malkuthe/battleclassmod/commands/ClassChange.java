package com.malkuthe.battleclassmod.commands;

import com.malkuthe.battleclassmod.PlayerClass;
import com.malkuthe.battleclassmod.config.Configs;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.EnumChatFormatting;

public class ClassChange extends IBCMCommands {

	@Override
	public String getCommandName() {
		return "bcmclasschange";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "bcmclasschange <user> <class>";
	}
	
	@Override
	public int getRequiredPermissionLevel(){
		return 3;
	}

	@Override
	public void ProcessPlayer(EntityPlayer player, String[] params) {
		if (params.length <= 1 || params.length > 2) {
			player.addChatMessage(EnumChatFormatting.RED + "[ERROR] bcmclasschange <player> <class>");
		} else if (params.length == 2){
			EntityPlayer entityplayermp = getPlayer(player, params[0]);
			String bcmclass = params[1];
			if (entityplayermp == player){
				if (bcmclass.equals(Configs.defaultClass)){
					PlayerClass props = PlayerClass.get(entityplayermp);
					props.ClassChange(bcmclass);
					entityplayermp.addChatMessage(EnumChatFormatting.RED + "You have been demoted successfully. Use /bcmdemote <user> instead next time.");
				} else {
					PlayerClass props = PlayerClass.get(entityplayermp);
					props.ClassChange(bcmclass);
				}
			} else {
				if (bcmclass.equals(Configs.defaultClass)){
					String targetname = ((EntityPlayer) entityplayermp).username;
					PlayerClass props = PlayerClass.get(entityplayermp);
					props.ClassChangeDiff(player, entityplayermp, bcmclass);
					player.addChatMessage(EnumChatFormatting.GREEN + targetname + EnumChatFormatting.RESET + EnumChatFormatting.RED + " has been successfully demoted. Use /bcmdemote <user> instead next time.");
				} else {
					PlayerClass props = PlayerClass.get(entityplayermp);
					props.ClassChangeDiff(player, entityplayermp, bcmclass);
				}
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

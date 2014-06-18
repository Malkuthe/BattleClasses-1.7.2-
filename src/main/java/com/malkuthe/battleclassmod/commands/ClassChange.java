package com.malkuthe.battleclassmod.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.malkuthe.battleclassmod.PlayerClass;
import com.malkuthe.battleclassmod.config.Configs;

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
		if (params.length < 1 || params.length > 2) {
			player.addChatMessage( new ChatComponentText(EnumChatFormatting.RED + "[ERROR] bcmclasschange <player> <class> or bcmclasschange <class> (for self)"));
		} else if (params.length == 2){
			EntityPlayer entityplayermp = getPlayer(player, params[0]);
			String bcmclass = params[1];
			if (entityplayermp == player){
				if (bcmclass.equals(Configs.defaultClass)){
					PlayerClass props = PlayerClass.get(entityplayermp);
					props.ClassChange(bcmclass);
					entityplayermp.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You have been demoted successfully. Use /bcmdemote <user> instead next time."));
				} else {
					PlayerClass props = PlayerClass.get(entityplayermp);
					props.ClassChange(bcmclass);
				}
			} else {
				if (bcmclass.equals(Configs.defaultClass)){
					String targetname = ((EntityPlayer) entityplayermp).getCommandSenderName();
					PlayerClass props = PlayerClass.get(entityplayermp);
					props.ClassChangeDiff(player, entityplayermp, bcmclass);
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + targetname + EnumChatFormatting.RESET + EnumChatFormatting.RED + " has been successfully demoted. Use /bcmdemote <user> instead next time."));
				} else {
					PlayerClass props = PlayerClass.get(entityplayermp);
					props.ClassChangeDiff(player, entityplayermp, bcmclass);
				}
			}
			
		} else if (params.length == 1){
			String bcmclass = params[0];
			if (bcmclass.equals(Configs.defaultClass)){
				PlayerClass props = PlayerClass.get(player);
				props.ClassChange(bcmclass);
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Successfully demoted. Use /demote next time."));
			} else {
				PlayerClass props = PlayerClass.get(player);
				props.ClassChange(bcmclass);
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

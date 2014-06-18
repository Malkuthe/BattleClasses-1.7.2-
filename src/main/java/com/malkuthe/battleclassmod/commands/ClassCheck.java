package com.malkuthe.battleclassmod.commands;

import java.util.ArrayList;
import java.util.List;

import com.malkuthe.battleclassmod.PlayerClass;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.EnumChatFormatting;

public class ClassCheck extends IBCMCommands {
	
	private List aliases;
	public ClassCheck (){
		this.aliases = new ArrayList();
		this.aliases.add("bcmcheck");
		this.aliases.add("bccheck");
		this.aliases.add("classcheck");
	}

	@Override
	public String getCommandName() {
		return "bcmclasscheck";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "bcmclasscheck <user>";
	}
	
	@Override
	public List getCommandAliases(){
		return this.aliases;
	}

	@Override
	public void ProcessPlayer(EntityPlayer player, String[] params) {
		if (params.length == 1){
			EntityPlayerMP entityplayermp = getPlayer(player, params[0]);
			if (entityplayermp == player){
				PlayerClass props = PlayerClass.get(entityplayermp);
				props.ClassCheckDiff(entityplayermp);
			} else {
				PlayerClass props = PlayerClass.get(entityplayermp);
				props.ClassCheckDiff(entityplayermp);
			}
			
		} else if(params.length == 0){
			PlayerClass props = PlayerClass.get(player);
			props.ClassCheck();
		} else {
			player.addChatMessage(EnumChatFormatting.RED + "Wrong command usage!");
		}
	}

	@Override
	public void ProcessCommandBlock(TileEntityCommandBlock commandBlock,
			String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ProcessServerConsole(ICommandSender console, String[] params) {
			
	}
	
}

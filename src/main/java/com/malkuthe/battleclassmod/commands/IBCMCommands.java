package com.malkuthe.battleclassmod.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityCommandBlock;

public abstract class IBCMCommands extends CommandBase{
	
	public abstract void ProcessPlayer(EntityPlayer player, String[] params);
	public abstract void ProcessCommandBlock(TileEntityCommandBlock commandBlock, String[] params);
	public abstract void ProcessServerConsole(ICommandSender console, String[] params);
	
	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring){
		if(icommandsender instanceof EntityPlayer){
			ProcessPlayer( (EntityPlayer) icommandsender, astring);
		} else if(icommandsender instanceof TileEntityCommandBlock){
			ProcessCommandBlock( (TileEntityCommandBlock) icommandsender, astring);
		} else {
			ProcessServerConsole(icommandsender, astring);
		}
	}

}


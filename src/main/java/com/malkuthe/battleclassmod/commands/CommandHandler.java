package com.malkuthe.battleclassmod.commands;

import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;

public class CommandHandler {
	
	public static boolean isPlayerOP(String name){
		return MinecraftServer.getServer().getConfigurationManager().isPlayerOpped(name);
	}
	
	public static void Init(){
		MinecraftServer server = MinecraftServer.getServer();
		ICommandManager command = server.getCommandManager();
		ServerCommandManager manager = (ServerCommandManager) command;
		manager.registerCommand(new ClassCheck());
		manager.registerCommand(new ClassChange());
		manager.registerCommand(new ClassDemote());
	}

}

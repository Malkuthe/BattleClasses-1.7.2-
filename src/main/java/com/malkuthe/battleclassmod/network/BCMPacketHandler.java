package com.malkuthe.battleclassmod.network;

import com.malkuthe.battleclassmod.BCMInfo;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class BCMPacketHandler {
	
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(BCMInfo.CHANNEL.toLowerCase());
	
	public static void Init(){
		
	}
	
}

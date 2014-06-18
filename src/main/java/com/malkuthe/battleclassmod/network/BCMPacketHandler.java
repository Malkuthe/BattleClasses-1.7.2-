package com.malkuthe.battleclassmod.network;

import com.malkuthe.battleclassmod.BCMInfo;
import com.malkuthe.battleclassmod.network.message.BCMClassMessage;
import com.malkuthe.battleclassmod.network.message.BCMInterfaceMessage;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class BCMPacketHandler {
	
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(BCMInfo.CHANNEL.toLowerCase());
	
	public static void Init(){
		INSTANCE.registerMessage(BCMInterfaceMessage.class, BCMInterfaceMessage.class, 0, Side.SERVER);
		INSTANCE.registerMessage(BCMClassMessage.class, BCMClassMessage.class, 1, Side.CLIENT);
	}
	
}

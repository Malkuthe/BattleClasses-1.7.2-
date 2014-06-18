package com.malkuthe.battleclassmod;

import com.malkuthe.battleclassmod.keys.KeyBindHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventBus;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		EventBus keyBus = FMLCommonHandler.instance().bus();
		KeyBindHandler.Init();
		keyBus.register(new KeyBindHandler());
	}

}

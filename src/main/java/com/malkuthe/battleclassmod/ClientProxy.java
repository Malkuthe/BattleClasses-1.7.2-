package com.malkuthe.battleclassmod;

import com.malkuthe.battleclassmod.keys.KeyBindHandler;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		KeyBindHandler.Init();
	}

}

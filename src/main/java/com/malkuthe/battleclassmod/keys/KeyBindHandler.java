package com.malkuthe.battleclassmod.keys;

import org.lwjgl.input.Keyboard;

import com.malkuthe.battleclassmod.BattleClassMod;
import com.malkuthe.battleclassmod.network.BCMPacketHandler;
import com.malkuthe.battleclassmod.network.message.BCMInterfaceMessage;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindHandler{
	
	public static KeyBinding openBCMInterface;
	
	public static void Init(){
		openBCMInterface = new KeyBinding("key.openBCMInterface", Keyboard.KEY_N, "key.categories.battleclassmod");
		
		ClientRegistry.registerKeyBinding(openBCMInterface);
	}
	
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event){
		if (!FMLClientHandler.instance().isGUIOpen(GuiChat.class)){
			String guiID = (new Integer(BattleClassMod.GUI_CLASS_INTERFACE_INV)).toString();
			int kb = Keyboard.getEventKey();
			if (kb == openBCMInterface.getKeyCode()){
				BCMPacketHandler.INSTANCE.sendToServer(new BCMInterfaceMessage(guiID));
				System.out.println("ButtonPressed!");
			}
		}
	}
	
}

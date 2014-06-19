package com.malkuthe.battleclassmod;

import java.io.File;

import com.malkuthe.battleclassmod.commands.CommandHandler;
import com.malkuthe.battleclassmod.config.ConfigHandler;
import com.malkuthe.battleclassmod.guis.BCMGuiHandler;
import com.malkuthe.battleclassmod.items.BCMItems;
import com.malkuthe.battleclassmod.items.crafting.BCMClasses;
import com.malkuthe.battleclassmod.items.crafting.BoonCraftingHandler;
import com.malkuthe.battleclassmod.network.BCMPacketHandler;
import com.malkuthe.battleclassmod.util.BCMClassConfigHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod( modid = BCMInfo.ID, name = BCMInfo.NAME, version = BCMInfo.VERSION )

public class BattleClassMod {
	
	private static int modGuiIndex = 0;
	public static final int GUI_CLASS_INTERFACE_INV = modGuiIndex++;
	public static final String classConfigPath = "\\battleclassmodClassConfig.xml";
	public static File confPath;
	
	@Instance(BCMInfo.ID)
	public static BattleClassMod instance = new BattleClassMod();
	
	@EventHandler
	public void preInit( FMLPreInitializationEvent event ){
		ConfigHandler.Init(event.getSuggestedConfigurationFile());
		BCMItems.init();
		this.confPath = event.getModConfigurationDirectory();
		File classconf = new File(this.confPath + this.classConfigPath);
		if (!classconf.isFile() || !classconf.exists()){
			BCMClassConfigHandler.ClassCreate();
		} else {
			System.out.println("[BCM] classConfig.xml detected. Not creating new file.");
		}
		BCMPacketHandler.Init();
		LanguageRegistry.instance();
	}

	@EventHandler
	public void Init( FMLInitializationEvent event ){
		MinecraftForge.EVENT_BUS.register(new BCMEventHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new BCMGuiHandler());
		proxy.registerRenderers();
		BCMClasses.readConfig();
		BoonCraftingHandler.Init();
		
	}
	
	@EventHandler
	public void serverStart( FMLServerStartingEvent event ){
		CommandHandler.Init();
	}
	
	@EventHandler
	public void postInit( FMLPostInitializationEvent event ){
		
	}
	
	public static CreativeTabs tabCustom = new BCMCreativeTab(CreativeTabs.getNextID(), "battleClasses");
	
	@SidedProxy( clientSide = BCMInfo.PROXY_LOCATION + ".ClientProxy", serverSide = BCMInfo.PROXY_LOCATION + ".CommonProxy" )
	public static CommonProxy proxy;
}

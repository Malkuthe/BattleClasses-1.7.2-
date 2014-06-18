package com.malkuthe.battleclassmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BCMEventHandler {

	@SubscribeEvent
	public void onEntityConstructing( EntityConstructing event ){
		if ( event.entity instanceof EntityPlayer && PlayerClass.get( (EntityPlayer) event.entity ) == null){
			PlayerClass.register( (EntityPlayer) event.entity );
		}
		
		if ( event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(PlayerClass.EXT_PROP_NAME) == null){
			event.entity.registerExtendedProperties(PlayerClass.EXT_PROP_NAME, new PlayerClass((EntityPlayer) event.entity) );
		}
	}
	
	@SubscribeEvent
	public void onLivingDeathEvent( LivingDeathEvent event ){
		if ( !event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer){
			DamageSource source = event.source;
			System.out.println(source);
		}
		if ( !event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer ){
			
			PlayerClass.saveProxyData((EntityPlayer) event.entity);
			
		}
		
	}
	
	@SubscribeEvent
	public void onEntityJoinWorld( EntityJoinWorldEvent event ){
		if ( !event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer ){
			
			PlayerClass.loadProxyData((EntityPlayer) event.entity);
			
		}
	}
	
}

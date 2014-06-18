package com.malkuthe.battleclassmod;

import ibxm.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Arrays;
import java.util.List;

import com.malkuthe.battleclassmod.config.Configs;
import com.malkuthe.battleclassmod.inventories.BCMInterfaceInventory;
import com.malkuthe.battleclassmod.items.crafting.BCMClasses;
import com.malkuthe.battleclassmod.network.BCMPacketHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerClass implements IExtendedEntityProperties {

	public final static String EXT_PROP_NAME = "PlayerClass";
	private final EntityPlayer player;
	private String playerClass;
	public final BCMInterfaceInventory inventory = new BCMInterfaceInventory();
	
	public PlayerClass( EntityPlayer player ){
		this.player = player;
		this.playerClass = Configs.defaultClass;
	}
	
	//Just for code cleanliness according to the tutorial
	
	public static final void register( EntityPlayer player ){
		player.registerExtendedProperties( PlayerClass.EXT_PROP_NAME, new PlayerClass(player) );
	}
	
	public static final PlayerClass get(EntityPlayer player){
		return (PlayerClass) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	//required overrides
	
	@Override
	public void saveNBTData( NBTTagCompound compound ){
	
		//creating new tag compound to store class properties
		NBTTagCompound bcmClass = new NBTTagCompound();
		
		//setting variables
		bcmClass.setString("PlayerClass", this.playerClass);
		
		inventory.writeToNBT(bcmClass);
		
		//adding custom tag to player's tag
		compound.setTag(EXT_PROP_NAME, bcmClass);
		
		//debug
		System.out.println("[PlayerClasses] saving class " + this.playerClass);
	}
	
	@Override
	public void loadNBTData( NBTTagCompound compound ){
		
		//fetching tag compound
		NBTTagCompound bcmClass = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		
		//retrieving data from tag compound
		this.playerClass = bcmClass.getString("PlayerClass");
		
		inventory.readFromNBT(bcmClass);
		
		//debug
		System.out.println("[PlayerClasses] loading class " + this.playerClass);
	}
	
	@Override
	public void init( Entity entity, World world ){
		
	}
	
	/*
	 * Syncing
	 */
	public final void syncProperties(){
		ByteArrayOutputStream bos = new ByteArrayOutputStream(9);
		DataOutputStream outputStream = new DataOutputStream(bos);
		
		try {
			outputStream.writeByte(BCMPacketHandler.OPEN_SERVER_GUI);
			outputStream.writeUTF(this.playerClass);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		Packet250CustomPayload packet = new Packet250CustomPayload( BCMInfo.CHANNEL, bos.toByteArray() );
		
		if (!player.worldObj.isRemote) {
			EntityPlayerMP player1 = (EntityPlayerMP) player;
			PacketDispatcher.sendPacketToPlayer(packet, (Player) player1);
		}
	}
	
	//Sets Class
	public final void setClass(String bcm){
		this.playerClass = bcm;
		this.syncProperties();
	}
	
	/*
	 * Methods to save/load data from CommonProxy
	 */
	public static String getSaveKey( EntityPlayer player ){
		return player.username + ":" + EXT_PROP_NAME;
	}
	
	public static void saveProxyData( EntityPlayer player ){
		PlayerClass playerData = PlayerClass.get(player);
		NBTTagCompound savedData = new NBTTagCompound();
		
		playerData.saveNBTData(savedData);
		
		CommonProxy.storeEntityData(getSaveKey(player), savedData);
	}
	
	public static void loadProxyData( EntityPlayer player ){
		PlayerClass playerData = PlayerClass.get(player);
		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
		
		if ( savedData != null ){
			playerData.loadNBTData(savedData);
		}
		
		playerData.syncProperties();
	}
	
	/*
	 * methods to interact with new variables
	 */
	
	//changes class
	public void ClassChange( String bcm ){
		if ( this.playerClass.equals(Configs.defaultClass) && !bcm.equals(Configs.defaultClass)) {
			this.playerClass = bcm;
			player.addChatMessage( Configs.noviceJoin + EnumChatFormatting.RED + this.playerClass + EnumChatFormatting.RESET + Configs.noviceJoinCont );
			
			//debug
			System.out.println("[PlayerClasses] class chosen confirmed");
		} else if( this.playerClass.equals(bcm) ){
			player.addChatMessage( Configs.classChangeErr + EnumChatFormatting.RED + this.playerClass + EnumChatFormatting.RESET + Configs.classChangeErrCont);
		} else {
			this.playerClass = bcm;
			player.addChatMessage( Configs.classChange + EnumChatFormatting.RED + this.playerClass + EnumChatFormatting.RESET + Configs.classChangeCont);
			
			//debug
			System.out.println("[PlayerClasses] class change confirmed");
		}
	}
	
	public void ClassChangeDiff( EntityPlayer commandplayer, EntityPlayer player, String bcmclass ){
		String playername = ((EntityPlayer) player).username;
		if ( this.playerClass.equals(bcmclass) ){
			player.addChatMessage( EnumChatFormatting.GREEN + playername + EnumChatFormatting.RESET + " is already a " + EnumChatFormatting.RED + this.playerClass);
		} else {
			String orgClass = this.playerClass;
			this.playerClass = bcmclass;
			player.addChatMessage( EnumChatFormatting.GREEN + "[SERVER]" + EnumChatFormatting.RESET + "Your class has been changed from " + EnumChatFormatting.RED + orgClass + EnumChatFormatting.RESET + " to " + EnumChatFormatting.RED + this.playerClass );
			commandplayer.addChatMessage( EnumChatFormatting.GREEN + playername + "'s " + EnumChatFormatting.RESET + "class has been changed from " + EnumChatFormatting.RED + orgClass + EnumChatFormatting.RESET + " to " + EnumChatFormatting.RED + this.playerClass );
		}
	}
	
	public void Demote( EntityPlayer player ){
		if (this.playerClass.equals(Configs.defaultClass)){
			player.addChatMessage("You are already a " + EnumChatFormatting.RED + Configs.defaultClass);
		} else {
			this.playerClass = Configs.defaultClass;
			player.addChatMessage("You have been successfully demoted to " + EnumChatFormatting.RED + Configs.defaultClass);
		}
	}
	
	public void DemoteDiff( EntityPlayer commandplayer, EntityPlayer player){
		String playername = ((EntityPlayer) player).username;
		if (this.playerClass.equals(Configs.defaultClass)){
			commandplayer.addChatMessage(EnumChatFormatting.GREEN + playername + EnumChatFormatting.RESET + " is already a " + EnumChatFormatting.RED + Configs.defaultClass);
		} else {
			String orgclass = this.playerClass;
			this.playerClass = Configs.defaultClass;
			commandplayer.addChatMessage(EnumChatFormatting.GREEN + playername + EnumChatFormatting.RESET + " has been successfully demoted from " + EnumChatFormatting.RED + orgclass + EnumChatFormatting.RESET + " to " + EnumChatFormatting.RED + Configs.defaultClass);
			player.addChatMessage(EnumChatFormatting.GREEN + "[SERVER]" + EnumChatFormatting.RESET + "You have been demoted from " + EnumChatFormatting.RED + orgclass + EnumChatFormatting.RESET + " to " + EnumChatFormatting.RED + Configs.defaultClass);
		}
	}
	
	public void ClassCheck(){
		if ( this.playerClass.equals(Configs.defaultClass) ) {
			player.addChatMessage( Configs.classless + EnumChatFormatting.RED + this.playerClass + EnumChatFormatting.RESET + Configs.classlessCont);
		} else {
			player.addChatMessage( Configs.classCheck + EnumChatFormatting.RED + this.playerClass + EnumChatFormatting.RESET + Configs.classCheckCont);
		}
	}
	
	public void ClassCheckDiff(EntityPlayer player){
		String playername = ((EntityPlayer) player).getCommandSenderName();
		player.addChatMessage( new ChatComponentText(EnumChatFormatting.GREEN + playername + EnumChatFormatting.RESET + " is a " + EnumChatFormatting.RED + this.playerClass));
	}
	
	public String getPlayerClass(){
		return this.playerClass;
	}
	
	public boolean isClassHaste(String bcmclass){
		List<String> hasteClasses = Arrays.asList(BCMClasses.HasteClasses);
		if (hasteClasses.contains(bcmclass)){
			return true;
		}
		return false;
	}
	
}

package com.malkuthe.battleclassmod.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import com.malkuthe.battleclassmod.PlayerClass;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class BCMClassMessage implements IMessage, IMessageHandler<BCMClassMessage, IMessage>{
	
	private NBTTagCompound playerData;
	
	public BCMClassMessage () {
		
	}
	
	public BCMClassMessage(EntityPlayer player){
		playerData = new NBTTagCompound();
		PlayerClass.get(player).saveNBTData(playerData);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		playerData = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, playerData);
	}
	
	@Override
	public IMessage onMessage(BCMClassMessage message, MessageContext ctx) {
		PlayerClass.get(ctx.getServerHandler().playerEntity).loadNBTData(message.playerData);
		return null;
	}

}

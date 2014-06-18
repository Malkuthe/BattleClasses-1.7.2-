package com.malkuthe.battleclassmod.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

import com.malkuthe.battleclassmod.BattleClassMod;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class BCMInterfaceMessage implements IMessage, IMessageHandler<BCMInterfaceMessage, IMessage>{
	
	private int guiID;
	
	public BCMInterfaceMessage () {
		
	}
	
	public BCMInterfaceMessage(int i){
		this.guiID = i;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		guiID = ByteBufUtils.readVarInt(buf, 8);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarInt(buf, guiID, 8);
	}

	@Override
	public IMessage onMessage(BCMInterfaceMessage message, MessageContext ctx) {
		EntityPlayerMP player = ctx.getServerHandler().playerEntity;
		player.openGui(BattleClassMod.instance, message.guiID, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
		return null;
	}

}

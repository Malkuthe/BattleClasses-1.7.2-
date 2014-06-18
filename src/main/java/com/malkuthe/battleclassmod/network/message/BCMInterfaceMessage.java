package com.malkuthe.battleclassmod.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import com.malkuthe.battleclassmod.BattleClassMod;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class BCMInterfaceMessage implements IMessage, IMessageHandler<BCMInterfaceMessage, IMessage>{
	
	private String guiID;
	
	public BCMInterfaceMessage () {
		
	}
	
	public BCMInterfaceMessage(String string){
		this.guiID = string;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		guiID = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, guiID);
	}

	@Override
	public IMessage onMessage(BCMInterfaceMessage message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		Integer gid = Integer.parseInt(message.guiID);
		player.openGui(BattleClassMod.instance, gid, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
		return null;
	}

}

package com.malkuthe.battleclassmod.network.message;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class BCMInterfaceMessage implements IMessage, IMessageHandler{

	@Override
	public IMessage onMessage(IMessage message, MessageContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		
	}

}

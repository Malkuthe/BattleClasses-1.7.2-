package com.malkuthe.battleclassmod.network.message;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import com.malkuthe.battleclassmod.BCMInfo;
import com.malkuthe.battleclassmod.network.BCMPacketHandler;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketOpenServerGui {
	public static Packet250CustomPayload getPacket(int guiID){
		ByteArrayOutputStream bos = new ByteArrayOutputStream(5);
		DataOutputStream outputStream = new DataOutputStream(bos);
		
		try {
			// Notice this first line writes the packet id - do this in your ExtendedPlayer.sync() method as well and add 1 to the size.
			outputStream.writeByte(BCMPacketHandler.OPEN_SERVER_GUI);
			outputStream.writeInt(guiID);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		Packet250CustomPayload packet = new Packet250CustomPayload( BCMInfo.CHANNEL, bos.toByteArray() );
		PacketDispatcher.sendPacketToServer(packet);
	
		return new Packet250CustomPayload(BCMInfo.CHANNEL, bos.toByteArray());
	}
	
}
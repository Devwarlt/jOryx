
package com.oryxhatesjava.net;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.oryxhatesjava.net.data.Parsable;

public class ReskinPacket extends Packet implements Parsable {

	public int skinID; //byte
	
	public ReskinPacket(DataInput in) {
		try {
			type = Packet.RESKIN;
			parseFromDataInput(in);
		} catch (IOException e) {
			
		}
	}
	
	public ReskinPacket() {
		type = Packet.RESKIN;
	}
	
	@Override
	public void parseFromDataInput(DataInput in) throws IOException {
		skinID = in.readByte();
	}
	
	@Override
	public void writeToDataOutput(DataOutput out) throws IOException {
		out.writeByte(skinID);
	}
	
	@Override
	public String toString() {
		return "RESKIN " + skinID;
	}
	
}

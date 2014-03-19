/* oryx-hates-java
 * Copyright (C) 2011-2012 Furyhunter <furyhunter600@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.oryxhatesjava.net;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import com.oryxhatesjava.net.data.Parsable;

/**
 * <p>
 * Sent by client to ensure it is up to date.
 * </p>
 * <p>
 * Started Mar 2, 2011
 * </p>
 * 
 * @author Furyhunter
 */
public class HelloPacket extends Packet implements Parsable {
    
	public String buildVersion;
	public int gameId;
	public String guid;
	public String password;
	public String secret;
	public int keyTime;
	public byte[] key = new byte[0];
	public byte[] obf0 = new byte[0];
	public String obf1;
	public String obf2;
	public String obf3;
	public String obf4;
	public String obf5;
	public String unkStr;
	
    public HelloPacket(DataInput read) {
        try {
            parseFromDataInput(read);
            type = Packet.HELLO;
        } catch (IOException e) {
            
        }
    }
    
    public HelloPacket() {
    	type = Packet.HELLO;
	}

 
@Override

    public void parseFromDataInput(DataInput in) throws IOException {
	this.buildVersion = in.readUTF();
	this.gameId = in.readInt();
	this.guid = in.readUTF();
	this.password = in.readUTF();
	secret = in.readUTF();
	keyTime = in.readInt();
	this.key = new byte[in.readShort()];
	in.readFully(this.key);
	this.obf0 = new byte[in.readInt()];
	in.readFully(this.obf0);
	this.obf1 = in.readUTF();
	this.obf2 = in.readUTF();
	this.obf3 = in.readUTF();
	this.obf4 = in.readUTF();
	this.obf5 = in.readUTF();
	int size = in.readUnsignedShort();
    if (size > 0) {
    	key = new byte[size];
    	in.readFully(key);
    }
    
    size = in.readInt();
    if (size > 0) {
    	byte[] buf = new byte[size];
    	in.readFully(buf);
    	unkStr = new String(buf, Charset.forName("UTF-8"));
    }
        
    }
    
    
@Override

    public String toString() {
        return "HELLO " + buildVersion + " " + gameId + " guid=" + guid + " pw="
                + password + " secret=" + secret + " " + keyTime + " " + Arrays.toString(key) + " ";
    }
    
    
@Override

    public void writeToDataOutput(DataOutput out) throws IOException {
	out.writeUTF(this.buildVersion);
	out.writeInt(this.gameId);
	out.writeUTF(this.guid);
	out.writeUTF(this.password);
	out.writeUTF(this.secret);
	out.writeInt(this.keyTime);
	if (key != null) {
        out.writeShort(key.length);
        out.write(key);
    } else {
    	out.writeShort(0);
    }
    
    if (unkStr != null) {
    	byte[] buf = unkStr.getBytes("UTF-8");
    	out.writeInt(buf.length);
    	out.write(buf);
    } else {
    	out.writeInt(0);
    }
	out.writeUTF(this.obf1);
	out.writeUTF(this.obf2);
	out.writeUTF(this.obf3);
	out.writeUTF(this.obf4);
	out.writeUTF(this.obf5);
    }
}

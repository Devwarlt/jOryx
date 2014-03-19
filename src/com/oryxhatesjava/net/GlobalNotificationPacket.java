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

import com.oryxhatesjava.net.data.Parsable;

public class GlobalNotificationPacket extends Packet implements Parsable {
    

	public int type;
	public String text;
	
    
    public GlobalNotificationPacket(int type, String text) {
        this.type = Packet.FAILURE;
        this.text = text;
    }
    
    public GlobalNotificationPacket(DataInput read) {
        try {
            this.type = Packet.FAILURE;
            parseFromDataInput(read);
        } catch (IOException e) {
            
        }
    }
    
    public GlobalNotificationPacket() {
    	type = Packet.GLOBAL_NOTIFICATION;
    }
    
    @Override
    public void parseFromDataInput(DataInput in) throws IOException {
    	this.type = in.readInt();
		this.text = in.readUTF();
    }
    
    @Override
    public void writeToDataOutput(DataOutput out) throws IOException {
    	out.writeInt(this.type);
		out.writeUTF(this.text);
    }
    
    @Override
    public String toString() {
        return ":";
    }
}

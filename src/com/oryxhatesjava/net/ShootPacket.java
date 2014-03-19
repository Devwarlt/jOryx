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

import com.oryxhatesjava.net.data.Location;
import com.oryxhatesjava.net.data.Parsable;


/**
 * <p>TODO document this type</p>
 *
 * <p>Started Mar 10, 2011</p>
 *
 * @author Furyhunter
 */
public class ShootPacket extends Packet implements Parsable {
    
	public int bulletId;
	public int ownerId;
	public int bulletType;
	public Location startingPos = new Location();
	public float angle;
	public short damage;
	public int numShots;
	public float angleInc;
    
    public ShootPacket(int bulletId, int ownerId, int bulletType, Location startingPos, float angle, short damage, int numShots, float angleInc) {
    	type = Packet.SHOOT;
        this.bulletId = bulletId;
        this.ownerId = ownerId;
        this.bulletType = bulletType;
        this.startingPos = startingPos.clone();
        this.angle = angle;
        this.damage = damage;
        this.numShots = numShots;
        this.angleInc = angleInc;
    }
    
    public ShootPacket(DataInput read) {
        try {
            type = Packet.SHOOT;
            parseFromDataInput(read);
        } catch (IOException e) {
            
        }
    }
    
    public ShootPacket() {
    	type = Packet.SHOOT;
    }
    
    @Override
    public void parseFromDataInput(DataInput in) throws IOException {
    	this.bulletId = in.readUnsignedByte();
		this.ownerId = in.readInt();
		this.bulletType = in.readUnsignedByte();
		this.startingPos.parseFromDataInput(in);
		this.angle = in.readFloat();
		this.damage = in.readShort();
		try {
			this.numShots = in.readUnsignedByte();
			this.angleInc = in.readFloat();
		} catch (IOException e) {
			this.numShots = 1;
			this.angleInc = 0;
		}
    }
    
    @Override
    public void writeToDataOutput(DataOutput out) throws IOException {
    	out.writeByte(this.bulletId);
		out.writeInt(this.ownerId);
		out.writeByte(this.bulletType);
		this.startingPos.writeToDataOutput(out);
		out.writeFloat(this.angle);
		out.writeShort(this.damage);
		if (this.numShots != 1 || this.angleInc != 0) {
			out.writeByte(this.numShots);
			out.writeFloat(this.angleInc);
		}
    }
    
    @Override
    public String toString() {
        return "SHOOT " + bulletId + " " + ownerId + " " + " " + startingPos + " " + angle + " " + damage;
    }
}

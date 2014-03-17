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
import java.util.Arrays;

import com.oryxhatesjava.net.data.Parsable;

/**
 * <p>
 * A generic packet.
 * </p>
 * <p>
 * Started Mar 2, 2011
 * </p>
 * 
 * @author Furyhunter
 */
public class Packet implements Parsable {
    
	  public static final int FAILURE = 0;
	  public static final int CANCELTRADE = 6;
	  public static final int USEPORTAL = 36;
	  public static final int INVRESULT = 60;
	  public static final int JOINGUILD = 11;
	  public static final int PING = 14;
	  public static final int MOVE = 58;
	  public static final int GUILDINVITE = 12;
	  public static final int GLOBAL_NOTIFICATION = 63;
	  public static final int SETCONDITION = 89;
	  public static final int UPDATEACK = 62;
	  public static final int TRADEDONE = 20;
	  public static final int SHOOT = 75;
	  public static final int GOTOACK = 79;
	  public static final int CREATEGUILD = 69;
	  public static final int PONG = 5;
	  public static final int HELLO = 90;
	  public static final int TRADEACCEPTED = 50;
	  public static final int ENEMYSHOOT = 19; //Unknown
	  public static final int NAMERESULT = 10;
	  public static final int REQUESTTRADE = 30;
	  
	  public static final int SHOOTACK = 1;
	  
	  public static final int TRADECHANGED = 53;
	  public static final int PLAYERHIT = 13;
	  public static final int TEXT = 93;
	  public static final int UPDATE = 21;
	  public static final int BUYRESULT = 23;
	  public static final int PIC = 49;
	  public static final int USEITEM = 40;
	  public static final int CREATE_SUCCESS = 81;
	  public static final int CHOOSENAME = 86;
	  public static final int QUESTOBJID = 88;
	  public static final int INVDROP = 55;
	  public static final int CREATE = 38;
	  public static final int CHANGETRADE = 82;
	  public static final int PLAYERSHOOT = 41;
	  
	  public static final int RECONNECT = 33;
	  
	  public static final int CHANGEGUILDRANK = 52;
	  public static final int DEATH = 4;
	  public static final int ESCAPE = 74;
	  public static final int PLAYSOUND = 42;
	  public static final int LOAD = 64;
	  public static final int ACCOUNTLIST = 59;
	  public static final int DAMAGE = 24;
	  public static final int CHECKCREDITS = 91;
	  public static final int TELEPORT = 9;
	  public static final int BUY = 28;
	  public static final int SQUAREHIT = 57;
	  public static final int GOTO = 7;
	  public static final int EDITACCOUNTLIST = 46;
	  //public static final int CLIENTSTAT_FILE = 55;
	  public static final int SHOW_EFFECT = 51;
	  public static final int ACCEPTTRADE = 66;
	  public static final int CREATEGUILDRESULT = 77;
	  public static final int AOEACK = 80;
	  public static final int MAPINFO = 65;
	  public static final int TRADEREQUESTED = 76;
	  public static final int NEW_TICK = 83;
	  public static final int NOTIFICATION = 37;
	  
	  public static final int GROUNDDAMAGE = 48;
	  
	  public static final int INVSWAP = 17;
	  public static final int OTHERHIT = 15;
	  public static final int TRADESTART = 78;
	  public static final int AOE = 92;
	  public static final int PLAYERTEXT = 87;
	  public static final int ALLYSHOOT = 34;
	  public static final int CLIENTSTAT = 35;
	  public static final int ENEMYHIT = 67;
	  public static final int INVITEDTOGUILD = 8;
	  public static final int GUILDREMOVE = 18;
	  public static final int RESKIN = 45;
	  
	  public static final int FILE = 25;
    
    public int type;
    
    private byte[] data;
    
    protected Packet() {
        
    }
    
    protected Packet(int type, byte[] data) {
        this.type = type;
        this.data = data.clone();
    }
    
    public String toString() {
        return "Unknown type " + type + " " + Arrays.toString(data);
    }
    
    @Override
    public void writeToDataOutput(DataOutput out) throws IOException {
        out.write(data);
    }
    
    @Override
    public void parseFromDataInput(DataInput in) throws IOException {
        in.readFully(data);
    }
    
    public static Packet parse(int type, byte[] data) {
        DataInput in = new ByteArrayDataInput(data);
        switch (type) {
            case FAILURE:
                return new FailurePacket(in);
            case CREATE_SUCCESS:
                return new CreateSuccessPacket(in);
            case CREATE:
                return new CreatePacket(in);
            case PLAYERSHOOT:
                return new PlayerShootPacket(in);
            case MOVE:
                return new MovePacket(in);
            case PLAYERTEXT:
                return new PlayerTextPacket(in);
            case TEXT:
                return new TextPacket(in);
            case SHOOT:
                return new ShootPacket(in);
            case HELLO:
                return new HelloPacket(in);
            case DAMAGE:
            	return new DamagePacket(in);
            case UPDATE:
            	return new UpdatePacket(in);
            case UPDATEACK:
            	return new UpdateAckPacket(in);
            case NOTIFICATION:
            	return new NotificationPacket(in);
            case NEW_TICK:
            	return new NewTickPacket(in);
            case INVSWAP:
            	return new InvSwapPacket(in);
            case USEITEM:
            	return new UseItemPacket(in);
            case SHOW_EFFECT:
            	return new ShowEffectPacket(in);
            case GOTO:
            	return new GotoPacket(in);
            case INVDROP:
            	return new InvDropPacket(in);
            case INVRESULT:
            	return new InvResultPacket(in);
            case RECONNECT:
            	return new ReconnectPacket(in);
            case PING:
            	return new PingPacket(in);
            case PONG:
            	return new PongPacket(in);
            case MAPINFO:
            	return new MapInfoPacket(in);
            case LOAD:
            	return new LoadPacket(in);
            case PIC:
            	return new PicPacket(in);
            case SETCONDITION:
            	return new SetConditionPacket(in);
            case TELEPORT:
            	return new TeleportPacket(in);
            case USEPORTAL:
            	return new UsePortalPacket(in);
            case DEATH:
            	return new DeathPacket(in);
            case BUY:
            	return new BuyPacket(in);
            case BUYRESULT:
            	return new BuyResultPacket(in);
            case AOE:
            	return new AOEPacket(in);
            case GROUNDDAMAGE:
            	return new GroundDamagePacket(in);
            case PLAYERHIT:
            	return new PlayerHitPacket(in);
            case ENEMYHIT:
            	return new EnemyHitPacket(in);
            case AOEACK:
            	return new AOEAckPacket(in);
            case SHOOTACK:
            	return new ShootAckPacket(in);
            case OTHERHIT:
            	return new OtherHitPacket(in);
            case SQUAREHIT:
            	return new SquareHitPacket(in);
            case GOTOACK:
            	return new GotoAckPacket(in);
            case EDITACCOUNTLIST:
            	return new EditAccountListPacket(in);
            case ACCOUNTLIST:
            	return new AccountListPacket(in);
            case QUESTOBJID:
            	return new QuestObjIDPacket(in);
            case CHOOSENAME:
            	return new ChooseNamePacket(in);
            case NAMERESULT:
            	return new NameResultPacket(in);
            case CREATEGUILD:
            	return new CreateGuildPacket(in);
            case CREATEGUILDRESULT:
            	return new CreateGuildResultPacket(in);
            case GUILDREMOVE:
            	return new GuildRemovePacket(in);
            case GUILDINVITE:
            	return new GuildInvitePacket(in);
            case ALLYSHOOT:
            	return new AllyShootPacket(in);
            case ENEMYSHOOT:
            	return new EnemyShootPacket(in);
            case REQUESTTRADE:
            	return new RequestTradePacket(in);
            case TRADEREQUESTED:
            	return new TradeRequestedPacket(in);
            case TRADESTART:
            	return new TradeStartPacket(in);
            case CHANGETRADE:
            	return new ChangeTradePacket(in);
            case TRADECHANGED:
            	return new TradeChangedPacket(in);
            case ACCEPTTRADE:
            	return new AcceptTradePacket(in);
            case CANCELTRADE:
            	return new CancelTradePacket(in);
            case TRADEDONE:
            	return new TradeDonePacket(in);
            case TRADEACCEPTED:
            	return new TradeAcceptedPacket(in);
            case CLIENTSTAT:
            	return new ClientStatPacket(in);
            case CHECKCREDITS:
            	return new CheckCreditsPacket(in);
            case ESCAPE:
            	return new EscapePacket(in);
            case FILE:
            	return new FilePacket(in);
            case INVITEDTOGUILD:
            	return new InvitedToGuildPacket(in);
            case JOINGUILD:
            	return new JoinGuildPacket(in);
            case CHANGEGUILDRANK:
            	return new ChangeGuildRankPacket(in);
            case PLAYSOUND:
            	return new PlaySoundPacket(in);
            case RESKIN:
                return new ReskinPacket(in);
            default:
                return new Packet(type, data);
        }
    }
}

package gamemessage;

import game.Game_info;
import message.Message;

public class PkGame_infoMessage extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Game_info gg;
	
	public PkGame_infoMessage() {
		this.type = 1213;
	}
}

package gamemessage;

import message.Message;

//Pk模式游戏开始信息
public class PkGameStartMessage extends Message {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String UserID1;
	public boolean UseTool_C1;
	public boolean UseTool_D1;
	public boolean UseTool_E1;
	
	public String UserID2;
	public boolean UseTool_C2;
	public boolean UseTool_D2;
	public boolean UseTool_E2;
	
	public PkGameStartMessage() {
		this.type = 103;
	}
	
	/*
	 * GameState gamestate1 = new GameState();
	 * \//保存第一个GameState,要注意要能根据UserID找到正确的GameState
	 * 
	 * GameStateMessage mess1 = new GameStateMessage();
	 * mess1.CurrentGameState = gamestate1;
	 * \//给玩家1返回mess1;
	 * 
	 *  GameState gamestate2 = new GameState();
	 * \//保存第二个GameState,要注意要能根据UserID找到正确的GameState
	 * 
	 * GameStateMessage mess2 = new GameStateMessage();
	 * mess2.CurrentGameState = gamestate2;
	 * \//给玩家2返回mess2;
	 * 
	 */
	
}

package gamemessage;

import message.Message;

//Pkģʽ��Ϸ��ʼ��Ϣ
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
	 * \//�����һ��GameState,Ҫע��Ҫ�ܸ���UserID�ҵ���ȷ��GameState
	 * 
	 * GameStateMessage mess1 = new GameStateMessage();
	 * mess1.CurrentGameState = gamestate1;
	 * \//�����1����mess1;
	 * 
	 *  GameState gamestate2 = new GameState();
	 * \//����ڶ���GameState,Ҫע��Ҫ�ܸ���UserID�ҵ���ȷ��GameState
	 * 
	 * GameStateMessage mess2 = new GameStateMessage();
	 * mess2.CurrentGameState = gamestate2;
	 * \//�����2����mess2;
	 * 
	 */
	
}
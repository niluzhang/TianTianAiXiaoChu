package game;

import java.io.Serializable;
import java.util.ArrayList;

public class GameStateFuBen implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int Lianji;
	public int Time;
	public int TotalGrade;
	
	public boolean NeedUseTool_E = false;
	public boolean InSuperState;
	public boolean UseTool_D;
	public boolean HasUseTool_B = false;
	
	public ArrayList<Position> Tool_E = new ArrayList<Position>();
	public int[][] CurrentPane = new int[9][9];
	
	public boolean HasUserOffLine = false;
	public ArrayList<String> OffLineUser = new ArrayList<String>();
	//public GameStateFuBen() {}
	
	public GameStateFuBen(GameState g) {
		Lianji = g.getLianji();
		Time = g.time;
		TotalGrade = g.getTotalGrade();
		
		NeedUseTool_E = g.isNeedUseTool_E();
		InSuperState = g.isInSuperState();
		
		Tool_E = g.getTool_E();
		UseTool_D = g.isUseTool_D();
		
		CurrentPane = g.getCurrentPane();
		
		HasUserOffLine = g.isHasUserOffLine();
		OffLineUser = g.getOffLineUser();
		HasUseTool_B = g.isHasUseTool_B();
	}
}

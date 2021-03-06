package presentation_localgame;

import java.util.ArrayList;

import presentation_localgame.singleGameGUI_gamePerfectpane.GamePerfectpane;



public class singleGameGUI_remindThread extends Thread{
	/*
	 * 该线程负责连击次数的计数，提示道具的使用，游戏状态的更改（加倍状态）三个功能
	 * */
	private singleGameGUI game;
	private int time;//连击的起点时间
	
public singleGameGUI_remindThread(singleGameGUI game){
	this.game=game;
}
	@Override
	public void run(){
		
		while(true){
			singleGameGUI.frame.repaint();
			singleGameGUI.frame.validate();
			//System.out.println("remind  is running ____======");
			int currentTime=game.timerObject.getTime();
			if(game.doublehitCount!=0){ 
				game.remindx1=-1;
				game.remindx2=-1;
				game.remindy1=-1;
				game.remindy2=-1;
				//System.out.println("提示道具重置");
			}
			if(Math.abs(game.lastTwoHitTime-game.lastDoubleHitTime)<=100&&(!game.doublehitState)){
				System.out.println("连击加一");
				game.doublehitCount++;
				//time=game.lastDoubleHitTime;
				game.remindx1=-1;
				game.remindx2=-1;
				game.remindy1=-1;
				game.remindy2=-1;
				game.doublehitState=true;
				
			}
			
			if(Math.abs(game.lastDoubleHitTime-currentTime)>100){
				game.doublehitCount=0;
				
				//System.out.println("******无法连击******");
			}
		if(Math.abs(currentTime-game.perfectStateStartTime)>100){
			game.perfectState=false;
		}
			if(game.doublehitCount>4){//再完美状态时间段内，再次刷新完没状态时，状态时间重置
				
				game.perfectState=true;
				game.perfectStateStartTime=game.lastDoubleHitTime;
				game.doublehitCount=0;
			
				//System.out.println("记入完美状态，count=0");
			}
			if(Math.abs(currentTime-game.lastDoubleHitTime)>100){//5s 计时器的时间间隔是0.05s
			ArrayList<Position> rpp=game.blserver.prop_E(game.gameRange.gameRange);
				//System.out.println("提示道具使用");
			if(game.gamepanel.getIsmovestate()==2&&game.state==1){
				game.remindx1=rpp.get(0).Column;
				game.remindy1=rpp.get(0).Row;
				game.remindx2=rpp.get(1).Column;
				game.remindy2=rpp.get(1).Row;
			}
			}
		}
	}

}

package presentation_localgame;

import java.util.ArrayList;

import presentation_localgame.singleGameGUI_gamePerfectpane.GamePerfectpane;



public class singleGameGUI_remindThread extends Thread{
	/*
	 * ���̸߳������������ļ�������ʾ���ߵ�ʹ�ã���Ϸ״̬�ĸ��ģ��ӱ�״̬����������
	 * */
	private singleGameGUI game;
	private int time;//���������ʱ��
	
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
				//System.out.println("��ʾ��������");
			}
			if(Math.abs(game.lastTwoHitTime-game.lastDoubleHitTime)<=100&&(!game.doublehitState)){
				System.out.println("������һ");
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
				
				//System.out.println("******�޷�����******");
			}
		if(Math.abs(currentTime-game.perfectStateStartTime)>100){
			game.perfectState=false;
		}
			if(game.doublehitCount>4){//������״̬ʱ����ڣ��ٴ�ˢ����û״̬ʱ��״̬ʱ������
				
				game.perfectState=true;
				game.perfectStateStartTime=game.lastDoubleHitTime;
				game.doublehitCount=0;
			
				//System.out.println("��������״̬��count=0");
			}
			if(Math.abs(currentTime-game.lastDoubleHitTime)>100){//5s ��ʱ����ʱ������0.05s
			ArrayList<Position> rpp=game.blserver.prop_E(game.gameRange.gameRange);
				//System.out.println("��ʾ����ʹ��");
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
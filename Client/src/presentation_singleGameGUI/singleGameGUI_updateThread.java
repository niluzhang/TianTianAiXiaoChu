package presentation_singleGameGUI;

import game.Position;
import gamemessage.FinishDropMessage;
import gamemessage.GameStateMessage;
import gamemessage.SingleGetStateMessage;

import java.io.IOException;
import java.util.ArrayList;



import main.Startup;
import net.Net;

public class singleGameGUI_updateThread extends Thread {
	singleGameGUI game;
	public singleGameGUI_updateThread(singleGameGUI game){
		this.game=game;
	}
@Override
public void run(){
	while(true){
		/*
		 * 发送和获取数据
		 * 
		*///
		
		synchronized (game.pVlock) {
			game.pVlock.notifyAll();
		System.out.println("update game");
		GameStateMessage message=new GameStateMessage();

		SingleGetStateMessage sendms=new SingleGetStateMessage();
		sendms.UserID=game.ID;
		Startup.net.sendMessage(sendms);
		message=(GameStateMessage)  Startup.net.getMessage(100);
		if(message!=null){
		game.doublehitCount=message.CurrentGameState.Lianji;
		game.doublehitState=message.CurrentGameState.InSuperState;
		game.grade=message.CurrentGameState.TotalGrade;
		game.time=message.CurrentGameState.Time;
		if(message.CurrentGameState.NeedUseTool_E){
		ArrayList<Position> pp=message.CurrentGameState.Tool_E;
		if(game.dropdirection==2){
		game.remindx1=pp.get(0).Row;
		game.remindy1=pp.get(0).Column;
		game.remindx2=pp.get(1).Row;
		game.remindy2=pp.get(1).Column;
		}else{
			game.remindy1=pp.get(0).Row;
			game.remindx1=pp.get(0).Column;
			game.remindy2=pp.get(1).Row;
			game.remindx2=pp.get(1).Column;
		}
		}else{
			game.remindx1=-1;
			game.remindy1=-1;
			game.remindx2=-1;
			game.remindy2=-1;
		}
		}
		try {
			game.pVlock.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
        
		}
		
	}
}
}

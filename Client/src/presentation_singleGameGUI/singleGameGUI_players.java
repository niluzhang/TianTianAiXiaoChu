package presentation_singleGameGUI;

import java.util.ArrayList;

public class singleGameGUI_players {
private int playerNumber;
private ArrayList<Player> players;
public singleGameGUI_players(int playerNumber,ArrayList<Player> players){
	this.playerNumber=playerNumber;
	this.players=players;
}
public boolean addNewPlayer(String playerid){
	Player player1=new Player(playerid,true);
	if(players.contains(player1)){
		return false;
	}else{
		this.players.add(player1);
		return true;
	}
	
}
public boolean isReadyForSending(){
	boolean result=true;
	for(Player player:players){
		if(!player.getState()){
			result=false;
			break;
		}
	}
	if(result){
		return true;
	}
	return result;
}
}

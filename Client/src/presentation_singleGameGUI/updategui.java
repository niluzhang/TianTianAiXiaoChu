package presentation_singleGameGUI;

import gamemessage.CloseNetMessage;
import gamemessage.ReNetMessage;
import gamemessage.SingleGameOverMessage;

import java.io.IOException;

import javax.swing.JOptionPane;

import main.Startup;
import net.Net;
import presentation.gameresultui.resultGUI;

public class updategui extends Thread{
	singleGameGUI game;
	static boolean ggg=false;
	public updategui(singleGameGUI game){
		this.game=game;
	}
	@Override
	public void run(){
		ggg=false;
		while(true){
			game.frame.repaint();
			game.frame.validate();
			if(!ggg&&game.time<=60)
			{
				game.layeredpane.remove(game.topPane);
				ggg=true;
			}
			game.timejpb.setValue(game.time);
			game.timelabel3.setText("  " + game.time);
			game.gradelabel.setText(""+game.grade);
			// timejpb.setToolTipText(""+time);
			
			if (game.time<0) {
				
				
				
				
				JOptionPane.showMessageDialog(game.frame, "��Ϸ����");
				game.frame.dispose();
				game.getMove.stop();
				//timeCaulater.cancel();
				game.updatethread.stop();
				
				Startup.net.sendMessage(new SingleGameOverMessage());
				Startup.net.sendMessage(new CloseNetMessage());
				try {
				Startup.net=new Net();
				ReNetMessage renet=new ReNetMessage();
				renet.UserID=Startup.window.uif.name;
				Startup.net.sendMessage(renet);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				resultGUI   resultgui=new resultGUI(game.grade,game.ID,Startup.window.uif.photo,true);
				
				resultgui.showtime(game.grade);
				game.up.stop();
			}
			try {
				sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		}
}

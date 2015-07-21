package presentation.gameui;

import game.Position;
import gamemessage.ChangePositionMessage;
import gamemessage.FinishDropMessage;
import gamemessage.GameStateMessage;
import gamemessage.Game_infoMessage;

import java.io.IOException;
import java.util.ArrayList;

import main.Startup;
import net.Net;

public class collaborationGameGUI_remindThread extends Thread {
	/*
	 * ���߳̿��Եȴ�����ֻ�ǽ������Զ�����ƶ���Ϣ game.iscanreceived �����ж��Ƿ������� ���ļ���������������
	 */
	private collaborationGameGUI game;

	public collaborationGameGUI_remindThread(collaborationGameGUI game) {
		this.game = game;

	}

	// Э���ƶ��Ľ���
	@Override
	public void run() {

		while (true) {
			synchronized (game.pVlock) {
				game.pVlock.notifyAll();
				game.frame.repaint();
				game.frame.repaint();
				game.iscansendstate=true;
				//System.out.println(" remind thread receiveready" + game.ID);
				game.gamepanel.getIsmovestate();
				if (game.iscanreceived) {
					// ��Ϸ���������Ϣ��ʼ��
					Boolean ishave = false;

					// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7
					// �������Զ�����ƶ���Ϣ
					// �������ͣ�115 ��Ϸ�ƶ�����Ӧ��Ϣ
					Game_infoMessage gamems = (Game_infoMessage) Startup.net
							.getMessage(115);
				
					if (gamems != null) {// �ȳ��Խ���game_info����,���ܳɹ�����˴ν���
                                  game.gameRange.gameRange = gamems.gg
								.getAfterdeleteRange();
						game.rebuildGameRange = gamems.gg.getNewAddRange();
						game.gameRange.guiUpdateCount = 0;
						game.state = 1;
						game.isNextDelete = gamems.gg.isCanDirectRemove();
						ishave = true;

					} else {
							// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
						// ���Խ���λ���ƶ���Ϣ
						// ������Ϣ���ͣ�108 ���ܵ�Ҫ��������������
						ChangePositionMessage ppms = (ChangePositionMessage) Startup.net
								.getMessage(108);
						if (ppms != null) {
                     		// ���ݵĽ��� �Խ��ܵĶ������
								if (game.dropdirection == 1) {
								game.gamepanel.exchangex1 = ppms.p1.Row;
								game.gamepanel.exchangey1 = ppms.p1.Column;
								game.gamepanel.exchangex2 = ppms.p2.Row;
								game.gamepanel.exchangey2 = ppms.p2.Column;

								game.gamepanel.color2 = game.gameRange.gameRange[ppms.p2.Row][ppms.p2.Column];
								game.gamepanel.color1 = game.gameRange.gameRange[ppms.p1.Row][ppms.p1.Column];

							} else {
								// System.out.println("������ܵ�");
								//game.gamepanel.exchangex1 = ppms.p1.Column;
								//game.gamepanel.exchangey1 = ppms.p1.Row;
								//game.gamepanel.exchangex2 = ppms.p2.Column;
								//game.gamepanel.exchangey2 = ppms.p2.Row;
								//game.gamepanel.color2 = game.gameRange.gameRange[ppms.p2.Column][ppms.p2.Row];
								//game.gamepanel.color1 = game.gameRange.gameRange[ppms.p1.Column][ppms.p1.Row];
								game.gamepanel.exchangex1 = ppms.p1.Row;
								game.gamepanel.exchangey1 = ppms.p1.Column;
								game.gamepanel.exchangex2 = ppms.p2.Row;
								game.gamepanel.exchangey2 = ppms.p2.Column;

								game.gamepanel.color2 = game.gameRange.gameRange[ppms.p2.Row][ppms.p2.Column];
								game.gamepanel.color1 = game.gameRange.gameRange[ppms.p1.Row][ppms.p1.Column];
							}
							game.gameRange.guiUpdateCount = 0;
							game.isNextDelete = false;
							game.state = 3;
							ishave = true;
						}
					}

					if(ishave){
						if(game.isStateReceibe){
							game.iscansendstate=true;
						}else{
						GameStateMessage message = (GameStateMessage) Startup.net
								.getMessage(100);
					// ���ݵĽ���
						if (message != null) {
							game.iscansendstate=true;
							game.receivenumber++;
							game.doublehitCount = message.CurrentGameState.Lianji;
							game.doublehitState = message.CurrentGameState.InSuperState;
							game.grade = message.CurrentGameState.TotalGrade;
							game.time = message.CurrentGameState.Time;
							game.playerExit=message.CurrentGameState.HasUserOffLine;
							if(message.CurrentGameState.NeedUseTool_E){
								if(game.dropdirection==2){
									ArrayList<Position> psi=message.CurrentGameState.Tool_E;
									game.remindx1=psi.get(0).Row;
									game.remindy1=psi.get(0).Column;
									game.remindx2=psi.get(1).Row;
									game.remindy2=psi.get(1).Column;
									}else{
										ArrayList<Position> psi=message.CurrentGameState.Tool_E;
										game.remindy1=psi.get(0).Row;
										game.remindx1=psi.get(0).Column;
										game.remindy2=psi.get(1).Row;
										game.remindx2=psi.get(1).Column;	
									
								}
							}else{
								game.remindx1=-1;
								game.remindy1=-1;
								game.remindx2=-1;
								game.remindy2=-1;
							}
							game.iscansendstate=true;
				
						} else {
							game.iscansendstate=false;
						
							
						}

					}
					}
				}else{
					if(!game.isStateReceibe){
						game.iscansendstate=false;
					}else{
						game.iscansendstate=true;
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
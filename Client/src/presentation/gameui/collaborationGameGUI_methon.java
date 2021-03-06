package presentation.gameui;
//界面初始化代码
//此处有三处网络添加部分
//负责界面的一些数据的初始化
import gamemessage.CorpGameStartMessage;
import gamemessage.CorpGetStateMessage;
import gamemessage.CorpTestMessage;
import gamemessage.FinishDropMessage;
import gamemessage.GameStateMessage;
import gamemessage.Game_infoMessage;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



import main.Startup;
import net.Net;
public class collaborationGameGUI_methon extends collaborationGameGUI{
	public void initlizeGameValue() throws IOException{
		playerExit=false;
		isStateReceibe=false;
		iscansendstate=true;
		sendnumber=0;
		receivenumber=0;
		iscanreceived=true;
		isNextDelete=false;
		state = 0;
        dropdirection=Startup.window.mainframe.direction;
		last_x = -1;// 为-1时表示初始时
		last_y = -1;//
		// 点击的当前位置
		now_x = -1;// -1表示初始位置
		now_y = -1;
		// 提示道具的初始化
		remindx1 = -1;
		remindx2 = -1;
		remindy1 = -1;
		remindy2 = -1;
		// 连击初始化
		doublehitCount = 0;
		//分数的初始化
		grade=0;
		time=65;
		// 附属方法的调用
		perfectState = false;
		 
		 System.out.println("初始化位置"+ID);
		 //发送项初始化
		 
		
		 
		 
		 
		 System.out.println("位置3");
		 
			
			 
			 //发送项初始化房主信息发送过去，包含游戏开始所包含的道具
			 CorpGetStateMessage sendms=new CorpGetStateMessage ();
			
			
			 
		sendms.UserID=ID;
		
		//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7
		//发送房主信息发送过去
		//消息类型：112  该客户端为房主，发送给服务器该局游戏道具使用情况，并开始游戏
		Startup.net.sendMessage(sendms);
		System.out.println("-sd-------------------------------------->???????????");
		 /*else{
			 FinishDropMessage sendms=new  FinishDropMessage();
				sendms.UserID=ID;
				//System.out.println("finish drop   移动"+ID);
	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
				//发送移动信息给服务器，
				//此时掉落完成      客户端发给服务器
				//消息类型：118    告诉服务器客户端当前掉落状态
				Startup.net.sendMessage(sendms);
		 }*/
		
		//接受 来自客户端的初始化界面
		 
		 
		Boolean receivedB=true;
		while(receivedB){
			//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7&&&&&&
			//接受服务器的消息，如果接受不成功，就继续接受
			//接收消息类型：100，用于获取界面初始化界面
			
		GameStateMessage message=(GameStateMessage) Startup.net.getMessage(100);
		if(message!=null){
		//rebuildGameRange = message.CurrentGameState.g;// 初始化界面，用于后期获得游戏初始排列
		gameRange.gameRange=message.CurrentGameState.CurrentPane;
		gameRange.guiUpdateCount=0;
		state=0;
		System.out.println("初始化接收到");
		
		print(gameRange.gameRange,9);
		receivedB=false;
		}else{
			System.out.println("无回复"+ID);
		}
		}
		
	
		 //rebuildGameRange = getinitlizeGame();// 初始化界面，用于后期获得游戏初始排列
		//	gameRange.gameRange=getinitlizeGame();
		// changeGameGUI();//用于获取经过算法算过的界面
	}
	public void initlizeGameFrame(){
		// frame面板的初始化
		frame = new gameFrame();
		frame.setSize(1160, 630);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		ImageIcon titlepicture = new ImageIcon("src/bin/title.jpg");
		frame.setIconImage(titlepicture.getImage());
		frame.setBackground(new Color(0,0,0,0));
		frame.setLocationRelativeTo(null);
		frame.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		frame.setVisible(true);
		frame.setDragable();
	}
	public int[][] getinitlizeGame(){
		//System.out.println("初始化");
		int[][] result=new int[9][9];
		for (int i=0;i<9;i++){
			//System.out.println("");
			for(int j=0;j<9;j++){
				
				result[i][j]=(int) (Math.random()*7+1);
				//System.out.print("    "+result[i][j]);
			}
		}
		return result;
	}
	public void print(int[][] game,int length){
		System.out.println("AAA");
		for(int i=0;i<length;i++){
			System.out.println("");
			for(int j=0;j<length;j++){
				System.out.print(game[i][j]+"  ");
			}
		}
		
	}
	public int[][] getRebuildGame(){
		int[][] result=new int[9][9];
		for (int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				result[i][j]=0;
			}
		}
		return result;
	}

class gameFrame extends JFrame{
	Point loc = null;
    Point tmp = null;
    boolean isDragged = false;
    public void setDragable() {
    frame.addMouseListener(new java.awt.event.MouseAdapter() {
    	public void mouseReleased(java.awt.event.MouseEvent e) {
    		isDragged = false;
    		frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    		}
    	public void mousePressed(java.awt.event.MouseEvent e) {
    		tmp = new Point(e.getX(), e.getY());
    		isDragged = true;
    		frame.setCursor(new Cursor(Cursor.MOVE_CURSOR));
    		}
    	});
    frame.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
    	public void mouseDragged(java.awt.event.MouseEvent e) {
    		if(isDragged) {
    			loc = new Point(frame.getLocation().x + e.getX() - tmp.x,
    					frame.getLocation().y + e.getY() - tmp.y);
    			frame.setLocation(loc);
    			}
    		}
    	});
    }
    }
}

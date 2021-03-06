package presentation_singleGameGUI;

import gamemessage.GameStateMessage;
import gamemessage.Game_infoMessage;
import gamemessage.SingleGameStartMessage;

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

public class singleGameGUI_methon extends singleGameGUI{
	public void initlizeGameValue(String ID1){
		ID=ID1;
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
		System.out.println(ID);
		SingleGameStartMessage sendms=new SingleGameStartMessage();
		sendms.UserID=ID;
		sendms.UseTool_C=UseTool_C;
		sendms.UseTool_D=UseTool_D;
		sendms.UseTool_E=UseTool_E;
        Startup.net.sendMessage(sendms);
		
		GameStateMessage message=(GameStateMessage) Startup.net.getMessage(100);
		if(message==null){
			System.out.println("kong");
		}else{
		//rebuildGameRange =;// 初始化界面，用于后期获得游戏初始排列
		gameRange.gameRange=message.CurrentGameState.CurrentPane;
		}
		
	}
	public void initlizeGameFrame(){
		// frame面板的初始化
				frame = new gameFrame();
				frame.setSize(1160, 630);
				frame.getContentPane().setLayout(null);
				frame.setUndecorated(true);
				ImageIcon titlepicture = new ImageIcon( singleGameGUI_methon.class.getResource("/bin/title.jpg"));
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
package presentation_localgame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Startup;

public class singleGameGUI_methon extends singleGameGUI{
	public void initlizeGameValue(){
		state = 0;
        dropdirection=1;
       
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
		// 附属方法的调用
		perfectState = false;
		perfectStateStartTime = -1;
		blserver=new Bl_Server();
		gameRange.setGameRange(blserver.GetNewPane());
		gameRange.guiUpdateCount=0;
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


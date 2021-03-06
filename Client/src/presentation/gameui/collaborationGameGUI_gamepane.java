package presentation.gameui;

import gamemessage.CorpChangePositionMessage;
import gamemessage.CorpUseToolMessage;

import gamemessage.FinishDropMessage;
import gamemessage.FinishMessage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import presentation_singleGameGUI.singleGameGUI;



import main.Startup;
import net.Net;
/*该部分只负责发送信息给服务器
 * 
 *                    此文件有5处网络添加
 * */
public class collaborationGameGUI_gamepane extends collaborationGameGUI {
	static int movex1;
    static int movey1;
    static int movex2;
    static int movey2;
public void initlizeGamepane(){
	gamepanel = new GamePanel();
	gamepanel.setBounds(75, 100, 450, 450);
	gamepanel.setOpaque(false);
	pane.add(gamepanel);
	// gamePanel的响应操作添加
			gamepanel.addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseDragged(MouseEvent e) {
					// TODO Auto-generated method stub
					System.out.println("A");
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					// TODO Auto-generated method stub
					int x = e.getX();
					int y = e.getY();
					mousex = y / (gamepanel.getHeight() / 9);
					mousey = x / (gamepanel.getWidth() / 9);

				}

			});
			gamepanel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					gamepanel.getIsmovestate();
	                if(iscanreceived){
					int x = e.getX();
					int y = e.getY();
					last_x = now_x;
					last_y = now_y;
					movex1=x;
					movey1=y;
					now_x = y / (gamepanel.getHeight() / 9);
					now_y = x / (gamepanel.getWidth() / 9);
					gamepanel.repaint();
					// 响应操作

					if(isNextDelete){
						last_x = -1;// 为-1时表示初始时
						last_y = -1;//
						// 点击的当前位置
						now_x = -1;// -1表示初始位置
						now_y = -1;
					}else{
					if(dropdirection==1){
						if(gameRange.gameRange[now_x][now_y]>7){
							//触发道具
							//触发道具的方法调用，
							//发送项的初始化
							CorpUseToolMessage sendms=new  CorpUseToolMessage(ID);
							sendms.UserID=ID;
							sendms.p.Column=now_y;
							sendms.p.Row=now_x;
							//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
							//发送移动信息给服务器，
							//消息类型   ：113   告诉服务器 用户点击了道具
							Startup.net.sendMessage(sendms);
							System.out.println("道具使用了");
							//清空选择标记
							last_x = -1;
							last_y = -1;
							now_x = -1;
							now_y = -1;
							movex1=-1;
							movey1=-1;
						}
						
						}else if(dropdirection==2){
							if(gameRange.gameRange[now_y][now_x]>7){
								//发送项初始化
								CorpUseToolMessage sendms=new  CorpUseToolMessage(ID);
								sendms.p.Column=now_x;
								sendms.p.Row=now_y;
								//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
								//发送移动信息给服务器，
								//消息类型   ：113   告诉服务器 用户点击了道具
								Startup.net.sendMessage(sendms);
								System.out.println("道具使用了");
								//清空选择标记
								last_x = -1;
								last_y = -1;
								now_x = -1;
								now_y = -1;
								movex1=-1;
								movey1=-1;
							}
							
						
						}}
					}
					}

				
				@Override
				public void mouseReleased(MouseEvent e) {
					int x = e.getX();
					int y = e.getY();
					last_x = now_x;
					last_y = now_y;
	               movex2=x;
	               movey2=y;
					
	               gamepanel.getIsmovestate();
					if(iscanreceived){
						if (isNextDelete) {
							last_x = -1;// 为-1时表示初始时
							last_y = -1;//
							// 点击的当前位置
							now_x = -1;// -1表示初始位置
						    now_y = -1;
						} else {
							if(movex1!=-1&&movex1-movex2!=0){
								
								int directx=Math.abs((movey2-movey1)/(movex2-movex1));
								int directy=movey2-movey1;
								int directsx=movex2-movex1;
								if(directx>=1&&directy>=0){
									now_x=now_x+1;
									
								}else if(directx>=1&&directy<0){
									now_x=now_x-1;
								}else if(directx<1&&directsx>=0){
									now_y=now_y+1;
								}else{
									now_y=now_y-1;
								}

								//System.out.println("CCC" + last_x);
								// 复制一份
								if(0<=now_x&&now_x<=8&&0<=now_y&&now_y<=8  ){
								if(dropdirection==1){
									CorpChangePositionMessage sendms=new CorpChangePositionMessage(ID);
									sendms.p1.Row=last_x;
									sendms.p1.Column=last_y;
									sendms.p2.Row=now_x;
									sendms.p2.Column=now_y;
									//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
									//发送移动信息给服务器，
									//消息类型   ：109    告诉服务器 用户交换的坐标
									Startup.net.sendMessage(sendms);
									
									System.out.println("放送成功");
								last_x = -1;
								last_y = -1;
								now_x = -1;
								now_y = -1;
						
								
								
							
								}else{
									CorpChangePositionMessage sendms=new CorpChangePositionMessage(ID);
									sendms.p1.Row=last_y;
									sendms.p1.Column=last_x;
									sendms.p2.Row=now_y;
									sendms.p2.Column=now_x;
									//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
									//发送移动信息给服务器，
									//消息类型   ：109    告诉服务器 用户交换的坐标
									Startup.net.sendMessage(sendms);

									last_x = -1;
								last_y = -1;
								now_x = -1;
								now_y = -1;
				
								}
							}
								
								}
						}
							last_x = -1;
							last_y = -1;
							now_x = -1;
							now_y = -1;
							movex1=-1;
							movey1=-1;
							movex2=-1;
							movey2=-1;
							}
							
						}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
					
						
				
				
				

				

			});
			
			
		}

class GamePanel extends JPanel {
	/**
 * 
 */ int imageNumber=0;
	private Boolean isLoop = false;
	public int exchangex1;
	public int exchangey1;
	public int exchangex2;
	public int exchangey2;
    public int color1;
    public int color2;
    public void exchangeRebulidGame(int[][] rebuild,int length,int y){
    	int x=rebuild[8][y];
    	int nexy=0;
    	for(int i=0;i<length;i++){
    		if(i==0){
    			//交换
    			nexy=x;
    			x=rebuild[0][y];
    			rebuild[0][y]=nexy;
    		}else{
    			nexy=rebuild[i][y];
    			rebuild[i][y]=x;
    			x=nexy;
    		}
    	}
    }
    public int getLineOfRow(int[][] gr,int line,int length){
    	boolean ishave=false;
    	int result=-1;
    	for(int i=length-1;i>-1;i--){
    		if(gr[i][line]==0){
    			result= i;
    			ishave=true;
    			break;
    		}
    	}
    	if(!ishave){
    		return -1;//表示该列没有可消除的行
    	}else{
    		return result;
    	}
    }
	public int getIsmovestate() {
		//返回值为0表示消除状态，1，掉落状态，2，正常状态
		 int result =2; 
		for (int f = 0; f < 9; f++) {
			for (int j = 0; j < 9; j++) {
				if (gameRange.gameRange[f][j]<0) {
					result = 0;
					break;
				}else if(gameRange.gameRange[f][j]==0){
					result=1;
				break;
				}
			}
			if (result==0||result==1) {
				//System.out.println(result);
				break;
			}
		}
	if(result==2&&state==1){
		iscanreceived=true;
	}else{
		iscanreceived=false;
	}
		//System.out.println(result);
		return result;
	}
	

	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		int onex = this.getWidth() / 9;
		int oney = this.getHeight() / 9;
		int countre = 0;
		//g.clearRect(0, 0, this.getWidth(), this.getHeight());
		if (state == 1) {
			//System.out.println("进入非初始化状态");
	
			if (getIsmovestate()==2) {
				for (int f = 0; f < 9; f++) {
					// System.out.println();
					for (int j = 0; j < 9; j++) {
						// System.out.print("    "+gameRange[f][j]);
						paintGame(g,gameRange.gameRange[f][j],j * onex, f * oney, onex, oney,0,0,0,0);
						
					}
				}
			} else if(getIsmovestate()==1) {
				
				if (gameRange.guiUpdateCount == 1) {
					
					int removex1;
					int removey1;
					for (int js = 0; js < 9; js++) {
						int i=getLineOfRow(gameRange.gameRange,js,9);
						if (i>-1) {
							removex1 = i;
							removey1 = js;
							if(rebuildGameRange[removex1][removey1]>7){
								//说明生成了道具
								gameRange.gameRange[i][js] =rebuildGameRange[removex1][removey1];
							}else{
								//没生成道具
							for (int gs = i; gs > -1; gs--) {
								if (gs == 0) {
									// System.out.print("gs:"+gs+"i:"+i+"js"+removey1+"    ");
									gameRange.gameRange[gs][js] = rebuildGameRange[removex1][removey1];
								} else {
									gameRange.gameRange[gs][js] = gameRange.gameRange[gs - 1][js];
								}
							}
							exchangeRebulidGame(rebuildGameRange,9,js);
					
							}
							
						}
					}
					for (int f = 0; f < 9; f++) {
						for (int j = 0; j < 9; j++) {
							paintGame(g,gameRange.gameRange[f][j],j * onex, f * oney, onex, oney,0,0,0,0);
							
						}
        }
					gameRange.guiUpdateCount = 0;
					//发送项初始化
					FinishMessage sendms=new FinishMessage();
					sendms.UserID=ID;
	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
					//发送移动信息给服务器，
					//此时掉落完成      客户端发给服务器
					//消息类型：118    告诉服务器客户端当前掉落状态
					Startup.net.sendMessage(sendms);
					
					
					
				} else {
					if (gameRange.guiUpdateCount <= 0) {
						gameRange.guiUpdateCount = 8;
						for (int f = 0; f < 9; f++) {
							for (int j = 0; j < 9; j++) {
							paintGame(g,gameRange.gameRange[f][j],j * onex, f * oney, onex, oney,0,0,0,0);
								}
							}
					} else {
						//System.out.println("半移动："
					//+ gameRange.guiUpdateCount);
						
					 	for (int j = 0; j < 9; j++) {
					 		int i=getLineOfRow(gameRange.gameRange,j,9);
							if (i>-1) {
								if(rebuildGameRange[i][j]>7){
									for(int k=0;k<9;k++){
										if(k==i){
											paintGame(g,rebuildGameRange[i][j],j * onex, i * oney, (int) (onex/8*(9.0 - gameRange.guiUpdateCount)), (int) (oney/8*(9.0 - gameRange.guiUpdateCount)),0, 0,0,0);
											
										}else{
											//System.out.println("举行检察"+k);
											paintGame(g,gameRange.gameRange[k][j],j * onex, k * oney, onex, oney,0, 0,0,0);
											}
									}
								}else{
								for (int n = i; n > -1; n--) {
                                            if(n==0){
                                            	paintGame(g,rebuildGameRange[i][j],j * onex,(int) (n* oney + (1.0 - gameRange.guiUpdateCount) * (oney/ 8)), onex, oney,0, 0,0,0);
                                          }else{
                                            	paintGame(g,gameRange.gameRange[n-1][j],j * onex,(int) (n* oney + (1.0 - gameRange.guiUpdateCount) * (oney/ 8)), onex, oney,0, 0,0,0);
                              }
								}
								for (int n = i + 1; n < 9; n++) {
									paintGame(g,gameRange.gameRange[n][j],j * onex, n * oney, onex, oney,0, 0,0,0);
						}
								}
							} else {
								for (int n = 0; n < 9; n++) {
									paintGame(g,gameRange.gameRange[n][j],j * onex, n * oney, onex, oney,0, 0,0,0);
								}
							}
							}
						gameRange.guiUpdateCount--;
					}
				}
				
			}else{
				//动态消除图代码
				//System.out.println("到哪个台消除");
				if(gameRange.guiUpdateCount<=0){
					for (int f = 0; f < 9; f++) {
						for (int j = 0; j < 9; j++) {
							paintGame(g,gameRange.gameRange[f][j],j * onex, f * oney, onex, oney,j * onex, f * oney,onex,oney);
							}
					}
					gameRange.guiUpdateCount=7;
				}else if(gameRange.guiUpdateCount==1){
					for (int f = 0; f < 9; f++) {
						for (int j = 0; j < 9; j++) {
							if(gameRange.gameRange[f][j]<0){
							}else{
								paintGame(g,gameRange.gameRange[f][j],j * onex, f * oney, onex, oney,0, 0,0,0);
							}
							
							}
					}
					for (int f = 0; f < 9; f++) {
						for (int j = 0; j < 9; j++) {
							if(gameRange.gameRange[f][j]<0)
							gameRange.gameRange[f][j]=0;
						}
					}
					gameRange.guiUpdateCount=0;
					}else{
						//动态消除的（432）
				
					for (int f = 0; f < 9; f++) {
						// System.out.println("");
						for (int j = 0; j < 9; j++) {
							// System.out.print("    "+gameRange[f][j]);
							paintGame(g,gameRange.gameRange[f][j],j * onex, f * oney, onex, oney,j * onex+(8-gameRange.guiUpdateCount)*onex/16, f * oney+(8-gameRange.guiUpdateCount)*oney/16, onex/8*gameRange.guiUpdateCount, oney/8*gameRange.guiUpdateCount);
						}
					}
					gameRange.guiUpdateCount--;
				}
			}
		} else if(state==0){
			//System.out.println("初始化");
			if(gameRange.guiUpdateCount<=0){
				gameRange.guiUpdateCount=7;
			}else if(gameRange.guiUpdateCount==1){
				for (int f = 0; f < 9; f++) {
					for (int j = 0; j < 9; j++) {
						paintGame(g,gameRange.gameRange[f][j],j * onex+gameRange.guiUpdateCount*onex/16, f * oney+gameRange.guiUpdateCount*oney/16, (8-gameRange.guiUpdateCount)*onex/8, (8-gameRange.guiUpdateCount)*oney/8,0, 0,0, 0);
						}
				}
				gameRange.guiUpdateCount=0;
					state = 1;//费初始状态
				//发送项初始化
				FinishDropMessage sendms=new FinishDropMessage();
				sendms.UserID=ID;
				//System.out.println("finished drop 掉落 :"+ID);
	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
				//发送移动信息给服务器，
				//此时掉落完成      客户端发给服务器
				//消息类型：118    告诉服务器客户端当前掉落状态
				Startup.net.sendMessage(sendms);
				sendnumber++;
				
			}else{
				for (int f = 0; f < 9; f++) {
					for (int j = 0; j < 9; j++) {
						paintGame(g,gameRange.gameRange[f][j],j * onex+gameRange.guiUpdateCount*onex/16, f * oney+gameRange.guiUpdateCount*oney/16, (8-gameRange.guiUpdateCount)*onex/8, (8-gameRange.guiUpdateCount)*oney/8,0, 0,0, 0);
					}
				}
				gameRange.guiUpdateCount--;
			}
			}else if(state==3){
			//有效移动
			int x12=exchangex1-exchangex2;
			int y12=exchangey1-exchangey2;
		if(gameRange.guiUpdateCount<=0){
			for (int f = 0; f < 9; f++) {
				for (int j = 0; j < 9; j++) {
						paintGame(g,gameRange.gameRange[f][j],j * onex, f * oney, onex, oney,0,0,0,0);
					}
			}
			gameRange.guiUpdateCount=7;
		}else if(gameRange.guiUpdateCount==1){
			for (int f = 0; f < 9; f++) {
				for (int j = 0; j < 9; j++) {
					if(f==exchangex1&&exchangey1==j){
					paintGame(g,color1,j * onex-(8-gameRange.guiUpdateCount)*y12*onex/8, f * oney-(8-gameRange.guiUpdateCount)*x12*oney/8, onex, oney,0, 0,0,0);
					}else if(f==exchangex2&&exchangey2==j){
						paintGame(g,color2,j * onex+(8-gameRange.guiUpdateCount)*y12*onex/8, f * oney+(8-gameRange.guiUpdateCount)*x12*oney/8, onex, oney,0, 0,0,0);
					}else{
						paintGame(g,gameRange.gameRange[f][j],j * onex, f * oney, onex, oney,0, 0,0,0);
					}
					}
			}
			gameRange.guiUpdateCount=0;
			state=1;
			}else{
			for (int f = 0; f < 9; f++) {
				for (int j = 0; j < 9; j++) {
					if(f==exchangex1&&exchangey1==j){
					paintGame(g,color1,j * onex-(8-gameRange.guiUpdateCount)*y12*onex/8, f * oney-(8-gameRange.guiUpdateCount)*x12*oney/8, onex, oney,0, 0,0,0);
					}else if(f==exchangex2&&exchangey2==j){
						paintGame(g,color2,j * onex+(8-gameRange.guiUpdateCount)*y12*onex/8, f * oney+(8-gameRange.guiUpdateCount)*x12*oney/8, onex, oney,0, 0,0,0);
					}else{
						paintGame(g,gameRange.gameRange[f][j],j * onex, f * oney, onex, oney,0, 0,0,0);
					}
					}
			}
			gameRange.guiUpdateCount--;
		}
		}
		
		if (remindx1 > -1 && remindx1 < 9) {
			
			if(imageNumber<=1){
				imageNumber=1;
				java.net.URL imgurlf = singleGameGUI.class
						.getResource("/bin/"+(int)(imageNumber/5+1)+".png");
				Image imagef = new ImageIcon(imgurlf).getImage();
				g.drawImage(imagef,remindx1*onex+(onex/2), remindy1*oney+(oney/2), this.getWidth()/18 , this.getHeight()/18 , this );
				g.drawImage(imagef,remindx2*onex+(onex/2), remindy2*oney+(oney/2), this.getWidth()/18 , this.getHeight()/18 , this );
			   imageNumber=19;
			
				
			}else {
				java.net.URL imgurlf = singleGameGUI.class
						.getResource("/bin/"+(int)(imageNumber/5+1)+".png");
				Image imagef = new ImageIcon(imgurlf).getImage();
				g.drawImage(imagef,remindx1*onex+(onex/2), remindy1*oney+(oney/2), this.getWidth()/18 , this.getHeight()/18 , this );
				g.drawImage(imagef,remindx2*onex+(onex/2), remindy2*oney+(oney/2), this.getWidth()/18 , this.getHeight()/18 , this );
			    imageNumber--;
			}
		}

		if(gameRange.guiUpdateCount!=0){
			this.setEnabled(false);
		}else{
			this.setEnabled(true);
		}
	}
	public void paintGame(Graphics g,int picture,int x1,int y1,int width1,int height1,int x2,int y2,int width2,int height2){
		int rex = y1 / (this.getHeight() / 9);
		int rey = x1 / (this.getWidth() / 9);
		if(dropdirection==2){
			int cv=x1;
			x1=y1;
			y1=cv;
			cv=x2;
			x2=y2;
			y2=cv;
		 rex = y1 / (this.getHeight() / 9);
		 rey = x1 / (this.getWidth() / 9);
		}
		if(!(rex==mousex&&rey==mousey)){
		switch(picture){
		case 1:
			java.net.URL imgurl1 =collaborationGameGUI.class
					.getResource("/bin/gameImg1.png");
			Image image1 = new ImageIcon(imgurl1).getImage();
			g.drawImage(image1, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 2:
			java.net.URL imgurl2 = collaborationGameGUI.class
					.getResource("/bin/gameImg2.png");
			Image image2 = new ImageIcon(imgurl2).getImage();
			g.drawImage(image2, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 3:
			java.net.URL imgurl3 = collaborationGameGUI.class
					.getResource("/bin/gameImg3.png");
			Image image3 = new ImageIcon(imgurl3).getImage();
			g.drawImage(image3, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 4:
			java.net.URL imgurl4 = collaborationGameGUI.class
					.getResource("/bin/gameImg4.png");
			Image image4 = new ImageIcon(imgurl4).getImage();
			g.drawImage(image4, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 5:
			java.net.URL imgurl5 = collaborationGameGUI.class
					.getResource("/bin/gameImg5.png");
			Image image5 = new ImageIcon(imgurl5).getImage();
			g.drawImage(image5, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 6:
			java.net.URL imgurl6 = collaborationGameGUI.class
					.getResource("/bin/gameImg6.png");
			Image image6 = new ImageIcon(imgurl6).getImage();
			g.drawImage(image6, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 7:
			java.net.URL imgurl7 = collaborationGameGUI.class
					.getResource("/bin/gameImg7.png");
			Image image7 = new ImageIcon(imgurl7).getImage();
			g.drawImage(image7, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 11:
			java.net.URL imgurl11 = collaborationGameGUI.class
					.getResource("/bin/gameImg1_c.png");
			Image image11 = new ImageIcon(imgurl11).getImage();
			g.drawImage(image11, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 12:
			java.net.URL imgurl12 = collaborationGameGUI.class
					.getResource("/bin/gameImg2_c.png");
			Image image12 = new ImageIcon(imgurl12).getImage();
			g.drawImage(image12, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 13:
			java.net.URL imgurl13 = collaborationGameGUI.class
					.getResource("/bin/gameImg3_c.png");
			Image image13 = new ImageIcon(imgurl13).getImage();
			g.drawImage(image13, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 14:
			java.net.URL imgurl14 = collaborationGameGUI.class
					.getResource("/bin/gameImg4_c.png");
			Image image14 = new ImageIcon(imgurl14).getImage();
			g.drawImage(image14, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 15:
			java.net.URL imgurl15 = collaborationGameGUI.class
					.getResource("/bin/gameImg5_c.png");
			Image image15 = new ImageIcon(imgurl15).getImage();
			g.drawImage(image15, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 16:
			java.net.URL imgurl16 = collaborationGameGUI.class
					.getResource("/bin/gameImg6_c.png");
			Image image16 = new ImageIcon(imgurl16).getImage();
			g.drawImage(image16, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 17:
			java.net.URL imgurl17 = collaborationGameGUI.class
					.getResource("/bin/gameImg7_c.png");
			Image image17 = new ImageIcon(imgurl17).getImage();
			g.drawImage(image17, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 20:
			java.net.URL imgurl20 = collaborationGameGUI.class
					.getResource("/bin/gameImgSpecial.png");
			Image image20 = new ImageIcon(imgurl20).getImage();
			g.drawImage(image20, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case -1:
			java.net.URL imgurl1f = collaborationGameGUI.class
					.getResource("/bin/gameImg1.png");
			Image image1f = new ImageIcon(imgurl1f).getImage();
			g.drawImage(image1f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -2:
			java.net.URL imgurl2f = collaborationGameGUI.class
					.getResource("/bin/gameImg2.png");
			Image image2f = new ImageIcon(imgurl2f).getImage();
			g.drawImage(image2f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -3:
			java.net.URL imgurl3f = collaborationGameGUI.class
					.getResource("/bin/gameImg3.png");
			Image image3f = new ImageIcon(imgurl3f).getImage();
			g.drawImage(image3f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -4:
			java.net.URL imgurl4f = collaborationGameGUI.class
					.getResource("/bin/gameImg4.png");
			Image image4f = new ImageIcon(imgurl4f).getImage();
			g.drawImage(image4f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -5:
			java.net.URL imgurl5f = collaborationGameGUI.class
					.getResource("/bin/gameImg5.png");
			Image image5f = new ImageIcon(imgurl5f).getImage();
			g.drawImage(image5f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -6:
			java.net.URL imgurl6f = collaborationGameGUI.class
					.getResource("/bin/gameImg6.png");
			Image image6f = new ImageIcon(imgurl6f).getImage();
			g.drawImage(image6f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -7:
			java.net.URL imgurl7f = collaborationGameGUI.class
					.getResource("/bin/gameImg7.png");
			Image image7f = new ImageIcon(imgurl7f).getImage();
			g.drawImage(image7f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -11:
			java.net.URL imgurl11f = collaborationGameGUI.class
					.getResource("/bin/gameImg1_c.png");
			Image image11f = new ImageIcon(imgurl11f).getImage();
			g.drawImage(image11f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -12:
			java.net.URL imgurl12f = collaborationGameGUI.class
					.getResource("/bin/gameImg2_c.png");
			Image image12f = new ImageIcon(imgurl12f).getImage();
			g.drawImage(image12f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -13:
			java.net.URL imgurl13f = collaborationGameGUI.class
					.getResource("/bin/gameImg3_c.png");
			Image image13f = new ImageIcon(imgurl13f).getImage();
			g.drawImage(image13f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -14:
			java.net.URL imgurl14f = collaborationGameGUI.class
					.getResource("/bin/gameImg4_c.png");
			Image image14f = new ImageIcon(imgurl14f).getImage();
			g.drawImage(image14f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -15:
			java.net.URL imgurl15f = collaborationGameGUI.class
					.getResource("/bin/gameImg5_c.png");
			Image image15f = new ImageIcon(imgurl15f).getImage();
			g.drawImage(image15f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -16:
			java.net.URL imgurl16f = collaborationGameGUI.class
					.getResource("/bin/gameImg6_c.png");
			Image image16f = new ImageIcon(imgurl16f).getImage();
			g.drawImage(image16f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -17:
			java.net.URL imgurl17f = collaborationGameGUI.class
					.getResource("/bin/gameImg7_c.png");
			Image image17f = new ImageIcon(imgurl17f).getImage();
			g.drawImage(image17f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -20:
			java.net.URL imgurl20f = collaborationGameGUI.class
					.getResource("/bin/gameImgSpecial.png");
			Image image20f = new ImageIcon(imgurl20f).getImage();
			g.drawImage(image20f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		}

	} else {
		switch (picture) {
		case 1:
			java.net.URL imgurl1 = collaborationGameGUI.class
					.getResource("/bin/gameImg1_a.png");
			Image image1 = new ImageIcon(imgurl1).getImage();
			g.drawImage(image1, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 2:
			java.net.URL imgurl2 = collaborationGameGUI.class
					.getResource("/bin/gameImg2_a.png");
			Image image2 = new ImageIcon(imgurl2).getImage();
			g.drawImage(image2, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 3:
			java.net.URL imgurl3 = collaborationGameGUI.class
					.getResource("/bin/gameImg3_a.png");
			Image image3 = new ImageIcon(imgurl3).getImage();
			g.drawImage(image3, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 4:
			java.net.URL imgurl4 = collaborationGameGUI.class
					.getResource("/bin/gameImg4_a.png");
			Image image4 = new ImageIcon(imgurl4).getImage();
			g.drawImage(image4, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 5:
			java.net.URL imgurl5 = collaborationGameGUI.class
					.getResource("/bin/gameImg5_a.png");
			Image image5 = new ImageIcon(imgurl5).getImage();
			g.drawImage(image5, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 6:
			java.net.URL imgurl6 = collaborationGameGUI.class
					.getResource("/bin/gameImg6_a.png");
			Image image6 = new ImageIcon(imgurl6).getImage();
			g.drawImage(image6, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 7:
			java.net.URL imgurl7 = collaborationGameGUI.class
					.getResource("/bin/gameImg7_a.png");
			Image image7 = new ImageIcon(imgurl7).getImage();
			g.drawImage(image7, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 11:
			java.net.URL imgurl11 = collaborationGameGUI.class
					.getResource("/bin/gameImg1_b.png");
			Image image11 = new ImageIcon(imgurl11).getImage();
			g.drawImage(image11, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 12:
			java.net.URL imgurl12 = collaborationGameGUI.class
					.getResource("/bin/gameImg2_b.png");
			Image image12 = new ImageIcon(imgurl12).getImage();
			g.drawImage(image12, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 13:
			java.net.URL imgurl13 = collaborationGameGUI.class
					.getResource("/bin/gameImg3_b.png");
			Image image13 = new ImageIcon(imgurl13).getImage();
			g.drawImage(image13, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 14:
			java.net.URL imgurl14 = collaborationGameGUI.class
					.getResource("/bin/gameImg4_b.png");
			Image image14 = new ImageIcon(imgurl14).getImage();
			g.drawImage(image14, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 15:
			java.net.URL imgurl15 = collaborationGameGUI.class
					.getResource("/bin/gameImg5_b.png");
			Image image15 = new ImageIcon(imgurl15).getImage();
			g.drawImage(image15, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 16:
			java.net.URL imgurl16 = collaborationGameGUI.class
					.getResource("/bin/gameImg6_b.png");
			Image image16 = new ImageIcon(imgurl16).getImage();
			g.drawImage(image16, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 17:
			java.net.URL imgurl17 = collaborationGameGUI.class
					.getResource("/bin/gameImg7_b.png");
			Image image17 = new ImageIcon(imgurl17).getImage();
			g.drawImage(image17, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case 20:
			java.net.URL imgurl20 = collaborationGameGUI.class
					.getResource("/bin/gameImgSpecial_a.png");
			Image image20 = new ImageIcon(imgurl20).getImage();
			g.drawImage(image20, x1, y1, width1 - 2, height1 - 2, this);
			break;
		case -1:
			java.net.URL imgurl1f = collaborationGameGUI.class
					.getResource("/bin/gameImg1.png");
			Image image1f = new ImageIcon(imgurl1f).getImage();
			g.drawImage(image1f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -2:
			java.net.URL imgurl2f = collaborationGameGUI.class
					.getResource("/bin/gameImg2.png");
			Image image2f = new ImageIcon(imgurl2f).getImage();
			g.drawImage(image2f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -3:
			java.net.URL imgurl3f = collaborationGameGUI.class
					.getResource("/bin/gameImg3.png");
			Image image3f = new ImageIcon(imgurl3f).getImage();
			g.drawImage(image3f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -4:
			java.net.URL imgurl4f = collaborationGameGUI.class
					.getResource("/bin/gameImg4.png");
			Image image4f = new ImageIcon(imgurl4f).getImage();
			g.drawImage(image4f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -5:
			java.net.URL imgurl5f = collaborationGameGUI.class
					.getResource("/bin/gameImg5.png");
			Image image5f = new ImageIcon(imgurl5f).getImage();
			g.drawImage(image5f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -6:
			java.net.URL imgurl6f = collaborationGameGUI.class
					.getResource("/bin/gameImg6.png");
			Image image6f = new ImageIcon(imgurl6f).getImage();
			g.drawImage(image6f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -7:
			java.net.URL imgurl7f = collaborationGameGUI.class
					.getResource("/bin/gameImg7.png");
			Image image7f = new ImageIcon(imgurl7f).getImage();
			g.drawImage(image7f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -11:
			java.net.URL imgurl11f = collaborationGameGUI.class
					.getResource("/bin/gameImg1.png");
			Image image11f = new ImageIcon(imgurl11f).getImage();
			g.drawImage(image11f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -12:
			java.net.URL imgurl12f = collaborationGameGUI.class
					.getResource("/bin/gameImg2.png");
			Image image12f = new ImageIcon(imgurl12f).getImage();
			g.drawImage(image12f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -13:
			java.net.URL imgurl13f = collaborationGameGUI.class
					.getResource("/bin/gameImg3.png");
			Image image13f = new ImageIcon(imgurl13f).getImage();
			g.drawImage(image13f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -14:
			java.net.URL imgurl14f = collaborationGameGUI.class
					.getResource("/bin/gameImg4.png");
			Image image14f = new ImageIcon(imgurl14f).getImage();
			g.drawImage(image14f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -15:
			java.net.URL imgurl15f = collaborationGameGUI.class
					.getResource("/bin/gameImg5.png");
			Image image15f = new ImageIcon(imgurl15f).getImage();
			g.drawImage(image15f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -16:
			java.net.URL imgurl16f = collaborationGameGUI.class
					.getResource("/bin/gameImg6.png");
			Image image16f = new ImageIcon(imgurl16f).getImage();
			g.drawImage(image16f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -17:
			java.net.URL imgurl17f = collaborationGameGUI.class
					.getResource("/bin/gameImg7.png");
			Image image17f = new ImageIcon(imgurl17f).getImage();
			g.drawImage(image17f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		case -20:
			java.net.URL imgurl20f = collaborationGameGUI.class
					.getResource("/bin/gameImgSpecial.png");
			Image image20f = new ImageIcon(imgurl20f).getImage();
			g.drawImage(image20f, x2, y2, width2 - 2, height2 - 2, this);
			break;
		}
	}
	}
}}


package presentation_localgame;

import gamemessage.ReNetMessage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;




public class resultGUI {
	static JPanel cspanel=new JPanel();
static JFrame frame;
static JLayeredPane layeredpane ;
static int frameweight=500;
	static int frameheight=500;
static int time=0;
static int grade;
static JLabel gradelabel;

public resultGUI(int playergrade,String playerName,String playerPhoto){
	
	frame = new JFrame();
	frame.setSize(frameweight, frameheight);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	frame.setUndecorated(true);
	frame.setBounds(450, 150, frameweight, frameheight);
	//frame.setBackground(new Color(0, 0, 0, 0));
	
    layeredpane = new JLayeredPane(); // 重要
	layeredpane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
	layeredpane.setLayout(null);
	layeredpane.setOpaque(false);
	frame.add(layeredpane);
	//背景添加 
	 ImageIcon bgimage=new ImageIcon(resultGUI.class.getResource("/bin/result.jpg"));
	 JLabel bglabel=new JLabel(bgimage);
	 JPanel bgpanel=new JPanel();
	 bgpanel.setLayout(null);
	 bgpanel.setBounds(0,0,frameweight, frameheight);
	 bglabel.setBounds(0, 0, frameweight, frameheight);
	 bgpanel.add(bglabel);
	 layeredpane.add(bgpanel,new Integer(10));
	 
	 this.gameInfo( playergrade, playerName, playerPhoto);
	
	frame.setVisible(true);
		
}
public static void showtime(final int gradeinput){
	final JPanel endshow=new JPanel();
 final Timer timer=new Timer(true);
time=0;
	timer.schedule(new TimerTask(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			time++;
			System.out.println(time+"B");
			if(time<=10){
				//congraduateShow();
				frame.repaint();
				frame.validate();
				
			}else{
				layeredpane.remove(cspanel);
				//jumpshow();
				timer.cancel();
				frame.repaint();
				frame.validate();
			}
		}
		
	}, 0,300);
 final Timer gradetimer=new Timer(true);

	gradetimer.schedule(new TimerTask(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			System.out.println(time+"A"); 
			if(time<=100&&time>10){
			    grade=gradeinput*(time-10)/90;
				gradelabel.setText("      "+grade);
				//frame.repaint();
				frame.validate();
				time++;
			}else if(time>100){
				layeredpane.remove(cspanel);
				jumpshow();
				timer.cancel();
			    gradetimer.cancel();
				//frame.repaint();
				frame.validate();
				time++;
			}
		}
		
	}, 0,50);
}
public static void congraduateShow(){
     cspanel.removeAll();
     
	cspanel.setSize(frameweight, frameheight);
	cspanel.setOpaque(false);
	cspanel.setLayout(null);
	
	ImageIcon image=new ImageIcon(resultGUI.class.getResource("/bin/"+time%3+".jpg"));
	
	JLabel label=new JLabel(image);
	label.setBounds(0,0,frameweight,frameheight);
	cspanel.add(label);
	layeredpane.add(cspanel,new Integer(200));
	
}

public static void jumpshow(){
	
	JPanel jumppanel=new JPanel();
	 jumppanel.setLayout(null);
	
	 jumppanel.setBounds(frameweight/2-30, frameheight-100, 60, 60 );
	 ImageIcon image=new ImageIcon(resultGUI.class.getResource("/bin/jump.png"));
	 JButton button=new JButton(image);
	 button.setOpaque(true);
	 button.setBorder(null);
     button.setContentAreaFilled(false);
	 button.setBounds(0,0,60,60);
	 button.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		frame.dispose();
		}
		
	 
	 });
	jumppanel.add(button);
	layeredpane.add(jumppanel,new Integer(20));
	jumppanel.setOpaque(false);	
}
public void gameInfo(int playergrade,String playerName,String playerPhoto){
	grade=0;
	gradelabel=new JLabel("      "+0);
	
   ImageIcon image=new ImageIcon(resultGUI.class.getResource("/bin/playerImg1.png"));
	JLabel bglabel=new JLabel(image);
	bglabel.setSize(50,50);
	bglabel.setLocation(25, 0);
	JLabel playernamelabel=new JLabel(playerName);
	playernamelabel.setFont(new Font("Rosewood Std 粗体",Font.ITALIC+Font.BOLD,25));
	playernamelabel.setBounds(0, 50, 100, 40);
	playernamelabel.setHorizontalAlignment(SwingConstants.CENTER);
	JPanel pPanel=new JPanel();
	pPanel.setLayout(null);
	pPanel.setOpaque(false);
	pPanel.setBounds(200, 150, 100, 100);
	
	pPanel.add(playernamelabel);
	pPanel.add(bglabel);
	
		
		JPanel panel1=new JPanel();
		panel1.setLayout(null);
        panel1.setOpaque(false);
        panel1.setBounds(170,330,300,50);
		
		
		gradelabel.setText("       "+grade);
		gradelabel.setFont(new Font("Rosewood Std 粗体",Font.ITALIC+Font.BOLD,28));
	    gradelabel.setBounds(0, 0, 250, 40);
		panel1.add(gradelabel);
		
	
		
		//layeredpane.add(animaPanel,new Integer(30));
		layeredpane.add(pPanel,new Integer(20));
		layeredpane.add(panel1,new Integer(20));

	
}
class AnimaPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	@Override
	public void paintComponent(Graphics g) {
		java.net.URL imgurl = singleGameGUI.class
				.getResource("/bin/resultAni.jpg");
		Image image = new ImageIcon(imgurl).getImage();
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);

	}
}


}

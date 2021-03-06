package presentation.mainui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Startup;
import message.UpdateUserInfo;

public class PhotoSelector extends JPanel{

	 private JLabel verify,cancel,instruction,i2;
	 private Photo[] photos;
	 private int selected_id;
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PhotoSelector(){
		setSize(600,300);
		setLayout(null);
		setBorder(new WhiteRoundRectBorder());
		
		verify=new JLabel("ȷ��");
		verify.setBounds(520,270,30,30);
		verify.setFont(new Font("΢���ź�",Font.PLAIN,13));
		verify.setForeground(Color.white);
		add(verify);
     
		verify.addMouseListener(new MouseListener(){
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				verify.setForeground(Color.white);
			}

			public void mouseReleased(MouseEvent e) {
				Startup.window.mainframe.photo_id=selected_id;
				switch(selected_id){
				case 0: Startup.net.sendMessage(new UpdateUserInfo(Startup.window.uif.name,"playerImg1.png")); break;
				case 1: Startup.net.sendMessage(new UpdateUserInfo(Startup.window.uif.name,"playerImg2.png")); break;
				case 2: Startup.net.sendMessage(new UpdateUserInfo(Startup.window.uif.name,"playerImg3.png")); break;
				case 3: Startup.net.sendMessage(new UpdateUserInfo(Startup.window.uif.name,"playerImg4.png")); break;
				case 4: Startup.net.sendMessage(new UpdateUserInfo(Startup.window.uif.name,"playerImg5.png")); break;
				case 5: Startup.net.sendMessage(new UpdateUserInfo(Startup.window.uif.name,"playerImg6.png")); break;
				case 6: Startup.net.sendMessage(new UpdateUserInfo(Startup.window.uif.name,"playerImg7.png")); break;
				}
				Startup.window.mainframe.panel.remove(Startup.window.mainframe.opa);
				 if(Startup.window.mainframe.fl!=null){
		            	Startup.window.mainframe.panel.remove(Startup.window.mainframe.fl);
						Startup.window.mainframe.fl=new FriendList();
						Startup.window.mainframe.panel.add(Startup.window.mainframe.fl,new Integer(1));
						Startup.window.mainframe.fl.setLocation(648,110);
					}
					else if(Startup.window.mainframe.mp!=null){
						Startup.window.mainframe.panel.remove(Startup.window.mainframe.mp);
						Startup.window.mainframe.mp=new MessagePanel();
						Startup.window.mainframe.panel.add(Startup.window.mainframe.mp,new Integer(1));
						Startup.window.mainframe.mp.setLocation(648,110);
					}
					else if(Startup.window.mainframe.afp!=null){
						Startup.window.mainframe.panel.remove(Startup.window.mainframe.afp);
						Startup.window.mainframe.afp=new AddFriendPanel();
						Startup.window.mainframe.panel.add(Startup.window.mainframe.afp,new Integer(1));
						Startup.window.mainframe.afp.setLocation(648,110);
					}
				Startup.window.mainframe.panel.repaint();
				Startup.window.mainframe.ps=null;
				Startup.window.mainframe.opa=null;
			}

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				verify.setForeground(new Color(36,85,141));
			}

			public void mousePressed(MouseEvent e) {
                   
			}
		});
		
		cancel=new JLabel("ȡ��");
		cancel.setBounds(560,270,30,30);
		cancel.setFont(new Font("΢���ź�",Font.PLAIN,13));
		cancel.setForeground(Color.white);
		add(cancel);
     
		cancel.addMouseListener(new MouseListener(){
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				cancel.setForeground(Color.white);
			}

			public void mouseReleased(MouseEvent e) {
				Startup.window.mainframe.panel.remove(Startup.window.mainframe.opa);
	            if(Startup.window.mainframe.fl!=null){
	            	Startup.window.mainframe.panel.remove(Startup.window.mainframe.fl);
					Startup.window.mainframe.fl=new FriendList();
					Startup.window.mainframe.panel.add(Startup.window.mainframe.fl,new Integer(1));
					Startup.window.mainframe.fl.setLocation(648,110);
				}
				else if(Startup.window.mainframe.mp!=null){
					Startup.window.mainframe.panel.remove(Startup.window.mainframe.mp);
					Startup.window.mainframe.mp=new MessagePanel();
					Startup.window.mainframe.panel.add(Startup.window.mainframe.mp,new Integer(1));
					Startup.window.mainframe.mp.setLocation(648,110);
				}
				else if(Startup.window.mainframe.afp!=null){
					Startup.window.mainframe.panel.remove(Startup.window.mainframe.afp);
					Startup.window.mainframe.afp=new AddFriendPanel();
					Startup.window.mainframe.panel.add(Startup.window.mainframe.afp,new Integer(1));
					Startup.window.mainframe.afp.setLocation(648,110);
				}
				Startup.window.mainframe.panel.repaint();
				Startup.window.mainframe.ps=null;
				Startup.window.mainframe.opa=null;
			}

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				cancel.setForeground(new Color(36,85,141));
			}

			public void mousePressed(MouseEvent e) {
                   
			}
		});
		
		instruction=new JLabel("ѡ�����ͷ��");
		instruction.setBounds(10,10,100,30);
		instruction.setFont(new Font("΢���ź�",Font.PLAIN,13));
		instruction.setForeground(Color.white);
		add(instruction);
		
		i2=new JLabel("<html>���찮�������<br/>ͷ�񽫳�������<br/>Ϸ����С�</html>");
		i2.setBounds(450,50,100,90);
		i2.setFont(new Font("΢���ź�",Font.PLAIN,13));
		i2.setForeground(Color.white);
		add(i2);
		
		photos=new Photo[7];
		for(int i=0;i<=photos.length-1;i++){
			photos[i]=new Photo(i);
			if(i<=4){
				photos[i].setBounds(30+i*80,60,45,45);
				add(photos[i]);
			}
			else{
				photos[i].setBounds(30+(i-5)*80,140,45,45);
				add(photos[i]);
			}
		}
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1,5,5);
		
		g.setColor(new Color(11,17,33));
		g.drawLine(10, 42, 580, 42);
		
		g.setColor(new Color(114,113,111));
		g.drawRoundRect(10,50,420,230,5,5);
	}
	
	public class Photo extends JPanel{
		
		private int p_id;
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Photo(int photo_id){
			p_id=photo_id;
			setOpaque(false);
			setBorder(new WhiteRectBorder());
			addMouseListener(new MouseListener(){
				public void mouseExited(MouseEvent e) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

				public void mouseReleased(MouseEvent e) {
					for(int i=0;i<=photos.length-1;i++)
						photos[i].setBorder(new WhiteRectBorder());
					if(e.getSource()==photos[0])
						selected_id=0;
					if(e.getSource()==photos[1])
						selected_id=1;
					if(e.getSource()==photos[2])
						selected_id=2;
					if(e.getSource()==photos[3])
						selected_id=3;
					if(e.getSource()==photos[4])
						selected_id=4;
					if(e.getSource()==photos[5])
						selected_id=5;
					if(e.getSource()==photos[6])
						selected_id=6;
                    setBorder(new BlueRectBorder());
				}

				public void mouseClicked(MouseEvent e) {

				}

				public void mouseEntered(MouseEvent e) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				public void mousePressed(MouseEvent e) {
	                   
				}
			});
		}
		
		public void paintComponent(Graphics g){
			Image i0=new ImageIcon(PhotoSelector.class.getResource("/img/0.jpg")).getImage();
			Image i1=new ImageIcon(PhotoSelector.class.getResource("/img/1.jpg")).getImage();
			Image i2=new ImageIcon(PhotoSelector.class.getResource("/img/2.jpg")).getImage();
			Image i3=new ImageIcon(PhotoSelector.class.getResource("/img/3.jpg")).getImage();
			Image i4=new ImageIcon(PhotoSelector.class.getResource("/img/4.jpg")).getImage();
			Image i5=new ImageIcon(PhotoSelector.class.getResource("/img/5.jpg")).getImage();
			Image i6=new ImageIcon(PhotoSelector.class.getResource("/img/6.jpg")).getImage();
			switch(p_id){
			case 0: g.drawImage(i0, 0, 0,45,45,this); break;
			case 1: g.drawImage(i1, 0, 0, 45,45,this); break;
			case 2: g.drawImage(i2, 0, 0, 45,45,this); break;
			case 3: g.drawImage(i3, 0, 0, 45,45,this); break;
			case 4: g.drawImage(i4, 0, 0, 45,45,this); break;
			case 5: g.drawImage(i5, 0, 0, 45,45,this); break;
			case 6: g.drawImage(i6, 0, 0, 45,45,this); break;
			}
		}
	}

}

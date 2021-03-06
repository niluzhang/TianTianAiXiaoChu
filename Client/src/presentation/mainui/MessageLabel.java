package presentation.mainui;

import gamemessage.CorpGameStartMessage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import presentation.gameui.collaborationGameGUI;

import main.Startup;
import message.AddFriendMessage;
import message.DenyCoMessage;
import message.PermitCoMessage;
import message.RemoveOneCoMessage;
import message.RemoveOneFriendRequestMessage;
import message.TellOthers;

public class MessageLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String text;
	JFrame frame;

	public MessageLabel(String text) {
		this.text = text;
		setSize(180, 30);

		setBorder(new Border() {

			@Override
			public void paintBorder(Component c, Graphics g, int x, int y,
					int width, int height) {
				// TODO Auto-generated method stub
				g.setColor(Color.darkGray);
				g.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
			}

			@Override
			public Insets getBorderInsets(Component c) {
				// TODO Auto-generated method stub
				return new Insets(1, 1, 1, 1);
			}

			@Override
			public boolean isBorderOpaque() {
				// TODO Auto-generated method stub
				return true;
			}

		});

		JLabel info = new JLabel(text);
		info.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		info.setForeground(Color.white);
		info.setBounds(0, 0, 130, 30);
		add(info);

		JLabel permit = new JLabel("√");
		permit.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		permit.setForeground(Color.green);
		permit.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if (MessageLabel.this.text.contains("请求添加你为好友")) {
					String[] split = MessageLabel.this.text.split(" ");
					// 添加好友
					Startup.net.sendMessage(new AddFriendMessage(
							Startup.window.uif.name, split[0]));
					// 在服务器端的序列化文件上删除这则请求
					Startup.net.sendMessage(new RemoveOneFriendRequestMessage(
							Startup.window.uif.name, split[0]));
					Startup.window.mainframe.mp.before();
					Startup.window.mainframe.mp.updateInfo();
					Startup.window.mainframe.mp.repaint();
					Startup.net.sendMessage(new TellOthers(
							Startup.window.uif.name));
				} else if (MessageLabel.this.text.contains("邀请您协作游戏")) {
					String[] split = MessageLabel.this.text.split(" ");
					Startup.net.sendMessage(new RemoveOneCoMessage(
							Startup.window.uif.name, split[0]));
					Startup.net.sendMessage(new PermitCoMessage(
							Startup.window.uif.name, split[0]));
					Startup.window.mainframe.mp.before();
					Startup.window.mainframe.mp.updateInfo();
					Startup.window.mainframe.mp.repaint();
					Startup.window.mainframe.frame.dispose();
					
					frame=new JFrame();
					frame.setSize(200,100);
					frame.setLayout(null);
					frame.setLocationRelativeTo(null);
					frame.setUndecorated(true);
					frame.setBackground(new Color(0,0,0,0));
					
					Wait w=new Wait();
					w.setOpaque(false);
					w.setBounds(0,0,200,100);
					w.setLayout(null);
					frame.add(w);
					
					frame.setVisible(true);
					
					GetCorpGameStartMessage g = new GetCorpGameStartMessage();
					g.start();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		});
		permit.setBounds(135, 0, 20, 30);
		add(permit);

		JLabel deny = new JLabel("×");
		deny.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		deny.setForeground(Color.red);
		deny.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if (MessageLabel.this.text.contains("请求添加你为好友")) {
					// 在界面上删除这条消息
					String[] split = MessageLabel.this.text.split(" ");
					// 在服务器端的序列化文件上删除这则请求
					Startup.net.sendMessage(new RemoveOneFriendRequestMessage(
							Startup.window.uif.name, split[0]));
					Startup.window.mainframe.mp.before();
					Startup.window.mainframe.mp.updateInfo();
					Startup.window.mainframe.mp.repaint();
				} else if (MessageLabel.this.text.contains("邀请您协作游戏")) {
					// do nothing
					String[] split = MessageLabel.this.text.split(" ");
					Startup.net.sendMessage(new RemoveOneCoMessage(
							Startup.window.uif.name, split[0]));
					Startup.net.sendMessage(new DenyCoMessage(
							Startup.window.uif.name, split[0]));
					Startup.window.mainframe.mp.before();
					Startup.window.mainframe.mp.updateInfo();
					Startup.window.mainframe.mp.repaint();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		});
		deny.setBounds(160, 0, 20, 30);
		add(deny);

	}

	public class GetCorpGameStartMessage extends Thread {

		public void run() {
			synchronized (Startup.window.mainframe.lock) {
				CorpGameStartMessage cgsm = (CorpGameStartMessage) Startup.net
						.getMessage(112);
				while (cgsm == null)
					cgsm = (CorpGameStartMessage) Startup.net.getMessage(112);
				// 界面跳转
				frame.dispose();
				try {
					collaborationGameGUI.colgamemain(Startup.window.uif.name, "成员", false,
							false, false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public class Wait extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public void paintComponent(Graphics g){
			Image i=new ImageIcon(MainFrame.class.getResource("/img/wait.png")).getImage();
			g.drawImage(i, 0,0,this);
		}	
	}

}

package presentation.mainui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Startup;
import message.ShowOfflineFriendBackMessage;
import message.ShowOfflineFriendMessage;
import message.ShowOnlineFriendBackMessage;
import message.ShowOnlineFriendMessage;

public class FriendList extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JLabel onlineFriend, offLineFriend;
	public OnlineFriendPanel on = new OnlineFriendPanel();
	public OffLineFriendPanel off = new OffLineFriendPanel();
	public boolean onlineOpen = false, offLineOpen = false;

	public FriendList() {
		setSize(200, 400);
		setLayout(null);
		add(new InnerPanel());
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
	}

	public class InnerPanel extends JPanel {
		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;

		public InnerPanel() {
			setBounds(4, 4, 192, 392);
			setLayout(null);
			setOpaque(false);
			JLabel mini = new JLabel("-");
			mini.setBounds(170, 0, 22, 22);
			mini.setForeground(new Color(189, 180, 123));
			mini.setFont(new Font("΢���ź�", Font.PLAIN, 18));
			mini.addMouseListener(new MouseListener() {
				public void mouseExited(MouseEvent e) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

				public void mouseReleased(MouseEvent e) {
					Startup.window.mainframe.panel
							.remove(Startup.window.mainframe.fl);
					Startup.window.mainframe.fl = null;
					Startup.window.mainframe.friend.bg = 0;
					Startup.window.mainframe.panel.repaint();
				}

				public void mouseClicked(MouseEvent e) {

				}

				public void mouseEntered(MouseEvent e) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				public void mousePressed(MouseEvent e) {

				}
			});
			add(mini);

			onlineFriend = new JLabel(" +         ���ߺ���");
			onlineFriend.setBounds(0, 30, 172, 30);
			onlineFriend.setFont(new Font("΢���ź�", Font.PLAIN, 14));
			onlineFriend.setForeground(new Color(189, 180, 123));
			onlineFriend.addMouseListener(new MouseListener() {
				public void mouseExited(MouseEvent e) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					onlineFriend.setForeground(new Color(189, 180, 123));
				}

				public void mouseReleased(MouseEvent e) {
					if (onlineOpen) {
						onlineOpen = false;
						onlineFriend.setText(" +         ���ߺ���");
						remove(on);
						offLineFriend.setLocation(0, 60);
						off.setLocation(0, offLineFriend.getY() + 30);
						repaint();
					} else {
						onlineOpen = true;
						onlineFriend.setText(" -          ���ߺ���");
						on.setLocation(0, 60);
						add(on);
						offLineFriend.setBounds(0, on.getHeight() + 60, 172, 30);
						off.setLocation(0, offLineFriend.getY() + 30);
						repaint();
					}
				}

				public void mouseClicked(MouseEvent e) {

				}

				public void mouseEntered(MouseEvent e) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
					onlineFriend.setForeground(Color.white);
				}

				public void mousePressed(MouseEvent e) {

				}
			});
			add(onlineFriend);

			offLineFriend = new JLabel(" +         ���ߺ���");
			offLineFriend.setBounds(0, 60, 172, 30);
			offLineFriend.setFont(new Font("΢���ź�", Font.PLAIN, 14));
			offLineFriend.setForeground(new Color(189, 180, 123));
			offLineFriend.addMouseListener(new MouseListener() {
				public void mouseExited(MouseEvent e) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					offLineFriend.setForeground(new Color(189, 180, 123));
				}

				public void mouseReleased(MouseEvent e) {
					if (offLineOpen) {
						offLineFriend.setText(" +         ���ߺ���");
						offLineOpen = false;
						remove(off);
						repaint();
					} else {
						offLineFriend.setText(" -          ���ߺ���");
						offLineOpen = true;
						off.setLocation(0, offLineFriend.getY() + 30);
						add(off);
						repaint();
					}
				}

				public void mouseClicked(MouseEvent e) {

				}

				public void mouseEntered(MouseEvent e) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
					offLineFriend.setForeground(Color.white);
				}

				public void mousePressed(MouseEvent e) {

				}
			});
			add(offLineFriend);

			JPanel scrollpane = new MyScrollPane();
			scrollpane.setBounds(172, 40, 15, 340);
			add(scrollpane);
		}

		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(1)); // ���ñʴ�����
			g2.setColor(new Color(84, 116, 157));
			g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
		}
	}

	public class MyScrollPane extends JPanel {
		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			g.setColor(new Color(0, 4, 10));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(new Color(64, 92, 140));
			g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		}
	}

	public class FriendPanel extends JPanel {
		ArrayList<String> friend;
		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;

		public FriendPanel() {
			setOpaque(false);
			setLayout(null);
		}
	}

	public class OnlineFriendPanel extends FriendPanel {

		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;

		public OnlineFriendPanel() {
			getOnlineFriend(null);
			init(Color.white);
		}

		public void getOnlineFriend(String user) {
			Startup.net.sendMessage(new ShowOnlineFriendMessage(
					Startup.window.uif.name));
			ShowOnlineFriendBackMessage sofb = (ShowOnlineFriendBackMessage) Startup.net
					.getMessage(-114);
			while (sofb == null)
				sofb = (ShowOnlineFriendBackMessage) Startup.net
						.getMessage(-114);
			friend = sofb.online_friends;
		}

		public void updateInfo() {
			getOnlineFriend(Startup.window.uif.name);
			init(Color.white);
		}

		public void init(Color c) {
			removeAll();
			setSize(172, friend.size() * 30);
			if (friend.size() == 1 && friend.get(0).equals("")) {
				setSize(172, 0);
			} else {
				for (int i = 0; i <= friend.size() - 1; i++) {
					JLabel l = new FriendLabel("            " + friend.get(i));
					l.setBounds(1, 30 * i, 172, 30);
					l.setForeground(c);
					l.setFont(new Font("΢���ź�", Font.PLAIN, 14));
					add(l);
				}
			}
			if (offLineFriend != null) {
				if (onlineOpen) {
					offLineFriend.setLocation(onlineFriend.getX(), on.getY()
							+ on.getHeight());
				} else {
					offLineFriend.setLocation(onlineFriend.getX(),
							onlineFriend.getY() + onlineFriend.getHeight());
				}
			}
			if (off != null)
				off.setLocation(on.getX(),
						offLineFriend.getY() + offLineFriend.getHeight());
		}
	}

	public class OffLineFriendPanel extends FriendPanel {
		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;

		public OffLineFriendPanel() {
			getOffLineFriend(null);
			init(Color.gray);
		}

		public void getOffLineFriend(String user) {
			Startup.net.sendMessage(new ShowOfflineFriendMessage(
					Startup.window.uif.name));
			ShowOfflineFriendBackMessage sofbm = (ShowOfflineFriendBackMessage) Startup.net
					.getMessage(-116);
			while (sofbm == null)
				sofbm = (ShowOfflineFriendBackMessage) Startup.net
						.getMessage(-116);
			friend = sofbm.offline_friends;
		}

		public void updateInfo() {
			getOffLineFriend(null);
			init(Color.gray);
		}

		public void init(Color c) {
			removeAll();
			setSize(172, friend.size() * 30);
			if (friend.size() == 1 && friend.get(0).equals("")) {
				setSize(172, 0);
			} else {
				for (int i = 0; i <= friend.size() - 1; i++) {
					JLabel l = new FriendLabel("            " + friend.get(i));
					l.setBounds(1, 30 * i, 172, 30);
					l.setForeground(c);
					l.setFont(new Font("΢���ź�", Font.PLAIN, 14));
					add(l);
				}
			}
		}
	}

	public class FriendLabel extends JLabel {
		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;

		public FriendLabel(String name) {
			super(name);
			setOpaque(true);
			setBackground(Color.black);
			addMouseListener(new MouseListener() {
				public void mouseEntered(MouseEvent e) {
					setBackground(new Color(2, 18, 33));
					setCursor(new Cursor(Cursor.HAND_CURSOR));
					repaint();
				}

				public void mouseExited(MouseEvent e) {
					setBackground(Color.black);
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					repaint();
				}

				public void mouseClicked(MouseEvent e) {

				}

				public void mousePressed(MouseEvent e) {

				}

				public void mouseReleased(MouseEvent e) {
					if (e.isPopupTrigger()) {
						MyPopupMenu popupmenu = new MyPopupMenu();
						popupmenu.add(new MyPopupMenu().new MyMenuItem("ɾ��"));
						popupmenu.show(e.getComponent(), e.getX(), e.getY());
						Startup.window.mainframe.theFriend = ((FriendLabel) e
								.getComponent()).getText().trim();
					}
				}
			});
		}
	}

}

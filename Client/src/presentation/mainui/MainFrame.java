package presentation.mainui;

import game.User_info;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JToolTip;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.border.Border;

import main.Startup;
import message.QueryCooperateRequestBackMessage;
import message.QueryCooperateRequestMessage;
import message.QueryFriendRequestBackMessage;
import message.QueryFriendRequestMessage;
import message.QueryNeedToChange;
import message.QueryNeedToChangeBackMessage;
import message.SetFalseMessage;
import message.ShowUserInfoMessage;

import presentation.personalinfoui.UserInfoPanel;
import presentation.rankui.RankUI;

public class MainFrame {

	JFrame frame;
	private int frameFirstX, frameFirstY;
	boolean clicked = false;
	boolean entered = false;
	private Color bg = new Color(21, 160, 245);
	private JPanel panel_2, panel_3, panel_5, panel_6, panel_user, rankpanel;
	private ModeSelectionPanel msp;

	public PhotoSelector ps;
	public JPanel opa;
	public int photo_id;
	public JLayeredPane panel;
	public FriendList fl;
	public AddFriendPanel afp;
	public MessagePanel mp;
	public JButton btnNewButton, btnNewButton_1;
	public FriendButton friend;
	public AddFriendButton afb;
	public MessageButton mb;
	public String theFriend;

	public boolean sound; // 是否要有声音
	public int direction = 1; // 消除方向
	public boolean newMessage = false;

	QueryRequestThread qrt;
	UpdateFriendsThread uft;

	public Object lock = new Object();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public MainFrame() {
		load_settings(Startup.window.uif.name);
		switch (Startup.window.uif.photo) {
		case "playerImg1.png":
			photo_id = 0;
			break;
		case "playerImg2.png":
			photo_id = 1;
			break;
		case "playerImg3.png":
			photo_id = 2;
			break;
		case "playerImg4.png":
			photo_id = 3;
			break;
		case "playerImg5.png":
			photo_id = 4;
			break;
		case "playerImg6.png":
			photo_id = 5;
			break;
		case "playerImg7.png":
			photo_id = 6;
			break;
		}

		initialize();

		qrt = new QueryRequestThread();
		qrt.start();

		uft = new UpdateFriendsThread();
		uft.start();
	}

	public void load_settings(String user) {
		File file = new File(user + "_settings.dat");
		if (file.exists()) {
			FileReader fr;
			try {
				fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String s = br.readLine();
				String[] split = s.split(" ");
				switch (split[0]) {
				case "横向消除":
					direction = 2;
					break;
				case "竖向消除":
					direction = 1;
					break;
				}
				switch (split[1]) {
				case "true":
					sound = true;
					break;
				case "false":
					sound = false;
					break;
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			direction = 1;
			sound = true;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setSize(858, 546);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(new ImageIcon(MainFrame.class
				.getResource("/bin/title.jpg")).getImage());
		frame.setUndecorated(true);
		frame.addMouseListener(new MouseListener() {
			public void mouseExited(MouseEvent e) {

			}

			public void mouseReleased(MouseEvent e) {

			}

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				frameFirstX = e.getX();
				frameFirstY = e.getY();
			}
		});
		frame.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {

			}

			public void mouseDragged(MouseEvent e) {
				int detX = e.getX() - frameFirstX;
				int detY = e.getY() - frameFirstY;
				frame.setBounds(frame.getX() + detX, frame.getY() + detY,
						frame.getWidth(), frame.getHeight());
			}
		});
		frame.setVisible(true);

		panel = new JLayeredPane();
		panel.setBounds(0, 0, 858, 546);
		frame.getContentPane().add(panel);
		panel.setBorder(new PanelBorder());

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(1, 1, 856, 32);
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(bg);

		final JLabel lblNewLabel = new MyLabel("\u00D7");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblNewLabel.setBounds(825, 0, 22, 33);
		lblNewLabel.setToolTipText("关闭天天爱消除");
		lblNewLabel.addMouseListener(new MouseListener() {
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setForeground(new Color(240, 206, 0));
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mouseReleased(MouseEvent e) {
				frame.dispose();
				qrt.stop();
				uft.stop();
			}

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setForeground(Color.red);
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mousePressed(MouseEvent e) {

			}
		});
		panel_1.add(lblNewLabel);

		final JLabel lblNewLabel_1 = new MyLabel("-");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(795, 0, 22, 33);
		lblNewLabel_1.setToolTipText("最小化天天爱消除");
		lblNewLabel_1.addMouseListener(new MouseListener() {
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mouseReleased(MouseEvent e) {
				frame.setExtendedState(JFrame.ICONIFIED);
			}

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mousePressed(MouseEvent e) {

			}
		});
		panel_1.add(lblNewLabel_1);

		panel_2 = new JPanel();
		panel_2.setBounds(5, 33, 847, 74);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBackground(bg);

		btnNewButton = new PlayButton();
		btnNewButton.setBounds(360, 7, 128, 56);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorder(null);
		btnNewButton.setToolTipText("选择您希望进行的游戏类型");
		btnNewButton.addMouseListener(new MouseListener() {
			public void mouseExited(MouseEvent e) {
				if (btnNewButton.isEnabled()) {
					entered = false;
					clicked = false;
				}
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mouseReleased(MouseEvent e) {
				if (btnNewButton.isEnabled()) {
					clicked = true;
					btnNewButton.setEnabled(false);
					frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					if (panel_5 != null) {
						panel.remove(panel_5);
						panel_5 = null;
					} else if (panel_user != null) {
						panel.remove(panel_user);
						panel_user = null;
					}
					else if(rankpanel!=null){
						panel.remove(rankpanel);
						rankpanel=null;
					}
					try {
						msp = new ModeSelectionPanel();
					} catch (FontFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					msp.setLocation(5, 107);
					panel.add(msp);
					panel.repaint();
				}
			}

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {
				if (btnNewButton.isEnabled()) {
					entered = true;
					frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}

			public void mousePressed(MouseEvent e) {

			}
		});
		panel_2.add(btnNewButton);

		panel_3 = new PhotoPanel();
		panel_3.setBounds(555, 12, 45, 45);
		panel_2.add(panel_3);
		panel_3.setBorder(new PhotoPanelBorder());
		panel_3.setLayout(null);
		panel_3.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mouseClicked(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseReleased(MouseEvent e) {
				if (opa == null) {
					if (fl != null) {
						panel.remove(fl);
						fl = new FriendList();
						fl.setLocation(648, 110);
						panel.add(fl, 1);
					} else if (mp != null) {
						panel.remove(mp);
						mp = new MessagePanel();
						mp.setLocation(648, 110);
						panel.add(mp, 1);
					} else if (afp != null) {
						panel.remove(afp);
						afp = new AddFriendPanel();
						afp.setLocation(648, 110);
						panel.add(afp, 1);
					}
					opa = new JPanel();
					opa.setLayout(null);
					opa.setBackground(new Color(0, 0, 0, 100));
					opa.setBounds(0, 0, panel.getWidth(), panel.getHeight());
					opa.addMouseListener(new MouseListener() {

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

						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub

						}

					});
					ps = new PhotoSelector();
					ps.setLocation(137, 140);
					opa.add(ps);
					panel.add(opa);
					panel.setLayer(opa, new Integer(2));
				}
			}
		});

		JLabel lblNewLabel_2 = new JLabel(Startup.window.uif.name);
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_2.setForeground(Color.white);
		lblNewLabel_2.setBounds(629, 5, 218, 25);
		panel_2.add(lblNewLabel_2);

		btnNewButton_1 = new HomeButton();
		;
		btnNewButton_1.setToolTipText("返回大厅");
		panel_2.add(btnNewButton_1);

		panel_5 = new BodyPanel();
		panel_5.setBounds(5, 107, 847, 381);
		panel.add(panel_5);

		panel_6 = new JPanel();
		panel_6.setBounds(1, 488, 856, 57);
		panel.add(panel_6);
		panel_6.setLayout(null);
		panel_6.setBackground(bg);

		JPanel panel_8 = new JPanel();
		panel_8.setBounds(1, 33, 4, 455);
		panel_8.setBackground(bg);
		panel_8.setLayout(null);
		panel.add(panel_8);

		JPanel panel_9 = new JPanel();
		panel_9.setBounds(852, 33, 5, 455);
		panel_9.setBackground(bg);
		panel_9.setLayout(null);
		panel.add(panel_9);

		friend = new FriendButton();
		friend.setBounds(790, 17, 50, 35);
		friend.setToolTipText("显示您的好友列表");
		panel_6.add(friend);

		JButton selfinfobutton = new SelfInfoButton();
		selfinfobutton.setBounds(800, 42, 26, 26);
		selfinfobutton.setToolTipText("查看您的资料");
		panel_2.add(selfinfobutton);

		afb = new AddFriendButton();
		afb.setToolTipText("添加好友");
		afb.setBounds(752, 17, 35, 35);
		panel_6.add(afb);

		mb = new MessageButton();
		mb.setToolTipText("查看未读消息");
		mb.setBounds(705, 17, 40, 35);
		panel_6.add(mb);

		JButton settings = new SettingButton();
		settings.setBounds(760, 42, 30, 30);
		settings.setToolTipText("查看您的设置");
		panel_2.add(settings);

		JButton rank = new RankButton();
		rank.setBounds(718, 40, 30, 30);
		rank.setToolTipText("查看排行榜");
		panel_2.add(rank);

	}

	// 重写面板边界类
	public class PanelBorder implements Border {
		public void paintBorder(Component c, Graphics g, int x, int y, int w,
				int h) {
			g.setColor(Color.gray);
			g.drawRect(0, 0, c.getWidth() - 1, c.getHeight() - 1);
		}

		public Insets getBorderInsets(Component c) {
			return new Insets(1, 1, 1, 1);
		}

		public boolean isBorderOpaque() {
			return true;
		}
	}

	// 重写头像面板类
	public class PhotoPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			g.setColor(new Color(0, 0, 0, 0));
			g.fillRect(0, 0, getWidth(), getHeight());

			Image i0 = new ImageIcon(
					PhotoSelector.class.getResource("/img/0.jpg")).getImage();
			Image i1 = new ImageIcon(
					PhotoSelector.class.getResource("/img/1.jpg")).getImage();
			Image i2 = new ImageIcon(
					PhotoSelector.class.getResource("/img/2.jpg")).getImage();
			Image i3 = new ImageIcon(
					PhotoSelector.class.getResource("/img/3.jpg")).getImage();
			Image i4 = new ImageIcon(
					PhotoSelector.class.getResource("/img/4.jpg")).getImage();
			Image i5 = new ImageIcon(
					PhotoSelector.class.getResource("/img/5.jpg")).getImage();
			Image i6 = new ImageIcon(
					PhotoSelector.class.getResource("/img/6.jpg")).getImage();
			switch (photo_id) {
			case 0:
				g.drawImage(i0, 0, 0, 45, 45, this);
				break;
			case 1:
				g.drawImage(i1, 0, 0, 45, 45, this);
				break;
			case 2:
				g.drawImage(i2, 0, 0, 45, 45, this);
				break;
			case 3:
				g.drawImage(i3, 0, 0, 45, 45, this);
				break;
			case 4:
				g.drawImage(i4, 0, 0, 45, 45, this);
				break;
			case 5:
				g.drawImage(i5, 0, 0, 45, 45, this);
				break;
			case 6:
				g.drawImage(i6, 0, 0, 45, 45, this);
				break;
			}
		}
	}

	// 重写头像面板边界类
	public class PhotoPanelBorder implements Border {
		public void paintBorder(Component c, Graphics g, int x, int y, int w,
				int h) {
			g.setColor(Color.gray);
			g.drawRect(0, 0, c.getWidth() - 1, c.getHeight() - 1);
		}

		public Insets getBorderInsets(Component c) {
			return new Insets(1, 1, 1, 1);
		}

		public boolean isBorderOpaque() {
			return true;
		}
	}

	// 重写开始按钮类
	public class PlayButton extends JButton {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			if (!clicked && !entered) {
				Image image = new ImageIcon(
						MainFrame.class.getResource("/img/play.png"))
						.getImage();
				g.drawImage(image, 0, 0, this);
			} else if (entered && clicked) {
				Image image = new ImageIcon(
						MainFrame.class.getResource("/img/play2.png"))
						.getImage();
				g.drawImage(image, 0, 0, this);
			} else if (entered && !clicked) {
				Image image = new ImageIcon(
						MainFrame.class.getResource("/img/play3.png"))
						.getImage();
				g.drawImage(image, 0, 0, this);
			}
		}

		public JToolTip createToolTip() {
			JToolTip tip = new MyToolTip();
			return tip;
		}
	}

	// 重写主界面按钮类
	public class HomeButton extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private boolean isIn;

		public HomeButton() {
			setBorder(null);
			setBounds(26, 10, 195, 54);
			setOpaque(false);
			setContentAreaFilled(false);
			addMouseListener(new MouseListener() {

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
					if (msp != null) {
						panel.remove(msp);
						msp = null;
						panel_5 = new BodyPanel();
						panel_5.setBounds(5, 107, 847, 381);
						panel.add(panel_5);
						btnNewButton.setEnabled(true);
						clicked = false;
						entered = false;
						panel.repaint();
					} else if (panel_user != null) {
						panel.remove(panel_user);
						panel_user = null;
						panel_5 = new BodyPanel();
						panel_5.setBounds(5, 107, 847, 381);
						panel.add(panel_5);
						btnNewButton.setEnabled(true);
						clicked = false;
						entered = false;
						panel.repaint();
					} else if (rankpanel != null) {
						panel.remove(rankpanel);
						rankpanel = null;
						panel_5 = new BodyPanel();
						panel_5.setBounds(5, 107, 847, 381);
						panel.add(panel_5);
						btnNewButton.setEnabled(true);
						clicked = false;
						entered = false;
						panel.repaint();
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					isIn = true;
					frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					isIn = false;
					frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

			});
		}

		public JToolTip createToolTip() {
			JToolTip tip = new MyToolTip();
			return tip;
		}

		public void paintComponent(Graphics g) {
			if (isIn) {
				g.drawImage(
						new ImageIcon(MainFrame.class
								.getResource("/img/HomeButton2.png"))
								.getImage(), 0, -14, this);
			} else {
				g.drawImage(
						new ImageIcon(MainFrame.class
								.getResource("/img/HomeButton.png")).getImage(),
						0, -14, this);
			}
		}

	}

	public class SelfInfoButton extends JButton {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SelfInfoButton() {
			setBorder(null);
			setContentAreaFilled(false);
			addMouseListener(new MouseListener() {

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
					Startup.net.sendMessage(new ShowUserInfoMessage(
							Startup.window.uif.name));
					Startup.window.uif = (User_info) (Startup.net
							.getMessage(-30000));
					UserInfoPanel u = new UserInfoPanel();

					try {
						panel_user = u.userPanel(
								Startup.window.uif.daily_game_numbers,
								Startup.window.uif.daily_average_scores,
								Startup.window.uif.per_game_scores,
								Startup.window.uif.max_combo_number,
								Startup.window.uif.game_number,
								Startup.window.uif.average_score,
								Startup.window.uif.max_score);
						panel_user.setLocation(5, 107);
					} catch (FontFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (panel_5 != null) {
						panel.remove(panel_5);
						panel.add(panel_user);
						panel_5 = null;
						panel.repaint();
					} else if (msp != null) {
						panel.remove(msp);
						panel.add(panel_user);
						msp = null;
						panel.repaint();
					} else if (rankpanel != null) {
						panel.remove(rankpanel);
						panel.add(panel_user);
						rankpanel = null;
						panel.repaint();
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
		}

		public void paintComponent(Graphics g) {
			Image image = new ImageIcon(
					MainFrame.class.getResource("/img/selfinfo.png"))
					.getImage();
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}

		public JToolTip createToolTip() {
			return new MyToolTip();
		}
	}

	public class SettingButton extends JButton {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SettingButton() {
			setBorder(null);
			setContentAreaFilled(false);
			addMouseListener(new MouseListener() {

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
					new SettingsFrame();
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
		}

		public void paintComponent(Graphics g) {
			Image image = new ImageIcon(
					MainFrame.class.getResource("/img/settings.png"))
					.getImage();
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}

		public JToolTip createToolTip() {
			return new MyToolTip();
		}

	}

	public class QueryRequestThread extends Thread {

		public void run() {
			while (true) {
				synchronized (lock) {
					lock.notifyAll();
					if (mp == null) { // 在用户没有打开消息面板时进行查询
						Startup.net.sendMessage(new QueryFriendRequestMessage(
								Startup.window.uif.name));
						QueryFriendRequestBackMessage qfrbm = (QueryFriendRequestBackMessage) Startup.net
								.getMessage(-104);

						while (qfrbm == null) {
							qfrbm = (QueryFriendRequestBackMessage) Startup.net
									.getMessage(-104);
							try {
								Thread.sleep(50);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						Startup.net
								.sendMessage(new QueryCooperateRequestMessage(
										Startup.window.uif.name));
						QueryCooperateRequestBackMessage qcrbm = (QueryCooperateRequestBackMessage) Startup.net
								.getMessage(-204);

						while (qcrbm == null) {
							qcrbm = (QueryCooperateRequestBackMessage) Startup.net
									.getMessage(-204);
							try {
								Thread.sleep(50);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						if (qfrbm.friends.get(0).equals(""))
							qfrbm.friends.remove(0);

						if (qcrbm.info.get(0).equals(""))
							qcrbm.info.remove(0);

						if (qfrbm.friends.size() + qcrbm.info.size() >= 1)
							newMessage = true;
						else
							newMessage = false;
					}
					panel.repaint();
					try {
						Thread.sleep(300);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public class UpdateFriendsThread extends Thread {

		public void run() {
			while (true) {
				synchronized (lock) {
					lock.notifyAll();
					Startup.net.sendMessage(new QueryNeedToChange(
							Startup.window.uif.name));
					QueryNeedToChangeBackMessage qntcbm = (QueryNeedToChangeBackMessage) Startup.net
							.getMessage(300000);
					while (qntcbm == null) {
						qntcbm = (QueryNeedToChangeBackMessage) Startup.net
								.getMessage(300000);
						try {
							Thread.sleep(50);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (qntcbm != null && fl != null) {
						if (qntcbm.whether_need) {
							// 收到好友的更新UI提醒，更新FriendList
							fl.on.updateInfo();
							fl.off.updateInfo();
							fl.repaint();
							Startup.net.sendMessage(new SetFalseMessage(
									Startup.window.uif.name));
						}
					}
					try {
						Thread.sleep(300);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public class RankButton extends JButton {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public RankButton() {
			setBorder(null);
			setContentAreaFilled(false);
			addMouseListener(new MouseListener() {

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
					RankUI rank = new RankUI();
					try {
						rankpanel = rank.rankPanel();
						rankpanel.setLocation(5, 107);
					} catch (FontFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (panel_5 != null) {
						panel.remove(panel_5);
						panel.add(rankpanel);
						panel_5 = null;
						panel.repaint();
					} else if (msp != null) {
						panel.remove(msp);
						panel.add(rankpanel);
						msp = null;
						panel.repaint();
					} else if (panel_user != null) {
						panel.remove(panel_user);
						panel.add(rankpanel);
						panel_user = null;
						panel.repaint();
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
		}

		public void paintComponent(Graphics g) {
			Image image = new ImageIcon(
					MainFrame.class.getResource("/img/rank.png")).getImage();
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}

		public JToolTip createToolTip() {
			return new MyToolTip();
		}

	}
}

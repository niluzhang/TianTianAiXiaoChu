package presentation.mainui;

import gamemessage.CorpGameStartMessage;
import gamemessage.PkGameStartMessage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import presentation.gameui.collaborationGameGUI;
import presentation.mainui.MessageLabel.GetCorpGameStartMessage;
import presentation_singleGameGUI.singleGameGUI;

import main.Startup;
import message.*;

public class ModeSelectionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ButtonLabel lblNewLabel, lblNewLabel_1, lblNewLabel_2;
	private JPanel panel_2, panel_3;
	private JLabel nameLabel11 = new JLabel("");
	private JLabel nameLabel12 = new JLabel("");
	private JButton corbutton = new JButton("发出邀请");
	private JButton pkbutton = new JButton("发出邀请");
	private chooseLabel tool1 = new chooseLabel("加强连击");
	private chooseLabel tool2 = new chooseLabel("得分UP");
	private chooseLabel tool3 = new chooseLabel("加强消除提示");
	private JButton corstartButton = new JButton("开始游戏");
	private JButton pkstartButton = new JButton("开始游戏");
	private ArrayList<String> friends = new ArrayList<String>();
	private JLabel infoLabel = new JLabel("");
	private String pkFriend = "";
	private boolean friendToolC = false;
	private boolean friendToolD = false;
	private boolean friendToolE = false;

	public ModeSelectionPanel() throws FontFormatException, IOException {
		setSize(847, 381);
		setLayout(null);

		nameLabel11.setBounds(200, 200, 100, 40);
		nameLabel11.setForeground(Color.black);
		add(nameLabel11);

		nameLabel12.setBounds(300, 200, 200, 40);
		nameLabel12.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		nameLabel12.setForeground(Color.black);
		add(nameLabel12);

		infoLabel.setBounds(200, 250, 200, 40);
		infoLabel.setFont(new Font("迷你简太极", Font.PLAIN, 20));
		infoLabel.setForeground(Color.black);
		add(infoLabel);
		// 道具选择

		tool1.setBounds(200, 330, 120, 40);
		tool1.setFont(new Font("文鼎CS舒同体", Font.PLAIN, 20));
		add(tool1);
		Image image1 = new ImageIcon(ModeSelectionPanel.class.getResource("/bin/prop2.png")).getImage();
		JLabel l1 = new aLabel(image1, 40, 40);
		l1.setBounds(160, 330, 40, 40);
		add(l1);

		tool2.setBounds(400, 330, 120, 40);
		tool2.setFont(new Font("文鼎CS舒同体", Font.PLAIN, 20));
		add(tool2);
		Image image2 = new ImageIcon(ModeSelectionPanel.class.getResource("/bin/prop3.png")).getImage();
		JLabel l2 = new aLabel(image2, 40, 40);
		l2.setBounds(360, 330, 40, 40);
		add(l2);

		tool3.setBounds(600, 330, 120, 40);
		tool3.setFont(new Font("文鼎CS舒同体", Font.PLAIN, 20));
		add(tool3);
		Image image3 = new ImageIcon(ModeSelectionPanel.class.getResource("/bin/prop1.png")).getImage();
		JLabel l3 = new aLabel(image3, 40, 40);
		l3.setBounds(560, 330, 40, 40);
		add(l3);

		tool1.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tool1.changeChooseState();
				if (tool1.getChooseState() == true) {
					tool1.setForeground(new Color(250, 234, 31));
				} else {
					tool1.setForeground(Color.black);
				}
				repaint();
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
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		});
		tool2.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tool2.changeChooseState();
				if (tool2.getChooseState() == true) {
					tool2.setForeground(new Color(250, 234, 31));
				} else {
					tool2.setForeground(Color.black);
				}
				repaint();
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
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		});
		tool3.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tool3.changeChooseState();
				if (tool3.getChooseState() == true) {
					tool3.setForeground(new Color(250, 234, 31));
				} else {
					tool3.setForeground(Color.black);
				}
				repaint();
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
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		});
		//
		final InnerPanel panel = new InnerPanel(new Color(7, 20, 36));
		panel.setBounds(187, 10, 471, 161);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setSize(150, 155);
		panel_1.setLocation(3, 3);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0, 0, 0, 0));
		panel.add(panel_1);

		panel_2 = new InnerPanel(new Color(1, 6, 12));
		panel_2.setLocation(157, 3);
		panel_2.setSize(150, 155);
		panel.add(panel_2);

		panel_3 = new InnerPanel(new Color(1, 6, 12));
		panel_3.setLocation(314, 3);
		panel_3.setSize(150, 155);
		panel_3.setLayout(null);
		panel.add(panel_3);

		corbutton.setBounds(550, 200, 200, 40);
		corbutton.setFont(new Font("迷你简太极", Font.PLAIN, 20));
		corbutton.setBackground(new Color(126, 234, 239, 122));
		corbutton.setOpaque(false);
		corbutton.setForeground(Color.black);
		corbutton.setBorder(null);
		corbutton.addMouseListener(new MouseListener() {

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
				remove(infoLabel);
				infoLabel.setText("发送邀请中...");
				add(infoLabel);
				repaint();
				new CoThread().start();
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

		corstartButton.setBounds(550, 200, 200, 40);
		corstartButton.setFont(new Font("迷你简太极", Font.PLAIN, 20));
		corstartButton.setBackground(new Color(126, 234, 239, 122));
		corstartButton.setOpaque(false);
		corstartButton.setForeground(Color.black);
		corstartButton.setBorder(null);
		corstartButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				CorpGameStartMessage m1 = new CorpGameStartMessage();
				m1.UseID = friends;
				if (tool1.chooseState) {
					m1.UseTool_C = true;
				} else {
					m1.UseTool_C = false;
				}
				if (tool2.chooseState) {
					m1.UseTool_D = true;
				} else {
					m1.UseTool_D = false;
				}
				if (tool3.chooseState) {
					m1.UseTool_E = true;
				} else {
					m1.UseTool_E = false;
				}
				Startup.net.sendMessage(m1);
				Startup.window.mainframe.frame.dispose();
				try {
					collaborationGameGUI.colgamemain(Startup.window.uif.name,
							"房主", tool1.chooseState, tool2.chooseState,
							tool3.chooseState);
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

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
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		});

		pkbutton.setBounds(550, 200, 200, 40);
		pkbutton.setFont(new Font("迷你简太极", Font.PLAIN, 20));
		pkbutton.setBackground(new Color(126, 234, 239, 122));
		pkbutton.setOpaque(false);
		pkbutton.setForeground(Color.black);
		pkbutton.setBorder(null);
		pkbutton.addMouseListener(new MouseListener() {

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
				remove(infoLabel);
				infoLabel.setText("发送邀请中...");
				add(infoLabel);
				repaint();
				new PKThread().start();
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

		pkstartButton.setBounds(550, 200, 200, 40);
		pkstartButton.setFont(new Font("迷你简太极", Font.PLAIN, 20));
		pkstartButton.setBackground(new Color(126, 234, 239, 122));
		pkstartButton.setOpaque(false);
		pkstartButton.setForeground(Color.black);
		pkstartButton.setBorder(null);
		pkstartButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				PkGameStartMessage m1 = new PkGameStartMessage();
				m1.UserID1 = Startup.window.uif.name;
				m1.UserID2 = pkFriend;
				if (tool1.chooseState) {
					m1.UseTool_C1 = true;
				} else {
					m1.UseTool_C1 = false;
				}
				if (tool2.chooseState) {
					m1.UseTool_D1 = true;
				} else {
					m1.UseTool_D1 = false;
				}
				if (tool3.chooseState) {
					m1.UseTool_E1 = true;
				} else {
					m1.UseTool_E1 = false;
				}
				
				if (friendToolC) {
					m1.UseTool_C2 = true;
				} else {
					m1.UseTool_C2 = false;
				}
				if (friendToolD) {
					m1.UseTool_D2 = true;
				} else {
					m1.UseTool_D2 = false;
				}
				if (friendToolE) {
					m1.UseTool_E2 = true;
				} else {
					m1.UseTool_E2 = false;
				}
				Startup.net.sendMessage(m1);
				Startup.window.mainframe.frame.dispose();
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
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
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		});
		
		lblNewLabel = new ButtonLabel("单机模式");
		lblNewLabel.setBounds(4, 4, 145, 47);
		lblNewLabel.addMouseListener(new MouseListener() {

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
				lblNewLabel.selected = true;
				lblNewLabel_1.selected = false;
				lblNewLabel_2.selected = false;
				remove(nameLabel11);
				remove(nameLabel12);
				remove(infoLabel);
				panel_2.removeAll();
				panel_3.removeAll();
				remove(corbutton);
				remove(corstartButton);
				remove(pkbutton);
				remove(pkstartButton);
				final JLabel l = new JLabel("   开始游戏");
				l.setForeground(Color.white);
				l.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				l.setBounds(5, 5, 140, 45);
				l.addMouseListener(new MouseListener() {

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
						synchronized (Startup.window.mainframe.lock) {
							Startup.window.mainframe.frame.dispose();
							Startup.window.mainframe.uft.stop();
							Startup.window.mainframe.qrt.stop();
							singleGameGUI singlegame = new singleGameGUI();
							singlegame.singlenamestart(Startup.window.uif.name,
									tool1.chooseState, tool2.chooseState,
									tool3.chooseState);
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						setCursor(new Cursor(Cursor.HAND_CURSOR));
						l.setOpaque(true);
						l.setBackground(new Color(35, 82, 66));
						repaint();
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						l.setOpaque(false);
						repaint();
					}

				});
				panel_2.add(l);
				repaint();
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
		panel_1.add(lblNewLabel);

		lblNewLabel_1 = new ButtonLabel("协作模式");
		lblNewLabel_1.setBounds(4, 56, 145, 47);
		lblNewLabel_1.addMouseListener(new MouseListener() {

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
				lblNewLabel.selected = false;
				lblNewLabel_1.selected = true;
				lblNewLabel_2.selected = false;
				panel_2.removeAll();
				panel_3.removeAll();
				remove(nameLabel11);
				remove(nameLabel12);
				remove(infoLabel);
				remove(corbutton);
				remove(corstartButton);
				remove(pkbutton);
				remove(pkstartButton);
				final JLabel l = new JLabel("   邀请协作");
				l.setForeground(Color.white);
				l.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				l.setBounds(5, 5, 140, 45);
				l.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub

						/*
						 * ArrayList<String> data = new ArrayList<String>();
						 * String[] Data = { "3#2014-03-02", "6#2014-03-03",
						 * "8#2014-03-04", "3#2014-03-05", "10#2014-03-06",
						 * "7#2014-03-07", "6#2014-03-08" }; data.add(Data[0]);
						 * data.add(Data[1]); data.add(Data[2]);
						 * data.add(Data[3]);
						 */

						ShowOnlineFriendMessage2 m1 = new ShowOnlineFriendMessage2(
								Startup.window.uif.name);
						Startup.net.sendMessage(m1);
						ShowOnlineFriendBackMessage2 sofbm = (ShowOnlineFriendBackMessage2) Startup.net
								.getMessage(-1234567);
						while (sofbm == null)
							sofbm = (ShowOnlineFriendBackMessage2) Startup.net
									.getMessage(-1234567);

						ArrayList<String> data = sofbm.online_friends;

						int height = 0;
						JPanel friendPanel = new JPanel();
						friendPanel.setPreferredSize(new Dimension(150, data
								.size() * 30));
						friendPanel.setLayout(null);

						for (int i = 0; i < data.size(); i++) {
							final JLabel idLabel = new JLabel(data.get(i));
							idLabel.setBounds(20, height, 140, 30);
							idLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
							idLabel.setForeground(Color.white);
							height = height + 30;
							idLabel.addMouseListener(new MouseListener() {

								@Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									if (!idLabel.getText().equals("")) {
										remove(nameLabel11);
										remove(nameLabel12);

										nameLabel11.setText("好友名称：");
										nameLabel12.setText(idLabel.getText());
										nameLabel11.setFont(new Font("迷你简太极",Font.PLAIN,20));
										friends.add(nameLabel12.getText());
										add(nameLabel11);
										add(nameLabel12);

										add(corbutton);

										repaint();
									}

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
									setCursor(new Cursor(Cursor.HAND_CURSOR));
									repaint();
								}

								@Override
								public void mouseExited(MouseEvent e) {
									setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
									repaint();
								}

							});
							friendPanel.add(idLabel);

						}
						friendPanel.setVisible(true);
						friendPanel.setOpaque(false);
						JScrollPane panel7 = new JScrollPane(friendPanel);
						panel7.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						panel7.getViewport().setOpaque(false);// 将JScrollPane设置为透明
						panel7.setOpaque(false);// 将中间的viewport设置为透明
						panel7.setBounds(0, 0, 150, 155);
						panel7.setBorder(null);
						JScrollBar bar = panel7.getVerticalScrollBar();
						bar.setBackground(Color.black);

						panel_3.add(panel7);
						repaint();
						// Test.frame.setVisible(true);
						Startup.window.mainframe.frame.setVisible(true);
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
						setCursor(new Cursor(Cursor.HAND_CURSOR));
						l.setOpaque(true);
						l.setBackground(new Color(35, 82, 66));
						repaint();
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						l.setOpaque(false);
						repaint();
					}

				});
				panel_2.add(l);
				repaint();
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
		panel_1.add(lblNewLabel_1);

		lblNewLabel_2 = new ButtonLabel("对战模式");
		lblNewLabel_2.setBounds(4, 108, 145, 47);
		lblNewLabel_2.addMouseListener(new MouseListener() {

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
				lblNewLabel.selected = false;
				lblNewLabel_1.selected = false;
				lblNewLabel_2.selected = true;
				panel_2.removeAll();
				panel_3.removeAll();
				remove(nameLabel11);
				remove(nameLabel12);
				remove(infoLabel);
				remove(corbutton);
				remove(corstartButton);
				remove(pkbutton);
				remove(pkstartButton);
				final JLabel l = new JLabel("   邀请PK");
				l.setForeground(Color.white);
				l.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				l.setBounds(5, 5, 140, 45);
				l.addMouseListener(new MouseListener() {

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
						/*
						 * ArrayList<String> data = new ArrayList<String>();
						 * String[] Data = { "3#2014-03-02", "6#2014-03-03",
						 * "8#2014-03-04", "3#2014-03-05", "10#2014-03-06",
						 * "7#2014-03-07", "6#2014-03-08" }; data.add(Data[0]);
						 * data.add(Data[1]); data.add(Data[2]);
						 * data.add(Data[3]);
						 */

						ShowOnlineFriendMessage2 m1 = new ShowOnlineFriendMessage2(
								Startup.window.uif.name);
						Startup.net.sendMessage(m1);
						ShowOnlineFriendBackMessage2 sofbm = (ShowOnlineFriendBackMessage2) Startup.net
								.getMessage(-1234567);
						while (sofbm == null)
							sofbm = (ShowOnlineFriendBackMessage2) Startup.net
									.getMessage(-1234567);

						ArrayList<String> data = sofbm.online_friends;

						int height = 0;
						JPanel friendPanel = new JPanel();
						friendPanel.setPreferredSize(new Dimension(150, data
								.size() * 30));
						friendPanel.setLayout(null);

						for (int i = 0; i < data.size(); i++) {
							final JLabel idLabel = new JLabel(data.get(i));
							idLabel.setBounds(20, height, 140, 30);
							idLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
							idLabel.setForeground(Color.white);
							height = height + 30;
							idLabel.addMouseListener(new MouseListener() {

								@Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									if (!idLabel.getText().equals("")) {
										remove(nameLabel11);
										remove(nameLabel12);

										nameLabel11.setText("好友名称：");
										nameLabel12.setText(idLabel.getText());
										nameLabel11.setFont(new Font("迷你简太极",Font.PLAIN,20));
										pkFriend = nameLabel12.getText();
										add(nameLabel11);
										add(nameLabel12);

										add(pkbutton);

										repaint();
									}

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
									setCursor(new Cursor(Cursor.HAND_CURSOR));
									repaint();
								}

								@Override
								public void mouseExited(MouseEvent e) {
									setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
									repaint();
								}

							});
							friendPanel.add(idLabel);

						}
						friendPanel.setVisible(true);
						friendPanel.setOpaque(false);
						JScrollPane panel7 = new JScrollPane(friendPanel);
						panel7.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						panel7.getViewport().setOpaque(false);// 将JScrollPane设置为透明
						panel7.setOpaque(false);// 将中间的viewport设置为透明
						panel7.setBounds(0, 0, 150, 155);
						panel7.setBorder(null);
						JScrollBar bar = panel7.getVerticalScrollBar();
						bar.setBackground(Color.black);

						panel_3.add(panel7);
						repaint();
						// Test.frame.setVisible(true);
						Startup.window.mainframe.frame.setVisible(true);
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						setCursor(new Cursor(Cursor.HAND_CURSOR));
						l.setOpaque(true);
						l.setBackground(new Color(35, 82, 66));
						repaint();
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						l.setOpaque(false);
						repaint();
					}

				});
				panel_2.add(l);
				repaint();
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
		panel_1.add(lblNewLabel_2);

	}

	public void paintComponent(Graphics g) {
		// Image image = new ImageIcon("src/bin/605372.jpg").getImage();
		Image image = new ImageIcon(
				MainFrame.class.getResource("/img/body2.jpg")).getImage();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}

	public class InnerPanel extends JPanel {

		Color color;

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public InnerPanel(Color c) {
			color = c;
			setLayout(null);
			setBorder(new Border() {

				@Override
				public void paintBorder(Component c, Graphics g, int x, int y,
						int width, int height) {
					// TODO Auto-generated method stub
					g.setColor(new Color(58, 69, 87));
					g.drawRoundRect(0, 0, width - 1, height - 1, 5, 5);
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
		}

		public void paintComponent(Graphics g) {
			g.setColor(color);
			g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 5, 5);
		}

	}


	public class chooseLabel extends JLabel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public boolean chooseState = false;
		public String name;

		public chooseLabel(String name) {
			this.name = name;
			setText(name);
		}

		public boolean getChooseState() {
			return chooseState;
		}

		public void changeChooseState() {
			if (chooseState == false) {
				chooseState = true;
			} else {
				chooseState = false;
			}
		}

	}

	private class aLabel extends JLabel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Image image;
		private int x;
		private int y;

		public aLabel(final Image image, int x, int y) {
			this.image = image;
			this.x = x;
			this.y = y;
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(image, 0, 0, x, y, null);
		}
	}

	public class CoThread extends Thread {
		public void run() {
			CooperateRequestMessage m1 = new CooperateRequestMessage(
					Startup.window.uif.name, friends);
			Startup.net.sendMessage(m1);
			PartenerBackMessage pbm = (PartenerBackMessage) (Startup.net
					.getMessage(-170000));
			while (pbm == null){
				pbm = (PartenerBackMessage) (Startup.net.getMessage(-170000));
				try{
					Thread.sleep(1000);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			switch (pbm.back) {
			case "no":
				// 拒绝提示
				remove(infoLabel);
				infoLabel.setText("对方拒绝邀请");
				add(infoLabel);
				break;
			case "ok":
				//
				remove(infoLabel);
				infoLabel.setText("对方准备完毕");
				add(infoLabel);
				remove(corbutton);
				add(corstartButton);
				repaint();
				break;
			}
		}
	}
	
	public class PKThread extends Thread {
		public void run() {
			SendPKInvitationMessage m1 = new SendPKInvitationMessage(
					Startup.window.uif.name, pkFriend);
			Startup.net.sendMessage(m1);
			SendPKInvitationBackMessage pbm = (SendPKInvitationBackMessage) (Startup.net
					.getMessage(-1111));
			while (pbm == null){
				pbm = (SendPKInvitationBackMessage) (Startup.net.getMessage(-1111));
				try{
					Thread.sleep(1000);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			switch (pbm.back_info) {
			case "no":
				// 拒绝提示
				remove(infoLabel);
				infoLabel.setText("对方拒绝邀请");
				add(infoLabel);
				break;
			case "ok":
				//
				remove(infoLabel);
				infoLabel.setText("等待对方选择道具...");
				add(infoLabel);
				remove(pkbutton);
				//add(pkstartButton);
				repaint();
				break;
			}
			
			SendPKInvitationToolBackMessage spitb = (SendPKInvitationToolBackMessage) (Startup.net
					.getMessage(-1112));
			while (spitb == null){
				spitb = (SendPKInvitationToolBackMessage) (Startup.net.getMessage(-1112));
				try{
					Thread.sleep(1000);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			friendToolC = spitb.Tool_C;
			friendToolD = spitb.Tool_D;
			friendToolE = spitb.Tool_E;
			remove(infoLabel);
			infoLabel.setText("对方准备完毕");
			add(infoLabel);
			//remove(pkbutton);
			add(pkstartButton);
			repaint();
		}
	}

}


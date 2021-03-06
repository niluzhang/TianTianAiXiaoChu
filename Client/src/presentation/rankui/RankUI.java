package presentation.rankui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import main.Startup;
import message.GetRankListBackMessage;
import message.GetRankListMessage;
import message.SendPKInvitationBackMessage;

public class RankUI {

	private JPanel rankPanel;
	private int totalPage = 0;
	private int currentPage = 0;


	public JPanel rankPanel() throws FontFormatException, IOException {
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 847,381);
		panel.setOpaque(false);
		panel.setLayout(null);

		Image image0 = new ImageIcon(RankUI.class.getResource("/bin/paper.png")).getImage();
		JLabel l0 = new aLabel(image0, 847,381);
		l0.setBounds(0, 0, 847,381);

		Image image1 = new ImageIcon(RankUI.class.getResource("/bin/gold1.png")).getImage();
		JLabel l1 = new aLabel(image1, 300, 105);
		l1.setBounds(125, 630, 300, 105);

		Image image2 = new ImageIcon(RankUI.class.getResource("/bin/gold2.png")).getImage();
		JLabel l2 = new aLabel(image2, 100, 85);
		l2.setBounds(747, 25, 100, 85);

		Image image3 = new ImageIcon(RankUI.class.getResource("/bin/gold3.png")).getImage();
		JLabel l3 = new aLabel(image3, 300, 61);
		l3.setBounds(273, 0, 300, 61);

		Image image4 = new ImageIcon(RankUI.class.getResource("/bin/gold4.png")).getImage();
		JLabel l4 = new aLabel(image4, 100, 85);
		l4.setBounds(0, 25, 100, 85);

		Image image5 = new ImageIcon(RankUI.class.getResource("/bin/gold7.png")).getImage();
		JLabel l5 = new aLabel(image5, 100, 85);
		l5.setBounds(747, 296, 100, 85);

		Image image6 = new ImageIcon(RankUI.class.getResource("/bin/gold8.png")).getImage();
		JLabel l6 = new aLabel(image6, 100, 85);
		l6.setBounds(0, 296, 100, 85);

		JLabel title = new JLabel("���а�");
		title.setFont(new Font("�����ʵ�",Font.PLAIN,35));
		title.setForeground(new Color(249, 230, 0));
		title.setForeground(Color.red);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(273, 30, 301, 100);

		JLabel label1 = new JLabel("����");
		label1.setFont(new Font("�����̫��",Font.PLAIN,20));
		label1.setForeground(new Color(79, 87, 253));
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setBounds(150, 100, 150, 40);

		JLabel label2 = new JLabel("����");
		label2.setFont(new Font("�����̫��",Font.PLAIN,20));
		label2.setForeground(new Color(79, 87, 253));
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setBounds(350, 100, 150, 40);

		JLabel label3 = new JLabel("�÷�");
		label3.setFont(new Font("�����̫��",Font.PLAIN,20));
		label3.setForeground(new Color(79, 87, 253));
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setBounds(550, 100, 150, 40);

		setRank();
		
		panel.add(l1);
		panel.add(l2);
		panel.add(l3);
		panel.add(l4);
		panel.add(l5);
		panel.add(l6);
		panel.add(title);
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(rankPanel);
		panel.add(l0);
		panel.repaint();
		
		return panel;
	}

	private void setRank() throws FontFormatException, IOException {
		rankPanel = new JPanel();
		rankPanel.setLayout(null);
		rankPanel.setOpaque(false);
		rankPanel.setBounds(0, 140, 847, 241);

		final JPanel helpPanel = new JPanel();
		helpPanel.setBounds(0, 0, 847, 200);
		helpPanel.setLayout(null);
		helpPanel.setOpaque(false);

		GetRankListMessage g = new GetRankListMessage(Startup.window.uif.name);
		Startup.net.sendMessage(g);
		
		GetRankListBackMessage pbm = (GetRankListBackMessage) (Startup.net
				.getMessage(-777));
		ArrayList<String> data = pbm.rankList;
		

		// ����pagePanel
		if (data.size() % 5 == 0) {
			totalPage = data.size() / 5;
		} else {
			totalPage = data.size() / 5 + 1;
		}
		final ArrayList<JPanel> pagePanel = new ArrayList<JPanel>();
		for (int pagenumber = 0; pagenumber < totalPage; pagenumber++) {
			int height = 0;
			JPanel innerPanel = new JPanel();
			innerPanel.setLayout(null);
			innerPanel.setOpaque(false);
			innerPanel.setBounds(0, 0, 847, 200);
			pagePanel.add(innerPanel);
			if (pagenumber == 0) {
				int dataSize = 0;
				if (totalPage == 1) {
					dataSize = data.size();
				} else {
					dataSize = 5;
				}
				for (int q = 0; q < dataSize; q++) {
					switch (q) {
					case 0:
						Image image1 = new ImageIcon(RankUI.class.getResource("/bin/first.png"))
								.getImage();
						JLabel l1 = new aLabel(image1, 40, 40);
						l1.setBounds(205, height, 40, 40);
						innerPanel.add(l1);
						break;
					case 1:
						Image image2 = new ImageIcon(RankUI.class.getResource("/bin/second.png"))
								.getImage();
						JLabel l2 = new aLabel(image2, 40, 39);
						l2.setBounds(205, height, 40, 39);
						innerPanel.add(l2);
						break;
					case 2:
						Image image3 = new ImageIcon(RankUI.class.getResource("/bin/third.png"))
								.getImage();
						JLabel l3 = new aLabel(image3, 40, 40);
						l3.setBounds(205, height, 40, 40);
						innerPanel.add(l3);
						break;
					default:
						JLabel rankLabel = new JLabel(Integer.toString(q + 1));
						rankLabel.setBounds(150, height, 150, 40);
						rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
						rankLabel.setFont(new Font("2203",Font.PLAIN,20));
						rankLabel.setForeground(new Color(251, 112, 28));
						innerPanel.add(rankLabel);
						break;
					}
					String rank[] = data.get(q).split("#");
					JLabel nameLabel = new JLabel(rank[0]);
					nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
					JLabel scoreLabel = new JLabel(rank[1]);
					scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
					nameLabel.setBounds(350, height, 150, 40);
					nameLabel.setFont(new Font("����", Font.BOLD, 15));
					scoreLabel.setBounds(550, height, 150, 40);
					scoreLabel.setFont(new Font("22203",Font.PLAIN, 15));
					height = height + 40;

					pagePanel.get(pagenumber).add(nameLabel);
					pagePanel.get(pagenumber).add(scoreLabel);
					pagePanel.get(pagenumber).setVisible(true);
					pagePanel.get(pagenumber).repaint();
				}
			} else {
				int dataSize = 0;
				if (pagenumber < totalPage - 1) {
					dataSize = 5;
				} else {
					dataSize = data.size() % 5 ;
				}
				for (int q = 0; q < dataSize; q++) {
					JLabel rankLabel = new JLabel(Integer.toString((pagenumber+1)*5+q-4));
					rankLabel.setBounds(150, height, 150, 40);
					rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
					rankLabel.setFont(new Font("2203",Font.PLAIN,20));
					rankLabel.setForeground(new Color(251, 112, 28));
					innerPanel.add(rankLabel);

					String rank[] = data.get(pagenumber * 5 + q).split("#");
					JLabel nameLabel = new JLabel(rank[0]);
					nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
					JLabel scoreLabel = new JLabel(rank[1]);
					scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
					nameLabel.setBounds(350, height, 150, 40);
					nameLabel.setFont(new Font("����", Font.BOLD, 15));
					scoreLabel.setBounds(550, height, 150, 40);
					scoreLabel.setFont(new Font("22203",Font.PLAIN, 15));
					height = height + 40;

					pagePanel.get(pagenumber).add(nameLabel);
					pagePanel.get(pagenumber).add(scoreLabel);
					pagePanel.get(pagenumber).setVisible(true);
					pagePanel.get(pagenumber).repaint();
				}
			}
		}

		// ����ѡ��panel
		Image image1 = new ImageIcon(RankUI.class.getResource("/bin/jumpleft.png")).getImage();
		final JLabel l1 = new aLabel(image1, 40, 40);
		l1.setBounds(320, 200, 40, 40);
		Image image2 = new ImageIcon(RankUI.class.getResource("/bin/jumpright.png")).getImage();
		JLabel l2 = new aLabel(image2, 40, 40);
		l2.setBounds(491, 200, 40, 40);

		final JLabel pageLabel = new JLabel(Integer.toString(currentPage+1) + "/"
				+ Integer.toString(totalPage));
		pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pageLabel.setBounds(358, 200, 131, 40);
		l1.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (currentPage != 0) {
					helpPanel.removeAll();
					helpPanel.add(pagePanel.get(currentPage - 1));
					helpPanel.repaint();
					currentPage = currentPage - 1;
					rankPanel.remove(pageLabel);
					pageLabel.setText(Integer.toString(currentPage+1) + "/"
				+ Integer.toString(totalPage));
					rankPanel.add(pageLabel);
					rankPanel.repaint();
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
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		l2.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (currentPage != totalPage-1) {
					helpPanel.removeAll();
					helpPanel.add(pagePanel.get(currentPage + 1));
					helpPanel.repaint();
					currentPage = currentPage + 1;
					rankPanel.remove(pageLabel);
					pageLabel.setText(Integer.toString(currentPage+1) + "/"
				+ Integer.toString(totalPage));
					rankPanel.add(pageLabel);
					rankPanel.repaint();
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

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		helpPanel.add(pagePanel.get(0));
		helpPanel.repaint();
		rankPanel.add(pageLabel);
		rankPanel.add(l1);
		rankPanel.add(l2);
		rankPanel.add(helpPanel);
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

}

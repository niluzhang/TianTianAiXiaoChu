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
import message.QueryCooperateRequestBackMessage;
import message.QueryCooperateRequestMessage;
import message.QueryFriendRequestBackMessage;
import message.QueryFriendRequestMessage;

public class MessagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<MessageLabel> l1;
	JLabel mini;

	public MessagePanel() {
		setSize(200, 400);
		setLayout(null);

		mini = new JLabel("-");
		mini.setBounds(170, 0, 22, 22);
		mini.setForeground(new Color(189, 180, 123));
		mini.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		mini.addMouseListener(new MouseListener() {
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mouseReleased(MouseEvent e) {
				Startup.window.mainframe.panel
						.remove(Startup.window.mainframe.mp);
				Startup.window.mainframe.mp = null;
				Startup.window.mainframe.mb.bg = 0;
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

	}

	public void before() { // ǰ�ô���
		removeAll();
		add(mini);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 0));
		g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);

		g2.setStroke(new BasicStroke(1)); // ���ñʴ�����
		g2.setColor(new Color(84, 116, 157));
		g2.drawRoundRect(4, 4, getWidth() - 9, getHeight() - 9, 10, 10);
	}

	public void updateInfo() {
		l1 = new ArrayList<MessageLabel>();

		Startup.net.sendMessage(new QueryFriendRequestMessage(
				Startup.window.uif.name));
		QueryFriendRequestBackMessage qfrbm = (QueryFriendRequestBackMessage) Startup.net
				.getMessage(-104);
		while (qfrbm == null)
			qfrbm = (QueryFriendRequestBackMessage) Startup.net
					.getMessage(-104);
		for (int i = 0; i <= qfrbm.friends.size() - 1; i++) {
			if (!qfrbm.friends.get(i).equals(""))
				l1.add(new MessageLabel(qfrbm.friends.get(i) + " ����������Ϊ����"));
		}

		Startup.net.sendMessage(new QueryCooperateRequestMessage(
				Startup.window.uif.name));
		QueryCooperateRequestBackMessage qcrbm = (QueryCooperateRequestBackMessage) Startup.net
				.getMessage(-204);
		while (qcrbm == null)
			qcrbm = (QueryCooperateRequestBackMessage) Startup.net
					.getMessage(-204);
		for (int i = 0; i <= qcrbm.info.size() - 1; i++) {
			if (!qcrbm.info.get(i).equals("")){
				l1.add(new MessageLabel(qcrbm.info.get(i) + " ������Э����Ϸ"));
			}
		}

		for (int i = 0; i <= l1.size() - 1; i++) {
			l1.get(i).setLocation(10, 25 + 30 * i);
			add(l1.get(i));
		}

	}

}

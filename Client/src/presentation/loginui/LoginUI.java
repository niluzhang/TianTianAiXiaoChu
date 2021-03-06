package presentation.loginui;

import game.User_info;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import presentation.mainui.GrayRectBorder;
import presentation.mainui.MainFrame;
import presentation.mainui.MyLabel;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.border.Border;

import main.Startup;
import message.IsLoginBackMessage;
import message.LoginBackMessage;
import message.LoginMessage;
import message.ShowUserInfoMessage;
import message.TellOthers;
import message.IsLogin;

import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.net.URL;

public class LoginUI {

	public JFrame frame;
	private JLayeredPane panel;
	private JPanel panel_6;
	private Color bg = new Color(21, 160, 245);
	private int frameFirstX, frameFirstY;
	public JTextField textField;
	public JPasswordField passwordField;
	private JLabel lblNewLabel_3, lblNewLabel_4, lblNewLabel_7, lblNewLabel_8;
	private JButton btnNewButton_1;
	public JCheckBox checkBox;
	public ExtensionPanel ext;

	public MainFrame mainframe;
	public User_info uif;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */

	public LoginUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 100, 874, 581);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0, 0, 0, 0));
		frame.setVisible(true);
		frame.setIconImage(new ImageIcon(LoginUI.class
				.getResource("/bin/title.jpg")).getImage());

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
				boolean in1 = e.getX() >= lblNewLabel_7.getX()
						&& e.getX() <= (lblNewLabel_7.getX() + lblNewLabel_7
								.getWidth())
						&& e.getY() >= lblNewLabel_7.getY()
						&& e.getY() <= (lblNewLabel_7.getY() + lblNewLabel_7
								.getHeight());
				boolean in2 = e.getX() >= lblNewLabel_8.getX()
						&& e.getX() <= (lblNewLabel_8.getX() + lblNewLabel_8
								.getWidth())
						&& e.getY() >= lblNewLabel_8.getY()
						&& e.getY() <= (lblNewLabel_8.getY() + lblNewLabel_8
								.getHeight());
				if (in1) {
					if (ext != null) {
						panel.remove(ext);
						ext = null;
					}
					lblNewLabel_7.setBackground(new Color(12, 21, 28));
					lblNewLabel_8.setBackground(new Color(0, 0, 0, 0));
					ext = new ExtensionPanel();
					ext.addItem("    ע���ʺ�");
					ext.addItem("    �޸�����");
					ext.addItem("    ��������");
					ext.setLocation(646, 43);
					panel.add(ext);
					panel.setLayer(ext, JLayeredPane.POPUP_LAYER);
					frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
					panel.repaint();
				}
				if (in2) {
					if (ext != null) {
						panel.remove(ext);
						ext = null;
					}
					lblNewLabel_7.setBackground(new Color(0, 0, 0, 0));
					lblNewLabel_8.setBackground(new Color(12, 21, 28));
					ext = new ExtensionPanel();
					ext.addItem("    ����ģʽ");
					ext.addItem("    Э��ģʽ");
					ext.setLocation(551, 43);
					panel.add(ext);
					panel.setLayer(ext, JLayeredPane.POPUP_LAYER);
					frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
					panel.repaint();
				}
				if (ext != null) {
					boolean in3 = e.getX() >= ext.getX()
							&& e.getX() <= (ext.getX() + ext.getWidth())
							&& e.getY() >= ext.getY()
							&& e.getY() <= (ext.getY() + ext.getHeight());
					if (!in3 && !in1 && !in2) {
						panel.remove(ext);
						ext = null;
						lblNewLabel_7.setBackground(new Color(0, 0, 0, 0));
						lblNewLabel_8.setBackground(new Color(0, 0, 0, 0));
						frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						panel.repaint();
					} else {
						if (in3 && e.getX() == lblNewLabel_7.getX())
							lblNewLabel_7.setBackground(new Color(12, 21, 28));
						if (in3 && e.getX() == lblNewLabel_8.getX())
							lblNewLabel_8.setBackground(new Color(12, 21, 28));
						panel.repaint();
					}
				}
			}

			public void mouseDragged(MouseEvent e) {
				int detX = e.getX() - frameFirstX;
				int detY = e.getY() - frameFirstY;
				frame.setBounds(frame.getX() + detX, frame.getY() + detY,
						frame.getWidth(), frame.getHeight());
			}
		});

		panel = new JLayeredPane();
		panel.setBounds(0, 0, 858, 543);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBorder(new GrayRectBorder());

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(1, 1, 856, 54);
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(bg);

		JButton btnNewButton = new MyButton();
		btnNewButton.setBounds(10, 0, 195, 54);
		panel_1.add(btnNewButton);

		final JLabel lblNewLabel = new MyLabel("\u00D7");
		lblNewLabel.setBounds(826, 0, 20, 29);
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 22));
		lblNewLabel.setToolTipText("�ر����찮����");
		lblNewLabel.addMouseListener(new MouseListener() {
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setForeground(new Color(240, 206, 0));
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mouseReleased(MouseEvent e) {
				System.exit(0);
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

		JLabel lblNewLabel_1 = new MyLabel("-");
		lblNewLabel_1.setBounds(803, 0, 20, 29);
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 22));
		lblNewLabel_1.setToolTipText("��С�����찮����");
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

		JLabel lblNewLabel_2 = new MyLabel("\uFF1F");
		lblNewLabel_2.setBounds(775, 2, 20, 29);
		lblNewLabel_2.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel_2.setToolTipText("������Ա");
		lblNewLabel_2.addMouseListener(new MouseListener() {
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mouseReleased(MouseEvent e) {
				new Album();
			}

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mousePressed(MouseEvent e) {

			}
		});
		panel_1.add(lblNewLabel_2);

		lblNewLabel_7 = new MyLabel("\u5E10\u53F7\u6CE8\u518C");
		lblNewLabel_7.setText("   \u5E10\u53F7\u6CE8\u518C");
		lblNewLabel_7.setBounds(645, 10, 87, 33);
		lblNewLabel_7.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		lblNewLabel_7.setForeground(Color.white);
		lblNewLabel_7.setOpaque(true);
		lblNewLabel_7.setBackground(new Color(0, 0, 0, 0));
		panel_1.add(lblNewLabel_7);

		lblNewLabel_8 = new MyLabel("\u6E38\u620F\u8BF4\u660E");
		lblNewLabel_8.setText("   \u6E38\u620F\u8BF4\u660E");
		lblNewLabel_8.setBounds(550, 10, 87, 33);
		lblNewLabel_8.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		lblNewLabel_8.setForeground(Color.white);
		lblNewLabel_8.setOpaque(true);
		lblNewLabel_8.setBackground(new Color(0, 0, 0, 0));
		panel_1.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new MyLabel("|");
		lblNewLabel_9.setForeground(Color.white);
		lblNewLabel_9.setBounds(639, 19, 10, 15);
		lblNewLabel_9.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		panel_1.add(lblNewLabel_9);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(1, 55, 5, 469);
		panel.add(panel_2);
		panel_2.setBackground(bg);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(852, 55, 5, 469);
		panel_3.setBackground(bg);
		panel.add(panel_3);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(1, 522, 856, 20);
		panel_4.setBackground(bg);
		panel.add(panel_4);

		JPanel panel_5 = new BodyPanel();
		panel_5.setBounds(6, 55, 846, 469);
		panel.add(panel_5);
		panel_5.setLayout(null);

		panel_6 = new LRPanel();
		panel_6.setBounds(50, 55, 277, 349);
		panel_5.add(panel_6);
		panel_6.setLayout(null);

		lblNewLabel_3 = new MyLabel("\u5E10\u53F7");
		lblNewLabel_3.setText("\u5E10\u53F7:");
		lblNewLabel_3.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(41, 41, 54, 23);
		lblNewLabel_3.setForeground(new Color(21, 160, 245));
		panel_6.add(lblNewLabel_3);

		textField = new MyTextField();
		textField.setBounds(41, 72, 214, 29);
		panel_6.add(textField);
		textField.setColumns(10);

		lblNewLabel_4 = new MyLabel("\u5BC6\u7801");
		lblNewLabel_4.setText("\u5BC6\u7801:");
		lblNewLabel_4.setBounds(41, 113, 54, 23);
		lblNewLabel_4.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel_4.setForeground(new Color(21, 160, 245));
		panel_6.add(lblNewLabel_4);

		passwordField = new MyPasswordField();
		passwordField.setBounds(41, 144, 212, 29);
		panel_6.add(passwordField);

		checkBox = new JCheckBox("��ס����");
		checkBox.setBounds(41, 208, 100, 30);
		checkBox.setForeground(Color.white);
		checkBox.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		checkBox.setOpaque(false);
		panel_6.add(checkBox);

		btnNewButton_1 = new JButton(new ImageIcon(
				LoginUI.class.getResource("/img/enterGame.png")));
		btnNewButton_1.setBounds(76, 261, 127, 37);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.addMouseListener(new MouseListener() {
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(MainFrame.class
						.getResource("/img/enterGame.png")));
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mouseReleased(MouseEvent e) {
				if (textField.getText().trim().equals("")
						|| passwordField.getPassword().toString().trim()
								.equals("")) {
					JOptionPane.showMessageDialog(
							null,
							"�ʺŻ�����Ϊ��",
							"",
							JOptionPane.ERROR_MESSAGE,
							new ImageIcon(LoginUI.class
									.getResource("/img/error.png")));
				} else {
					Startup.net.sendMessage(new IsLogin(textField.getText()));
					boolean is = ((IsLoginBackMessage) Startup.net
							.getMessage(-999)).is;
					if (is) {
						JOptionPane.showMessageDialog(
								null,
								"�����ʺ��Ѿ���¼",
								"",
								JOptionPane.ERROR_MESSAGE,
								new ImageIcon(LoginUI.class
										.getResource("/img/error.png")));
					} else {
						Startup.net.sendMessage(new LoginMessage(textField
								.getText(), new String(passwordField
								.getPassword())));
						LoginBackMessage lbm = (LoginBackMessage) Startup.net
								.getMessage(3);
						switch (lbm.login_back_info) {
						case "��½�ɹ�":
							frame.dispose();
							Startup.net.sendMessage(new ShowUserInfoMessage(
									textField.getText()));
							uif = (User_info) (Startup.net.getMessage(-30000));
							if (checkBox.isSelected()) {
								remember_password();
							} else {
								delete_pass_file();
							}
							Startup.net.sendMessage(new TellOthers(uif.name));
							mainframe = new MainFrame();
							break;
						case "��¼ʧ��":
							JOptionPane.showMessageDialog(
									null,
									"�������",
									"",
									JOptionPane.ERROR_MESSAGE,
									new ImageIcon(LoginUI.class
											.getResource("/img/error.png")));
							break;
						case "���û�������":
							JOptionPane.showMessageDialog(
									null,
									"�û�������",
									"",
									JOptionPane.ERROR_MESSAGE,
									new ImageIcon(LoginUI.class
											.getResource("/img/error.png")));
							break;
						}
					}
				}
			}

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(MainFrame.class
						.getResource("/img/enterGame2.png")));
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mousePressed(MouseEvent e) {

			}
		});
		panel_6.add(btnNewButton_1);

		final JLabel lblNewLabel_6 = new JLabel("�Ķ��û�Э��");
		lblNewLabel_6.setFont(new Font("΢���ź�", Font.PLAIN, 11));
		lblNewLabel_6.setForeground(new Color(93, 231, 255));
		lblNewLabel_6.setBounds(100, 311, 70, 15);
		lblNewLabel_6.addMouseListener(new MouseListener() {

			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				lblNewLabel_6.setFont(new Font("΢���ź�", Font.PLAIN, 11));
				lblNewLabel_6.setBorder(null);
			}

			public void mouseReleased(MouseEvent e) {
				try {
					URI uri = new URI("http://peng.qq.com/");
					Desktop.getDesktop().browse(uri);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblNewLabel_6.setFont(new Font("΢���ź�", Font.ITALIC, 11));
				lblNewLabel_6.setBorder(new Border() {

					@Override
					public void paintBorder(Component c, Graphics g, int x,
							int y, int width, int height) {
						// TODO Auto-generated method stub
						g.setColor(new Color(93, 231, 255));
						g.drawLine(0, height - 1, width - 1, height - 1);
					}

					@Override
					public Insets getBorderInsets(Component c) {
						// TODO Auto-generated method stub
						return new Insets(0, 0, 0, 0);
					}

					@Override
					public boolean isBorderOpaque() {
						// TODO Auto-generated method stub
						return true;
					}

				});
			}

			public void mousePressed(MouseEvent e) {

			}
		});
		panel_6.add(lblNewLabel_6);

	}

	public void remember_password() {
		File file = new File(uif.name + ".pass");
		if (!file.exists()) {
			try {
				file.createNewFile();
				String pass = new String(passwordField.getPassword());
				FileWriter fw = new FileWriter(file);
				fw.write(pass);
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void delete_pass_file() {
		File file = new File(uif.name + ".pass");
		if (file.exists()) {
			file.delete();
		}
	}

	public class MyButton extends JButton {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyButton() {
			setContentAreaFilled(false);
			setBorder(null);
			setIcon(new ImageIcon(
					MainFrame.class.getResource("/img/HomeButton.png")));
		}
	}

	public class BodyPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			URL imgurl = LoginUI.class.getResource("/img/loginuibg.jpg");
			Image img = new ImageIcon(imgurl).getImage();
			g.drawImage(img, 0, 0, this);
			// g.setColor(new Color(93, 231, 255));
			/*
			 * g.setColor(new Color(139,0,139));
			 * g.drawRoundRect(panel_6.getX()-1, panel_6.getY()-1,
			 * panel_6.getWidth(), panel_6.getHeight(),5,5);
			 */
		}
	}
}

package presentation.changepasswordui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.Startup;
import message.ChangePasswordBackMessage;
import message.ChangePasswordMessage;

public class ChangePasswordUI {
	private JFrame frame;
	private JTextField passwordField0;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private JButton ensureButton;
	private int frameFirstX, frameFirstY;
	private JLabel passwordInfo0 = new JLabel("");
	private JLabel passwordInfo1 = new JLabel("");
	private JLabel passwordInfo2 = new JLabel("");

	public ChangePasswordUI() {
		frame = new JFrame();
	}

	public void setFrame() throws FontFormatException, IOException {
		frame.setBounds(400, 170, 650, 380);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(null);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0, 0, 0, 0));

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

		final JPanel panel = new JPanel();
		panel.setBounds(0, 0, 650, 380);
		panel.setLayout(null);
		// panel.setBackground(Color.BLUE);
		panel.setOpaque(false);

		Image image = new ImageIcon(
				ChangePasswordUI.class.getResource("/bin/605372.jpg"))
				.getImage();
		JLabel l = new aLabel(image, 650, 380);
		l.setBounds(0, 0, 650, 380);

		// ��������
		// label����
		JLabel lblNewLabel_1 = new JLabel("ԭ����:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("�����ѩ��", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(60, 90, 120, 20);
		lblNewLabel_1.setForeground(new Color(113, 133, 244));
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("������:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("�����ѩ��", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(60, 160, 120, 20);
		lblNewLabel_2.setForeground(new Color(113, 133, 244));
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("ȷ��������:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("�����ѩ��", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(60, 230, 120, 20);
		lblNewLabel_3.setForeground(new Color(113, 133, 244));
		panel.add(lblNewLabel_3);

		// �˳�
		JLabel exitLabel = new JLabel("x");
		exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabel.setFont(new Font("curly", Font.PLAIN, 50));
		exitLabel.setBounds(590, 0, 40, 40);
		exitLabel.setForeground(Color.RED);
		exitLabel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}
		});
		panel.add(exitLabel);

		// �ı���������
		passwordField0 = new MyTextField();
		passwordField0.setBounds(230, 90, 180, 20);
		passwordField0.getDocument().addDocumentListener(
				new DocumentListener() {
					public void insertUpdate(DocumentEvent e) {
						if (passwordInfo0.isShowing()) {
							panel.remove(passwordInfo0);
							panel.revalidate();
							panel.repaint();
							frame.repaint();
						}
					}

					public void removeUpdate(DocumentEvent e) {

					}

					public void changedUpdate(DocumentEvent e) {

					}

				});
		panel.add(passwordInfo0);

		passwordField1 = new MyPasswordField();
		passwordField1.setBounds(230, 160, 180, 20);
		passwordField1.getDocument().addDocumentListener(
				new DocumentListener() {
					public void insertUpdate(DocumentEvent e) {
						panel.remove(passwordInfo1);
						panel.revalidate();
						panel.repaint();
						frame.repaint();
					}

					public void removeUpdate(DocumentEvent e) {

					}

					public void changedUpdate(DocumentEvent e) {

					}

				});
		panel.add(passwordField1);

		passwordField2 = new MyPasswordField();
		passwordField2.setBounds(230, 230, 180, 20);
		passwordField2.getDocument().addDocumentListener(
				new DocumentListener() {
					public void insertUpdate(DocumentEvent e) {
						panel.remove(passwordInfo2);
						panel.revalidate();
						panel.repaint();
						frame.repaint();
					}

					public void removeUpdate(DocumentEvent e) {
						panel.remove(passwordInfo2);
						panel.revalidate();
						panel.repaint();
						frame.repaint();
					}

					public void changedUpdate(DocumentEvent e) {
					}

				});
		panel.add(passwordField2);

		// ȷ�ϰ�ť
		ensureButton = new JButton("ȷ��");
		ensureButton.setBorder(null);
		ensureButton.setBackground(new Color(126, 234, 239, 122));
		ensureButton.setOpaque(false);
		ensureButton.setFont(new Font("�����̫��", Font.PLAIN, 20));
		ensureButton.setBounds(275, 290, 100, 40);
		ensureButton.addMouseListener(new MouseListener() {
			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent e) {
				if (passwordField0.getText().trim().length() < 1) {
					panel.remove(passwordInfo0);
					passwordInfo0 = new JLabel("����дԭ����");
					passwordInfo0.setBounds(450, 90, 100, 20);
					passwordInfo0.setFont(new Font("�Ķ�CS��ͬ��", Font.PLAIN, 16));
					passwordInfo0.setForeground(Color.red);
					panel.add(passwordInfo0);

				} else if (passwordField1.getText().trim().length() < 1) {
					panel.remove(passwordInfo1);
					passwordInfo1 = new JLabel("����д������");
					passwordInfo1.setBounds(450, 160, 100, 20);
						passwordInfo1.setFont(new Font("�Ķ�CS��ͬ��", Font.PLAIN,16));
					passwordInfo1.setForeground(Color.red);
					panel.add(passwordInfo1);
				} else if (passwordField2.getText().trim().length() < 1) {
					panel.remove(passwordInfo2);
					passwordInfo2 = new JLabel("��ȷ������");
					passwordInfo2.setBounds(450, 230, 100, 20);
						passwordInfo2.setFont(new Font("�Ķ�CS��ͬ��", Font.PLAIN,16));
					passwordInfo2.setForeground(Color.red);
					panel.add(passwordInfo2);
				}

				String oldPassword = passwordField0.getText();
				String password = passwordField1.getText();
				String password2 = passwordField2.getText();

				if ((password.equals(password2))
						&& (!(passwordField0.getText().trim().length() < 1)
								&& !(passwordField1.getText().trim().length() < 1) && !(passwordField2
								.getText().trim().length() < 1))) {

					ChangePasswordMessage m1 = new ChangePasswordMessage(
							oldPassword, password);
					Startup.net.sendMessage(m1);
					ChangePasswordBackMessage m2 = (ChangePasswordBackMessage) Startup.net
							.getMessage(-888);
					String back_info = m2.back_info;

					if (back_info.equals("�������")) {
						panel.remove(passwordInfo0);
						passwordInfo0 = new JLabel("�������");
						passwordInfo0.setBounds(450, 90, 100, 20);
							passwordInfo0.setFont(new Font("�Ķ�CS��ͬ��", Font.PLAIN,16));
						passwordInfo0.setForeground(Color.red);
						panel.add(passwordInfo0);
					} else {
						frame.dispose();
					}

				} else if (!(password.equals(password2))
						&& !(passwordField2.getText().trim().length() < 1)) {
					passwordInfo2 = new JLabel("���벻��");
					passwordInfo2.setBounds(450, 230, 100, 20);
						passwordInfo2.setFont(new Font("�Ķ�CS��ͬ��",Font.PLAIN, 18));
					passwordInfo2.setForeground(Color.red);
					panel.add(passwordInfo2);
				}
				panel.revalidate();
				panel.repaint();
				frame.repaint();
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

		});
		panel.add(ensureButton);
		panel.add(passwordField0);
		panel.add(passwordInfo1);
		panel.add(passwordInfo2);
		// panel.add(l);

		frame.add(l);
		frame.getLayeredPane().setLayout(null);
		frame.getLayeredPane().add(panel, new Integer(Integer.MAX_VALUE));
		// frame.add(panel);

		frame.setVisible(true);
		frame.repaint();
	}

	private class MyTextField extends JTextField {
		private static final long serialVersionUID = 1L;

		public MyTextField() {
			super.setBorder(new MyBorder());
			super.setOpaque(false);
		}
	}

	private class MyPasswordField extends JPasswordField {
		private static final long serialVersionUID = 1L;

		public MyPasswordField() {
			super.setBorder(new MyBorder());
			super.setOpaque(false);
		}
	}

	private class MyBorder implements Border {

		public void paintBorder(Component c, Graphics g, int x, int y, int w,
				int h) {
			g.setColor(new Color(100, 100, 100, 220));
			g.drawLine(0, c.getHeight() - 1, c.getWidth(), c.getHeight() - 1);
			g.drawLine(0, c.getHeight() - 3, 0, c.getHeight() - 1);
			g.drawLine(c.getWidth() - 1, c.getHeight() - 3, c.getWidth() - 1,
					c.getHeight() - 1);
		}

		public Insets getBorderInsets(Component c) {
			return new Insets(1, 1, 1, 1);
		}

		public boolean isBorderOpaque() {
			return true;
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

}

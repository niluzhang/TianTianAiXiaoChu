package presentation.registerui;

import java.awt.*;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.Startup;
import message.RegisterBackMessage;
import message.RegisterMessage;

public class RegisterUI {
	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JButton ensureButton;
	private int frameFirstX, frameFirstY;
	private JLabel textInfo = new JLabel("");
	private JLabel passwordInfo = new JLabel("");
	private JLabel passwordInfo1 = new JLabel("");

	public RegisterUI() {
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
				RegisterUI.class.getResource("/bin/605372.jpg")).getImage();
		JLabel l = new aLabel(image, 650, 380);
		l.setBounds(0, 0, 650, 380);

		// ��������
		
		//Font nf = setFont("�����ѩ��", 20); Font nf3 = setFont("�����̫��", 20);
		 
		// label����
		JLabel lblNewLabel_1 = new JLabel("�û���:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_1.setFont(new Font("�����ѩ��",Font.BOLD,20));
		lblNewLabel_1.setBounds(60, 90, 120, 20);
		lblNewLabel_1.setForeground(Color.black);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("����:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("�����ѩ��",Font.BOLD,20));
		lblNewLabel_2.setBounds(60, 160, 120, 20);
		lblNewLabel_2.setForeground(Color.black);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("����������:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("�����ѩ��",Font.BOLD,20));
		lblNewLabel_3.setBounds(60, 230, 120, 20);
		lblNewLabel_3.setForeground(Color.black);
		panel.add(lblNewLabel_3);

		// �˳�
		JLabel exitLabel = new JLabel("x");
		// Font nf2 = setFont("curlytype", 50);
		exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabel.setFont(new Font("curly",Font.BOLD,50));
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
		textField = new MyTextField();
		textField.setBounds(230, 90, 180, 20);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (textInfo.isShowing()) {
					panel.remove(textInfo);
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
		panel.add(textField);

		passwordField = new MyPasswordField();
		passwordField.setBounds(230, 160, 180, 20);
		passwordField.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				panel.remove(passwordInfo);
				panel.revalidate();
				panel.repaint();
				frame.repaint();
			}

			public void removeUpdate(DocumentEvent e) {

			}

			public void changedUpdate(DocumentEvent e) {

			}

		});
		panel.add(passwordField);

		passwordField_1 = new MyPasswordField();
		passwordField_1.setBounds(230, 230, 180, 20);
		passwordField_1.getDocument().addDocumentListener(
				new DocumentListener() {
					public void insertUpdate(DocumentEvent e) {
						panel.remove(passwordInfo1);
						panel.revalidate();
						panel.repaint();
						frame.repaint();
					}

					public void removeUpdate(DocumentEvent e) {
						panel.remove(passwordInfo1);
						panel.revalidate();
						panel.repaint();
						frame.repaint();
					}

					public void changedUpdate(DocumentEvent e) {
					}

				});
		panel.add(passwordField_1);

		// ȷ�ϰ�ť
		ensureButton = new JButton("ȷ��");
		ensureButton.setBorder(null);
		ensureButton.setBackground(new Color(126, 234, 239, 122));
		ensureButton.setForeground(Color.black);
		ensureButton.setOpaque(false);
		ensureButton.setFont(new Font("�����̫��",Font.BOLD,20));
		ensureButton.setBounds(275, 290, 100, 40);
		ensureButton.addMouseListener(new MouseListener() {
			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().trim().length() < 1) {
					panel.remove(textInfo);
					textInfo = new JLabel("����д����");
					textInfo.setBounds(450, 90, 100, 20);
					/*
					 * try { textInfo.setFont(setFont("�Ķ�CS��ͬ��", 18)); } catch
					 * (FontFormatException e1) { e1.printStackTrace(); } catch
					 * (IOException e1) { e1.printStackTrace(); }
					 */
					textInfo.setForeground(Color.red);
					panel.add(textInfo);

				} else if (passwordField.getText().trim().length() < 1) {
					panel.remove(passwordInfo);
					passwordInfo = new JLabel("����д����");
					passwordInfo.setBounds(450, 160, 100, 20);
					/*
					 * try { passwordInfo.setFont(setFont("�Ķ�CS��ͬ��", 18)); }
					 * catch (FontFormatException e1) { e1.printStackTrace(); }
					 * catch (IOException e1) { e1.printStackTrace(); }
					 */
					passwordInfo.setForeground(Color.red);
					panel.add(passwordInfo);
				} else if (passwordField_1.getText().trim().length() < 1) {
					panel.remove(passwordInfo1);
					passwordInfo1 = new JLabel("����д����");
					passwordInfo1.setBounds(450, 230, 100, 20);
					passwordInfo1.setFont(new Font("�Ķ�CS��ͬ��", Font.PLAIN,18)); 
					passwordInfo1.setForeground(Color.red);
					panel.add(passwordInfo1);
				}

				String name = textField.getText();
				String password = passwordField.getText();
				String password2 = passwordField_1.getText();

				if ((password.equals(password2))
						&& (!(textField.getText().trim().length() < 1)
								&& !(passwordField.getText().trim().length() < 1) && !(passwordField_1
								.getText().trim().length() < 1))) {
					RegisterMessage m1 = new RegisterMessage(name, password);
					Startup.net.sendMessage(m1);

					RegisterBackMessage cgsm = (RegisterBackMessage) Startup.net
							.getMessage(1);
					String backInfo = cgsm.register_back_info;
					if (backInfo.equals("ע��ɹ�")) {
						frame.dispose();
					} else {
						panel.remove(textInfo);
						textInfo = new JLabel("���û����ѱ�ע��");
						textInfo.setBounds(450, 90, 100, 20);
					    textInfo.setFont(new Font("�Ķ�CS��ͬ��", Font.PLAIN,18));
						textInfo.setForeground(Color.red);
						panel.add(textInfo);
					}

				} else if (!(password.equals(password2))
						&& !(passwordField_1.getText().trim().length() < 1)) {
					passwordInfo1 = new JLabel("���벻��");
					passwordInfo1.setBounds(450, 230, 100, 20);
					passwordInfo1.setFont(new Font("�Ķ�CS��ͬ��", Font.PLAIN,18)); 
					passwordInfo1.setForeground(Color.red);
					panel.add(passwordInfo1);
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
		panel.add(textInfo);
		panel.add(passwordInfo);
		panel.add(passwordInfo1);
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
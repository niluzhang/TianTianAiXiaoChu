package presentation.loginui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JLabel;
import javax.swing.JTextField;

import main.Startup;

public class MyTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	public MyTextField() {
		super();
		setBorder(new InputAreaBorder());
	//	setOpaque(false);
		setCaretColor(new Color(93, 231, 255));
		setForeground(new Color(93, 231, 255));
		setFont(new Font("΢���ź�", Font.PLAIN, 15));
		setSelectedTextColor(Color.white);
		setSelectionColor(Color.blue);

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

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				File file = new File(getText()
						+ ".pass");
				if (file.exists()) {
					try {
						FileReader fr = new FileReader(file);
						BufferedReader br = new BufferedReader(fr);
						Startup.window.passwordField.setText(br.readLine());
						br.close();
						Startup.window.checkBox.setSelected(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				else{
					Startup.window.passwordField.setText("");
					Startup.window.checkBox.setSelected(false);
				}
			}

		});

		addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				final JLabel delete = new JLabel("��");
				delete.setFont(new Font("΢���ź�", Font.PLAIN, 20));
				delete.setForeground(new Color(220, 60, 80));
				delete.setBounds(MyTextField.this.getWidth() - 24, 5, 20, 20);
				delete.addMouseListener(new MouseListener() {
					public void mouseExited(MouseEvent e) {
						setCursor(new Cursor(Cursor.TEXT_CURSOR));

					}

					public void mouseReleased(MouseEvent e) {
						setText("");
					}

					public void mouseClicked(MouseEvent e) {

					}

					public void mouseEntered(MouseEvent e) {
						delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}

					public void mousePressed(MouseEvent e) {

					}
				});
				add(delete);
				repaint();
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				removeAll();
				repaint();
			}

		});
	}

}

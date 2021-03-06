package presentation.loginui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class MyPasswordField extends JPasswordField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyPasswordField() {
		super();
		setBorder(new InputAreaBorder());
	//	setOpaque(false);
		setCaretColor(new Color(93, 231, 255));
		setForeground(new Color(93, 231, 255));
		setFont(new Font("΢���ź�", Font.PLAIN, 15));
		setSelectedTextColor(Color.white);
		setSelectionColor(Color.blue);

		addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				final JLabel delete = new JLabel("��");
				delete.setFont(new Font("΢���ź�", Font.PLAIN, 20));
				delete.setForeground(new Color(220, 60, 80));
				delete.setBounds(MyPasswordField.this.getWidth() - 24,
						MyPasswordField.this.getHeight() - 24, 20, 20);
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

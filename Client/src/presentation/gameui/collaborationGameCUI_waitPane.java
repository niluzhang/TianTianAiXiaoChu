package presentation.gameui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation_singleGameGUI.singleGameGUI;

public class collaborationGameCUI_waitPane extends collaborationGameGUI {
public void initlizewaitPane(){
	
	//waitPanel.setBounds(0, 0, 1160, 630);
	java.net.URL imgurl = singleGameGUI.class.getResource("/bin/waitPane.png");
	ImageIcon image=new ImageIcon(imgurl);
	JLabel imagelabel=new JLabel(image);
	imagelabel.setBounds(0, 30, 1160, 600);
	imagelabel.setOpaque(false);
	topPane.setLayout(null);
	
	topPane.setOpaque(false);
	
	topPane.addMouseListener(new MouseListener(){

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	});
	topPane.add(imagelabel);

}
class WaitPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.clearRect(0, 0,  1160,630);
		java.net.URL imgurl = collaborationGameGUI.class.getResource("/bin/waitPane.png");
		Image image = new ImageIcon(imgurl).getImage();
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);

	}
	
}

}

package presentation.loginui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import presentation.changepasswordui.ChangePasswordUI;
import presentation.registerui.RegisterUI;
import presentation_localgame.singleGameGUI;

public class ExtensionPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<JLabel> items=new ArrayList<JLabel>();
	public singleGameGUI s;
	
    public ExtensionPanel(){
    	setBorder(new Border(){

			@Override
			public void paintBorder(Component c, Graphics g, int x, int y,
					int width, int height) {
				// TODO Auto-generated method stub
				Graphics2D g2=(Graphics2D)g;
				g2.setColor(new Color(12,21,28));
				g2.setStroke(new BasicStroke(3));
				g2.drawRoundRect(x, y, width-1, height-1, 3, 3);
			}

			@Override
			public Insets getBorderInsets(Component c) {
				// TODO Auto-generated method stub
				return new Insets(1,1,1,1);
			}

			@Override
			public boolean isBorderOpaque() {
				// TODO Auto-generated method stub
				return true;
			}
    		
    	});
    	setBackground(Color.white);
    	setLayout(null);
    	
    }
    
    public void addItem(String str){
    	final JLabel l=new JLabel(str);
    	l.setBorder(new Border(){

			@Override
			public void paintBorder(Component c, Graphics g, int x, int y,
					int width, int height) {
				// TODO Auto-generated method stub
				Graphics2D g2=(Graphics2D)g;
				g2.setColor(Color.gray);
				g2.setStroke(new BasicStroke(1));
				g.drawLine(3, height-4,width-3,height-4);
			}

			@Override
			public Insets getBorderInsets(Component c) {
				// TODO Auto-generated method stub
				return new Insets(1,1,1,1);
			}

			@Override
			public boolean isBorderOpaque() {
				// TODO Auto-generated method stub
				return true;
			}
    		
    	});
    	
    	l.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if(l.getText().equals("    单机模式")){
					//System.out.println("单机模式");
					java.net.URI uri;
					try{
						uri=new java.net.URI("http://peng.qq.com/");
						java.awt.Desktop.getDesktop().browse(uri);
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(l.getText().equals("    协作模式")){
					//System.out.println("协作模式");
					java.net.URI uri;
					try{
						uri=new java.net.URI("http://peng.qq.com/");
						java.awt.Desktop.getDesktop().browse(uri);
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(l.getText().equals("    注册帐号")){
					//System.out.println("注册帐号");
					RegisterUI r=new RegisterUI();
					try {
						r.setFrame();
					} catch (FontFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(l.getText().equals("    离线试玩")){
		          s=new singleGameGUI();
		          s.initilize();
				}
				else if(l.getText().equals("    修改密码")){
					ChangePasswordUI c = new ChangePasswordUI();
					try {
						c.setFrame();
					} catch (FontFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				l.setForeground(new Color(139,0,139));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				l.setForeground(Color.BLACK);
			}
    		
    	});
    	l.setForeground(Color.black);
    	l.setFont(new Font("微软雅黑",Font.PLAIN,12));
    	items.add(l);
       	l.setBounds(0,(items.size()-1)*33,87,33);
    	setSize(87,33*items.size());
    	add(l);
    }
}

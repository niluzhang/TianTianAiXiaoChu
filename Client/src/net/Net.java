package net;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

import javax.swing.JOptionPane;

import message.Message;

public class Net {

	public Socket sock;
	public ObjectInputStream ois;
	public ObjectOutputStream oos;
	public ArrayList<Message> message_list = new ArrayList<Message>();

	public Net() throws IOException { // 调用者根据是否捕获异常判断是否介入网络
		//114.212.42.116
		sock = new Socket("127.0.0.1", 5000);
		oos = new ObjectOutputStream(sock.getOutputStream());
		ois = new ObjectInputStream(sock.getInputStream());
	}

	public synchronized void sendMessage(Message message) {
		try {
			oos.writeObject(message);
			oos.flush();
		} 
		catch(SocketException e){
			JOptionPane.showMessageDialog(null, "send网络异常");
			System.exit(0);
		}catch (IOException e) {
			JOptionPane.showMessageDialog(null, "send网络异常");
			System.exit(0);
		}
	}

	public synchronized Message getMessage(int expected_type) {
		try {
			System.out.println(message_list.size());
			if (message_list.size() >= 1) {
				Message m = message_list.get(0);
				if (m.type == expected_type) {
					// System.out.println("从队列中读取  "+m.type+" "+expected_type);
					message_list.remove(0);
					return m;
				}
			} else {
				Message back_msg = (Message) ois.readObject();
				// System.out.println("从流中读取  "+back_msg.type+" "+expected_type);
				System.out.println(back_msg.type);
				if (back_msg.type == expected_type)
					return back_msg;
				else
					message_list.add(back_msg);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "get网络异常");
			System.exit(0);
		}
		return null;
	}

}

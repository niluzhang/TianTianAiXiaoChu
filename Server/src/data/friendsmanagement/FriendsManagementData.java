package data.friendsmanagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import net.Net;

public class FriendsManagementData {

	public void addFriend(String user, String friend) {
		File file = new File(user + ".ser");
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(user + ".ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				ArrayList<String> id_list = (ArrayList<String>) ois
						.readObject();
				id_list.add(friend);
				FileOutputStream fos = new FileOutputStream(user + ".ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(id_list);
				oos.close();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				file.createNewFile();
				ArrayList<String> id_list = new ArrayList<String>();
				id_list.add(friend);
				FileOutputStream fos = new FileOutputStream(user + ".ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(id_list);
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		File file2 = new File(friend + ".ser");
		if (file2.exists()) {
			try {
				FileInputStream fis = new FileInputStream(friend + ".ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				ArrayList<String> id_list = (ArrayList<String>) ois
						.readObject();
				id_list.add(user);
				FileOutputStream fos = new FileOutputStream(friend + ".ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(id_list);
				ois.close();
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				file2.createNewFile();
				ArrayList<String> id_list = new ArrayList<String>();
				id_list.add(user);
				FileOutputStream fos = new FileOutputStream(friend + ".ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(id_list);
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public boolean requestFriend(String user, String friend) {
		boolean ok = true;
		File f = new File(user + ".ser");
		if (f.exists()) {
			try {
				FileInputStream fis = new FileInputStream(user + ".ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				ArrayList<String> id_list = (ArrayList<String>) ois
						.readObject();
				ois.close();
				for (int i = 0; i <= id_list.size() - 1; i++) {
					if (id_list.get(i).equals(friend))
						ok = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			ok = true;
		}
		if (ok) {
			File file = new File(friend + "_toBeSolved.ser");
			boolean duplicate = false;
			if (file.exists()) {
				try {
					FileInputStream fis = new FileInputStream(friend
							+ "_toBeSolved.ser");
					ObjectInputStream ois = new ObjectInputStream(fis);
					ArrayList<String> id_list = (ArrayList<String>) ois
							.readObject();
					for (int i = 0; i <= id_list.size() - 1; i++) {
						if (id_list.get(i).equals(user))
							duplicate = true;
					}
					if (duplicate == false) {
						id_list.add(user);
						FileOutputStream fos = new FileOutputStream(friend
								+ "_toBeSolved.ser");
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(id_list);
						ois.close();
						oos.close();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					file.createNewFile();
					ArrayList<String> id_list = new ArrayList<String>();
					id_list.add(user);
					FileOutputStream fos = new FileOutputStream(friend
							+ "_toBeSolved.ser");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(id_list);
					oos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return true;
		} else
			return false;
	}

	public String queryFriendRequest(String user) {
		File file = new File(user + "_toBeSolved.ser");
		String result = "";
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(user
						+ "_toBeSolved.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				ArrayList<String> id_list = (ArrayList<String>) ois
						.readObject();
				ois.close();
				for (int i = 0; i <= id_list.size() - 1; i++) {
					if (i == 0)
						result = id_list.get(0);
					else
						result = result + " " + id_list.get(i);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			result = "";
		}
		return result;
	}

	public void clearRequest(String user) {
		File file = new File(user + "_toBeSolved.ser");
		if (file.exists()) {
			file.delete(); // ע��
		} else {

		}
	}

	public void removeOneFriendRequest(String user, String friend) {
		File file = new File(user + "_toBeSolved.ser");
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				ArrayList<String> id_list = (ArrayList<String>) ois
						.readObject();
				ois.close();
				for (int i = 0; i <= id_list.size() - 1; i++) {
					if (id_list.get(i).equals(friend)) {
						id_list.remove(i);
						break;
					}
				}
				if (id_list.size() == 0)
					file.delete();
				else {
					FileOutputStream fos = new FileOutputStream(file);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(id_list);
					oos.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String showOnlineFriend(String user) {
		File file = new File(user + ".ser");
		String result = "";
		String result2 = "";
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(user + ".ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				ArrayList<String> id_list = (ArrayList<String>) ois
						.readObject();
				ois.close();
				for (int i = 0; i <= id_list.size() - 1; i++) {
					if (i == 0)
						result = id_list.get(0);
					else
						result = result + " " + id_list.get(i);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

		}
		String[] split = result.split(" ");
		for (int i = 0; i <= split.length - 1; i++) {
			for (int j = 0; j <= Net.users.size() - 1; j++)
				if (Net.users.get(j).id.equals(split[i])) {
					result2 = result2 + split[i] + " ";
					break;
				}
		}
		return result2;
	}

	public String showOfflineFriend(String user) {
		File file = new File(user + ".ser");
		String result = "";
		String result2 = "";
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(user + ".ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				ArrayList<String> id_list = (ArrayList<String>) ois
						.readObject();
				ois.close();
				for (int i = 0; i <= id_list.size() - 1; i++) {
					if (i == 0)
						result = id_list.get(0);
					else
						result = result + " " + id_list.get(i);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

		}
		if (result.equals(""))
			return "";
		String[] split = result.split(" ");
		boolean offline = true;
		for (int i = 0; i <= split.length - 1; i++) {
			for (int j = 0; j <= Net.users.size() - 1; j++) {
				if (Net.users.get(j).id.equals(split[i])) {
					offline = false;
					break;
				}
			}
			if (offline)
				result2 = result2 + split[i] + " ";
			offline = true;
		}
		return result2;
	}

	public void cooperateRequest(String user, String friend) {
		File file = new File(friend + "_coRequest" + ".ser");
		boolean duplicate = false;
		if (file.exists()) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				ArrayList<String> list = (ArrayList<String>) ois.readObject();
				for (int i = 0; i <= list.size() - 1; i++) {
					if (list.get(i).equals(user)) {
						duplicate = true;
						break;
					}
				}
				if (!duplicate) {
					list.add(user);
					FileOutputStream fos = new FileOutputStream(file);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(list);
					ois.close();
					oos.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				file.createNewFile();
				ArrayList<String> list = new ArrayList<String>();
				list.add(user);
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(list);
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void pkRequest(String user, String friend) {
		File file = new File(friend + "_pkRequest" + ".ser");
		boolean duplicate = false;
		if (file.exists()) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				ArrayList<String> list = (ArrayList<String>) ois.readObject();
				for (int i = 0; i <= list.size() - 1; i++) {
					if (list.get(i).equals(user)) {
						duplicate = true;
						break;
					}
				}
				if (!duplicate) {
					list.add(user);
					FileOutputStream fos = new FileOutputStream(file);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(list);
					ois.close();
					oos.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				file.createNewFile();
				ArrayList<String> list = new ArrayList<String>();
				list.add(user);
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(list);
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String queryCooperateRequest(String user) {
		File file = new File(user + "_coRequest" + ".ser");
		String result = "";
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				ArrayList<String> list = (ArrayList<String>) ois.readObject();
				for (int i = 0; i <= list.size() - 1; i++) {
					result = result + list.get(i) + " ";
				}
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

		}
		return result;
	}

	public String queryPKRequest(String user) {
		File file = new File(user + "_pkRequest" + ".ser");
		String result = "";
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				ArrayList<String> list = (ArrayList<String>) ois.readObject();
				for (int i = 0; i <= list.size() - 1; i++) {
					result = result + list.get(i) + " ";
				}
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

		}
		return result;
	}

	public void removeFriend(String user, String friend) {
		File file = new File(user + ".ser");
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			@SuppressWarnings("unchecked")
			ArrayList<String> list = (ArrayList<String>) ois.readObject();
			Iterator<String> itr = list.iterator();
			while (itr.hasNext()) {
				String tmp = itr.next();
				if (tmp.equals(friend)) {
					list.remove(tmp);
					break;
				}
			}
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			ois.close();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		File file2 = new File(friend + ".ser");
		try {
			FileInputStream fis = new FileInputStream(file2);
			ObjectInputStream ois = new ObjectInputStream(fis);
			@SuppressWarnings("unchecked")
			ArrayList<String> list = (ArrayList<String>) ois.readObject();
			Iterator<String> itr = list.iterator();
			while (itr.hasNext()) {
				String tmp = itr.next();
				if (tmp.equals(user)) {
					list.remove(tmp);
					break;
				}
			}
			FileOutputStream fos = new FileOutputStream(file2);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			ois.close();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeOneCo(String user, String friend) {
		File file = new File(user + "_coRequest" + ".ser");
		if (file.exists()) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				ArrayList<String> list = (ArrayList<String>) ois.readObject();
				Iterator<String> itr = list.iterator();
				while (itr.hasNext()) {
					String s = itr.next();
					if (s.equals(friend)) {
						list.remove(s);
						break;
					}
				}
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(list);
				ois.close();
				oos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			// do nothing
		}
	}

	public void removeOnePK(String user, String friend) {
		File file = new File(user + "_pkRequest" + ".ser");
		if (file.exists()) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				ArrayList<String> list = (ArrayList<String>) ois.readObject();
				Iterator<String> itr = list.iterator();
				while (itr.hasNext()) {
					String s = itr.next();
					if (s.equals(friend)) {
						list.remove(s);
						break;
					}
				}
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(list);
				ois.close();
				oos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			// do nothing
		}
	}

}

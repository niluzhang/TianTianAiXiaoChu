package presentation_localgame;

//游戏时间的计时器（倒计时器）
public class gameTimer extends Thread {
	private int currenttime;

	// private int maxtime;
	public gameTimer(int time) {
		currenttime = time;

	}

	public int getTime() {
		return currenttime;
	}

	@Override
	public void run() {
		while (currenttime > 0) {
			try {
				sleep(50);// 进程睡0.s
				currenttime--;
				// System.out.println("时间为："+currenttime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//
		}
	}
}

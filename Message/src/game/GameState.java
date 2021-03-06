package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameState implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int lianji = 0;
	private int TotalLianji = 0;
	private int MaxLianji = 0;
	private int TotalGrade = 0;
	private int lianji_time = 1000;
	private int NoticeTime = 3000;
	
	private boolean InSuperState = false;
	private boolean Finished = false;
	private boolean Pass_LianjiTime = false;
	private boolean isReceived = false;
	private boolean needUseTool_E = false;
	private boolean InRemoveState = false;
	private boolean HasUseTool_B = false;
	private boolean ContainPkState = false;
	
	private boolean UseTool_C;
	private boolean UseTool_D;
	private boolean UseTool_E;
	
	private boolean HasUserOffLine = false;
	private ArrayList<String> OffLineUser = new ArrayList<String>();
	
	private int[][] CurrentPane = new int[9][9];
	private ArrayList<Position> Tool_E = new ArrayList<Position>();
	public int time=65;
	
	private transient MyTimer t1 = new MyTimer();
	private transient MyTimer t2 = new MyTimer();
	private transient MyTimer t3 = new MyTimer();
	private transient MyTimer t4 = new MyTimer();
	private transient MyTimer t5 = new MyTimer();
	private transient GameState pk_state;
	
	public GameState() {}
	
	public GameState(boolean UseTool_C, boolean UseTool_E) {	
		if(UseTool_C) {
			lianji_time = 2000;
		}
		
		if(UseTool_E) {
			NoticeTime = 2000;
		}
		
		System.out.println("UseTool_C: " + UseTool_C);
		System.out.println("UseTool_E: " + UseTool_E);
		
		t1.schedule(new GameTime(this), 65000);
		t2.schedule(new Game_Lianji(this), lianji_time);
		t4.schedule(new Tool_E_Timer(this), NoticeTime + 5000);
		
		t5.schedule(new TimerTask(){

			@Override
			public void run() {
				time--;
			}
			
		}, 0, 1000);
	}
	
	public synchronized void AddLianji() {
		
		if(!Finished) {
			t2.cancel();
			
			if(Pass_LianjiTime) {
				lianji = 1;
				
				if(TotalLianji > MaxLianji) {
					MaxLianji = TotalLianji;
				}
				
				TotalLianji = 1;
				
			} else {
				
				if(lianji == 4) {
					System.out.println("进入了无敌模式！");
					
					if(!InSuperState) {
						InSuperState = true;
					}
					
					t3.cancel();
						
					t3 = new MyTimer();
					t3.schedule(new SuperState(this), 5000);
					lianji = 0;
					
					if(ContainPkState && pk_state.isUseTool_C()) {
						pk_state.SetLianji_time(1000);
						
						Timer b = new Timer();
						b.schedule(new TimerTask() {

							@Override
							public void run() {
							pk_state.SetLianji_time(2000);
							}
						}, 5000);
					}
				} else {
					lianji++;
				}
			
				TotalLianji++;
				
				if(TotalLianji > MaxLianji) {
					MaxLianji = TotalLianji;
				}
			}
			
			Pass_LianjiTime = false;
				
			t2 = new MyTimer();
			t2.schedule(new Game_Lianji(this), lianji_time);
		}
		
		System.out.println("当前连击次数： " + lianji);
		
	}

	public boolean isFinished() {
		return Finished;
	}

	public void setFinished(boolean ifFinished) {
		this.Finished = ifFinished;
	}

	public boolean isPass_LianjiTime() {
		return Pass_LianjiTime;
	}

	public void setPass_LianjiTime(boolean ifPass_1min) {
		this.Pass_LianjiTime = ifPass_1min;
	}

	public boolean isInSuperState() {
		return InSuperState;
	}

	public void setInSuperState(boolean inSuperState) {
		InSuperState = inSuperState;
	}

	public int getLianji() {
		return lianji;
	}

	public void setLianji(int lianji) {
		this.lianji = lianji;
	}
	public int Getlianji_time() {
		return this.lianji_time;
	}

	public void SetLianji_time(int a) {
		this.lianji_time = a;
	}
	
	public int getTotalGrade() {
		return TotalGrade;
	}

	public void setTotalGrade(int totalGrade) {
		TotalGrade = totalGrade;
	}
	
	public void setMaxLianji(int maxLianji) {
		MaxLianji = maxLianji;
	}
	
	public int getMaxLianji() {
		return MaxLianji;
	}
	
	public void AddGrade(int grade) {
		TotalGrade += grade;
	}

	public int[][] getCurrentPane() {
		int[][] p = new int[9][9];
		
		for(int i = 0; i < 9; i++) {
			for(int k = 0; k < 9; k++) {
				p[i][k] = CurrentPane[i][k];
			}
		}
		return p;
	}

	public void setCurrentPane(int[][] currentPane) {
		CurrentPane = currentPane;
	}

	public boolean isUseTool_E() {
		return UseTool_E;
	}

	public void setUseTool_E(boolean useTool_E) {
		UseTool_E = useTool_E;
	}
	
	public boolean isNeedUseTool_E() {
		return needUseTool_E;
	}

	public void setNeedUseTool_E(boolean needUseTool_E) {
		if(!needUseTool_E) {
			t4.cancel();
			t4 = new MyTimer();
			t4.schedule(new Tool_E_Timer(this), NoticeTime);
		}
		
		this.needUseTool_E = needUseTool_E;
	}
	
	public ArrayList<Position> getTool_E() {
		return Tool_E;
	}

	public void setTool_E(ArrayList<Position> tool_E) {
		Tool_E = tool_E;
	}

	public boolean isUseTool_C() {
		return UseTool_C;
	}

	public void setUseTool_C(boolean useTool_C) {
		UseTool_C = useTool_C;
	}

	public boolean isUseTool_D() {
		return UseTool_D;
	}

	public void setUseTool_D(boolean useTool_D) {
		System.out.println("UseTool_D: " + UseTool_D);
		UseTool_D = useTool_D;
	}

	public boolean isReceived() {
		return isReceived;
	}

	public void setReceived(boolean isReceived) {
		this.isReceived = isReceived;
	}

	public boolean isInRemoveState() {
		return InRemoveState;
	}

	public void setInRemoveState(boolean inRemoveState) {
		InRemoveState = inRemoveState;
	}

	public boolean isHasUserOffLine() {
		return HasUserOffLine;
	}

	public void setHasUserOffLine(boolean hasUserOffLine) {
		HasUserOffLine = hasUserOffLine;
	}

	public ArrayList<String> getOffLineUser() {
		return OffLineUser;
	}

	public void setOffLineUser(ArrayList<String> offLineUser) {
		OffLineUser = offLineUser;
	}

	public boolean isHasUseTool_B() {
		return HasUseTool_B;
	}

	public void setHasUseTool_B(boolean hasUseTool_B) {
		HasUseTool_B = hasUseTool_B;
		
		if(hasUseTool_B) {
			Timer ko = new Timer();
			ko.schedule(new TimerTask() {

				@Override
				public void run() {
					HasUseTool_B = false;
				}
				
			}, 2000);
		}
	}

	public GameState getPk_state() {
		return pk_state;
	}

	public void setPk_state(GameState pk_state) {
		this.pk_state = pk_state;
	}

	public boolean isContainPkState() {
		return ContainPkState;
	}

	public void setContainPkState(boolean containPkState) {
		ContainPkState = containPkState;
	}
	
}

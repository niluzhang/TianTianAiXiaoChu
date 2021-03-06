package po;

public class RecordPO {
	private int id;
	private int score;
	private String userName;
	private int maxComboNum;
	private String time;
	

	public RecordPO(int score,String userName,int maxComboNum){
		this.maxComboNum=maxComboNum;
		this.score=score;
		this.userName=userName;
	}
	public RecordPO(int score,String userName,int maxComboNum,String time){
		this.maxComboNum=maxComboNum;
		this.score=score;
		this.userName=userName;
		this.time=time;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getMaxComboNum() {
		return maxComboNum;
	}
	public void setMaxComboNum(int maxComboNum) {
		this.maxComboNum = maxComboNum;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}

package message;

public class GetUpdateInfo extends Message {
//用于不停获取游戏    成绩grade 游戏时间 gameTime 连击次数 doubleHitNumber  连击状态 superState
	private static final long serialVersionUID = 1L;
	private int grade;
	private int gameTime;
	private int doubleHitNumber;
	private boolean superState;

	public GetUpdateInfo(int grade, int gameTime, int doubleHitNumber, boolean superState){
		type=10;
		this.doubleHitNumber=doubleHitNumber;
		this.gameTime=gameTime;
		this.grade=grade;
		this.superState=superState;
	}
	public int getGrade(){
		return grade;
	}
	public int getGameGrade(){
		return gameTime;
	}
	public int getDoubleHitNumber(){
		return doubleHitNumber;
	}
	public Boolean getDoubleHitState(){
		return superState;
	}
}

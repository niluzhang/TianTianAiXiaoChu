package po;

//个人信息对象
public class UserPO implements Comparable<UserPO>{

	private String password;
	private String name;
	private String photo;
	private int maxScore;

	public UserPO() {}
	
	public UserPO(String name,String password,String photo,int maxScore){
		this.name=name;
		this.password=password;
		this.photo=photo;
		this.maxScore=maxScore;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	@Override
	public int compareTo(UserPO o) {
		return (o.maxScore - this.maxScore);
	}
	

}

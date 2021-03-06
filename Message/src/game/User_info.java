/*每日局数ArrayList<string> daily_game_numbers,string = 每日局数+“#”+日期
每日平均得分 ArrayList<string> daily_average_scores,string = 每日平均得分+“#”+日期
每局得分 ArrayList<string> per_game_scores,string = 每局得分+“#”+日期
最高连击次数 int max_combo_number
总局数 int game_number
最高分 int max_score
 */
package game;

import java.util.ArrayList;

import message.Message;

public class User_info extends Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String name;
	public String photo;
	public ArrayList<String> daily_game_numbers;
	public ArrayList<String> daily_average_scores;
	public ArrayList<String> per_game_scores;
	public int max_combo_number = 0;
	public int game_number = 0;
	public int max_score = 0;
	public int average_score = 0;

	public User_info(String name, String photo,
			ArrayList<String> daily_game_numbers,
			ArrayList<String> daily_average_score,
			ArrayList<String> per_game_scores, int max_combo_number,
			int game_number, int max_score) {
		this.name=name;
		this.photo=photo;
		this.daily_game_numbers = daily_game_numbers;
		this.daily_average_scores = daily_average_score;
		this.per_game_scores = per_game_scores;
		this.max_combo_number = max_combo_number;
		this.game_number = game_number;
		this.max_score = max_score;
		type=-30000;
	}

	public User_info() {
		this.daily_game_numbers = new ArrayList<String>();
		this.daily_average_scores = new ArrayList<String>();
		this.per_game_scores = new ArrayList<String>();
		type=-30000;}
}
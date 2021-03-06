package businesslogic.remove;

import game.Game_info;
import game.Position;

public class Row_Double_Remove {

	private int Tool_p1 = 0;
	private int Tool_p2 = 0;
	
	private int[][] AfterChangePane;
	
	private Game_info gg;
	private Single_Remove single;
	
	public Row_Double_Remove(int[][] AfterChangePane, Game_info gg) {
		this.AfterChangePane = new int[9][9];
		
		for(int i = 0; i < 9; i++) {
			for(int k = 0; k < 9; k++) {
				this.AfterChangePane[i][k] = AfterChangePane[i][k];
			}
		}
		this.gg = gg;
		single = new Single_Remove(AfterChangePane);
	}
	
	public int GetTool_p1() {
		return Tool_p1;
	}
	
	public int GetTool_p2() {
		return Tool_p2;
	}
	
	public int[][] GetPane() {
		return AfterChangePane;
	}
	
	public boolean Row_Can_Remove(Position p1, Position p2) {
		boolean po = false;
		
		int res = AfterChangePane[p1.Row][p1.Column];
		AfterChangePane[p1.Row][p1.Column] = AfterChangePane[p2.Row][p2.Column];
		AfterChangePane[p2.Row][p2.Column] = res;
		
		int a1 = p1.Row;
		int a2 = p1.Column;
		int a3 = AfterChangePane[p1.Row][p1.Column]%10;
		
		int u1 = single.UColumn_Remove(p1);
		int d1 = single.DColumn_Remove(p1);	
			
		if((u1 + d1) >= 2) {
			po = true;
		}
		
		if(a2 >= 2) {
			boolean res1 = (a3 == (AfterChangePane[a1][a2 - 1])%10);
			boolean res2 = (a3 == (AfterChangePane[a1][a2 - 2])%10);
					
			if(res1 && res2) {
				po = true;
			}
		}
		
		int b1 = p2.Row;
		int b2 = p2.Column;
		int b3 = AfterChangePane[p2.Row][p2.Column]%10;
		
		int u2 = single.UColumn_Remove(p2);
		int d2 = single.DColumn_Remove(p2);	
			
		if((u2 + d2) >= 2) {
			po = true;
		}
		
		if(b2 <= 6) {
			boolean res1 = (b3 == (AfterChangePane[b1][b2 + 1])%10);
			boolean res2 = (b3 == (AfterChangePane[b1][b2 + 2])%10);
					
			if(res1 && res2) {
				po = true;
			}	
		}
		
		res = AfterChangePane[p1.Row][p1.Column];
		AfterChangePane[p1.Row][p1.Column] = AfterChangePane[p2.Row][p2.Column];
		AfterChangePane[p2.Row][p2.Column] = res;
		
		return po;
	}
	
	public void Row_Remove(Position p1, Position p2) {
		
		int a1 = p1.Row;
		int a2 = p1.Column;
		int a3 = AfterChangePane[p1.Row][p1.Column]%10;
		
		int u1 = single.UColumn_Remove(p1);
		int d1 = single.DColumn_Remove(p1);	
			
		if((u1 + d1) >= 2) {
			for(int i = 0; i <= u1; i++) {		
				AfterChangePane[a1 - i][a2] = -1;
			}
						
			for(int i = 0; i <= d1; i++) {
				AfterChangePane[a1 + i][a2] = -1;
			}
			
			int Score = ScoreCounter.GetScore(u1 + d1 + 1);
			gg.addGrade(Score);
			
		}
		
		if(a2 >= 2) {
			boolean res1 = (a3 == (AfterChangePane[a1][a2 - 1])%10);
			boolean res2 = (a3 == (AfterChangePane[a1][a2 - 2])%10);
					
			if(res1 && res2) {
				for(int i = 0; i < 3; i++) {	
					AfterChangePane[a1][a2 - i] = -1;
				}
				
				if((u1 + d1) == 2) {
					Tool_p1 = (a3%10) + 10;
				}
				
				if((u1 + d1) > 2) {
					Tool_p1 = 20;
				}
				
				int Score = ScoreCounter.GetScore(3);
				gg.addGrade(Score);
				
			} else {
				
				if((u1 + d1) == 3) {
					Tool_p1 = (a3%10) + 10;
				}
				
				if((u1 + d1) > 3) {
					Tool_p1 = 20;
				}
				
			}
		} else {
			
			if((u1 + d1) == 3) {
				Tool_p1 = (a3%10) + 10;
			}
			
			if((u1 + d1) > 3) {
				Tool_p1 = 20;
			}
		}
		
		int b1 = p2.Row;
		int b2 = p2.Column;
		int b3 = AfterChangePane[p2.Row][p2.Column]%10;
		
		int u2 = single.UColumn_Remove(p2);
		int d2 = single.DColumn_Remove(p2);	
			
		if((u2 + d2) >= 2) {
			for(int i = 0; i <= u2; i++) {		
				AfterChangePane[b1 - i][b2] = -1;
			}
						
			for(int i = 0; i <= d2; i++) {
				AfterChangePane[b1 + i][b2] = -1;
			}
			
			int Score = ScoreCounter.GetScore(u2 + d2 + 1);
			gg.addGrade(Score);
			
		}
		
		if(b2 <= 6) {
			boolean res1 = (b3 == (AfterChangePane[b1][b2 + 1])%10);
			boolean res2 = (b3 == (AfterChangePane[b1][b2 + 2])%10);
					
			if(res1 && res2) {
				for(int i = 0; i < 3; i++) {	
					AfterChangePane[b1][b2 + i] = -1;
				}
						
				if((u2 + d2) == 2) {
					Tool_p2 = (b3%10) + 10;
				}
				
				if((u2 + d2) > 2) {
					Tool_p2 = 20;
				}
				
				int Score = ScoreCounter.GetScore(3);
				gg.addGrade(Score);
				
			} else {
				
				if((u2 + d2) == 3) {
					Tool_p2 = (b3%10) + 10;
				}
				
				if((u2 + d2) > 3) {
					Tool_p2 = 20;
				}
				
			}
			
		} else {
			
			if((u2 + d2) == 3) {
				Tool_p2 = (b3%10) + 10;
			}
			
			if((u2 + d2) > 3) {
				Tool_p2 = 20;
			}
			
		}
	}
	
}

package message;

import java.util.ArrayList;

public class GetRankListBackMessage extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// String : ID#����
	public ArrayList<String> rankList = new ArrayList<String>();

	public GetRankListBackMessage(ArrayList<String> rankList) {
		this.rankList = rankList;
		type = -777;
	}

}
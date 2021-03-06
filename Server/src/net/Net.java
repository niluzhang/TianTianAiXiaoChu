package net;

import game.GameState;
import game.GameStateFuBen;
import game.Game_info;
import game.User_info;
import gamemessage.ChangePositionMessage;
import gamemessage.CorpChangePositionMessage;
import gamemessage.CorpGameStartMessage;
import gamemessage.CorpGetStateMessage;
import gamemessage.CorpTestMessage;
import gamemessage.CorpUseToolMessage;
import gamemessage.FinishDropMessage;
import gamemessage.FinishMessage;
import gamemessage.GameStateMessage;
import gamemessage.Game_infoMessage;
import gamemessage.PkChangePositionMessage;
import gamemessage.PkDirectRemoveMessage;
import gamemessage.PkGameStartMessage;
import gamemessage.PkGameStateMessage;
import gamemessage.PkGame_infoMessage;
import gamemessage.PkGetStateMessage;
import gamemessage.PkSelectPositionMessage;
import gamemessage.PkUseToolMessage;
import gamemessage.ReNetMessage;
import gamemessage.SelectPositionMessage;
import gamemessage.SingleChangePositionMessage;
import gamemessage.SingleDirectremoveMessage;
import gamemessage.SingleGameStartMessage;
import gamemessage.SingleGetStateMessage;
import gamemessage.SingleUseToolMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import message.AddFriendMessage;
import message.ChangePasswordBackMessage;
import message.ChangePasswordMessage;
import message.ClearRequestMessage;
import message.CooperateRequestMessage;
import message.DenyCoMessage;
import message.GetRankListBackMessage;
import message.IsLogin;
import message.IsLoginBackMessage;
import message.LoginBackMessage;
import message.LoginMessage;
import message.Message;
import message.PartenerBackMessage;
import message.PermitCoMessage;
import message.QueryCooperateRequestBackMessage;
import message.QueryCooperateRequestMessage;
import message.QueryFriendRequestBackMessage;
import message.QueryFriendRequestMessage;
import message.QueryNeedToChange;
import message.QueryNeedToChangeBackMessage;
import message.RegisterBackMessage;
import message.RegisterMessage;
import message.RemoveFriendMessage;
import message.RemoveOneCoMessage;
import message.RemoveOneFriendRequestMessage;
import message.RequestFriendBackMessage;
import message.RequestFriendMessage;
import message.SetFalseMessage;
import message.ShowOfflineFriendBackMessage;
import message.ShowOfflineFriendMessage;
import message.ShowOnlineFriendBackMessage;
import message.ShowOnlineFriendBackMessage2;
import message.ShowOnlineFriendMessage;
import message.ShowOnlineFriendMessage2;
import message.ShowUserInfoMessage;
import message.TellOthers;
import message.UpdateUserInfo;
import po.RecordPO;
import po.UserPO;
import po.UserState;
import businesslogic.remove.MakeNewPane;
import businesslogic.remove.RemoveAndDrop;
import data.dataoperation.DataOperation;
import data.friendsmanagement.FriendsManagementData;
import dataservice.DataService;

public class Net {
	ServerSocket serverSock;
	public static Pair ppp = new Pair();
	public static ArrayList<User> users = new ArrayList<User>();
	public static ArrayList<Pair> pairs = new ArrayList<Pair>();
	public static ArrayList<PkPair> pk_pairs = new ArrayList<PkPair>();
	public static ArrayList<Single> single = new ArrayList<Single>();

	public Net() {
		try {
			serverSock = new ServerSocket(5000);
			while (true) {
				Socket sock = serverSock.accept();
				User user = new User("", sock); // " "是不可能的id $$$$$$$$
				users.add(user);

				Thread t = new Thread(new ClientHandler(user));
				t.start();

				// GameOverCollector goc = new GameOverCollector();
				// goc.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class ClientHandler implements Runnable {

		User user;
		ObjectInputStream ois;
		boolean ReNet = false;

		public ClientHandler(User user) {
			try {
				this.user = user;
				ois = new ObjectInputStream(user.sock.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void run() {
			Message message;
			try {
				while ((!ReNet) && ((message = (Message) (ois.readObject())) != null)) { // 流为空时该线程会等待
					switch (message.type) {
					case 0:
						RegisterMessage rm = (RegisterMessage) message;
						UserPO userpo = new UserPO();
						userpo.setName(rm.register_name);
						userpo.setPassword(rm.register_password);
						userpo.setPhoto("playerImg1.png");

						DataService ds = new DataOperation();
						boolean id = ds.addUser(userpo);
						String reg = "注册失败";
						if(id) {
							reg = "注册成功";
						}
						
						RegisterBackMessage rbm = new RegisterBackMessage(reg);
						sendMessageToClient(user.oos, rbm);
						break;
					case 2:
						LoginMessage lm = (LoginMessage) message;
						DataService ds1 = new DataOperation();
						UserPO usp = ds1.queryUserByName(lm.login_ID);

						if (usp != null) {
							if (usp.getPassword().equals(lm.login_password)) {
								user.id = lm.login_ID;
								sendMessageToClient(user.oos,
										new LoginBackMessage("登陆成功"));
								user.id = lm.login_ID;
							} else {
								sendMessageToClient(user.oos,
										new LoginBackMessage("登录失败"));
							}
						} else {
							sendMessageToClient(user.oos, new LoginBackMessage(
									"该用户不存在"));
						}
						break;
					case 6:
						AddFriendMessage afm = (AddFriendMessage) message;
						FriendsManagementData fmd = new FriendsManagementData();
						fmd.addFriend(afm.ID1, afm.ID2);
						break;
					case -100:
						RequestFriendMessage rfm = (RequestFriendMessage) message;
						FriendsManagementData fmd2 = new FriendsManagementData();
						boolean ok = fmd2.requestFriend(rfm.ID1, rfm.ID2);
						RequestFriendBackMessage rfbm = new RequestFriendBackMessage(
								ok);
						sendMessageToClient(user.oos, rfbm);
						break;
					case -102:
						QueryFriendRequestMessage qfrm = (QueryFriendRequestMessage) message;
						FriendsManagementData fmd3 = new FriendsManagementData();
						String str = fmd3.queryFriendRequest(qfrm.ID);
						String[] split = str.split(" ");
						ArrayList<String> arr = new ArrayList<String>();
						for (int i = 0; i <= split.length - 1; i++)
							arr.add(split[i]);
						QueryFriendRequestBackMessage qfrbm = new QueryFriendRequestBackMessage(
								arr);
						sendMessageToClient(user.oos, qfrbm);
						break;
					case -106:
						ClearRequestMessage crm = (ClearRequestMessage) message;
						FriendsManagementData fmd4 = new FriendsManagementData();
						fmd4.clearRequest(crm.ID);
						break;
					case -108:
						RemoveOneFriendRequestMessage rofrm = (RemoveOneFriendRequestMessage) message;
						FriendsManagementData fmd5 = new FriendsManagementData();
						fmd5.removeOneFriendRequest(rofrm.ID1, rofrm.ID2);
						break;
					case -110:
						ShowOnlineFriendMessage sofm = (ShowOnlineFriendMessage) message;
						FriendsManagementData fmd6 = new FriendsManagementData();
						String[] split2 = fmd6.showOnlineFriend(sofm.ID).split(
								" ");
						ArrayList<String> online_friend = new ArrayList<String>();
						for (int i = 0; i <= split2.length - 1; i++) {
							online_friend.add(split2[i]);
						}
						ShowOnlineFriendBackMessage sofbm = new ShowOnlineFriendBackMessage(
								online_friend);
						sendMessageToClient(user.oos, sofbm);
						break;
					case -112:
						ShowOfflineFriendMessage sofffm = (ShowOfflineFriendMessage) message;
						FriendsManagementData fmd7 = new FriendsManagementData();
						String[] split3 = fmd7.showOfflineFriend(sofffm.ID)
								.split(" ");
						ArrayList<String> offline_friend = new ArrayList<String>();
						for (int i = 0; i <= split3.length - 1; i++) {
							offline_friend.add(split3[i]);
						}
						ShowOfflineFriendBackMessage sofffbm = new ShowOfflineFriendBackMessage(
								offline_friend);
						sendMessageToClient(user.oos, sofffbm);
						break;
					case -200:
						CooperateRequestMessage corm = (CooperateRequestMessage) message;
						FriendsManagementData fmd8 = new FriendsManagementData();
						for (int i = 0; i <= corm.friend.size() - 1; i++)
							fmd8.cooperateRequest(corm.user,
									corm.friend.get(i));
						break;
					case -202:
						QueryCooperateRequestMessage qcrm = (QueryCooperateRequestMessage) message;
						FriendsManagementData fmd9 = new FriendsManagementData();
						String[] split4 = fmd9.queryCooperateRequest(qcrm.ID)
								.split(" ");
						ArrayList<String> list = new ArrayList<String>();
						for (int i = 0; i <= split4.length - 1; i++) {
							list.add(split4[i]);
						}
						QueryCooperateRequestBackMessage qcrbm = new QueryCooperateRequestBackMessage(
								list);
						sendMessageToClient(user.oos, qcrbm);
						break;
					case 112:
						CorpGameStartMessage mi = (CorpGameStartMessage) message;
						
						Pair newPair = new Pair();
						newPair.player.add(user);
						user.state = UserState.inCorpGame;
						
						GameState gamestate = new GameState(mi.UseTool_C, mi.UseTool_E);
						gamestate.setUseTool_D(mi.UseTool_D);
						gamestate.setUseTool_E(mi.UseTool_E);

						gamestate.setCurrentPane(MakeNewPane.GetInitialPane());
						gamestate.setTool_E(MakeNewPane.getTool_e());
						gamestate.setNeedUseTool_E(false);

						newPair.gameState = gamestate;
						pairs.add(newPair);
						
						for(String lo: mi.UseID) {
							User pd = FindUser(lo);
							
							if(pd != null) {
								newPair.player.add(pd);
								pd.state = UserState.inCorpGame;
								sendMessageToClient(pd.oos, mi);
							}
						}
						System.out.println("游戏GameState已初始化！！！！！！！！！！！！！！！！！！！！！");
						break;

					case 109:
						CorpChangePositionMessage cp = (CorpChangePositionMessage) message;
						Pair p2 = FindCorpPair(cp.UserID);

						ChangePositionMessage mess2 = new ChangePositionMessage();
						mess2.p1 = cp.p1;
						mess2.p2 = cp.p2;
						SendMessageToPair(mess2, p2);

						System.out
								.println(user.id
										+ "已将CorpChangePositionMessage请求的ChangePositionMessage发送！"
										+ user.id);

						Game_info gg1 = new Game_info(p2.gameState);
						RemoveAndDrop rad1 = new RemoveAndDrop(gg1);
						rad1.TwoPositionRemove(cp.p1, cp.p2);
						System.out.println(gg1.getGrade());
						if (gg1.getGrade() != 0) {
							Game_infoMessage mess3 = new Game_infoMessage();
							mess3.gg = gg1;
							SendMessageToPair(mess3, p2);
							System.out
									.println("可以交换消除,Game_infoMessage已发送！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！"
											+ user.id);

						} else {
							ChangePositionMessage mess12 = new ChangePositionMessage();
							mess12.p1 = cp.p1;
							mess12.p2 = cp.p2;

							SendMessageToPair(mess12, p2);
							System.out
									.println("不可以交换消除,ChangePositionMessage已发送！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！"
											+ user.id);
						}
						break;

					case 113:
						CorpUseToolMessage ctm = (CorpUseToolMessage) message;
						Pair p4 = FindCorpPair(ctm.UserID);

						Game_info gg3 = new Game_info(p4.gameState);
						RemoveAndDrop rad3 = new RemoveAndDrop(gg3);
						rad3.UseToolToRemove(ctm.p);

						Game_infoMessage mess = new Game_infoMessage();
						mess.gg = gg3;
						SendMessageToPair(mess, p4);
						System.out
								.println("CorpUseToolMessage,Game_infoMessage已发送！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！"
										+ user.id);
						break;

					case 121:
						CorpGetStateMessage cgsm = (CorpGetStateMessage) message;
						Pair p8 = FindCorpPair(cgsm.UserID);
						
						GameStateMessage lou = new GameStateMessage();
						
						if(p8 != null) {
							lou.CurrentGameState = new GameStateFuBen(p8.gameState);
						}
						sendMessageToClient(user.oos, lou);
						break;

					case 118:
						FinishDropMessage fdm = (FinishDropMessage) message;
						Pair pop = FindCorpPair(fdm.UserID);
						user.CanReceiveMessage = true;

						GameStateMessage luo = new GameStateMessage();
						luo.CurrentGameState = new GameStateFuBen(pop.gameState);
						sendMessageToClient(user.oos, luo);
						System.out
								.println("FinishDropMessage,GameStateMessage已发送！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！"
										+ user.id);
						break;

					case 123:
						FinishMessage fm = (FinishMessage) message;
						Pair popo = FindCorpPair(fm.UserID);
						user.CanReceiveMessage = true;

						if (UsersIsAllReady(fm.UserID)) {
							Game_info go = new Game_info(popo.gameState);
							RemoveAndDrop rad2 = new RemoveAndDrop(go);
							rad2.DirectRemove();

							Game_infoMessage kl = new Game_infoMessage();
							kl.gg = go;
							SendMessageToPair(kl, popo);
							System.out
									.println("FinishMessage,Game_infoMessage已发送！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！"
											+ user.id);
						}

						break;

					// 单击开始游戏
					case 106:
						SingleGameStartMessage sgs = (SingleGameStartMessage) message;
						Single s1 = new Single();
						s1.player = user;
						user.state = UserState.inSingleGame;
						single.add(s1);
						
						GameState gg4 = new GameState(sgs.UseTool_C, sgs.UseTool_E);
						gg4.setUseTool_D(sgs.UseTool_D);

						gg4.setCurrentPane(MakeNewPane.GetInitialPane());
						gg4.setTool_E(MakeNewPane.getTool_e());
						gg4.setNeedUseTool_E(false);

						GameStateMessage gsm = new GameStateMessage();
						gsm.CurrentGameState = new GameStateFuBen(gg4);

						s1.gameState = gg4;
						sendMessageToClient(user.oos, gsm);
						break;

					// 单机交换消除
					case 110:
						SingleChangePositionMessage scpm = (SingleChangePositionMessage) message;

						System.out.println(scpm.UserID);

						Single s2 = FindSingle(scpm.UserID);

						if (s2 != null) {
							GameState gs = s2.gameState;
							Game_info gg = new Game_info(gs);

							RemoveAndDrop rad = new RemoveAndDrop(gg);
							rad.TwoPositionRemove(scpm.p1, scpm.p2);

							Game_infoMessage mes = new Game_infoMessage();
							mes.gg = gg;
							// 发送mess
							sendMessageToClient(user.oos, mes);
						} else {
							System.out.println("s2不存在！");
						}

						break;

					// 单机直接消除
					case 105:
						SingleDirectremoveMessage sdm = (SingleDirectremoveMessage) message;
						System.out.println(sdm.UserID);

						Single s3 = FindSingle(sdm.UserID);

						if (s3 != null) {
							GameState gs1 = s3.gameState;
							Game_info gg5 = new Game_info(gs1);

							RemoveAndDrop rad4 = new RemoveAndDrop(gg5);
							rad4.DirectRemove();

							Game_infoMessage mes = new Game_infoMessage();
							mes.gg = gg5;
							// 发送mess
							sendMessageToClient(user.oos, mes);
						}
						break;

					// 单机道具消除
					case 107:
						SingleUseToolMessage stm = (SingleUseToolMessage) message;
						Single s4 = FindSingle(stm.UserID);

						GameState gs2 = s4.gameState;
						Game_info gg6 = new Game_info(gs2);

						RemoveAndDrop rad5 = new RemoveAndDrop(gg6);
						rad5.UseToolToRemove(stm.p);

						Game_infoMessage mes = new Game_infoMessage();
						mes.gg = gg6;
						// 发送mess
						sendMessageToClient(user.oos, mes);
						break;

					case 119:
						SingleGetStateMessage ssm = (SingleGetStateMessage) message;
						Single ss = FindSingle(ssm.UserID);

						if(ss != null) {
							GameStateMessage gsm2 = new GameStateMessage();
							gsm2.CurrentGameState = new GameStateFuBen(ss.gameState);
							sendMessageToClient(user.oos, gsm2);
						}
						break;

					// Pk开始游戏
					case 103:
						PkGameStartMessage pst = (PkGameStartMessage) message;

						Pair pair1 = new Pair();
						User pu1 = FindUser(pst.UserID1);
						pu1.state = UserState.inPkGame;
						pair1.player.add(pu1);
						
						Pair pair2 = new Pair();
						User pu2 = FindUser(pst.UserID2);
						pu2.state = UserState.inPkGame;
						pair1.player.add(pu2);
						
						GameState gst1 = new GameState(pst.UseTool_C1, pst.UseTool_E1);
						gst1.setUseTool_D(pst.UseTool_D1);
						gst1.setCurrentPane(MakeNewPane.GetInitialPane());
						gst1.setTool_E(MakeNewPane.getTool_e());
						gst1.setNeedUseTool_E(false);
						pair1.gameState = gst1;

						GameState gst2 = new GameState(pst.UseTool_C2, pst.UseTool_E2);
						gst2.setUseTool_D(pst.UseTool_D2);
						gst2.setCurrentPane(MakeNewPane.GetInitialPane());
						gst2.setTool_E(MakeNewPane.getTool_e());
						gst2.setNeedUseTool_E(false);
						pair2.gameState = gst2;

						gst1.setPk_state(gst2);
						gst1.setContainPkState(true);
						
						gst2.setPk_state(gst1);
						gst2.setContainPkState(true);
						
						if(!pu1.id.equals(user.id)) {
							sendMessageToClient(pu1.oos, pst);
						} else {
							sendMessageToClient(pu2.oos, pst);
						}
						break;

					// Pk交换消除
					case 101:
						PkChangePositionMessage pcpm = (PkChangePositionMessage) message;

						Pair pair3 = FindPkPair(pcpm.UserID);
						Pair pair4 = FindPkOtherPair(pcpm.UserID);

						ChangePositionMessage pcpb1 = new ChangePositionMessage();
						pcpb1.p1 = pcpm.p1;
						pcpb1.p2 = pcpm.p2;
						
						SendMessageToPair(pcpb1, pair3);
						SendMessageToPair(pcpm, pair4);

						Game_info gg7 = new Game_info(pair3.gameState);
						RemoveAndDrop rad6 = new RemoveAndDrop(gg7);
						rad6.TwoPositionRemove(pcpm.p1, pcpm.p2);

						if (gg7.getGrade() != 0) {
							Game_infoMessage mess7 = new Game_infoMessage();
							mess7.gg = gg7;
							// 给消除玩家发送mess1
							SendMessageToPair(mess7, pair3);

							PkGame_infoMessage mess8 = new PkGame_infoMessage();
							mess8.gg= gg7;
							// 给Pk玩家发送mess2
							SendMessageToPair(mess8, pair4);
						} else {
							SendMessageToPair(pcpb1, pair3);
							SendMessageToPair(pcpm, pair4);
						}

						break;

					// pk直接消除
					case 102:
						PkDirectRemoveMessage pdrm = (PkDirectRemoveMessage) message;

						Pair pair5 = FindPkPair(pdrm.UserID);
						Pair pair6 = FindPkOtherPair(pdrm.UserID);

						Game_info gg8 = new Game_info(pair5.gameState);
						RemoveAndDrop rad8 = new RemoveAndDrop(gg8);
						rad8.DirectRemove();

						if (gg8.getGrade() != 0) {
							Game_infoMessage mes1 = new Game_infoMessage();
							mes1.gg = gg8;
							// 发送给消除玩家信息mess1
							SendMessageToPair(mes1, pair5);

							PkGame_infoMessage mes2 = new PkGame_infoMessage();
							mes2.gg = gg8;
							SendMessageToPair(mes2, pair6);
						}
						break;

					// Pk道具消除
					case 104:
						PkUseToolMessage putm = (PkUseToolMessage) message;

						Pair pair7 = FindPkPair(putm.UserID);
						Pair pair8 = FindPkOtherPair(putm.UserID);

						Game_info gg9 = new Game_info(pair7.gameState);
						RemoveAndDrop rad9 = new RemoveAndDrop(gg9);
						rad9.UseToolToRemove(putm.p);

						if(rad9.isRemoveTool_B()) {
							pair7.gameState.setHasUseTool_B(true);
						}
						Game_infoMessage mes3 = new Game_infoMessage();
						mes3.gg = gg9;
						SendMessageToPair(mes3, pair7);

						PkGame_infoMessage mes4 = new PkGame_infoMessage();
						mes4.gg = gg9;
						SendMessageToPair(mes4, pair8);

						break;

					case 116:
						PkSelectPositionMessage psp = (PkSelectPositionMessage) message;
						Pair pa1 = FindPkPair(psp.UserID);

						SendMessageToPair(psp, pa1);
						break;

					case 117:
						SelectPositionMessage spm = (SelectPositionMessage) message;
						Pair pa2 = FindCorpPair(spm.UserID);

						for (User u : pa2.player) {
							if (!u.id.equals(u.id)) {
								sendMessageToClient(u.oos, spm);
							}
						}
						break;

					case 120:
						PkGetStateMessage pgsm = (PkGetStateMessage) message;

						Pair pair9 = FindPkPair(pgsm.UserID);
						Pair pair10 = FindPkOtherPair(pgsm.UserID);

						PkGameStateMessage pkmess3 = new PkGameStateMessage();
						pkmess3.self = new GameStateFuBen(pair9.gameState);
						pkmess3.other = new GameStateFuBen(pair10.gameState);

						SendMessageToPair(pkmess3, pair9);
						break;

					case 1212:
						PkPair ho = FindthePKPair(user.id);
						for(User to1: ho.p1.player) {
							to1.state = UserState.OnLine;
						}
						for(User to2: ho.p2.player) {
							to2.state = UserState.OnLine;
						}
						pk_pairs.remove(ho);
						break;
						
					case 122:
						CorpTestMessage ctme = (CorpTestMessage) message;
						user.id = ctme.UserID;
						break;
						
					case 150:
						Single se = FindSingle(user.id);
						GameState g = se.gameState;
						DataService dsd = new DataOperation();
						Date date=new Date();
						SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd#HH:mm:ss");
						String time=dateformat.format(date);
						RecordPO rpg = new RecordPO(g.getTotalGrade(),user.id, g.getMaxLianji(),time);
						dsd.addRecord(rpg);
						user.state = UserState.OnLine;
						
						UserPO loo = dsd.queryUserByName(user.id);
						
						if(loo.getMaxScore() < g.getTotalGrade()) {
							loo.setMaxScore(g.getTotalGrade());
						}
						
						dsd.updateUserByName(user.id, loo);
 						single.remove(se);
						break;
						
					case 151:
						Pair pse = FindCorpPair(user.id);
						for(User o: pse.player) {
							o.state = UserState.OnLine;
						}
						System.out.println("shoudao CorpGameOverMessage-----------------------------");
						pairs.remove(pse);
						break;

					case -10000:
						RemoveFriendMessage rfm2 = (RemoveFriendMessage) message;
						FriendsManagementData fmd10 = new FriendsManagementData();
						fmd10.removeFriend(rfm2.user, rfm2.friend);
						break;

					case -20000:
						ShowUserInfoMessage suim = (ShowUserInfoMessage) message;
						DataOperation dop = new DataOperation();

						UserPO po = dop.queryUserByName(suim.username);
						ArrayList<RecordPO> rp = dop
								.getAllrecordByUserName(suim.username);

						User_info ui = new User_info();
						ui.name = po.getName();
						ui.photo = po.getPhoto();
						ui.max_score = po.getMaxScore();
						ui.game_number = rp.size();

						int maxCombo = 0;
						int allGameGrade = 0;
						ArrayList<String> UsedTime = new ArrayList<String>();

						if ((rp != null) && (rp.size() != 0)) {
							for (RecordPO pob : rp) {
								allGameGrade += pob.getScore();
								ui.per_game_scores.add(pob.getScore() + "#"
										+ pob.getTime());
								int combo = pob.getMaxComboNum();

								if (combo > maxCombo) {
									maxCombo = combo;
								}
								String pob_time = pob.getTime().split("#")[0];
								
								if (!UsedTime.contains(pob_time)) {
									int totalGrade = 0;
									int games = 0;

									
									
									for (RecordPO pod : rp) {
										String pod_time = pod.getTime().split("#")[0];
										if (pob_time.equals(pod_time)) {
											totalGrade += pod.getScore();
											games++;
										}
									}

									if (games != 0) {
										int average_score = totalGrade / games;
										ui.daily_average_scores
												.add(average_score + "#"
														+ pob.getTime());
										ui.daily_game_numbers.add(games + "#"
												+ pob.getTime());
									} else {
										ui.daily_average_scores.add(0 + "#"
												+ pob.getTime());
										ui.daily_game_numbers.add(0 + "#"
												+ pob.getTime());
									}

									UsedTime.add(pob_time);
								}

							}
						}
						
						if(ui.game_number != 0) {
							ui.average_score = allGameGrade/ui.game_number;
						}
						ui.max_combo_number = maxCombo;
						sendMessageToClient(user.oos, ui);
						break;

					case -40000:
						UpdateUserInfo uui = (UpdateUserInfo) message;
						DataOperation dod = new DataOperation();
						UserPO up = dod.queryUserByName(uui.id);

						if (up != null) {
							up.setPhoto(uui.img);
							dod.updateUserByName(uui.id, up);
						}
						break;
					case -50000:
						RemoveOneCoMessage rocm = (RemoveOneCoMessage) message;
						FriendsManagementData fmd11 = new FriendsManagementData();
						fmd11.removeOneCo(rocm.user, rocm.friend);
						break;
					case -60000:
						PermitCoMessage pcm = (PermitCoMessage) message;
						Iterator<User> itr = users.iterator();
						while (itr.hasNext()) {
							User u = itr.next();
							if (u.id.equals(pcm.friend)) {
								sendMessageToClient(u.oos,
										new PartenerBackMessage(u.id, pcm.user,
												"ok"));
								break;
							}
						}
						break;
					case -70000:
						DenyCoMessage dcm = (DenyCoMessage) message;
						Iterator<User> itr2 = users.iterator();
						while (itr2.hasNext()) {
							User u = itr2.next();
							if (u.id.equals(dcm.friend)) {
								sendMessageToClient(u.oos,
										new PartenerBackMessage(u.id, dcm.user,
												"no"));
								break;
							}
						}
						break;
					case 10000:
						TellOthers to = (TellOthers) message;
						FriendsManagementData fmd12 = new FriendsManagementData();
						String[] split5 = fmd12.showOnlineFriend(to.user)
								.split(" ");
						if (!split5[0].equals("")) {
							for (int i = 0; i <= split5.length - 1; i++) {
								Iterator<User> itr3 = users.iterator();
								while (itr3.hasNext()) {
									User u = itr3.next();
									if (u.id.equals(split5[i])) {
										u.needToUpdateInfo = true;
										break;
									}
								}
							}
						}
						break;
					case -200000:
						QueryNeedToChange qntc = (QueryNeedToChange) message;
						Iterator<User> itrx = users.iterator();
						while (itrx.hasNext()) {
							User u = itrx.next();
							if (u.id.equals(qntc.id)) {
								if (u.needToUpdateInfo) {
									sendMessageToClient(u.oos,
											new QueryNeedToChangeBackMessage(
													true));
								} else {
									sendMessageToClient(u.oos,
											new QueryNeedToChangeBackMessage(
													false));
								}
								break;
							}
						}
						break;
					case 1999:
						SetFalseMessage sfm = (SetFalseMessage) message;
						Iterator<User> itrx2 = users.iterator();
						while (itrx2.hasNext()) {
							User u = itrx2.next();
							if (u.id.equals(sfm.id)) {
								u.needToUpdateInfo = false;
								break;
							}
						}
						break;
					case 999:
						boolean is = false;
						IsLogin il = (IsLogin) message;
						Iterator<User> itr3 = users.iterator();
						while (itr3.hasNext()) {
							User u = itr3.next();
							if (u.id.equals(il.user)) {
								is = true;
								break;
							}
						}
						sendMessageToClient(user.oos,new IsLoginBackMessage(is));
						break;
						
					case 162:
						ReNetMessage rnm = (ReNetMessage) message;
						user.id = rnm.UserID;
						System.out.println(user.id + "收到ReNetMessage------------------------------");
						break;
						
					case 166:
						System.out.println("---------------------------------------------------------");
						users.remove(user);
						ReNet = true;
						System.out.println(user.id + "收到CloseNetMessage------------------------------");
						break;
						
					case 888:
						ChangePasswordMessage pu = (ChangePasswordMessage) message;
						DataOperation de = new DataOperation();
						UserPO mom = de.queryUserByName(user.id);
						
						String res = "密码错误";
						
						if(mom.getPassword().equals(pu.old_password)) {
							res = "修改成功";
							mom.setPassword(pu.new_password);
							de.updateUserByName(user.id, mom);
						}
						
						ChangePasswordBackMessage cpb = new ChangePasswordBackMessage(res);
						sendMessageToClient(user.oos, cpb);
						break;
						
					case 777:
						//获取好友分数和好友id
						FriendsManagementData fmd100=new FriendsManagementData();
						String[] friend_list2 = fmd100.showOfflineFriend(user.id).split(" ");
						String[] friend_list = fmd100.showOnlineFriend(user.id).split(" ");
						
						ArrayList<UserPO> li = new ArrayList<UserPO>();
						ArrayList<String> RankList = new ArrayList<String>();
						
						DataOperation doo = new DataOperation();
						UserPO self = doo.queryUserByName(user.id);
						li.add(self);
						
						if(friend_list.length != 0) {
							for(String s: friend_list) {
								if(s != "") {
									UserPO uu = doo.queryUserByName(s);
									li.add(uu);
								}
							}
						}
						
						if(friend_list2.length != 0) {
							for(String s: friend_list2) {
								if(s != "") {
									UserPO uu = doo.queryUserByName(s);
									li.add(uu);
								}
							}
						}
						
						Collections.sort(li);
						
						for(UserPO pi: li) {
							RankList.add(pi.getName() + "#" + pi.getMaxScore());
						}
						
						GetRankListBackMessage glb = new GetRankListBackMessage(RankList);
						sendMessageToClient(user.oos,glb);
						break;
					case -99999:
						ShowOnlineFriendMessage2 sofm2 = (ShowOnlineFriendMessage2) message;
						FriendsManagementData fmd13 = new FriendsManagementData();
						String[] split10 = fmd13.showOnlineFriend(sofm2.ID).split(
								" ");
						ArrayList<String> online_friend2 = new ArrayList<String>();
						for (int i = 0; i <= split10.length - 1; i++) {
							online_friend2.add(split10[i]);
						}
						ShowOnlineFriendBackMessage2 sofbm2 = new ShowOnlineFriendBackMessage2(
								online_friend2);
						sendMessageToClient(user.oos, sofbm2);
						break;
					}
				}
				
				System.out.println(user.id + "ReNet!!!!!!!!!!!!!!!!!!!!!!!!");
			} catch (SocketException e) {
				// 删除一个人
				System.out.println(user.id + "断开");
				FriendsManagementData fmd12 = new FriendsManagementData();
				String[] split5 = fmd12.showOnlineFriend(user.id).split(" ");
				if (split5.length > 0) {
					if (!split5[0].equals("")) {
						for (int i = 0; i <= split5.length - 1; i++) {
							Iterator<User> itr3 = users.iterator();
							while (itr3.hasNext()) {
								User u = itr3.next();
								if (u.id.equals(split5[i])) {
									u.needToUpdateInfo = true;
									break;
								}
							}
						}
					}
				}
				switch (user.state) {
				case inSingleGame:
					Single s1 = FindSingle(user.id);
					s1.gameState = null;
					single.remove(s1);
					break;

				case inCorpGame:
					Pair p1 = FindCorpPair(user.id);
					p1.gameState.setHasUserOffLine(true);
					p1.gameState.getOffLineUser().add(user.id);
					System.out.println(user.id + "离开游戏！！！！！！！！！！！！！！");
					p1.player.remove(user);
					
					if(p1.player.size() == 0) {
						pairs.remove(p1);
					}
					break;

				case inPkGame:
					Pair pb2 = FindPkOtherPair(user.id);
					PkPair je = FindthePKPair(user.id);
					
					if(je != null) {
						je.p1 = null;
						if(pb2 == null) {
							pk_pairs.remove(je);
						} else {
							pb2.gameState.setHasUserOffLine(true);
							pb2.gameState.getOffLineUser().add(user.id);
						}
					}
					break;

				default:
					break;
				}

				users.remove(user);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void SendMessageToPair(Message a, Pair p) {
			try {
				for (User u : p.player) {
					sendMessageToClient(u.oos, a);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public boolean UsersIsAllReady(String id) {
		Pair p = FindCorpPair(id);
		for (User u : p.player) {
			if (!u.CanReceiveMessage) {
				return false;
			}
		}

		return true;
	}

	public PkPair FindthePKPair(String id) {
		for(PkPair jo : pk_pairs) {
			for(User no1: jo.p1.player) {
				if(no1.id.equals(id)) {
					return jo;
				}
			}
			
			for(User no2: jo.p2.player) {
				if(no2.id.equals(id)) {
					return jo;
				}
			}
		}
		
		return null;
	}
	
	public Pair FindCorpPair(String id) {

		for (Pair p : pairs) {
			for (User u : p.player) {
				if (u.id.equals(id)) {
					return p;
				}
			}
		}

		return null;
	}

	public User FindUser(String UserID) {
		for(User o:users) {
			if(o.id.equals(UserID)) {
				return o;
			}
		}
		return null;
	}
	
	public Single FindSingle(String id) {
		for (Single s : single) {
			System.out.println(s.player.id);

			if (s.player.id.equals(id)) {
				return s;
			}
		}

		System.out.println("没找到User！");
		return null;
	}

	public Pair FindPkPair(String id) {

		for (PkPair p : pk_pairs) {
			for (User u : p.p1.player) {
				if (u.id.equals(id)) {
					return p.p1;
				}
			}

			for (User u : p.p2.player) {
				if (u.id.equals(id)) {
					return p.p2;
				}
			}
		}

		return null;
	}

	public Pair FindPkOtherPair(String id) {

		for (PkPair p : pk_pairs) {
			for (User u : p.p1.player) {
				if (u.id.equals(id)) {
					return p.p2;
				}
			}

			for (User u : p.p2.player) {
				if (u.id.equals(id)) {
					return p.p1;
				}
			}
		}

		return null;
	}

	public synchronized void sendMessageToClient(ObjectOutputStream oos,
			Message message) {
		try {
			oos.writeObject(message);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

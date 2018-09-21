package kr.or.kosta.boundary;

import java.awt.Button;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * 초대 받았을 때 뜨는 창
 * @author 송주현
 *
 */
public class ReceiveInvitaionDialog extends Dialog {
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	WaitingRoomPanel waitingPanel;
	
	Label titleL;
	TextArea inviteTA;
	Button acceptB, denyB;
	
	String invitingName; //이 user에게 초대를 보낸 user의 이름

	public ReceiveInvitaionDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		inviteTA = new TextArea();
	}
	
	/**
	 * 이 Dialog 띄우기
	 */
	public void callThisDialog() {
		setContents();
		eventRegist();
		setSize(400, 300);
		setResizable(false);
		setLocation(waitingPanel.getFrame().getCenterPoint());
		setVisible(true);
	}
	
	public void setContents() {
		
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();

		titleL = new Label("Receive Invitation");
		titleL.setFont(waitingPanel.getFrame().titleF);
		titleL.setAlignment(Label.CENTER);
		acceptB = new Button("ACCEPT");
		denyB = new Button("DENY");

		setLayout(gridBagLayout);
		setCons(titleL, 0, 0, 1, 1, 1, 0);
		add(titleL);

		setCons(inviteTA, 0, 1, 1, 1, 0, 0);
		add(inviteTA);

		Panel buttonP = new Panel();
		buttonP.add(acceptB);
		buttonP.add(denyB);
		setCons(buttonP, 0, 2, 1, 1, 0, 0);
		add(buttonP);
	}

	private void setCons(Component component, int gridX, int gridY, int gridWidth, int gridHeight, double weightX,
			double weightY) {
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = gridX; // x축
		gridBagConstraints.gridy = gridY; // y축
		gridBagConstraints.gridwidth = gridWidth;// 몇개의 격자를 합칠 것인가
		gridBagConstraints.gridheight = gridHeight; // 몇개의 격자를 합칠 것인가

		gridBagConstraints.weightx = weightX;
		gridBagConstraints.weighty = weightY;
		gridBagConstraints.insets = new Insets(10, 10, 10, 10);

		gridBagLayout.setConstraints(component, gridBagConstraints);
	}
	

	public WaitingRoomPanel getWaitingPanel() {
		return waitingPanel;
	}

	public void setWaitingRoomPanel(WaitingRoomPanel waitingPanel) {
		this.waitingPanel = waitingPanel;
	}

	/**
	 * 초대창에 초대 받은 방의 정보 띄우기
	 * @param name 초대한 사람 이름
	 * @param roomNum 초대받은 방 번호
	 * @param title 초대받은 방 제목
	 * @param currentNum 초대받은 방의 현재 인원
	 * @param maxNum 초대받은 받의 최대 인원
	 */
	public void setInviteTA(String name, String roomNum,String title,String currentNum,String maxNum) {
		invitingName=name;
		inviteTA.setText("");
		inviteTA.append("초대한 사람 : "+name+"\n");
		inviteTA.append("[No. "+roomNum+"] : "+title+"\n");
		inviteTA.append("memNum : "+currentNum+" &  maxMemNum : "+maxNum+"\n");
	}

	public void finish() {
		setVisible(false);
		dispose();
	}
	
	public void eventRegist() {
		
		//초대 수락하기
		acceptB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				waitingPanel.getFrame().askAcceptInvitation(invitingName);
			}
		});
		
		//초대 거절하기
		denyB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				waitingPanel.getFrame().askDenyInvitation(invitingName);
			}
		});
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
	}

}

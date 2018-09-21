package kr.or.kosta.boundary;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 대기방에서 새 대화방 만들때 뜨는 창
 * 
 * @author 송주현
 *
 */
public class CreateChatRoomDialog extends Dialog {

	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	WaitingRoomPanel waitingPanel;
	
	Label announceL, titleL, memNumL;
	TextField titleTF;
	Choice memNumC;
	Button createB, cancelB;

	public CreateChatRoomDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
	}

	public WaitingRoomPanel getWaitingPanel() {
		return waitingPanel;
	}

	public void setWaitingPanel(WaitingRoomPanel waitingPanel) {
		this.waitingPanel = waitingPanel;
	}
	
	public void callThisDialog() {
		setContents();
		eventRegist();
		setResizable(false);
		setSize(400, 300);
		setLocation(waitingPanel.getFrame().getCenterPoint());
		setVisible(true);
	}

	public void setContents() {

		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		announceL = new Label("Room Create");
		announceL.setFont(waitingPanel.getFrame().titleF);
		announceL.setAlignment(Label.CENTER);
		titleL = new Label("Title");
		titleL.setAlignment(Label.CENTER);
		memNumL = new Label("Max num of member in this room");
		memNumL.setAlignment(Label.CENTER);
		titleTF = new TextField();
		memNumC = new Choice();
		memNumC.add("2");
		memNumC.add("3");
		memNumC.add("4");
		memNumC.add("5");
		memNumC.add("6");
		memNumC.add("7");
		memNumC.add("8");
		memNumC.add("9");
		memNumC.add("10");
		createB = new Button("create");
		cancelB = new Button("cancel");

		setLayout(gridBagLayout);

		setCons(announceL, 0, 0, 1, 1, 1, 0);
		add(announceL);
		setCons(titleL, 0, 1, 1, 1, 1, 0);
		add(titleL);
		setCons(titleTF, 0, 2, 1, 1, 1, 0);
		add(titleTF);
		setCons(memNumL, 0, 3, 1, 1, 1, 0);
		add(memNumL);
		setCons(memNumC, 0, 4, 1, 1, 1, 0);
		add(memNumC);

		Panel buttonP = new Panel(gridBagLayout);
		setCons(createB, 0, 0, 1, 1, 0, 0);
		buttonP.add(createB);
		setCons(cancelB, 1, 0, 1, 1, 0, 0);
		buttonP.add(cancelB);
		setCons(buttonP, 0, 5, 1, 1, 1, 0);
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

		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(component, gridBagConstraints);
	}

	public void eventRegist() {

		createB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 새로 생성된 방 정보 보내기[제목,인원]
				waitingPanel.getFrame().askCreateRoom(titleTF.getText(), memNumC.getSelectedItem());
			}
		});
	
		cancelB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				finish();
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
	}

	public void finish() {
		setVisible(false);
		dispose();
	}
}
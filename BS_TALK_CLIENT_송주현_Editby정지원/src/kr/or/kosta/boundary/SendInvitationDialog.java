package kr.or.kosta.boundary;

import java.awt.Button;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

/**
 * 대화방 내에서 대기방에 있는 다른 User에게 초대창 보낼때 뜨는 창
 * 
 * @author 송주현
 *
 */
public class SendInvitationDialog extends Dialog {

	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	ChatRoomPanel chatPanel;

	List userList;
	Label titleL;
	Button sendB;
	
	String selectedName; //userList에서 선택된 이름 

	public SendInvitationDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
	}

	/**
	 * 이 Dialog 띄우기
	 */
	public void callThisDialog() {
		
		setContents();
		eventRegist();
		setSize(400, 300);
		setResizable(false);
		setLocation(chatPanel.getFrame().getCenterPoint());
		setVisible(true);
	}

	
	
	public void setContents() {
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();

		userList = new List();
		titleL = new Label("send Invitation");
		titleL.setFont(getChatPanel().getFrame().titleF);
		titleL.setAlignment(Label.CENTER);
		sendB = new Button("send");

		setLayout(gridBagLayout);
		setCons(titleL, 0, 0, 1, 1, 1, 0);
		add(titleL);

		setCons(userList, 0, 1, 1, 1, 0, 0);
		add(userList);

		setCons(sendB, 0, 2, 1, 1, 0, 0);
		add(sendB);
	}

	public ChatRoomPanel getChatPanel() {
		return chatPanel;
	}

	public void setChatPanel(ChatRoomPanel chatPanel) {
		this.chatPanel = chatPanel;
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

	/**
	 * 이름 1개를 대기방 user 목록에 추가
	 * @param name 추가할 이름
	 */
	public void addUserList(String name) {
		userList.add(name);
	}
	
	/**
	 * 이름 여러개를 대기방 user목록에 추가
	 * @param names 추가할 이름 배열
	 */
	public void setUserList(String[] names) {
		for(int i=0; i<names.length; i++) {
			addUserList(names[i]);
			System.out.println(names[i]);
		}
	}
	
	/**
	 * 대기방 user 목록 초기화
	 */
	public void clearUserList() {
		userList.removeAll();
	}

	public void eventRegist() {

		userList.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				//리스트 클릭 할 때마다 selectedName 갱신하기
				selectedName=userList.getSelectedItem();
			}
		});
		
		sendB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 초대 보내기
				if(selectedName== null) {
					JOptionPane.showMessageDialog(chatPanel.getFrame(),"초대할 이름이 없습니다");
					return;
				}
				chatPanel.sendInvitation(selectedName);
				setVisible(false);
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
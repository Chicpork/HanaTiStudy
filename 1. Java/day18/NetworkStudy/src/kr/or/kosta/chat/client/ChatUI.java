package kr.or.kosta.chat.client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.JOptionPane;

import kr.or.kosta.chat.common.Protocol;

@SuppressWarnings("serial")
public class ChatUI extends Frame {
	private ChatClient chatClient;
	private String nickName;

	Label nickNameL;
	TextField nickNameTF, inputTF;
	Button connectB, sendB;
	TextArea messageTA;
	List userList;

	MenuBar menuBar;
	Menu menu;
	MenuItem newMI, exitMI;

	public ChatUI() {
		this("이름없음");
	}

	public ChatUI(String title) {
		super(title);
		nickNameL = new Label("대화명");
		nickNameTF = new TextField();
		inputTF = new TextField();
		inputTF.setEditable(false);
		connectB = new Button("연결");
		sendB = new Button("전송");
		messageTA = new TextArea();
		messageTA.setEditable(false);
		messageTA.setBackground(Color.WHITE);
		userList = new List();
		menuBar = new MenuBar();
		menu = new Menu("File");
		newMI = new MenuItem("New");
		newMI.setShortcut(new MenuShortcut(KeyEvent.VK_N));
		exitMI = new MenuItem("Exit");
		exitMI.setShortcut(new MenuShortcut(KeyEvent.VK_X));

	}

	public void setChatClient(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	public String getNickName() {
		return nickName;
	}

	// 화면 배치
	public void setContents() {

		Panel northP = new Panel();
		northP.setLayout(new BorderLayout());
		northP.add(nickNameL, BorderLayout.WEST);
		northP.add(nickNameTF, BorderLayout.CENTER);
		northP.add(connectB, BorderLayout.EAST);

		Panel southP = new Panel(new BorderLayout());
		southP.add(inputTF, BorderLayout.CENTER);
		southP.add(sendB, BorderLayout.EAST);

		add(northP, BorderLayout.NORTH);
		add(messageTA, BorderLayout.CENTER);
		add(userList, BorderLayout.EAST);
		add(southP, BorderLayout.SOUTH);

		setMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(newMI);
		menu.addSeparator();
		menu.add(exitMI);

	}

	public void setCenter() {
		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		int width = (int) ((dim.getWidth() - this.getWidth()) / 2);
		int height = (int) ((dim.getHeight() - getSize().getHeight()) / 2);
		setLocation(width, height);
	}

	public void connect() {
		try {
			// 최초 연결 메시지 전송
			nickName = nickNameTF.getText().trim();
			if (nickName.isEmpty()) {
				throw new Exception("닉네임이 비어있습니다.");
			}
			
			chatClient.connectServer();
			chatClient.sendMessage(Protocol.CONNECT + Protocol.DELEMETER + nickName + Protocol.DELEMETER + Calendar.getInstance().getTimeInMillis());
			chatClient.receiveMessage();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "연결 실패", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void connectEnable(boolean value) {
		nickNameTF.setEnabled(value);
		connectB.setEnabled(value);
		inputTF.setEditable(!value);
	}

	public void sendMessage() {
		String message = inputTF.getText().trim();
		if (message == null || message.length() == 0) {
			return;
		}
		chatClient.sendMessage(
				Protocol.MULTI_CHAT_MESSAGE + Protocol.DELEMETER + nickName + Protocol.DELEMETER + message);

		inputTF.setText("");
	}

	public void appendMessage(String message) {
		messageTA.append(message + "\n");
		inputTF.setText("");
	}

	public void addUserList(String nickName) {
		userList.add(nickName);
	}

	public void removeUserList(String nickName) {
		userList.remove(nickName);
	}

	public void finish() {
		chatClient.sendMessage(Protocol.DISCONNECT + Protocol.DELEMETER + nickName);
		setVisible(false);
		dispose();
		System.exit(0);
	}

	public void eventRegist() {
		nickNameTF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});

		connectB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});

		inputTF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

		sendB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

		userList.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String name = userList.getSelectedItem();
					JOptionPane.showMessageDialog(null, name + "님 선택이요..", "알림", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		exitMI.addActionListener(new ActionListener() {
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
}

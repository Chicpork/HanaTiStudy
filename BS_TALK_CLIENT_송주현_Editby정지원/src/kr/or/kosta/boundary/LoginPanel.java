package kr.or.kosta.boundary;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class LoginPanel extends Panel {

	MainFrame frame; // CardLayout으로 MainFrame을 제어하기 위해 선언

	JLabel titleL, nickNameL;
	TextField nickNameTF;
	Button loginB;

	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;

	public LoginPanel(MainFrame frame) {
		this.frame = frame;
		frame.setSize(400, 600);
		titleL = new JLabel("BS TALK");
		titleL.setHorizontalAlignment(JLabel.CENTER);
		titleL.setFont(new Font("Britannic Bold", Font.BOLD, 55));

		nickNameL = new JLabel("Typing your nicname..");
		nickNameL.setHorizontalAlignment(JLabel.CENTER);
		nickNameTF = new TextField();
		loginB = new Button("Enter");
	}

	public void setContents() {

		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();

		Panel loginP = new Panel(gridBagLayout);

		setCons(titleL, 0, 0, 1, 1, 1, 0);
		loginP.add(titleL);
		setCons(nickNameL, 0, 1, 1, 1, 1, 0);
		loginP.add(nickNameL);
		setCons(nickNameTF, 0, 2, 1, 1, 1, 0);
		loginP.add(nickNameTF);
		setCons(loginB, 0, 3, 1, 1, 1, 0);
		loginP.add(loginB);

		titleL.setOpaque(true);
		titleL.setBackground(new Color(180, 235, 255));
		BorderLayout borderLayout = new BorderLayout();
		nickNameTF.setBackground(new Color(180, 235, 255));
		loginB.setBackground(new Color(180, 235, 255));

		setLayout(borderLayout);
		add(loginP);

	}

	private void setCons(Component component, int gridX, int gridY, int gridWidth, int gridHeight, double weightX,
			double weightY) {
		gridBagConstraints.gridx = gridX; // x축
		gridBagConstraints.gridy = gridY; // y축
		gridBagConstraints.gridwidth = gridWidth;// 몇개의 격자를 합칠 것인가
		gridBagConstraints.gridheight = gridHeight; // 몇개의 격자를 합칠 것인가

		gridBagConstraints.weightx = weightX;
		gridBagConstraints.weighty = weightY;
		gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(10, 70, 10, 70);

		gridBagLayout.setConstraints(component, gridBagConstraints);
	}

	public String getNickNameTF() {
		return nickNameTF.getText().trim();
	}

	public void eventRegist() {

		loginB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 소켓 열림 & 서버에 연결
				// frame.getChatClient().setMyName(myName);
				frame.connect();
			}
		});

		nickNameTF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 소켓 열림 & 서버에 연결
				frame.connect();
			}
		});
	}
}
package day10_11;

import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

public class Practice extends Panel {
	Button searchB, sendB, cancleB;
	TextField receiverTF, fileTF, titleTF, mainTF;
	TextArea mainTA;
	Label receiverL, fileL, titleL, blankL;
	GridBagLayout bagLayout;
	GridBagConstraints bagConstraints;
	Panel buttonsP, searchP;

	public Practice() {
		searchB = new Button("찾기");
		sendB = new Button("보내기");
		cancleB = new Button("취 소");
		receiverTF = new TextField();
		fileTF = new TextField();
		titleTF = new TextField();
		mainTA = new TextArea();
		mainTF = new TextField();
		receiverL = new Label("받는사람");
		fileL = new Label("첨부파일");
		titleL = new Label("제목");
		bagLayout = new GridBagLayout();
//		bagConstraints = new GridBagConstraints();
		blankL = new Label();
		buttonsP = new Panel();
		searchP = new Panel(new FlowLayout(FlowLayout.LEFT));
	}

	public void setContents() {
		setLayout(bagLayout);

		setCon(0, 0, 1, 1, 0, 0);
		setCon2(false, false, new Insets(5, 10, 5, 0));
		bagLayout.setConstraints(receiverL, bagConstraints);
		add(receiverL);

//		bagConstraints.fill = GridBagConstraints.HORIZONTAL;
//		gridBagConstraints.insets = new Insets(5,5,5,5);
//		bagLayout.setConstraints(searchB, bagConstraints);
//		add(searchB);
		setCon(1, 0, 1, 1, 2, 0);
		setCon2(true, false, new Insets(5, 0, 5, 0));
		bagLayout.setConstraints(receiverTF, bagConstraints);
		add(receiverTF);

		setCon(2, 0, 1, 1, 1, 0);
		bagLayout.setConstraints(blankL, bagConstraints);
		add(blankL);

		setCon(0, 1, 1, 1, 0, 0);
		setCon2(false, false, new Insets(5, 10, 5, 0));
		bagLayout.setConstraints(fileL, bagConstraints);
		add(fileL);

		setCon(1, 1, 1, 1, 2, 0);
		setCon2(true, false, new Insets(5, 0, 5, 0));
		bagLayout.setConstraints(fileTF, bagConstraints);
		add(fileTF);

		searchP.add(searchB);
		setCon(2, 1, 1, 1, 1, 0);
		setCon2(true,false,null);
		bagLayout.setConstraints(searchP, bagConstraints);
		add(searchP);

		setCon(0, 3, 1, 1, 0, 0);
		setCon2(false, false, new Insets(5, 10, 5, 0));
		bagLayout.setConstraints(titleL, bagConstraints);
		add(titleL);

		setCon(1, 3, 2, 1, 1, 0);
		setCon2(true, false, new Insets(5, 0, 5, 10));
		bagLayout.setConstraints(titleTF, bagConstraints);
		add(titleTF);

		setCon(0, 4, 3, 10, 1, 1);
		setCon2(true, true, new Insets(10, 10, 10, 10));
		bagLayout.setConstraints(mainTA, bagConstraints);
		add(mainTA);
		
		buttonsP.add(sendB);
		buttonsP.add(cancleB);
		setCon(0,14,3,1,1,0);
		setCon2(true,true,new Insets(0,0,10,0));
		bagLayout.setConstraints(buttonsP, bagConstraints);
		add(buttonsP);

	}

	private void setCon(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty) {
		bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = gridx;
		bagConstraints.gridy = gridy;
		bagConstraints.gridwidth = gridwidth;
		bagConstraints.gridheight = gridheight;
		bagConstraints.weightx = weightx;
		bagConstraints.weighty = weighty;
	}

	private void setCon2(boolean hori, boolean verti, Insets insets) {
		if (hori && verti) {
			bagConstraints.fill = GridBagConstraints.BOTH;
		}else if (hori) {
			bagConstraints.fill = GridBagConstraints.HORIZONTAL;
		}else if (verti) {
			bagConstraints.fill = GridBagConstraints.VERTICAL;
		}
		if (insets != null) {
			bagConstraints.insets = insets;
		}
	}

	public static void main(String[] args) {
		Frame frame = new Frame("Pactice!!");

		Practice practice = new Practice();
		practice.setContents();

		frame.add(practice);
		frame.pack();
		frame.setVisible(true);

	}

}

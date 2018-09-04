package project_memo;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MemoUI3 extends Panel {
	TextArea mainTA;
	MenuBar menuBar;
	Menu fileM;
	MenuItem newMI, openMI, saveMI, exitMI;
	Choice mainFontSizeC, mainFontFamilyC;
	Frame frame;
	FileDao fileDao;
	Font mainFont;
	

	public MemoUI3(Frame frame) {
		this.frame = frame;
		mainTA = new TextArea();
		menuBar = new MenuBar();
		fileM = new Menu("File");
		newMI = new MenuItem("new");
		openMI = new MenuItem("open");
		saveMI = new MenuItem("save");
		exitMI = new MenuItem("exit");
		mainFontSizeC = new Choice();
		mainFontSizeC.add("10");
		mainFontSizeC.add("12");
		mainFontSizeC.add("14");
		mainFontSizeC.add("16");
		mainFontSizeC.add("18");
		mainFontSizeC.add("20");
		mainFontSizeC.select(2);
		mainFontFamilyC = new Choice();
		mainFontFamilyC.add("바탕");
		mainFont = new Font("Batang", Font.PLAIN, 14);
	}

	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	public void setContents() {
		menuBar.add(fileM);
		fileM.add(newMI);
		fileM.add(openMI);
		fileM.addSeparator();
		fileM.add(saveMI);
		fileM.addSeparator();
		fileM.add(exitMI);
		frame.setMenuBar(menuBar);

		mainTA.setFont(mainFont);
		setLayout(new BorderLayout());
		add(mainFontSizeC,BorderLayout.NORTH);
		add(mainTA, BorderLayout.CENTER);
	}

	public void eventRegist() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});

		newMI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newMemo();
			}
		});
		openMI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadMemo();
			}
		});
		saveMI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveMain();
			}
		});
		exitMI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				finish();
			}
		});
		mainFontSizeC.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				setMainFontSize();
			}
		});
	}

	public void newMemo() {
		mainTA.setText("");
	}

	public void loadMemo() {
		FileDialog fd = new FileDialog(frame, "파일 저장", FileDialog.LOAD);
		fd.setVisible(true);
		String directory = fd.getDirectory();
		String filename = fd.getFile();
		try {
			newMemo();
			mainTA.setText(fileDao.loadFile(directory, filename));
		} catch (MemojangException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "에러 발생", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveMain() {
		FileDialog fd = new FileDialog(frame, "파일 저장", FileDialog.SAVE);
		fd.setVisible(true);
		String directory = fd.getDirectory();
		String filename = fd.getFile();
		try {
			
			fileDao.saveFile(mainTA.getText(), directory, filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setMainFontSize() {
		int fontsize = Integer.parseInt(mainFontSizeC.getSelectedItem());
		mainFont = new Font("돋음", Font.PLAIN, fontsize);
		mainTA.setFont(mainFont);
	}

	public void finish() {
		frame.setVisible(false);
		frame.dispose();
		System.exit(0);
	}

}

package project_memo;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class MemoUI_save extends JPanel {
	JTextArea mainTA;
	JScrollPane mainSP;
	MenuBar menuBar;
	Menu fileM;
	MenuItem newMI, openMI, saveMI, exitMI;
	Choice mainFontSizeC;
	Label mainFontSizeL;
	Frame frame;
	FileDao fileDao;

	public MemoUI_save(Frame frame) {
		this.frame = frame;
		mainTA = new JTextArea(10,20);
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
		mainFontSizeC.add("25");
		mainFontSizeC.add("30");
		mainFontSizeC.select(7);
		mainFontSizeL = new Label("폰트 사이즈");
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
		mainTA.setFont(new Font("batang", Font.PLAIN, 30));
		
		setLayout(new BorderLayout());
		mainSP = new JScrollPane(mainTA);
		mainSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(mainFontSizeC,BorderLayout.NORTH);
		add(mainSP, BorderLayout.CENTER);
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
		mainTA.setFont(new Font("batang", Font.PLAIN, fontsize));
	}

	public void finish() {
		frame.setVisible(false);
		frame.dispose();
		System.exit(0);
	}

}

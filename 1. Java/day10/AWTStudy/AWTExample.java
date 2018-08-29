package day10;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;

public class AWTExample {

	public static void main(String[] args) {

		Frame frame = new Frame("처음으로 만든 프레임");
		frame.setSize(600, 500);
		frame.setVisible(true);
		
		Button button1 = new Button("AWT 버튼1");
		Button button2 = new Button("AWT 버튼2");
		
		// FlowLayout()은 글자를 적어나가는 것처럼
		// 계속 오른쪽으로 이어나가다 가득 차면 다음 줄로 넘어가는 방법
		frame.setLayout(new FlowLayout());
		frame.add(button1);
		frame.add(button2);
		
		
		Label label = new Label("AWT Label");
		frame.add(label);
		
		TextField textField = new TextField("ID", 10);
		frame.add(textField);
		
		TextArea textArea = new TextArea(5, 30);
		frame.add(textArea);
	
		Checkbox checkbox = new Checkbox("남자", true);
		frame.add(checkbox);
		
		// 라디오 버튼 만드는 것. 그룹을 먼저 만들고 그룹안에 체크박스를 넣는 것.
		CheckboxGroup cg = new CheckboxGroup();
		Checkbox cb1 = new Checkbox("Male",true,cg);
		Checkbox cb2 = new Checkbox("Female",false,cg);
		frame.add(cb1);
		frame.add(cb2);
		
		Choice selectBox = new Choice();
		selectBox.add("박지성");
		selectBox.add("박찬호");
		selectBox.add("박찬숙");
		frame.add(selectBox);
		
//		frame.setResizable(false);
	}

}

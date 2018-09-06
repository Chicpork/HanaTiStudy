package day15;

import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Vector;

public class ObjectStreamExample {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, AccountException {
		
		String path = "example7.ser";
		
		int age = 20;
		double weight = 77.7;
		String message = "오브젝트 스트림 실습";
		Calendar today = Calendar.getInstance();
		Frame frame = new Frame("타이틀");
		frame.setSize(500,200);
		
		Account account = new Account("1111-2222-3333", "정지원", 1234, 100000);
		
		Vector<Account> vector = new Vector<>();
		for(int i=0;i<50000;i++) {
			vector.addElement(new Account(i+"th","o",1,1));
		}
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
		out.writeObject(age);
		out.writeObject(weight);
		out.writeObject(message);
		out.writeObject(today);
		out.writeObject(frame);
		out.writeObject(account);
		out.writeObject(vector);
		
		/* 
		 * 이런 방법으로 writeObject를 실행할때 유효성 검사를 한다.
		if(!(account instanceof Serializable)) {
			throw new NotSerializableException();
		}
		*/
		
		out.flush();
		out.close();
		
		System.out.println("다 썻음");
		
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
		age = 0;
		weight = 0;
		
		message = null;
		today = null;
		frame = null;
		account = null;
		vector = null;
		
		age = (Integer)in.readObject();
		weight = (Double)in.readObject();
		message = (String)in.readObject();
		today = (Calendar)in.readObject();
		frame = (Frame)in.readObject();
		account = (Account)in.readObject();
		vector = (Vector<Account>)in.readObject();
		
		System.out.println(age);
		System.out.println(weight);
		System.out.println(message);
		System.out.println(today);
		frame.setVisible(true);
		System.out.println(account);
		System.out.println(vector.size());
		
		in.close();
	}

}

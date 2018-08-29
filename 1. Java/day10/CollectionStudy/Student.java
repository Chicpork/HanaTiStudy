package day10;

import java.util.HashMap;
import java.util.Map;

public class Student {
	Map<String, Object> prop;

	public Student() {
		prop = new HashMap<String, Object>();
	}

	public Map<String, Object> getProp() {
		return prop;
	}

	public void setProp(Map<String, Object> prop) {
		this.prop = prop;
	}
	
	public void setProperty(String key, Object value) {
		prop.put(key, value);
	}
	
	public Object getProperty(String key) {
		return prop.get(key);
	}

	public static void main(String[] args) {
		Student student = new Student();
		student.setProperty("ssn", "88544");
		student.setProperty("name", "박소연");
		student.setProperty("address", "LA");
	}
	
}

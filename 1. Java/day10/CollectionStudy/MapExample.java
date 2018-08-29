package day10;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapExample {

	public static void main(String[] args) {
	
		Map<String, String> map = new HashMap<>();
		
		// HashMap은 똑같은 Key값으로 저장시키면 나중에 들어온 데이터로 덮어 씌운다.
		map.put("931022", "홍길동");
		map.put("691022", "정길동");
		map.put("871022", "박길동");
		map.put("781022", "김길동");
		
		if(map.containsKey("931022")) {
			System.out.println("존재하는 키입니다.");
		}else {
			map.put("931022", "조길동");
		}
		
		System.out.println(map.get("931022"));
		
		Set<String> keyList = map.keySet();
		for (String string : keyList) {
			System.out.println(string);
		}
		
		Collection<String> values = map.values();
		for (String string : values) {
			System.out.println(string);
		}
		
	}

}

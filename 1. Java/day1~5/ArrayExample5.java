/**
 * 레퍼런스타입 배열 선언, 생성, 초기화
 * 다차원 배열
 * 
 * @author 정지원
 *
 */
public class ArrayExample5 {
	public static void main(String[] args) {
		String[][] array = new String[3][100];
		array[0][0] = "AAA";
		array[2][0] = "ZZZ";

//		String[][] array2 = new String[][] { { "a", "b", "c" }, { "d", "e", "f" }, { "g", "h", "f" } };
		String[][] array2 = { { "a", "b", "c" }, { "d", "e", "f" }, { "g", "h", "f" } };
		
	}
}

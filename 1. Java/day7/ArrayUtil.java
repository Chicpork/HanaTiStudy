package day7;
/**
 * 배열 관련한 공통 기능 정의
 * 
 * @author 정지원
 */
public class ArrayUtil {
	/**
	 * 배열 복사
	 * 
	 * @param src       복사하고자 하는 원본 배열
	 * @param increment 증가치
	 * @param output    복사한 새로운 배열
	 * @return 복사된 배열 반환
	 */
	public static int[] duplicate(int[] src, int increment) {
		int[] output = new int[src.length + increment];
		for (int i = 0; i < src.length; i++) {
			output[i] = src[i];
		}
		return output;
	}

	/**
	 * 배열 오름차순 정렬
	 * 
	 * @param src  정렬하고자 하는 원본 배열
	 * @param temp 임시 저장 공간
	 */
	public static void sort(int[] src) {
		int temp = 0;
		for (int i = 0; i < src.length - 1; i++) {
			for (int j = 0; j < src.length - 1; j++) {
				if (src[j] > src[j + 1]) {
					temp = src[j];
					src[j] = src[j + 1];
					src[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 배열 내림차순 정렬
	 * 
	 * @param src  정렬하고자 하는 원본 배열
	 * @param temp 임시 저장 공간
	 */
	public static void sortInverse(int[] src) {
		sort(src);
		int temp = 0;
		for (int i = 0; i < src.length / 2; i++) {
			temp = src[i];
			src[i] = src[src.length - i - 1];
			src[src.length - i - 1] = temp;
		}
	}
}

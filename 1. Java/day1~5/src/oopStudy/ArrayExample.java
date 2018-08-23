package oopStudy;
/**
 * 1차원 배열 선언, 생성 초기화
 * 
 * @author 정지원
 */
public class ArrayExample {

	public static void main(String[] args) {
		int[] array;
		array = new int[10];
		array[0] = 10;
		array[9] = 100;
//		System.out.println(array[0]);
//		System.out.println(array[9]);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
//		int[] array2=new int[] {1,2,3,4,5};
		int[] array2 = { 1, 2, 3, 4, 5 };
		for (int i = 0; i < array2.length; i++) {
			System.out.println(array2[i]);
		}
		System.out.println("========");

		// 미션 1(배열 복사)
		int[] array3 = { 3, 1, 9, 2, 5 };
		int[] array4 = new int[7];
		array4 = ArrayUtil.duplicate(array3, 2);
		for (int i : array4) {
			System.out.print(i + " ");
		}
		System.out.println("\n========");

		// 미션 2(정렬)
		int[] lottos = new int[8];
		int temp = 0;
		for (int i = 0; i < lottos.length; i++) {
			temp = (int) (Math.random() * 45) + 1;
			HERE: for (int j = 0; j < i; j++) {
				if (lottos[j] == temp) {
					temp = (int) (Math.random() * 45) + 1;
					break HERE;
				}
			}
			lottos[i] = temp;
		}

		// 정렬 코드
		ArrayUtil.sortInverse(lottos);
		for (int i : lottos) {
			System.out.print(i + " ");
		}
		System.out.println("\n========");
	}	
}
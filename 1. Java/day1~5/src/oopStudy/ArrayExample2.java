package oopStudy;
/**
 * 다차원 배열 테스트
 * 
 * @author 정지원
 *
 */
public class ArrayExample2 {

	public static void main(String[] args) {
		char[][] array;
		array = new char[10][10];
		array[0][0] = 'A';
		array[9][9] = 'Z';
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}

//		int[][] twoDim1 = new int[][] {{1,2,3,},{4,5,6},{7,8,9}};
//		int[][] twoDim2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] gugudan = new int[8][9];
		for (int i = 0; i < gugudan.length; i++) {
			for (int j = 0; j < gugudan[i].length; j++) {
				gugudan[i][j] = (i + 2) * (j + 1);
				System.out.print((i + 2) + " * " + (j + 1) + " = " + gugudan[i][j] + "\t");
			}
			System.out.println();
		}
	}
}

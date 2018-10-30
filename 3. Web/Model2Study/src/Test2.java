import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test2 {

	public static void main(String[] args) {
		Map<Integer, List<Cell>> passTime = new HashMap<>();
		List<Cell> cells = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int time = sc.nextInt();

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				cells.add(new Cell(0, sc.nextInt(), 0, i, j));
			}
		}
		passTime.put(0, cells);
		for (int i = 1; i <= time; i++) {
			for (Cell cell : cells) {
			}
		}

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				cells.get(i + j);
			}
		}
	}

	public static class Cell {
		int status;
		int life;
		int lifeCount;
		int x;
		int y;

		public Cell() {
		}

		public Cell(int status, int life, int lifeCount, int x, int y) {
			this.status = status;
			this.life = life;
			this.lifeCount = lifeCount;
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void passTime() {
		
	}
	
	public static int[] getXs(List<Cell> cells) {
		int[] x = new int[cells.size()];
		int count = 0;
		for (Cell cell : cells) {
			x[count++] = cell.x;
		}
		return x;
	}
	
	public static int[] getYs(List<Cell> cells) {
		int[] y = new int[cells.size()];
		int count = 0;
		for (Cell cell : cells) {
			y[count++] = cell.y;
		}
		return y;
	}
}

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Baekjoon2447 {
	static Scanner scanner = new Scanner(System.in);
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static char[][] grid;
	static int N;
	
	public static void main(String[] args) throws IOException {
		N = scanner.nextInt();
		grid = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				grid[i][j] = ' ';
			}
		}
		
		makeStar(N, 0, 0);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				bw.write(grid[i][j]);
			}
			
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	static void makeStar(int size, int x, int y) {
		if(size == 1) {
			grid[x][y] = '*';
			return;
		}
		
		int nextSize = size / 3;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(i == 1 && j == 1) {
					continue;
				}
				
				makeStar(nextSize, x + i * nextSize, y + j * nextSize);
			}
		}
	}
}

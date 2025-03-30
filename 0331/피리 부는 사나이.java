import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon16724 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[][] map;
	static boolean[][] visited;
	static int N, M, safeZoneCount = 0;
	
	public static void main(String[] args) throws IOException {
		String[] size = br.readLine().split(" ");
		N = Integer.parseInt(size[0]);
		M = Integer.parseInt(size[1]);
		map = new char[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			String[] oneLine = br.readLine().split("");
			
			for(int j = 0; j < M; j++) {
				map[i][j] = oneLine[j].charAt(0);
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					dfs(i, j);
					safeZoneCount++;
				}
			}
		}
		
		System.out.println(safeZoneCount);
	}
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		
		int[] mx = {-1, 1, 0, 0};
		int[] my = {0, 0, -1, 1};
		
		for(int i = 0; i < 4; i++) {
			int nextX = x + mx[i];
			int nextY = y + my[i];
			
			if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) {
				if(i == 0 && map[nextX][nextY] == 'D' && !visited[nextX][nextY]) {
					dfs(nextX, nextY);
				}else if(i == 1 && map[nextX][nextY] == 'U' && !visited[nextX][nextY]) {
					dfs(nextX, nextY);
				}else if(i == 2 && map[nextX][nextY] == 'R' && !visited[nextX][nextY]) {
					dfs(nextX, nextY);
				}else if(i == 3 && map[nextX][nextY] == 'L' && !visited[nextX][nextY]) {
					dfs(nextX, nextY);
				}
				
				if(i == 0 && map[x][y] == 'U' && !visited[nextX][nextY]) {
					dfs(nextX, nextY);
				}else if(i == 1 && map[x][y] == 'D' && !visited[nextX][nextY]) {
					dfs(nextX, nextY);
				}else if(i == 2 && map[x][y] == 'L' && !visited[nextX][nextY]) {
					dfs(nextX, nextY);
				}else if(i == 3 && map[x][y] == 'R' && !visited[nextX][nextY]) {
					dfs(nextX, nextY);
				}
			}
		}
	}
}

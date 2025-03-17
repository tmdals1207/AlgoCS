import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon3987 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static char[][] map;
	static boolean[][][] visited;
	
	public static void main(String[] args) throws IOException {
		String[] size = br.readLine().split(" ");
		N = Integer.parseInt(size[0]);
		M = Integer.parseInt(size[1]);
		map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String oneLine = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = oneLine.charAt(j);
			}
		}
		
		String[] startInfo = br.readLine().split(" ");
		int startX = Integer.parseInt(startInfo[0]) - 1;
		int startY = Integer.parseInt(startInfo[1]) - 1;
		char[] dir = {'U', 'R', 'D', 'L'};
		int maxTime = -1;
		char maxTimeDir = ' ';
		
		for(int i = 0; i < 4; i++) {
			visited = new boolean[N][M][4];
			int result = dfs(startX, startY, i, 0);
			
			if(result == -1) {
				System.out.println(dir[i]);
				System.out.println("Voyager");
				return;
			}
			
			if(maxTime < result) {
				maxTime = result;
				maxTimeDir = dir[i];
			}
		}
		
		System.out.println(maxTimeDir);
		System.out.println(maxTime);
	}
	
	static int dfs(int x, int y, int direction, int time) {
		int result = -1;
		
		if(visited[x][y][direction]) {
			return -1;
		}
		
		visited[x][y][direction] = true;
		
		if(direction == 0) {
			int nextX = x - 1;
			int nextY = y;
			int nextTime = time + 1;
			
			if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || map[nextX][nextY] == 'C') {
				return nextTime;
			}
			
			if(map[nextX][nextY] == '/') {
				result = dfs(nextX, nextY, 1, nextTime);
			}else if(map[nextX][nextY] == '\\') {
				result = dfs(nextX, nextY, 3, nextTime);
			}else {
				result = dfs(nextX, nextY, 0, nextTime);
			}
		}else if(direction == 1) {
			int nextX = x;
			int nextY = y + 1;
			int nextTime = time + 1;
			
			if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || map[nextX][nextY] == 'C') {
				return nextTime;
			}
			
			if(map[nextX][nextY] == '/') {
				result = dfs(nextX, nextY, 0, nextTime);
			}else if(map[nextX][nextY] == '\\') {
				result = dfs(nextX, nextY, 2, nextTime);
			}else {
				result = dfs(nextX, nextY, 1, nextTime);
			}
		}else if(direction == 2) {
			int nextX = x + 1;
			int nextY = y;
			int nextTime = time + 1;
			
			if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || map[nextX][nextY] == 'C') {
				return nextTime;
			}
			
			if(map[nextX][nextY] == '/') {
				result = dfs(nextX, nextY, 3, nextTime);
			}else if(map[nextX][nextY] == '\\') {
				result = dfs(nextX, nextY, 1, nextTime);
			}else {
				result = dfs(nextX, nextY, 2, nextTime);
			}
		}else if(direction == 3) {
			int nextX = x;
			int nextY = y - 1;
			int nextTime = time + 1;
			
			if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || map[nextX][nextY] == 'C') {
				return nextTime;
			}
			
			if(map[nextX][nextY] == '/') {
				result = dfs(nextX, nextY, 2, nextTime);
			}else if(map[nextX][nextY] == '\\') {
				result = dfs(nextX, nextY, 0, nextTime);
			}else {
				result = dfs(nextX, nextY, 3, nextTime);
			}
		}
		
		return result;
	}
}

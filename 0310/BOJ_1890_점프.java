import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1890 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[][] map;
	static long[][] dp;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new long[N][N];
		
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				dp[i][j] = -1;
			}
		}
		
		System.out.println(dfs(0, 0));
	}
	// (x, y)에서 (N - 1, N - 1)까지 가는 경로의 수를 dp[x][y]에 저장해두고, 
	// 탐색 도중 dp[x][y]가 -1이면 dfs로 계속 탐색하고, -1이 아니면 이미 해당 지점에서 목적지까지
	// 도달 가능한 경로의 수가 저장되어 있는 것이므로 dp[x][y]를 리턴하여 중복 탐색을 방지한다.
	static long dfs(int x, int y) {
		if(x == N - 1 && y == N - 1) {
			return 1;
		}
		
		if(dp[x][y] != -1) { 
			return dp[x][y];
		}
		
		dp[x][y] = 0;
		int jumpDist = map[x][y];
		int[] mr = {jumpDist, 0};
		int[] mc = {0, jumpDist};
		
		for(int i = 0; i < 2; i++) {
			int nextX = x + mr[i];
			int nextY = y + mc[i];
			
			if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
				dp[x][y] += dfs(nextX, nextY);
			}
		}
		
		return dp[x][y];
	}
}

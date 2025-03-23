import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1932 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] grid;
	static int[][] dp;
	static int n;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		n = Integer.parseInt(br.readLine());
		grid = new int[n][n];
		dp = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			String[] oneLine = br.readLine().split(" ");
			
			for(int j = 0; j < oneLine.length; j++) {
				grid[i][j] = Integer.parseInt(oneLine[j]);
				dp[i][j] = -1;
			}
		}
		
		System.out.println(getMaxSum(0, 0));
	}
	
	static int getMaxSum(int i, int j) {
		if(i == n - 1) {
			return grid[i][j];
		}
		
		if(dp[i][j] != -1) {
			return dp[i][j];
		}
		
		int left = grid[i][j] + getMaxSum(i + 1, j);
		int right = grid[i][j] + getMaxSum(i + 1, j + 1);
		dp[i][j] = Math.max(left, right);
		
		return dp[i][j];
	}
}

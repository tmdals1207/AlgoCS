package s0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * n*n 게임판에서 가장 왼쪽 위에서 출발, 맨 오른쪽 아래로 이동
 * 게임판에 적혀있는 수는 갈 수 있는 거리, 도착지점인 맨 오른쪽 아래는 반드시 0
 */
public class Main_BJ1890 {										//점프
	static int n;
	static int [][]arr;
	static long [][]dp;						//입력 값이 큰 관계로 long

	public static void main(String[] args) throws IOException { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		dp = new long[n][n];								//int로 잡으니까 틀리더라
		
		for(int i =0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = 1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++){
				if(arr[i][j] == 0) {
					break;
				}
				if(i + arr[i][j] < n) { 				//다음으로 이동할 칸이 배열 안에 존재하면
					dp[i+arr[i][j]][j] += dp[i][j];		//i,j 까지 오는 경우의 수를  다음 이동하는 곳에도 더해주기
				}
				if(j + arr[i][j] < n) {
					dp[i][j+arr[i][j]] += dp[i][j];
				}
			}
		}
		
		System.out.println(dp[n-1][n-1]);
	}
}

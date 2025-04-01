package s0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 돌과 돌 사이를 점프하며 마지막 n번째 돌까지 도착하기
 * 1번째 돌에서 점프를 시작
 * 점프 1 : 1칸 이동하는 작은 점프
 * 점프 2 : 2칸 이동하는 큰 점프
 * 점프 1, 2는 점프하는 돌에 따라 소모하는 에너지가 각각 다름 (같은 돌의 작은 점프 / 큰 점프 끼리도 다를 수 있음)
 * 점프 3 : 3칸 이동하는 매우 큰 점프, 단 한 번만 가능, 고정적으로 k만큼의 에너지 소모 
 */
public class Main_BJ21317 {						//징검다리 건너기

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int n = Integer.parseInt(br.readLine());
		int arr[][] = new int[n][2];				//1번돌부터 n-1번째 돌까지 0-작은점프 1-큰점프
		int[][] dp = new int[n+1][2];		//n번째 돌에 도달하기 위한 최소 에너지 0 : 매우 큰 점프x 1: 매우 큰 점프o
		if(n < 4) {
			arr = new int[4][2];
			dp = new int[4][2];		
		}
		
		StringTokenizer st;
		for(int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());	//작은 점프
			arr[i][1] = Integer.parseInt(st.nextToken());	//큰 점프
		}
		
		int k = Integer.parseInt(br.readLine());			//매우 큰 점프 값
		
		for(int i = 2; i <= n; i++) {
			dp[i][0] = Integer.MAX_VALUE/4;
			dp[i][1] = Integer.MAX_VALUE/4;
		}
		
		dp[1][0] = 0;
		dp[2][0] = arr[1][0];
		dp[3][0] = Math.min(dp[2][0] + arr[2][0], dp[1][0] + arr[1][1]);
		
		//매우 큰 점프 고려하지 않은 경우 / 고려한 경우 모두 dp 구하기
		for(int i = 4; i <= n; i++) {
			dp[i][0] = Math.min(dp[i][0], Math.min(dp[i-1][0] + arr[i-1][0], dp[i-2][0] + arr[i-2][1]));
			dp[i][1] = Math.min(Math.min(dp[i-1][1] + arr[i-1][0], dp[i-2][1] + arr[i-2][1]), dp[i-3][0]+k);
		}
		
		System.out.println(Math.min(dp[n][0], dp[n][1]));
	}

}

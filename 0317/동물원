package s0317;

import java.util.Scanner;

/*
 *	2*n배열에 사자를 배치할 때, 사자를 배치할 수 있는 경우의 수를 구하여라. 
 *	단, 가로로도 세로로도 붙어서 배치할 수는 없다.
 *	사자를 배치하는 경우의 수를 9901로 나누어 그 나머지를 출력
 *  2*n 배열에 올 수 있는 최대 사자의 수 = n 마리
 *  
 */
public class Main_BJ1309 {								//동물원

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		long[][] dp = new long[n + 1][3];
		dp[1][0] = 1;					//0 = 다음 줄에 사자를 배치 하지 않음
		dp[1][1] = 1;					//1 = 다음 줄에 사자를 왼쪽칸에 배치
		dp[1][2] = 1; 					//2 = 다음 줄에 사자를 오른쪽칸에 배치

		for (int i = 2; i <= n; i++) {
			dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
			dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;
			dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
		}
		
		long answer = (dp[n][0] + dp[n][1] + dp[n][2]) % 9901;
		System.out.println(answer);
	}

}

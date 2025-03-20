package s0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * n개의 자연수에 대해 m번의 질문에 대해 대답을 구하는 프로그램 작성하기
 * 질문은 두 정수 s, e로 나태낼 수 있음(s<=e)
 * =>s번째 수부터 e번째까지의 수가 '팰린드롬'을 이루는지 질문 -> 예 아니오로 대답해야함
 * 팰린드롬 = 앞 뒤로 읽어도 동일한 수열
 * 팰린드롬이면 1, 아니면 0을 m개의 줄에 걸쳐 출력
 */
public class Main_BJ10942 {						//팰린드롬

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int arr[] = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][] dp = new boolean[n+1][n+1];		//s부터 e까지의 수열이 펠린드롬인가 여부
		//1. arr[s] == arr[e]
		//2. dp[s+1][e-1] == true
		//1, 2를 만족하면 dp[s][e] = true
		
		for(int i = 1; i <= n; i++) {	//수열이 1개면 펠린드롬
			dp[i][i] = true;
		}
		
		for(int i= 1; i < n; i++) {		//수열이 2개면 같을때만 펠린드롬
			if(arr[i] == arr[i+1]) {
				dp[i][i+1] = true;
			}
		}
		
		//점화식 적용
		for(int i = 2; i < n; i++) {			
			for(int j = 1; j <= n-i; j++) {
				if(arr[j] == arr[j+i] && dp[j+1][j+i-1]) {
					dp[j][j+i] = true;
				}
			}
		}
		
		int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			if(dp[s][e]) {
				sb.append("1\n");
			}else {
				sb.append("0\n");
			}
		}
		System.out.println(sb.toString());		//stringbuilder 안쓰면 시간초과남
	}

}

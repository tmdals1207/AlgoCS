package s0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 수 n개가 주어질떼 i번째부터 j번째 수까지 합 구하는 프로그램
 * 
 */
public class Main_BJ11659 {									//구간 합 구하기 4

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int s[] = new int [n+1];
		for(int i = 1; i <= n; i++) {
			s[i] = s[i-1] + arr[i];				//s[i] = i번째 인덱스까지 더한 누적합(1+2+3+..i번째 인덱스의 합)
		}
		
		for(int t = 0; t < m; t++) {
			st = new StringTokenizer(br.readLine());
			
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			int sum = s[j] - s[i-1];
			
			System.out.println(sum);
		}
	}

}

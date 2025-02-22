package s0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * n개의 정수로 이루어진 수열에서 "연속된" 몇 개의 수를 선택해 구할 수 있는 합 중 가장 큰 합 찾기
 * 수는 최소 1개이상 골라야함 
 * 점화식을 어떻게 세워야하지??
 */
public class Main_BJ1912 {									//연속합

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];										//입력값
		int[] max = new int[n];										//i번째까지 연속된 수를 더했을때의 최댓값
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i <n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		max[0] = arr[0];
		for(int i = 1; i < n; i++) {
			//i번째 배열의 값이 더크냐 i-1번째까지 연속합에 i번째까지 더한게 더 크냐
			max[i] = Math.max(arr[i], arr[i] + max[i-1]);
		}
		
		int answer = max[0];
		for(int i = 0; i < n; i++) {
			if(answer < max[i]) {
				answer = max[i];
			}
		}
	
		System.out.println(answer);
	}
}

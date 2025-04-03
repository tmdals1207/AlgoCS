package s0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * m명의 친구들이 입국 심사 대기 중
 * n개의 입국 심사대 존재, 각 심사대 마다 걸리는 시간은 다름
 * k번 심사원이 심사하는데 걸리는 시간은 Tk
 * m명이 n개의 입국심사대에서 심사를 받는데 걸리는 최소 시간 구하기
 * 비어있는 심사대에 꼭 가야할 필요는 없음, 더 빠른곳 기다렸다가 걸로 가도 됨
 */
public class Main_BJ3079 {									//입국심사

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		long m = Long.parseLong(st.nextToken());
		long tk[] = new long[n];
		for(int i = 0; i < n; i++) {
			tk[i] = Long.parseLong(br.readLine());				//1부터 n 인덱스까지 입력
		}
		
		Arrays.sort(tk);
		
		long min = 0;				    //탐색 범위 최소값
		long max = tk[n-1] * m;			//탐색 범위 최댓값, 가장 최악의 경우 (가장 오래걸리는데로 m이 다 몰린 경우)
		long answer = Long.MAX_VALUE;	
		
		while(min <= max) {
			long mid = (max + min) / 2;		//중간값 탐색 (시간 T)
			
			long sum = 0;
			for(int i = 0; i < n; i++) {
				if(sum >= m) {					//?????????????????????? 오버플로우라도 터지나
					break;
				}
				
				sum += (mid / tk[i]);		//T 시간 동안 통과 가능한 사람 수 sum
			}
			
			
			if (sum >= m){				//조건을 만족하면 탐색 범위를 왼쪽으로 좁히기(조건은 만족하나 최소값이 아님)
				max = mid - 1;
				answer = Math.min(mid, answer);	//범위를 좁히면서 조건을 만족하는 mid 값 중 최소가 되는애 찾기
			}
			else {			//조건을 불만족하면 탐색 범위를 오른쪽으로 좁히기 (시간이 더 필요함)
				min = mid + 1;
			}
			
		}
		
		System.out.println(answer);
		
	}

}

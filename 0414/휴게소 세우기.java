package s0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 휴게소 n개를 가지고 있고, m개를 더 세우고자 함
 * 이미 휴게소가 있는 곳에는 세울 수 없고, 고속도로의 끝에도 세울 수 없음
 * m개를 전부 지어서 휴게소가 없는 구간의 길이의 최댓값을 최소로 만들고자 함
 * 단, n이 0인 경우도 존재
 */
public class Main_BJ1477 {					//휴게소 세우기

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n+2];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		arr[0] = 0;
		arr[n+1] = k;
		Arrays.sort(arr);										//거리 순으로 정렬
		
		int max = 0;
		int [] diff = new int[n+1];								//최초 휴게소 간의 간격 배열
		for(int i = 0; i <= n; i++) {
			diff[i] = arr[i+1] - arr[i];
			max = Math.max(max, diff[i]);						//휴게소 간의 간격들 중 최댓값을 max로 지정
		}
		
		int min = 1;
		while(min <= max) {										//이분탐색
			int mid = (max+min)/2;
			int extra = 0;										//세운 휴게소의 개수
			
			for(int i = 0; i <= n; i++) {
				extra += diff[i] / mid;					
				if(diff[i] % mid == 0) {						//이미 세운곳에 세운다면 겹치니까 제외
					extra--;
				}
			}
			
			if(extra > m) {							//휴게소 설치 개수가 정해진 m값보다 많이 설치되었으면
				min = mid + 1;						//탐색하는 값 증가
			} else {								//적거나 같으면 탐색하는 값 감소
				max = mid - 1;						//(최솟값을 찾기위해 조건을 만족해도반복문 탈출까지 수행)
			}
		}
		
		System.out.println(max+1);					//반복문 빠져나오기 전에 -1 된거 감안해서 max+1을 답으로
		
	}

}

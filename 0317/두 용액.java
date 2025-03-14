package s0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 산성 용액(양수)와 알칼리성 용액(음수)가 1~1000000000의 값으로 존재.
 * 두 종류의 용액을 섞어(두 수의 합이) 0에 가장 가까운 용액을 만들어내는 경우를 찾고자 함.
 * 섞는 두 용액의 종류는 같을수도 다를수도 있음.
 * => 합에 절댓값을 씌워서 최솟값 비교하기?
 */
public class Main_BJ2470 {										//두 용액

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int arr[] = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);							//정렬
		
		int min = Integer.MAX_VALUE;
		int idx1 = 0, idx2 = 0;	//정답 시점의 인덱스
		
		int start = 0;
		int end = n-1;			//투 포인터
			
		while(start < end) {
			int sum = arr[start] + arr[end];
			
			//1. 0에 가장 가까운 값 확인
			if(min > Math.abs(sum)) {
				min = Math.abs(sum);
				idx1 = start;
				idx2 = end;
			}
			
			//2. 특성합이 양수인지, 음수인지에 따라 포인터 이동 결정 
			//(양수 = 두 용액의 합을 줄여야함 / 음수 = 두 용액의 합을 늘려야함)
			if(sum > 0) {
				end--;
			}else if(sum < 0) {
				start++;
			}else {				//sum이 0이면 즉시 탈출
				break;
			}
		}
		
		System.out.println(arr[idx1]+" "+arr[idx2]);
	}

}

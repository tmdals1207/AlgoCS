package s0317;

import java.util.Scanner;

/*
 * 소수의 n제곱인 수 = 거의 소수라고 부르기로 함.
 * 정수 A, B가 주어질 때, A이상 B이하의 "거의 소수"를 구하기
 */
public class Main_BJ1456 {								//거의 소수

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long a = sc.nextLong();
		long b = sc.nextLong();
		
		int sqrtB = (int) Math.sqrt(b);
		//에라토스테네스의 체
		boolean arr[] = new boolean[sqrtB+1];				//1부터 b의 제곱근까지 담을 배열 (b까지 갈 이유가 없네)
		
		for(int i = 0; i < arr.length; i++) {			
			arr[i] = true;
		}
		
		arr[0] = arr[1] = false;					
		for(int i = 2; i <= Math.sqrt(sqrtB); i++) {	
			if(arr[i]) {							
				for(int j = i * i; j < arr.length; j += i) {
					arr[j] = false;
				}
			}
		} // 소수 판별 끝
		
		int count = 0;
		for(int i = 2; i < arr.length; i++) {
			if(arr[i]) {
				long pow = i;
				while(pow <= (double) b / i) { //오버플로우 방지 -> b와 a를 각각 i로 나눠서 계산
					if((double) a / i <= pow) {
						count++;
					}
		
					pow = pow * i;
				}
			}
		}
		System.out.println(count);
		
		sc.close();
	}

}

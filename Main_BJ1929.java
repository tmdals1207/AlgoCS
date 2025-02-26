package s0303;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * M이상 N이하의 소수를 모두 출력하기(한줄에 하나씩, 오름차순으로)
 */
public class Main_BJ1929 {									//소수 구하기

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int m = sc.nextInt();
		int n = sc.nextInt();
		
		boolean arr[] = new boolean[n+1];				//1부터 n까지 담을 배열
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = true;
		}
		
		arr[0] = arr[1] = false;					//1은 소수 아님
		for(int i = 2; i <= Math.sqrt(n); i++) {	//n제곱근까지만 검증하면 됨
			if(arr[i]) {							//i가 소수면
				for(int j = i * i; j <= n; j += i) {//소수의 제곱부터 n까지는 전부 소수가 아님
					arr[j] = false;
				}
			}
		}
		
		for(int i = m; i <= n; i++) {
			if(arr[i]) {
				System.out.println(i);
			}
		}
		
		sc.close();
	}
}

package s0414;

import java.util.Scanner;

/*
 * 재귀적인 패턴으로 별을 찍어보기
 * n 크기의 패턴은 n*n 정사각형 모양의 패턴
 * 크기 3의 패턴은 가운데가 비어있는 3x3의 패턴
 */

public class Main_BJ2447 {							//별 찍기 - 10
	static char arr[][];
	
	static void star(int i, int j, int n) {
		if(n == 1) {					//쪼갤 수 없을때만 별 그리기
			arr[i][j] = '*';
			return;
		}
		
		star(i, j, n/3);
		star(i, j + n/3, n/3);
		star(i, j + (n/3)*2, n/3);
		star(i + n/3, j, n/3);
		star(i + n/3, j + (n/3)*2, n/3);
		star(i + (n/3)*2, j, n/3);
		star(i + (n/3)*2, j + n/3, n/3);
		star(i + (n/3)*2, j + (n/3)*2, n/3);
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int n = sc.nextInt();
		
		
		arr = new char[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				arr[i][j] = ' ';				//전부 빈칸으로 초기화
			}
		}
		
		star(0, 0, n);
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}

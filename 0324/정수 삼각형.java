package s0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 정수가 적힌 삼각형에서 맨 위층에서부터 맨 아래층으로 내려갈거임
 * 단, 합이 최대가 되는 경로로 내려갈 것이며, 그 합을 출력할 예정
 * 현재 층에서 왼쪽 아래 or 오른쪽 아래에 있는 애들만 접근 가능함
 */
public class Main_BJ1932 {								//정수 삼각형

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] triangle = new int [n][n];
		
		for(int i = 0; i < n; i++) {
			String[] tmp = br.readLine().split(" ");
			for(int j = 0; j < tmp.length; j++) {
				triangle[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(j == 0) {					//가장 왼쪽 원소라면
					triangle[i][j] += triangle[i-1][j]; 
				}else if(j == n-1) {			//가장 오른쪽 원소라면 (인덱스 에러 방지용)
					triangle[i][j] += triangle[i-1][j-1]; 
				}else {
					triangle[i][j] += Math.max(triangle[i-1][j], triangle[i-1][j-1]); 
				}
			}
		}
		
		int max = 0;
		for(int j = 0; j < n; j++) {
			max = Math.max(max, triangle[n-1][j]);
		}
		
		System.out.println(max);
	}

}

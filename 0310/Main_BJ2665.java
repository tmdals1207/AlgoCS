package s0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * n*n방에서 (0,0) 자리에서 시작해 (n-1, n-1)자리로 가는 것이 목적
 * 검은 방(벽, 0)을 흰 방(길, 1)로 만들어서 정해진 목적지로 가고자 할때, 방을 바꾸어야할 최소의 수를 구하기
 * 단, 검은 방을 하나도 흰방으로 바꾸지 않아도 되는 경우는 0!!!.
 * =>검은 방 하나만 바꿔서 목적지에 가기 -> 두개를 바꿔서 -> 이렇게 반복?
 */
public class Main_BJ2665 {								//미로만들기

	static int[][] arr;
	static int n;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static void change(int cnt, int answer) {						//answer 만큼 방을 바꾸기
		if(cnt == answer) {
			bfs();									
			return;
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j <n; j++) {
				if(arr[i][j] == 0) {
					arr[i][j] = 1;
					change(cnt+1, answer);
					arr[i][j] = 0;
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			for(int j =0; j < n; j++) {
				arr[i][j] = str.charAt(j)-'0';
			}
		}
		
		int answer = 0;
		while(true) {
			change(0, answer);
			answer++;
		}
	}

}

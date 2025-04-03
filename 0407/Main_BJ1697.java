package s0407;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/*
 * 수빈이는 점 n에, 동생은 점 k에 위치
 * 수빈이는 수빈이의 현재위치 x에서 x+1 or x-1로 걷거나, 2*x의 위치로 순간이동이 가능
 * 동생을 찾을 수 있는 가장 빠른 시간 구하기
 */
public class Main_BJ1697 {								//숨바꼭질
	
	static int n, k;
	static boolean[] visit;
	static int[] arr;
	static int[] dx = {-1, 1};

	static void bfs() {								//그냥 일반적인 bfs
		
		Queue<Integer> q = new ArrayDeque<>();
		visit[n] = true;
		q.offer(n);
		
		while(!q.isEmpty()) {
			int x = q.poll();
			
			if(x == k) {							//다만 목표지점 오면 탈출하는
				break;
			}
			
			for(int d = 0; d <3; d++) {
				int nx;
				if(d == 2) {						//2*x
					nx = x * 2;
				} else{								//x-1 or x+1
					nx = x + dx[d];
				}
				if(0 <= nx&&nx <= 100000 && !visit[nx]) {
					visit[nx] = true;
					arr[nx] = arr[x] + 1;
					q.offer(nx);
				}
			}
		}
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		visit = new boolean[100001];		
		arr = new int[100001];					//입력 값범위 0~100000 => 전체 맵 크기라고 봐도 됨
		
		n = sc.nextInt();
		k = sc.nextInt();
		
		bfs();
		
		System.out.println(arr[k]);
		
		sc.close();
	}

}

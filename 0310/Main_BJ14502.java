package s0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * n*m 연구소에 바이러스 유출
 * 바이러스는 사방으로 퍼질 수 있고, 이를 막기 위해 벽을 꼭 3개 세워야 함
 * 구할 수 있는 안전 영역 (바이러스가 퍼지지 않는 곳)의 크기의 최댓값 구하기 =>(0의 개수 구하기)
 * 0은 빈칸, 1은 벽, 2는 바이러스
 */
public class Main_BJ14502 {							//연구소
	static int[][] arr;
	static int n, m, max;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static void bfs(int[][] map, int i, int j) {	//bfs로 바이러스를 퍼트리는 함수
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {i, j});
		
		while(!q.isEmpty()) {
			int[] ij = q.poll();
			i = ij[0];
			j = ij[1];
			
			for(int d = 0; d < 4; d++) {
				int ni = dx[d] + i;
				int nj = dy[d] + j;
				if(0 <= ni&&ni < n && 0 <= nj&&nj < m && map[ni][nj] == 0) {
					map[ni][nj] = 2;
					q.offer(new int[] {ni, nj});
				}
			}
		}
	}
	
	static void safe() {								//안전지대 최대 개수 구하기
		int[][] map = new int[n][m];
		for(int i = 0; i < n; i++) {
			map[i] = arr[i].clone();				//벽을 3개 설치한 arr을 map으로 복사해오기 (map 배열에서 시뮬레이션 돌린다는 느낌)
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 2) {				//바이러스가 맵에 있다면 확산시키기
					bfs(map, i , j);
				}
			}
		}
		
		int count = 0;								//바이러스 확산이 끝나고 안전지대 갯수 세기
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 0) {
					count++;
				}
			}
		}
		max = Math.max(max, count);					//기존 최댓값과 비교
	}
	
	static void wall(int cnt) {						//벽 3개 고르기
		if(cnt == 3) {
			safe();									//3개 골랐으면 최댓값 구하기
			return;
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j <m; j++) {
				if(arr[i][j] == 0) {
					arr[i][j] = 1;
					wall(cnt+1);
					arr[i][j] = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		ArrayList<int[]> list = new ArrayList<>(); //0의 좌표들 입력
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		wall(0);

		System.out.println(max);
	}
}

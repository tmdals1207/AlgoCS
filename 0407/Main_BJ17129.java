package s0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 딱따구리 가족이 가장 빠르게 도착할 수 있는 음식 찾기
 * 0-빈공간 1-벽 2-딱따구리 3-청국장 4-스시 5-맥앤치즈
 * 2, 3, 4, 5는 반드시 1개씩 존재
 */
public class Main_BJ17129 {			//윌리암슨수액빨이딱따구리가 정보섬에 올라온 이유
	static int n, m;
	static int[][] arr, dis;					//arr = 지도 / dis = 시작점부터 거리를 표시한 배열
	static boolean visit[][];
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static int bfs(int i , int j) {				//bfs를 통해 접근한 음식까지의 거리 반환, 접근 불가 시 -1
		
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {i, j});
		visit[i][j] = true;
		
		while(!q.isEmpty()) {
			int[] ij = q.poll();
			i = ij[0];
			j = ij[1];
			
			if(arr[i][j] == 3 || arr[i][j] == 4 || arr[i][j] == 5) {	//현재 위치에 음식이 있으면
				return dis[i][j];
			}
			
			for(int d = 0; d < 4; d++) {
				int ni = i + dx[d];
				int nj = j + dy[d];
				
				if(0 <= ni&&ni < n && 0 <= nj&&nj < m && arr[ni][nj] != 1 && !visit[ni][nj]) {
					visit[ni][nj] = true;
					dis[ni][nj] = dis[i][j] + 1;
					q.offer(new int[] {ni, nj});
				}
			}
		}
		
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		dis = new int[n][m];
		visit = new boolean[n][m];
		
		int ai = 0, aj = 0;
		for(int i = 0; i < n; i ++) {
			String str = br.readLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j) - '0';
				if(arr[i][j] == 2) {				//시작 위치 가져오기
					ai = i;
					aj = j;
				}
			}
		}
		
		int answer = bfs(ai, aj);
		
		if(answer == -1) {
			System.out.println("NIE");
		} else {
			System.out.println("TAK");
			System.out.println(answer);
		}
	}

}

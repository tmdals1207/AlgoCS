package s0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * n*m 배열, 1은 길, 0은 벽
 * (1,1)(가장 왼쪽)에서 시작, (n, m)(가장 오른쪽)에서 도착
 * 이때 최소거리를 구하기(출발점과 도착점도 세기)
 * 미로 입력이 붙어서 입력으로 들어옴 => 뭔 싸가지지
 */
public class Main_BJ2178 {											//미로탐색
	static int n, m;
	static int[][] arr;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};	//방향
	static boolean[][] visit;
	
	private static int bfs(int i, int j) {
		int answer = 0;
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {i, j, 1});					//시작노드 큐에 넣기
		visit[i][j] = true;
		
		while(!q.isEmpty()) {
			int ij[] = q.poll();
			i = ij[0];
			j = ij[1];
			int cnt = ij[2];							//큐에서 좌표값, 칸수 받아오기
			
			if(i == n-1 && j == m -1) {
				answer = cnt;
				break;							//목표지점에 도착시 최단 거리 반환
			}
			
			for(int d = 0; d < 4; d++) {				//상하좌우 탐색
				int ni = i + dx[d];
				int nj = j + dy[d];
				if(0 <= ni&&ni < n && 0 <= nj&&nj < m && !visit[ni][nj] && arr[ni][nj] == 1) {
					visit[ni][nj] = true;
					q.offer(new int[] {ni, nj, cnt+1});		//갈 수 있는 곳이면 큐에 넣어 탐색
				}
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visit = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j) - '0';			//하나씩 문자를 수로 변환하여 대입
			}
		}
		
		int min = bfs(0, 0);								//최단 거리 계산

		System.out.println(min);
		
		br.close();
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소 {
	
	static int N, M, max;
	static final int[] dx = {1, -1, 0, 0};
	static final int[] dy = {0, 0, 1, -1};
	
    
	// 벽을 세우는 DFS 함수
	static void wallDfs(int i, int j, int cnt, int[][] map) {
		
        // 벽을 3개 세운 경우 바이러스 전파 시작
		if (cnt == 3) {
			infectionBfs(map);
			return;
		}
		
		for (int x = i; x < N; x++) {
			for (int y = (x == i ? j : 0); y < M; y++) { // 같은 행이면 j부터, 새 행이면 0부터
				if(map[x][y] == 0) { // 빈 공간(0)일 때만 벽 세우기 가능
					map[x][y] = 1; // 벽 세우기
					wallDfs(x, y+1, cnt+1, map); // 다음 벽 세우러 이동
					map[x][y] = 0; // 백트래킹: 원상 복구
				}
			}
		}
		
	}

    // 바이러스를 퍼뜨리는 BFS 함수
	private static void infectionBfs(int[][] map) {
		Queue<Point> queue = new LinkedList<Point>();
		int[][] newMap = new int[N][M]; // 원본 맵을 복사하여 사용
		
        // 맵 복사 (원본이 변경되면 안됨)
		for (int i = 0; i < N; i++) {
	        System.arraycopy(map[i], 0, newMap[i], 0, M);
	    }
		
		for (int a = 0; a < N; a++) {
			for (int b = 0; b <M; b++) {
				if(newMap[a][b]==2) {
					queue.add(new Point(a, b));
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			// 상하좌우 이동
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				// 맵 범위 안에 있고, 빈 공간(0)이면 바이러스 전파
				if(nx>=0 && nx<N && ny>=0 && ny<M && newMap[nx][ny]==0) {
					newMap[nx][ny] = 2;
					queue.add(new Point(nx, ny));
				}
			}
		}
        
        // 안전 영역 크기 계산
		int safeZone = 0;
		for (int k = 0; k < N; k++) {
			for (int j = 0; j < M; j++) {
				if(newMap[k][j]==0) {
					safeZone++;
				}
			}
		}
		max = Math.max(max, safeZone);
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		wallDfs(0, 0, 0, map);
		
		System.out.println(max);

	}
    
    // 좌표를 저장할 클래스
    public static class Point {
    	int x, y;
	
	    public Point(int x, int y) {
	    	this.x = x;
    		this.y = y;
	    }
    }  
}
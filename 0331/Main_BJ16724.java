package s0331;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 방향지도가 주어졌을 때, 지도 어느 구역에 있더라도 safe zone안에 들어가도록하는
 * safe zone을 만들어 그 최소 개수를 출력하기
 * dfs를 돌리고 사이클인지 검증?
 */
public class Main_BJ16724 {								//피리 부는 사나이
	static char[][] map;
	static int n, m, count;
	static boolean visit[][];							//방문 여부
	static boolean cycle[][];							//방문한 곳이 사이클인지 여부
	
	public static void dfs(int i , int j) {
		visit[i][j] = true;
		
		int ni = i, nj = j;
		if(map[i][j] == 'U') {
			ni = i - 1;
		} else if(map[i][j] == 'R') {
			nj = j + 1;
		} else if(map[i][j] == 'D') {
			ni = i + 1;
		} else if(map[i][j] == 'L') {
			nj = j - 1;
		}
		
		if(!visit[ni][nj]) {			//미 방문 지점이면 계속 탐색
			dfs(ni, nj);
		}
		else {							//이미 방문한 지점에 왔다면
			if(!cycle[ni][nj]) {		//사이클인지 여부 확인
				count++;
			}
		}
		
		cycle[i][j] = true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		visit = new boolean[n][m];
		cycle = new boolean[n][m];
		
		for(int i =0; i < n; i++) {
			String str = br.readLine();
			for(int j =0; j <m ; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for(int i = 0; i < n ; i++) {
			for(int j = 0; j < m; j++) {
				if(!visit[i][j]) {
					dfs(i, j);
				}
			}
		}
		
		System.out.println(count);
	}

}

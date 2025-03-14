package s0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 트리 : 사이클이 없는 그래프
 * 트리는 정점이 n개, 간선이 n-1개 존재, 임의의 두 정점에 대해서 경로가 유일
 * 그래프가 주어졌을 때, 트리의 개수를 세는 프로그램을 작성하시오.
 * 정점은 1번부터 n번까지 번호가 매겨져 있음
 */
public class Main_BJ4803 {								//트리
	static ArrayList<Integer> graph[];
	static boolean visit[];
	static int n, m, t;
	
	private static boolean dfs(int i, int parents) {		//dfs를 통해 사이클 여부를 반환받음
		
		visit[i] = true;
		
		for(int node : graph[i]) {
			if(!visit[node]) {
				if(dfs(node, i)) {		//다음으로 탐색하는 노드가 사이클이 존재한다면 true 반환
					return true;
				}
			}else if(node != parents) { //다음에 방문할 곳이 이미 방문한 부모노드가 아니라면
				return true;
			}
			
		}
		return false;										//사이클 미 존재시 false 반환
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int c = 1;
		while(true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			t =  0;
			
			if(n == 0 && m == 0) {
				break;
			}
			
			graph = new ArrayList[n+1];
			visit = new boolean[n+1];
			for(int i = 0; i < n+1; i++) {
				graph[i] = new ArrayList<>();
			}													//리스트 초기화
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int m1 = Integer.parseInt(st.nextToken());
				int m2 = Integer.parseInt(st.nextToken());
				graph[m1].add(m2);
				graph[m2].add(m1);
			}
			
			for(int i = 1; i <= n; i++) {
				if(!visit[i]) {
					if(!dfs(i, -1)) {//i번째 노드에서 dfs 탐색을 시작하므로 부모가 없어서 -1 대입
						t++;		//사이클이 없을때만 t값 증가
					}		
				}
			}
			
			if(t == 1) {
				System.out.println("Case "+(c++)+": There is one tree.");
			}else if(t == 0) {
				System.out.println("Case "+(c++)+": No trees.");
			}else{
				System.out.println("Case "+(c++)+": A forest of "+t+" trees.");
			}
		}
	}

}

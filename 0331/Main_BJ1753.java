package s0331;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 방향 그래프가 주어질 때, 시작점에서 다른 모든 정점으로의 최단 경로 구하기
 * 단, 간선의 가중치는 모두 10 이하 '자연수'
 * 자기 자신을 가리키는 간선은 없음 / 서로 다른 두 정점 사이에 여러 개의 간선이 있음에 유의?
 */
public class Main_BJ1753 {							//최단경로
	
	public static class Node implements Comparable<Node>{
		int end;
		int w;
		
		public Node(int end, int w) {
			this.end = end;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			//현재 w와 비교대상 w 중 더 작은 것 비교
			return Integer.compare(this.w, o.w);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken());		//노드 수
		int e = Integer.parseInt(st.nextToken());		//간선 수
		
		List<Node> graph[] = new ArrayList[v+1];
		for(int i =0; i <= v; i++) {
			graph[i] = new ArrayList();					//그래프 초기화
		}
		
		int start = Integer.parseInt(br.readLine());			//시작점
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());			//간선의 시작점
			int end = Integer.parseInt(st.nextToken());			//간선의 끝점
			int w = Integer.parseInt(st.nextToken());			//간선의 가중치
			
			graph[s].add(new Node(end, w));						//그래프에 간선 정보 추가
		}
		
		boolean[] visit = new boolean[v+1];
		int[] dis = new int[v+1];
		for(int i = 0; i <= v; i++) {
			dis[i] = Integer.MAX_VALUE;
		}
		
		dis[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();			//우선 순위 큐 사용
		pq.offer(new Node(start, 0));		//start->start 이므로 가중치는 0
		while(!pq.isEmpty()) {
			int cur = pq.poll().end;
			
			if(visit[cur]) {
				continue;
			}
			visit[cur] = true;
			
			for(Node node : graph[cur]) {
				if(dis[node.end] > dis[cur] + node.w) {
					dis[node.end] = dis[cur] + node.w;	//cur까지의 최단거리 + cur -> end 간선의 가중치
					
					pq.offer(new Node(node.end, dis[node.end]));
				}
			}
		}
		
		for(int i = 1; i <= v; i++) {
			if(dis[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}else {
				System.out.println(dis[i]);
			}
		}
	}

}

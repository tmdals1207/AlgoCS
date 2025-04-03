package s0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1번 정점에서 n번 정점으로 최단 거리로 이동하기
 * >>>단, 주어진 두 정점 v1, v2는 반드시 지나가야 함!<<<
 * 그래프는 무방향(=양방향) 그래프
 * 
 */
public class Main_BJ1504 {						//특정한 최단 경로
	static int n, e;
	final static int INF = 1000*200000;			//입력값의 최댓값 설정(이거보단 작겠지)
	
	static ArrayList<Node> graph[];

	static class Node implements Comparable<Node>{		//노드 클래스
		int end;
		int w;
		
		public Node(int end, int w) {
			this.end = end;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	public static int dijkstra(int start, int end) {	//start에서 end까지의 최단 경로 구하기
		boolean visit[] = new boolean[n+1];
		int dis[] = new int[n+1]; 
		for(int i = 0; i <= n; i++) {
			dis[i] = INF;
		}
		
		if(start == end) {
			return 0;
		}
		
		dis[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			int cur = pq.poll().end;	//현재 방문한 노드
			
			if(visit[cur]) {
				continue;
			}
			visit[cur] = true;
			
			for(Node node : graph[cur]) {
				if(dis[node.end] > dis[cur] + node.w) {
					dis[node.end] = dis[cur]+ node.w;
				}
				
				pq.offer(new Node(node.end, dis[node.end]));
			}
		}
		
		return dis[end];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[start].add(new Node(end, w));
			graph[end].add(new Node(start, w));	//무방향이니 양쪽에 추가
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		int dis1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, n); //1. 1->v1->v2->n
		int dis2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, n); //2. 1->v2->v1->n
		
		if(dis1 >= INF && dis2 >= INF) {				
			System.out.println(-1);
		} else if(dis1 < dis2){					
			System.out.println(dis1);
		} else {
			System.out.println(dis2);
		}

	}

}

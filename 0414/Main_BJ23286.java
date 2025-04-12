package s0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * n개의 정점, m개의 간선이 있는 방향 그래프
 * 허들을 넘는다 = 해당 간선을 지난다
 * 각 테스트 케이스에 대해 출발 노드부터 도착 노드까지 가는 경로 중 허들의 높이(=간선의 가중치) 가 최소가 되는 경로를 찾아보기
 * 경로로 이동 중 가장 높은 허들의 높이를 출력
 */
public class Main_BJ23286 {										//허들 넘기
	
	static int n, m, t;
    static List<Node>[] graph;
    static int[][] route;

    static class Node implements Comparable<Node> {
        int end, w;

        public Node(int end, int w) {
            this.end = end;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    // 시작점이 start인 최대 간선 비용을 최소화하는 다익스트라
    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        int[] maxW = new int[n + 1];		//start에서 i로 가는데 가는 경로 중 존재하는 가중치의 최대값 중 최솟값
        
        for(int i = 0; i < n+1; i++) {
        	maxW[i] = Integer.MAX_VALUE;
        }
        
        maxW[start] = 0;					//start->start니 당연히 0
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            int now = pq.poll().end;

            if (visited[now]) {
            	continue;
            }
            visited[now] = true;

            for (Node next : graph[now]) {
                int nextMax = Math.max(maxW[now], next.w);	//지금 가는 경로에서 가중치가 최대면 갱신
                if (maxW[next.end] > nextMax) {				//근데 그 최대값이 이미 입력된 저장된 값보다 작으면
                    maxW[next.end] = nextMax;				//최솟값 배열에 갱신
                    pq.offer(new Node(next.end, nextMax));	
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            route[start][i] = maxW[i];						//그렇게 maxW배열 다 채우면 그거 route배열에 입력
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        route = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, h));
        }

        // 다익스트라 실행
        for (int i = 1; i <= n; i++) {
            dijkstra(i);
        }

        //테스트 케이스 입력
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int answer = route[s][e];
            if(answer == Integer.MAX_VALUE) {
            	System.out.println(-1);
            } else {
            	System.out.println(answer);
            }
        }
    }

}

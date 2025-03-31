import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최단경로 {
    static int V, E, K;
    static final int INF = Integer.MAX_VALUE;
    static List<List<Node>> graph = new ArrayList<>(); // 인접 리스트
    static int[] dist; // 최단 거리를 저장할 배열
    
    // 다익스트라 알고리즘 구현 (우선순위 큐 사용)
    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(K, 0)); // 시작 정점을 큐에 삽입
        dist[K] = 0; // 시작 정점의 최단 거리는 0
        
        while (!pq.isEmpty()) {
            Node current = pq.poll(); // 현재 최단 거리의 정점을 꺼냄
            int curVertex = current.vertex;
            int curCost = current.cost;
            
            if (curCost > dist[curVertex]) continue; // 현재 정점의 거리가 이미 최소값이면 무시
            
            for (Node neighbor : graph.get(curVertex)) { // 현재 정점의 인접 노드 확인
                int nextVertex = neighbor.vertex;
                int newCost = curCost + neighbor.cost;
                
                if (newCost < dist[nextVertex]) { // 더 짧은 경로를 발견한 경우
                    dist[nextVertex] = newCost; // 최단 거리 업데이트
                    pq.offer(new Node(nextVertex, newCost)); // 우선순위 큐에 삽입
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        
        dist = new int[V + 1];
        Arrays.fill(dist, INF); // 모든 거리를 무한대로 설정

        for (int i = 0; i <= V; i++) graph.add(new ArrayList<>()); // 그래프 초기화
        
        for (int i = 0; i < E; i++) { 
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            graph.get(u).add(new Node(v, w));
        }
        
        dijkstra();
        
        for (int i = 1; i <= V; i++) {
            System.out.println(dist[i] == INF ? "INF" : dist[i]); 
        }
    }
}

// 우선순위 큐에서 사용할 노드 클래스 (가중치 기준 정렬)
class Node implements Comparable<Node> {
    int vertex, cost;

    Node(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return this.cost - other.cost; // 비용이 작은 순으로 정렬
    }
}

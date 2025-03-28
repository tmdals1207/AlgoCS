import java.util.*;
import java.io.*;

public class Main {

    static int V, E; // 정점, 간선의 개수
    static int start; // 시작점
    static List<int[]>[] graph;
    static int[] distances; // 각 노드까지의 최단 거리
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        V = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);

        start = Integer.parseInt(br.readLine());
        distances = new int[V + 1];
        Arrays.fill(distances, Integer.MAX_VALUE); // 각 노드까지의 최단 거리를 MAX 값으로 초기화
        distances[start] = 0;

        graph = new ArrayList[V + 1];
        for (int i=1; i<=V; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i=0; i<E; i++) {
            input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]); // 출발
            int e = Integer.parseInt(input[1]); // 도착
            int w = Integer.parseInt(input[2]); // 가중치
            graph[s].add(new int[] {e, w});     // 그래프에 추가
        }

        dijkstra(); // 다익스트라 수행

        for (int i=1; i<=V; i++) {
            int dist = distances[i];
            if (dist == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distances[i]);
            }
        }
    }

    static void dijkstra() {
        // 우선순위 큐를 사용해서 이때까지 탐색한 노드들 중 가장 짧은 거리의 노드가 항상 먼저 처리(poll)되도록 -> 시간 절약
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] poll = pq.poll(); // 이때까지 탐색한 노드 중 거리가 가장 짧은 노드 poll

            int nowNode = poll[0]; // poll한 node
            int nowDist = poll[1]; // 탐색한 거리

            if (distances[nowNode] < nowDist) { // 만약 탐색한 거리가 현재 저장된 거리보다 멀다면 버림
                continue;
            }

            for (int[] g : graph[nowNode]) { // 연결된 노드 탐색
                int nextNode = g[0];
                int nextDist = g[1];
                if (nowDist + nextDist < distances[nextNode]) { // 지금까지 탐색한 거리 + 연결된 노드까지의 거리 계산해서 distances 갱신
                    distances[nextNode] = nowDist + nextDist;
                    pq.add(new int[] {nextNode, nowDist + nextDist}); // 우선순위 큐에 추가
                }
            }
        }
    }
}

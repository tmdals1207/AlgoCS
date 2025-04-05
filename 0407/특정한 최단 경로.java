import java.util.*;
import java.io.*;

public class Main {

    static final int MAX = Integer.MAX_VALUE;

    static int N; // 정점의 개수
    static int E; // 간선의 개수
    static List<int[]>[] graph;
    static int v1, v2; // 경유할 정점의 번호
    static int[] distances;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);

        graph = new ArrayList[N + 1];
        for (int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<E; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]); // 정점1
            int b = Integer.parseInt(input[1]); // 정점2
            int c = Integer.parseInt(input[2]); // 가중치

            graph[a].add(new int[] {b, c});
            graph[b].add(new int[] {a, c});
        }

        input = br.readLine().split(" ");
        v1 = Integer.parseInt(input[0]);
        v2 = Integer.parseInt(input[1]);

        // 입력 끝
        
        distances = new int[N + 1];
        dijkstra(1);
        int dist10 = distances[v1];
        int dist11 = distances[v2];
        dijkstra(v1);
        int dist20 = distances[v2];
        int dist21 = distances[N];
        dijkstra(v2);
        int dist30 = distances[N];
        int dist31 = distances[v1];

        if (dist10 == MAX || dist20 == MAX || dist30 == MAX) {
            System.out.println(-1);
            return;
        }

        if (dist11 == MAX || dist21 == MAX || dist31 == MAX) {
            System.out.println(-1);
            return;
        }

        System.out.println(Math.min(dist10 + dist20 + dist30, dist11 + dist21 + dist31)); // 1 -> v1 -> v2 -> N 경로와 1 -> v2 -> v1 -> N 경로 비교
    }

    static void dijkstra(int start) {
        Arrays.fill(distances, MAX);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[] {start, 0});
        
        distances[start] = 0;

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int now = poll[0];
            int nowDist = poll[1];

            if (distances[now] < nowDist) {
                continue;
            }

            for (int[] l : graph[now]) {
                int next = l[0]; // 다음 정점
                int nextDist = l[1]; // 다음 정점까지의 거리

                int newDist = nowDist + nextDist;
                if (distances[next] < newDist) {
                    continue;
                }

                pq.add(new int[] {next, newDist});
                distances[next] = newDist;
            }
        }
    }
}

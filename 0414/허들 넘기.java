import java.util.*;
import java.io.*;

public class Main {

    static final int MAX = Integer.MAX_VALUE;

    static int N; // 정점의 수
    static int M; // 간선의 수
    static int T; // 테스트 케이스 수
    static int[][] graph;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);

        graph = new int[N + 1][N + 1];
        for (int[] i : graph) {
            Arrays.fill(i, MAX);
        }

        for (int i=0; i<M; i++) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int h = Integer.parseInt(input[2]);
            graph[u][v] = h;
        }

        fw(); // 플로이드 워셜

        for (int t = 0; t < T; t++) {
            input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]);
            int e = Integer.parseInt(input[1]);
            if (graph[s][e] == MAX) {
                System.out.println(-1);
                continue;
            }
            System.out.println(graph[s][e]);
        }
    } 

    static void fw() {
        for (int mid = 1; mid <= N; mid++) {
            for (int start = 1; start <= N; start++) {
                for (int end = 1; end <= N; end++) {
                    int oldValue = graph[start][end]; // 이전 탐색 중 가장 높은 허들의 높이
                    int newValue = Math.max(graph[start][mid], graph[mid][end]); // 현재 탐색에서 가장 높은 허들의 높이
                    graph[start][end] = Math.min(oldValue, newValue);
                }
            }
        }
    }
}

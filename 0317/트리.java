import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 풀이 로직
// dfs로 탐색하면서 직전에 방문한 노드가 아닌데 이미 방문 처리된 노드가 있을 경우 트리가 아닌 것으로 판단
public class Main {

    static int N;
    static int M;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = 1;
        while (true) {
            String[] split = br.readLine().split(" ");
            if (split[0].equals("0") && split[1].equals("0")) {
                break;
            }

            N = Integer.parseInt(split[0]);
            M = Integer.parseInt(split[1]);
            graph = new List[N + 1];
            for (int i=1; i<N+1; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i=0; i<M; i++) {
                split = br.readLine().split(" ");
                int n1 = Integer.parseInt(split[0]);
                int n2 = Integer.parseInt(split[1]);

                graph[n1].add(n2);
                graph[n2].add(n1);
            }
            // 입력===================================
          
            int treeCnt = countTrees();
            System.out.print("Case " + testCase + ": ");
            if (treeCnt == 0) {
                System.out.println("No trees.");
            } else if (treeCnt == 1) {
                System.out.println("There is one tree.");
            } else {
                System.out.println("A forest of " + treeCnt + " trees.");
            }
            testCase++;
        }
    }

    private static int countTrees() {
        boolean[] visited = new boolean[N + 1];
        int cnt = 0;
        for (int i=1; i<=N; i++) {
            if (!visited[i]) { // 방문하지 않은 노드를 시작점으로 dfs 탐색
                boolean isTree = dfs(-1, i, visited);
                if (isTree) { // 탐색 결과 트리라면 cnt 증가
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static boolean dfs(int parent, int now, boolean[] visited) {
        visited[now] = true;
        boolean isTree = true;
        for (int child : graph[now]) {
            if (child == parent) { // 간선으로 연결된 노드가 직전에 방문한 노드인 경우 넘김
                continue;
            }
            if (visited[child]) { // 부모 노드(직전에 방문한 노드)가 아닌데 이미 방문한 적이 있는 경우 트리가 아님
                return false;
            }
          
            isTree = dfs(now, child, visited);
            
            if (!isTree) { // 트리가 아니라면 탐색 중지
                break;
            }
        }

        return isTree;
    }
}

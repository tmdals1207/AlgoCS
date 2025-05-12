import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// dfs 문제 중에 2차원 배열을 탐색하면서 각 점을 가중치로 초기화하는 방식처럼
// 기존의 트리랑 다른 서브 트리를 만들고 그에 따라 각 정점에 자신이 가지는 정점의 수로 초기화

public class 트리와쿼리 {

    static int N, R, Q;
    static ArrayList<Integer>[] tree;
    static int[] subTree;
    static boolean[] visited;


    static void dfs(int root) {
        subTree[root] = 1;
        visited[root] = true;

        for (int child : tree[root]) {
            if (!visited[child]) {
                dfs(child);
                subTree[root] += subTree[child];
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];
        subTree = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int j = 0; j < N - 1; j++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(R);

        for (int k = 0; k < Q; k++) {
            sb.append(subTree[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }
}

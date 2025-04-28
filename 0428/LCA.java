import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LCA {

    static int N, M;
    static int[] parent, depth;
    static List<Integer>[] tree;


    static void dfs(int cur, int par, int d) {
        parent[cur] = par;
        depth[cur] = d;

        for (int next : tree[cur]) {
            if (next != par) {
                dfs(next, cur, d+1);
            }
        }
    }


    static int lca(int a, int b) {
        while (depth[a] > depth[b]) {
            a = parent[a];
        }
        while (depth[b] > depth[a]) {
            b = parent[b];
        }

        while (a!=b) {
            a = parent[a];
            b = parent[b];
        }
        return a;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        tree =  new ArrayList[N+1];
        parent = new int[N + 1];
        depth = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int j = 1; j < N; j++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1, 0, 0);

        M = Integer.parseInt(br.readLine());

        for (int k = 0; k < M; k++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(lca(a, b));
        }
    }
}
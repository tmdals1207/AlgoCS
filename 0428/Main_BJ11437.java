package s0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    * LCA = 최소 공통 조상
    * n개의 정점으로 이루어진 트리에서
    * 두 노드의 쌍이 m개가 주어질 때, 두 노드의 가장 가까운 공통 조상이 몇번인지 출력하기
    * ->????
    * 문제에서 루트 1번이라고 정해져있었ㅇ,ㅁ...........................
 */
public class Main_BJ11437 {                                 //LCA
    static ArrayList<Integer>[] tree;
    static int n;
    static int[] parent, depth;

    private static void init(int node, int p, int d){
        parent[node] = p;
        depth[node] = d;
        for(int next : tree[node]){
            if(next == p) {
                continue;
            }
            init(next, node, d+1);
        }
    }

    private static int lca(int u, int v) {
        int u_depth = depth[u];
        int v_depth = depth[v];

        while(u_depth > v_depth){
            u = parent[u];
            u_depth = depth[u];
        }

        while(v_depth > u_depth){
            v = parent[v];
            v_depth = depth[v];
        }

        while(u != v){
            u = parent[u];
            v = parent[v];
        }

        return u;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n+1];          //트리 구조
        parent = new int[n+1];              //각 노드의 부모, 1번 노드는 루트 노드임으로 0 대입
        depth = new int[n+1];               //각 노드에서 트리의 깊이
        for(int i = 0; i <= n; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

        //트리에 입력된 값에 따라 parent와 depth 값 갱신
        init(1, 0, 1);

        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            System.out.println(lca(u, v));
        }
    }
}

package s0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
   * 간선에 가중치와 방향성이 없는 임의의 루트가 있는 트리가 주어질 때,
   * 정점 U를 루트로 하는 서브트리에 속한 정점의 수 출력하기
   * 정점의 수 n, 루트 번호 r, 쿼리의 수 q
   * u와 v를 양 끝점으로 하는 간선이 존재
 */
public class Main_BJ15681 {                                     //트리와 쿼리
    static List<Integer>[] tree;
    static int[] size;
    static boolean[] visited;

    private static int dfs(int r) {
        visited[r] = true;
        size[r] = 1;

        for(int node : tree[r]){
            if(!visited[node]){
                size[r] += dfs(node);
            }
        }

        return size[r];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[n+1];
        visited = new boolean[n+1];
        size = new int[n+1];

        for(int i = 0;i <= n; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

        dfs(r);          //노드 r부터 각 노드의 서브트리 정점 개수 구하기
        
        for(int i = 0; i < q; i++){
            int root = Integer.parseInt(br.readLine()); //root를 루트로 하는 서브트리의 노드 수 구하기

            System.out.println(size[root]);
        }
    }
}

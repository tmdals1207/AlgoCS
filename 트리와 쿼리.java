import java.util.*;
import java.io.*;

public class Main {

    static int N; // 정점의 수
    static int R; // 루트의 번호
    static int Q; // 쿼리의 수
    static Map<Integer, List<Integer>> tree;
    static int[] subNodes;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        tree = new HashMap<>();
        for (int i=1; i<=N; i++) {
            tree.put(i, new ArrayList<>());
        }

        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            tree.get(U).add(V);
            tree.get(V).add(U);
        }

        subNodes = new int[N + 1];
        countSubNodes(R, 0);

        for (int q=0; q<Q; q++) {
            int node = Integer.parseInt(br.readLine());
            bw.write(subNodes[node] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int countSubNodes(int node, int pre) {
        subNodes[node] = 1; // 자기 자신을 추가
        for (int next : tree.get(node)) { // 자식 노드들 추가
            if (next == pre) {
                continue;
            }
            subNodes[node] += countSubNodes(next, node);
        }
        return subNodes[node];
    }
}

import java.util.*;
import java.io.*;

// [아이디어 BY 상윤]
// "그래프 내의 사이클의 개수를 찾아라"
// 지도 밖으로 향하는 방향의 입력은 주어지지 않으므로
// 필연적으로 사이클이 발생하게 됩니다.
// 그래서 어디서 시작하든 마지막엔 무조건 사이클에 갇히게 되는데
// 이때 사이클에 SAFE ZONE이 있으면 최소 개수를 구할 수 있다는 아이디어입니다.

// DFS로 사이클을 판단합니다.
// 보통 DFS로 사이클을 판단할 때 이미 방문한 노드를 다시 방문하면 사이클로 판단하지만,
// 이 문제는 그렇게 판단할 수가 없습니다.
// 예를 들어 그래프가 A->B->C->D->E->D 이런 식으로 D와 E 사이에 사이클이 존재할 때, 
// 먼저 C부터 DFS를 실행하면 C는 방문처리되고 사이클을 하나 찾게 됩니다.
// 그 다음으로 B에서 DFS를 실행하면 C 노드를 방문했을 때 이미 방문한 노드를 다시 방문하게 되어 사이클이 아님에도 사이클로 판단할 수 있습니다.

// 이걸 방지하기 위해서
// "(C 노드 처럼)이미 방문한 노드이면서 사이클을 찾은 노드"이면 사이클 개수를 추가하지 않고
// "이미 방문한 노드인데 사이클을 찾지 못한 노드"이면 사이클 개수를 추가하는 방식으로 구현합니다.

class Main {

    static int N, M;
    static int answer;
    static boolean[] isCycle; // 사이클과 연결 된 경우 true, 사이클과 연결되지 않았거나 아직 모르는 경우 false
    static boolean[] visited; // 이전에 방문한 경우 true, 방문하지 않은 경우 false
    static int[] graph;       // 입력 받은 map을 변환한 그래프로 변환
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        isCycle = new boolean[N * M];
        visited = new boolean[N * M];
        graph = new int[N * M];

        for (int i=0; i<N; i++) {                        // 입력 map을 바탕으로 그래프 구성
            char[] line = br.readLine().toCharArray();
            for (int j=0; j<M; j++) {
                char c = line[j];
                if (c == 'U') {
                    graph[i * M + j] = (i - 1) * M + j;
                } else if (c == 'R') {
                    graph[i * M + j] = i * M + j + 1;
                } else if (c == 'D') {
                    graph[i * M + j] = (i + 1) * M + j;
                } else {
                    graph[i * M + j] = i * M + j - 1;
                }
            }
        }

        int answer = 0;
        for (int node=0; node<N*M; node++) { // 노드 하나씩 보면서 아직 사이클을 못찾은 노드에 대해 dfs 수행
            if (!isCycle[node]) {
                boolean isNew = dfs(node);
                if (isNew) { // 새로 발견한 사이클인 경우
                    answer++; 
                }
            }
        }

        System.out.println(answer);
    }

    static boolean dfs(int node) {
        if (visited[node] && !isCycle[node]) { // 이미 방문한 노드인데 사이클을 발견하지 못한 경우
            isCycle[node] = true;
            visited[node] = true;
            return true; // 새로 발견한 사이클
        }

        if (isCycle[node]) {
            isCycle[node] = true;
            visited[node] = true;
            return false; // 이미 발견한 사이클
        }

        visited[node] = true; // 탐색하면서 방문처리
        boolean isNew = dfs(graph[node]);
        isCycle[node] = true; // 탐색 완료 후(사이클을 찾은 후) 사이클 찾음 처리

        return isNew;
    }
}

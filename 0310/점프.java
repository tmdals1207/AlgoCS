import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N;
    static int[][] map;
    static long[][] visited; // visited[i][j] : i행 j열에서 N-1행 N-1열까지 경로의 개수
    static int[] dxs = {0, 1};
    static int[] dys = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][];
        visited = new long[N][N];
        for (int i=0; i<N; i++) {
            Arrays.fill(visited[i], -1); 
            // visited를 -1로 초기화하지 않으면 시간초과 or 메모리초과 오류 발생함
            // 그 이유는 0으로 초기화하면 i행 j열에서 N-1행 N-1열까지의 경로의 개수가 0개인 건지 아니면 애초에 방문하지 않은 칸인 건지 구분할 수 없기 때문에
            // 이미 dfs를 돌린 결과 경로의 개수가 0개라고 판단된 칸에 대해서도 dfs가 다시 수행될 수 있음
        }

        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        long answer = dfs(0, 0);

        System.out.println(answer);
    }

    private static long dfs(int x, int y) {
        if (x == N - 1 && y == N - 1) { // 도착지인 경우 1 리턴
            return 1;
        }

        if (visited[x][y] >= 0) { // 이미 방문한 칸인 경우 그 값 그대로 리턴
            return visited[x][y];
        }

        visited[x][y] = 0;
        for (int i=0; i<2; i++) {
            int nx = x + dxs[i] * map[x][y];
            int ny = y + dys[i] * map[x][y];

            if (!inRange(nx, ny)) {
                continue;
            }

            visited[x][y] += dfs(nx, ny);
        }

        return visited[x][y];
    }

    private static boolean inRange(int x, int y) {
        return 0<=x && x<N && 0<=y && y<N;
    }
}

package algo0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class boj2178_미로탐색 {

    static int N;
    static int M;
    static int[][] map;
    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];

        map = new int[N][M];
        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(bfs(0, 0));
    }

    public static int bfs(int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        q.add(new int[] {n, m, 1});
        visited[n][m] = true;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            n = poll[0];
            m = poll[1];
            int cnt = poll[2];

            for (int i=0; i<4; i++) {
                int nn = n + dns[i];
                int nm = m + dms[i];
                if (!inRange(nn, nm)) { // 범위를 벗어나는 경우
                    continue;
                }
                if (visited[nn][nm]) { // 이미 방문한 경우
                    continue;
                }
                if (map[nn][nm] == 0) { // 벽인 경우
                    continue;
                }

                if (nn == N - 1 && nm == M - 1) { // 도착지에 도착한 경우
                    return cnt + 1;
                }

                q.add(new int[] {nn, nm, cnt + 1});
                visited[nn][nm] = true;
            }
        }

        return -1;
    }

    private static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int M;
    static int[][] map;
    static int startN, startM;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        map = new int[N][M];

        for (int i=0; i<N; i++) {
            int[] input = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<M; j++) {
                if (input[j] == 2) {
                    startN = i;
                    startM = j;
                }
                map[i][j] = input[j];
            }
        }

        int answer = bfs();
        if (answer == -1) {
            System.out.println("NIE");
        } else {
            System.out.println("TAK");
            System.out.println(answer - 1);
        }
  }

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {startN, startM});
        map[startN][startM] = -1;

        int[] dns = {-1, 0, 1, 0};
        int[] dms = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            // printMap();
            int[] poll = q.poll();
            int n = poll[0];
            int m = poll[1];

            for (int i=0; i<4; i++) {
                int nn = n + dns[i];
                int nm = m + dms[i];

                if (!inRange(nn, nm)) { // 범위를 벗어나는 경우
                    continue;
                }

                if (map[nn][nm] == 1) { // 벽인 경우
                    continue;
                }

                if (map[nn][nm] < 0) { // 이미 지나온 곳인 경우
                    continue;
                }

                if (map[nn][nm] > 2) { // 과일인 경우
                    return -map[n][m] + 1;
                }

                q.add(new int[] {nn, nm});
                map[nn][nm] = map[n][m] - 1;
            }
        }

        return -1;
    }

    static void printMap() {
        for (int i=0; i<N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    static boolean inRange(int n, int m) {
        return 0<=n && n<N && 0<=m && m<M;
    }
}

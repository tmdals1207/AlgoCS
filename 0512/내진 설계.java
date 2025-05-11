import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int fallCnt;
    static int noneFallCnt;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];
        int startN = 0;
        int startM = 0;
        for (int i=0; i<N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j=0; j<M; j++) {
                if (chars[j] == '*') {
                    map[i][j] = 1; // 내진설계 x
                    noneFallCnt++;
                }
                if (chars[j] == '.') map[i][j] = 0; // 도로
                if (chars[j] == '|') map[i][j] = -1; // 방파제
                if (chars[j] == '#') {
                    map[i][j] = 2; // 내진설계 o
                    noneFallCnt++;
                }
                if (chars[j] == '@') {
                    map[i][j] = 0;
                    startN = i;
                    startM = j;
                }
            }
        }

        bfs(startN, startM);

        System.out.println(fallCnt + " " + noneFallCnt);
    }

    static void bfs(int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        for (int i=0; i<4; i++) { // 본진
            for (int r=1; r<=2; r++) {
                boolean flag = earthquake(n, m, r, q, i);
                if (!flag) break;
            }
        }

        while (!q.isEmpty()) { // 여진
            int[] poll = q.poll();
            n = poll[0];
            m = poll[1];
            for (int i=0; i<4; i++) {
                earthquake(n, m, 1, q, i);
            }
        }
    }

    static boolean earthquake(int n, int m, int r, Queue<int[]> q, int d) {
        int[] dns = {-1, 0, 1, 0};
        int[] dms = {0, 1, 0, -1};
        
        int nn = n + dns[d] * r;
        int nm = m + dms[d] * r;
        if (!inRange(nn, nm)) return false;
        
        if (map[nn][nm] == -1) return false; // 방파제인 경우
        
        if (map[nn][nm] >= 1) { // 건물인 경우
            map[nn][nm] -= 1;
            if (map[nn][nm] == 0) { // 건물이 무너진 경우 큐에 추가
                q.add(new int[] {nn, nm});
                fallCnt++;
                noneFallCnt--;
            }
        }

        return true;
    }

    static boolean inRange(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}

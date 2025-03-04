import java.util.*;
import java.io.*;

public class Main {
    
    static int N;
    static int[][] map;
    static int[][] changeCnt;
    static int[] dxs = {-1, 0, 1, 0};
    static int[] dys = {0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        map = new int[N][];
        changeCnt = new int[N][N];
        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
            Arrays.fill(changeCnt[i], Integer.MAX_VALUE);
        }
        
        bfs(0, 0);
        
        System.out.println(changeCnt[N - 1][N - 1]);
    }
    
    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y});
        changeCnt[x][y] = 0;
        
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            x = poll[0];
            y = poll[1];
            
            for (int i=0; i<4; i++) {
                int nx = x + dxs[i];
                int ny = y + dys[i];
                
                if (!inRange(nx, ny)) {
                    continue;
                }
                
                if (map[nx][ny] == 0) { // 다음 방이 검은 방인 경우
                    if (changeCnt[x][y] + 1 < changeCnt[nx][ny]) {
                        changeCnt[nx][ny] = changeCnt[x][y] + 1;
                        q.add(new int[] {nx, ny});
                    }
                } else { // 다음 방이 흰 방인 경우
                    if (changeCnt[x][y] < changeCnt[nx][ny]) {
                        changeCnt[nx][ny] = changeCnt[x][y];
                        q.add(new int[] {nx, ny});
                    }
                }
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return 0<=x && x<N && 0<=y && y<N;
    }
}

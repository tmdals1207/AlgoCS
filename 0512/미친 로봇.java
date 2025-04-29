package s0512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
   * 미친 로봇 - 4방향 중 임의의 방향을 택해 움직이는 로봇
   * 이동 경로가 단순하다 - 같은 곳을 한 번보다 많이 이동하지 않은 경우 (=중복 방문하지 않는 경우)
   * 로봇의 이동 경로가 단순할 확률 구하기
   * 명령은 n회, 1<=n<=14
   * 절대/상대 오차는 10-9 까지 허용
 */
public class Main_BJ1405 {                              //미친 로봇
    static int n;
    static int[] di = {0, 0, 1, -1};
    static int[] dj = {1, -1, 0, 0};
    static double answer;
    static double[] arr;
    static double[][] map;
    static boolean[][] visited;

    private static void dfs(int i, int j, int cnt) {
        if(cnt == n){
            answer += map[i][j];
            return;
        }

        for(int d = 0; d < 4; d++){
            int ni = i + di[d];
            int nj = j + dj[d];

            if(!visited[ni][nj]) {
                visited[ni][nj] = true;
                map[ni][nj] = map[i][j] * arr[d];
                dfs(ni, nj, cnt + 1);
                map[ni][nj] = 0;
                visited[ni][nj] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new double[4];                       //동서남북으로 갈 확률 구하기
        for(int i = 0; i < 4; i++){
            arr[i] = Double.parseDouble(st.nextToken()) / 100;
        }

        map = new double[29][29];
        visited = new boolean[29][29];

        visited[14][14] = true;
        map[14][14] = 1;
        answer = 0;
        dfs(14, 14, 0);

        System.out.println(answer);
    }
}

package s0512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    n*m 크기의 격자모양 지역에서 지진이 발생
    진원지에서 시작한 충격은 상하좌우 2칸까지 뻗어가고,
    충격에 의해 건물이 무너지며 생기는 여진은 상하좌우 1칸까지 뻗어간다. 단, 방파제를 만나면 멈춤
    지진으로 인해 무너진 건물 수와 무너지지 않은 건물의 개수 구하기
    @: 진원지 / .: 일반 도로 / *: 내진 설계가 되어있지 않은 건물 / #: 내진 설계가 되어있는 건물 / |: 방파제
 */
public class Main_BJ31863 {                             //내진 설계
    static int n, m, broken, safe;
    static char[][] arr;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    private static void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x, y});

        while(!q.isEmpty()){
            int[] ij = q.poll();
            int i = ij[0];
            int j = ij[1];

            if(arr[i][j] == '@'){
                arr[i][j] = '.';

                for(int d = 0; d < 4; d++){
                    int ni = dx[d] + i;
                    int nj = dy[d] + j;

                    if(0 <= ni&&ni < n && 0 <= nj&&nj < m){
                        q.offer(new int[] {ni, nj});

                        if(0 <= (ni+dx[d])&&(ni+dx[d]) < n && 0 <= (nj+dy[d])&&(nj+dy[d]) < m && arr[ni][nj] !='|'){
                            q.offer(new int[] {ni + dx[d], nj + dy[d]});
                        }
                    }
                }
            } else if(arr[i][j] == '*'){
                arr[i][j] = '.';
                broken++;
                safe--;

                for(int d = 0; d < 4; d++) {
                    int ni = dx[d] + i;
                    int nj = dy[d] + j;

                    if(0 <= ni&&ni < n && 0 <= nj&&nj < m) {
                        q.offer(new int[] {ni, nj});
                    }
                }
            } else if (arr[i][j] == '#'){
                arr[i][j] = '*';
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        broken = 0;             //무너진 건물
        safe = 0;               //안 무너진 건물

        int x = 0;
        int y = 0;
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                arr[i][j] = str.charAt(j);
                if(arr[i][j] == '@'){
                    x = i;
                    y = j;
                } else if(arr[i][j] == '*' || arr[i][j] == '#'){
                    safe++;
                }
            }
        }

        bfs(x, y);

        System.out.println(broken + " " + safe);
    }

}

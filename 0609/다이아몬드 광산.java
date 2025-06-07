import java.util.*;
import java.io.*;

// 참고 블로그
// https://blog.encrypted.gg/267
public class Main {

    static int R, C;
    static int[][] map;
    static int[][] dpDL;
    static int[][] dpDR;
    static int[][] dpUL;
    static int[][] dpUR;
    static int answer = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        map = new int[R][C];
        dpDL = new int[R][C];
        dpDR = new int[R][C];
        dpUL = new int[R][C];
        dpUR = new int[R][C];

        for (int i=0; i<R; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for (int r=0; r<R; r++) {
            for (int c=0; c<C; c++) {
                if (map[r][c] == 0) {
                    continue;
                }
                set(r, c, 1, -1, dpDL); // 아래 왼쪽 최장 길이
                set(r, c, 1, 1, dpDR); // 아래 오른쪽
                set(r, c, -1, -1, dpUL); // 위 왼쪽
                set(r, c, -1, 1, dpUR); // 위 오른쪽
            }
        }


        for (int r=0; r<R; r++) {
            for (int c=0; c<C; c++) {
                if (map[r][c] == 0) { // 다이아몬드를 만들 수 없는 경우
                    continue;
                }
                
                int size = Math.min(dpDL[r][c], dpDR[r][c]); // 다이아몬드 크기가 최대 1인 경우
                if (size == 1 && answer == 0) {
                    answer = 1;
                    continue;
                }

                if (size < answer) { // 만들 수 있는 다이아몬드의 최대 크기가 현재 answer보다 작은 경우
                    continue;
                }
                
                for (int s = size; s >= 2; s--) {
                    // 맞은편 길이가 s 이상이면 s 크기의 다이아몬드를 만들 수 있음
                    int otherR = r + 2 * s - 2;
                    if (0 > otherR || otherR >= R) {
                        continue;
                    }
                    if (map[otherR][c] == 0) { // 맞은편에 1이 없는 경우
                        continue;
                    }

                    if (Math.min(dpUL[otherR][c], dpUR[otherR][c]) >= s) { // 다이아몬드를 만들 수 있는 경우
                        answer = Math.max(answer, s);
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static void set(int r, int c, int dr, int dc, int[][] dp) {
        int length = 1;
        int nr = r + dr;
        int nc = c + dc;

        while (inRange(nr, nc) && map[nr][nc] == 1) {
            length++;
            nr += dr;
            nc += dc;
        }

        dp[r][c] = length;
    }

    static boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    static void printArr(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}

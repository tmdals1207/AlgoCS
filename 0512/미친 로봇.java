import java.util.*;
import java.io.*;

public class Main {
    static int N; // 이동 횟수
    static double[] prob; // 각 방향으로 이동할 확률(동, 서, 남, 북)
    static boolean[][] visited = new boolean[29][29]; // 좌표는 -14~14 범위, 중앙은 (14,14)로 설정
    static double answer;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        prob = new double[4];
        prob[0] = Double.parseDouble(input[1]) / 100;
        prob[1] = Double.parseDouble(input[2]) / 100;
        prob[2] = Double.parseDouble(input[3]) / 100;
        prob[3] = Double.parseDouble(input[4]) / 100;
        
        visited[14][14] = true; // 시작 위치 방문 표시
        f(14, 14, 0, 1);
        System.out.println(answer);
    }
    
    static void f(int x, int y, int cnt, double p) {
        if (cnt == N) {
            answer += p;
            return;
        }
        int[] dxs = {0, 0, 1, -1}; // 동, 서, 남, 북
        int[] dys = {1, -1, 0, 0};
        
        for (int d=0; d<4; d++) {
            int nx = x + dxs[d];
            int ny = y + dys[d];
            
            if (!inRange(nx, ny)) continue; // 범위 체크
            
            if (visited[nx][ny]) { // 이미 방문한 위치인 경우
                continue;
            }
            
            visited[nx][ny] = true;
            f(nx, ny, cnt + 1, p * prob[d]);
            visited[nx][ny] = false;
        }
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < 29 && 0 <= y && y < 29;
    }
}

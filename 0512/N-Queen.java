import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] queenX; // queenX[y] : y행에 위치한 퀸의 행 좌표
    static int answer;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        queenX = new int[N];
        Arrays.fill(queenX, -1);
        f(0);

        System.out.println(answer);
    }

    static void f(int x) { // 백트래킹: x번째 행(0 ~ N-1) 탐색
        if (x == N) { // 마지막 행까지 모두 탐색한 경우 퀸을 놓을 수 있는 경우이므로 answer++
            answer++;
            return;
        }

        for (int y=0; y<N; y++) { // y번째 열에 퀸을 놓고자 할 때
            if (queenRange(x, y)) { // 다른 퀸에게 영향을 받는 경우 넘김
                continue;
            }

            queenX[y] = x; // x행 y열에 퀸 놓기
            f(x + 1); // 다음 행 탐색
            queenX[y] = -1; // 놓은 퀸 없애기
        }
    }

    static boolean queenRange(int x, int y) { // x, y가 다른 퀸에게 영향을 받는지 여부 판단
        if (queenX[y] >= 0) { // y열에 퀸이 있는 경우
            return true;
        }

        int rate = 1; // 1칸 떨어진 위치부터 탐색
        boolean found = false;
        while (true) {
            if (findQueen(x, y, rate++)) { // 대각선에 위치한 퀸에게 영향을 받는다면 found = true
                found = true;
                break;
            }

            if (rate == N) { // 탐색 범위가 N이면 모두 탐색한 걸로 판단하고 break
                break;
            }
        }

        return found;
    }

    static boolean findQueen(int x, int y, int rate) { // x, y 칸에서 rate칸 만큼 떨어진 곳에 퀸이 있는지 판단
        boolean found = false;
        if (inRange(x - rate, y - rate) && queenX[y - rate] == x - rate) { // 왼쪽 위 대각선 칸이 범위 안에 있고 퀸이 있는 경우
            return true;
        }

        if (inRange(x - rate, y + rate) && queenX[y + rate] == x - rate) { // 오른쪽 위 대각선
            return true;
        }

        if (inRange(x + rate, y - rate) && queenX[y - rate] == x + rate) { // 왼쪽 아래 대각선
            return true;
        }

        if (inRange(x + rate , y + rate) && queenX[y + rate] == x + rate) { // 오른쪽 아래 대각선
            return true;
        }

        return false;
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}

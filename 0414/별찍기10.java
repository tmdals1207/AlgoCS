import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 별찍기10 {

    static int N;
    static char[][] board;


    static void star(int x, int y, int size, boolean isMid) {
        // 현재 크기에서 가운데, 즉 5번째 위치라면 board에서 size에 비례한 크기만큼 지움
        if (isMid) {
            for (int i = x; i < x + size; i++) {
                for (int j = y; j < y + size; j++) {
                    board[i][j] = ' ';
                }
            }
        }

        // size가 1보다 작아질 수 없으니 1이면 return 시킴
        if (size == 1) return;

        // size는 1/3 크기로 작아지고 있음.
        int newSize = size / 3;
        // 현재 size에서 몇번째 블록인지 판별하는 변수
        int cnt = 1;

        for (int i = x; i < x+size; i+=newSize) {
            for (int j = y; j < y+size; j+=newSize) {
                star(i, j, newSize, cnt==5); // 재귀적으로 size가 1/3가 된 크기로 새로운 시작 지점에서 호출
                cnt++;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new char[N][N];

        // *로 board를 전부 채우고 지워가는 방식으로 시작.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = '*';
            }
        }

        // 시작 x좌표 y좌표와 board의 크기, 가운데인지 확인하는 boolean
        star(0, 0, N, false);

        for (char[] row : board) {
            System.out.println(row);
        }
    }
}

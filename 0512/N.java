import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class N-Queen {  
    

    public static int[] arr;
    public static int N;
    public static int count = 0;


    public static void nQueen(int depth) {
        // 모든 행에 퀸을 놓았다면 경우의 수 증가
        if (depth == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[depth] = i;
            if (isPossible(depth)) {
                nQueen(depth + 1);
            }
        }

    }

    public static boolean isPossible(int col) {

        for (int i = 0; i < col; i++) {
            // 1. 같은 열에 퀸이 있는지 확인
            if (arr[col] == arr[i]) {
                return false;
            }

            // 2. 대각선상에 퀸이 있는지 확인 (행 차이 == 열 차이)
            else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        nQueen(0);
        System.out.println(count);
    }
}
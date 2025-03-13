import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [풀이 흐름]
// 처음엔 각 칸에 사자를 놓는 경우와 놓지 않는 경우로 나눠야겠다고 생각해서
// dp1과 dp2를 둘 다 이차원 배열로 선언했는데,
// 어짜피 한 행에는 사자 한마리만 놓을 수 있으니까
// i번째 행에 사자를 놓는 경우와 놓지 않는 경우로만 나누면 되겠다고 생각해서
// 일차원 배열로 수정함
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp1 = new int[N]; // dp1[i] : i행 사자를 놓을 때 사자를 배치하는 최대 경우의 수
        int[] dp2 = new int[N]; // dp2[i] : i행 사자를 놓지 않을 때 사자를 배치하는 최대 경우의 수

        dp1[0] = 2; // 첫 열에 사자를 놓는 경우의 수
        dp2[0] = 1; // 첫 열에 사자를 놓지 않는 경우의 수

        for (int i=1; i<N; i++) {
            dp1[i] = (dp1[i - 1] + dp2[i - 1] * 2) % 9901; // 이전 행에 사자를 놓은 경우의 수 + 사자를 놓지 않은 경우의 수 * 2
            dp2[i] = (dp1[i - 1] + dp2[i - 1]) % 9901;     // 이전 행에 사자를 놓은 경우의 수 + 사자를 놓지 않은 경우의 수
        }

        System.out.println((dp1[N - 1] + dp2[N - 1]) % 9901);
    }
}

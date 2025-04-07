import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 징검다리 건너기 {

    static int N, K;
    static int[][] energy;
    static int[] dp;


    static void dp() {
        dp = new int[N+1];

        dp[1] = 0;
        if (N >= 2) dp[2] = energy[1][0];
        if (N >= 3) dp[3] = Math.min(dp[2] + energy[2][0], dp[1] + energy[1][1]);

        for (int i = 4; i <= N; i++) {
            dp[i] = Math.min(dp[i-1] + energy[i-1][0], dp[i-2] + energy[i-2][1]);
        }

    }

    static int dpWithSuperJump() {
        if (N < 4) return Integer.MAX_VALUE;

        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= N - 3; i++) {
            dp = new int[N + 1];
            for (int j = 0; j <= N; j++) dp[j] = Integer.MAX_VALUE;

            dp[1] = 0;
            dp[2] = energy[1][0];
            dp[3] = Math.min(dp[2] + energy[2][0], dp[1] + energy[1][1]);

            for (int j = 4; j <= N; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1] + energy[j - 1][0]);
                dp[j] = Math.min(dp[j], dp[j - 2] + energy[j - 2][1]);
                if (j == i + 3) {
                    dp[j] = Math.min(dp[j], dp[i] + K);
                }
            }

            min = Math.min(min, dp[N]);
        }

        return min;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        energy = new int[N+1][2];

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            energy[i][0] = Integer.parseInt(st.nextToken());
            energy[i][1] = Integer.parseInt(st.nextToken());
        }

        K = Integer.parseInt(br.readLine());

        dp();

        int answer = dp[N];
        answer = Math.min(answer, dpWithSuperJump());

        System.out.println(answer);
    }
}
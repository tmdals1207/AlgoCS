import java.util.*;
import java.io.*;

public class Main {

    static int T; // 테스트 케이스 수
    static int L; // 문자열의 길이
    static long[] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t=0; t<T; t++) {
            L = Integer.parseInt(br.readLine());
            dp = new long[L + 1];
            long answer = f();
            System.out.println(answer);
        }
    }

    static long f() {
        if (L == 1) {
            return 0;
        }
        dp[0] = 1;
        dp[2] = 1;
        for (int i=4; i<=L; i+=2) {
            for (int j=2; j<=i; j+=2) {
                dp[i] += (dp[i - j] * dp[j - 2]) % 1_000_000_007;
                // dp[8] =
                // dp[6] * dp[0] +
                // dp[4] * dp[2] +
                // dp[2] * dp[4] +
                // dp[0] * dp[6]
                dp[i] %= 1_000_000_007;
            }
        }

        return dp[L] % 1_000_000_007;
    }
}

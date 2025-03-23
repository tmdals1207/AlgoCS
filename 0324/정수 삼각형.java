import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][];
        int[][] dp = new int[n][n];

        for (int i=0; i<n; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp[0][0] = arr[0][0];
        for (int i=0; i<n-1; i++) {
            for (int j=0; j<i+1; j++) {
                dp[i + 1][j] = Math.max(dp[i][j] + arr[i + 1][j], dp[i + 1][j]);
                dp[i + 1][j + 1] = Math.max(dp[i][j] + arr[i + 1][j + 1], dp[i + 1][j + 1]);
            }
        }

        System.out.println(Arrays.stream(dp[n - 1]).max().getAsInt());
    }
}

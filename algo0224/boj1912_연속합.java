package algo0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1912_연속합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[n];
        dp[0] = nums[0]; // 초기화

        for (int i=1; i<n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]); // (이전까지의 최대합 + 현재값)과 현재값을 비교
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}

package s0609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    '('와 ')' 문자로만 이루어진 문자열 = 괄호
    올바른 괄호 문자열 = (), ()이 올바르면 (s)도 올바른 괄호 문자열
    s와 t가 올바른 괄호 문자열이면, 두 문자열을 붙인 st도 올바른 괄호 문자열
    (()())()은 올바른 괄호 문자열, 그러나 (()는 아님
    길이 l이 주어질 때, 길이가 l인 올바른 괄호 문자열의 개수를 구하기
    * l <= 5000
 */
public class Main_BJ10422 {                         //괄호
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        long[] dp = new long[5001];

        dp[0] = 1;
        dp[2] = 1;

        for(int i = 4; i < 5001; i += 2){
            for(int j = 0; j < i; j += 2) {
                dp[i] += (dp[j] * dp[i - (j + 2)]);
                dp[i] = dp[i] % 1000000007;
            }
        }

        for(int i = 0; i < t; i++){
            int l = Integer.parseInt(br.readLine());

            System.out.println(dp[l]);
        }
    }
}

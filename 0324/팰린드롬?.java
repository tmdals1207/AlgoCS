import java.io.*;
import java.util.Arrays;

// 팰린드롬?
public class Main {

    static int N; // 수열의 크기
    static int M; // 질문의 개수
    static int[] numbers; // 수열
    static int[][] questions; // 질문
    static boolean[][] dp; // dp[i][j] 정의 : i번째 숫자부터 j번째 숫자까지가 팰린드롬이면 true

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());
        questions = new int[M][2];

        for (int i=0; i<M; i++) {
            String[] split = br.readLine().split(" ");
            questions[i][0] = Integer.parseInt(split[0]);
            questions[i][1] = Integer.parseInt(split[1]);
        }

        // 입력 끝============================================

		    // 초기화
        dp = new boolean[N][N];
        for (int i=0; i<N; i++) {
            dp[i][i] = true; // 자기 자신을 팰린드롬으로 추가
        }

        // 팰린드롬 설정 (점화식 적용)
        for (int start=N-2; start>=0; start--) { // 시작점(S)을 기준으로 끝에서 시작
            for (int end=start+1; end<N; end++) {
                if (isPalindrome(start, end)) {
                    dp[start][end] = true;
                }
            }
        }

        // 정답 출력
        for (int[] q : questions) {
            int s = q[0] - 1;
            int e = q[1] - 1;

            if (dp[s][e]) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }

        bw.close();
    }

    private static boolean isPalindrome(int start, int end) {
        if (numbers[start] != numbers[end]) { // 시작점 수와 끝점의 수가 다르면 팰린드롬X
            return false;
        }
        if (end - start == 1) { // 시작점의 수와 끝점의 수가 같고 시작점과 끝점의 차이가 1이라면 팰린드롬O
            return true;
        }
        
        // 이외의 경우
        return dp[start + 1][end - 1];
    }
}

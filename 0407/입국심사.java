import java.util.*;
import java.io.*;

// 풀이
// - 총 소요시간을 기준으로 이분탐색한다(최소 시간 0, 최대 시간 10억 * 10억)
// - 각 심사대가 총 소요시간 동안 심사할 수 있는 사람의 수를 계산한다
// - 심사 가능한 사람의 총 수가 M보다 큰 경우 총 소요시간을 줄인다
// - 그렇지 않으면 총 소요시간을 늘린다
public class Main {

    static int N; // N개의 심사대
    static int M; // M명의 친구
    static long[] times;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        times = new long[N];

        for (int i=0; i<N; i++) {
            times[i] = Integer.parseInt(br.readLine());
        }

        // 최소 시간 0, 최대 시간 10억 * 10억
        long left = 0L;
        long right = 1_000_000_000L * 1_000_000_000L;
        while (left < right) {
            long totalTime = (left + right) / 2; // 탐색 중인 총 소요시간(가정)
            long tmpCnt = 0;
            for (long time : times) { // 각 심사대가 총 소요시간 동안 심사할 수 있는 사람의 수 계산
                long cnt = totalTime / time;
                tmpCnt += cnt; // 사람 수 갱신
                if (tmpCnt > M) { // tmpCnt의 크기가 long 범위를 넘어갈 수 있으므로 M보다 커지면 바로 break;
                    break;
                }
            }

            if (tmpCnt < M) {
                left = totalTime + 1;
            } else {
                right = totalTime;
            }
        }

        System.out.println(left);
  }
}

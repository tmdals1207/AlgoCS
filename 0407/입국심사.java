import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class 입국심사 {

    static int N, M;
    static int[] times;


    static long binarySearch() {
        long left = 1;
        long right = (long) times[N - 1] * M;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            long cnt = 0;
            for (int time : times) {
                cnt += mid / time;
            }

            if (cnt >= M) { // 심사 가능한 인원이 충분함 → 시간 줄이기
                answer = mid;
                right = mid - 1;
            } else { // 인원이 부족 → 시간 늘려야 함
                left = mid + 1;
            }
        }

        return answer;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 심사관 수
        M = Integer.parseInt(st.nextToken()); // 사람 수

        times = new int[N]; // 각 심사대가 걸리는 시간
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(times); // 이분탐색을 위한 정렬

        System.out.println(binarySearch());
    }
}
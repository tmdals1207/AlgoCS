import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    
    static int[] cumulative; // 누적 합 배열
    static BufferedWriter bw; // 출력 버퍼
    
    // 특정 구간 합을 계산하여 출력하는 메서드
    static void sum(int start, int end) throws IOException {
        int sum = cumulative[end] - cumulative[start - 1]; // 누적 합 배열을 이용한 구간 합 계산
        bw.write(sum + "\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 첫 번째 줄 입력 (N: 수의 개수, M: 구간 합을 구해야 하는 횟수)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        cumulative = new int[N + 1]; // 누적 합 배열

        // 두 번째 줄 입력 (N개의 숫자)
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) { // 누적 합 배열 생성
            cumulative[i] = Integer.parseInt(st.nextToken()) + cumulative[i - 1]; 
        }

        // M개의 구간 합 쿼리 처리
        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine());
            sum(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        bw.close(); // 출력 버퍼 닫기 (flush 역할도 수행행)
    }
}

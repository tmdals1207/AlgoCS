package a0526;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_15565_귀여운라이언 {

    static int N, K;
    static int[] dolls;
    static List<Integer> lion;


    static void cal() {
        // 라이언 인형 수가 K보다 적으면 조건을 만족하는 구간이 없음
        if (lion.size() < K) {
            System.out.println(-1);
            return;
        }

        int answer = Integer.MAX_VALUE;

        // K개의 라이언이 있는 인덱스 구간을 검사
        for (int i = 0; i <= lion.size() - K; i++) {
            int start = lion.get(i);
            int end = lion.get(i + K - 1);
            answer = Math.min(answer, end - start + 1); // 구간 길이중 최소값으로 갱신
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dolls = new int[N];

        st = new StringTokenizer(br.readLine());

        // 라이언(1)인 인형의 인덱스를 리스트에 저장
        for (int i = 0; i < N; i++) {
            dolls[i] = Integer.parseInt(st.nextToken());
        }

        lion = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (dolls[i] == 1) {
                lion.add(i);
            }
        }
        cal();
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 컵라면 {

    static int N, sum;
    static Problem[] problems;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();


    static void cal() {
        for (Problem p : problems) {      // 데드라인 순서대로 정렬된 문제 배열 순회
            pq.add(p.cupNoodle);          // 현재 문제의 컵라면 보상 추가

            // 데드라인을 초과해서 문제를 풀면 컵라면을 받을 수 없으므로
            // 현재까지 푼 문제 수가 데드라인보다 크면 가장 적은 컵라면 보상을 제거
            if (pq.size() > p.deadLine) { 
                pq.poll();
            }
        }

        // 우선순위 큐에 남은 컵라면 보상의 총 합을 구함
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        problems = new Problem[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            problems[i] = new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(problems);

        cal();

        System.out.println(sum);
    }


    static class Problem implements Comparable<Problem>{
        int deadLine, cupNoodle;

        public Problem (int deadLine, int cupNoodle) {
            this.deadLine = deadLine;
            this.cupNoodle = cupNoodle;
        }

        @Override
        public int compareTo(Problem o) {
            return Integer.compare(this.deadLine, o.deadLine);
        }
    }
}
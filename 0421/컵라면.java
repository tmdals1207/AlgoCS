import java.util.*;
import java.io.*;

// 문제 풀 때 단위 시간 1 소요
// 각 문제의 데드라인은 N이하의 자연수
public class Main {

    static int N; // 숙제의 개수
    static Homework[] homeworks;
    static int[] answer;

    static class Homework implements Comparable<Homework> {
        int deadLine;
        int reward;

        Homework(int deadLine, int reward) {
            this.deadLine = deadLine;
            this.reward = reward;
        }

        @Override
        public int compareTo(Homework h) {
            if (this.reward < h.reward) { // 컵라면 수 기준으로 내림차순 정렬
                return 1;
            } else if (this.reward > h.reward) {
                return -1;
            }
            return Integer.compare(h.deadLine, this.deadLine); // 데드라인 기준으로 내림차순 정렬
        } // 컵라면 수가 많으면 앞에, 수가 같다면 데드라인이 느리면 앞에

        @Override
        public String toString() {
            return "[" + deadLine + ", " + reward + "]";
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        homeworks = new Homework[N];

        for (int i=0; i<N; i++) {
            String[] split = br.readLine().split(" ");
            homeworks[i] = new Homework(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }

        Arrays.sort(homeworks, new Comparator<Homework>() { // 데드라인 기준으로 내림차순으로 정렬
            @Override
            public int compare(Homework o1, Homework o2) {
                return Integer.compare(o2.deadLine, o1.deadLine);
            }
        });
      
        PriorityQueue<Homework> pq = new PriorityQueue<>();
        int cursor = 0;
        int sum = 0;
        for (int i=200_000; i>=1; i--) {
            while (cursor < N && homeworks[cursor].deadLine == i) {
                pq.add(homeworks[cursor++]);
            }

            if (pq.isEmpty()) {
                continue;
            }

            sum += pq.poll().reward;
        }

        System.out.println(sum);
    }

    private static int findTarget(int start, int end) {
        if (start == end) {
            if (answer[start] > 0) {
                return -1;
            } else {
                return start;
            }
        }
        int mid = (start + end) / 2;
        int rightMax = findTarget(mid + 1, end);
        if (rightMax > 0) {
            return rightMax;
        } else {
            return findTarget(start, mid);
        }
    }
}

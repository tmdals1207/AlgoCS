import java.io.*;
import java.util.*;

public class 휴게소세우기 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        List<Integer> positions = new ArrayList<>();

        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                positions.add(Integer.parseInt(st.nextToken()));
            }
        }

        // 고속도로의 양 끝
        positions.add(0);
        positions.add(L);

        // 입력받은 휴계소들을 정렬
        Collections.sort(positions);

        PriorityQueue<Section> pq = new PriorityQueue<>();

        // 초기에 주어진 구간들을 큐에 저장
        for (int i = 0; i < positions.size() - 1; i++) { // 4개의 구간은 3개의 휴계소로부터 생기니 -1
            int gap = positions.get(i + 1) - positions.get(i);
            pq.offer(new Section(gap));
        }

        // M개의 휴게소 설치
        for (int i = 0; i < M; i++) {
            Section sec = pq.poll();     // 가장 긴 구간 꺼내서
            sec.addRestStop();           // 그 구간에 휴게소 하나 추가
            pq.offer(sec);               // 다시 큐에 저장
        }

        System.out.println(pq.peek().getMax());
    }


    static class Section implements Comparable<Section> {

        int length; // 해당 구간의 길이
        int count;  // 설치된 휴게소 개수 (이 간격을 몇 조각으로 쪼갰는지)

        Section(int length) {
            this.length = length;
            this.count = 1;
        }

        // 구간이 쪼개질 때 구간의 휴계소 개수를 증가
        void addRestStop() {
            this.count++;
        }

        // 각 구역이 모두 나눠진 후의 최대 간격 계산
        int getMax() {
            return (int) Math.ceil((double) length / count);
        }

        // 해당 클래스를 내림차순 즉 큰 것부터 큐에 저장되도록 compareTo 살정
        @Override
        public int compareTo(Section o) {
            return o.getMax() - this.getMax();
        }
    }
}
import java.util.*;
import java.io.*;

public class Main {

    static int N; // 수의 개수
    static int M; // 수의 변경이 일어나는 횟수
    static int K; // 구간의 합을 구하는 횟수

    static class SegmentTree {

        long tree[];

        SegmentTree(int numsSize) { // tree 배열 생성
            int h = (int) Math.ceil(Math.log(numsSize)/ Math.log(2));

            int treeSize = (int) Math.pow(2, h + 1);
            tree = new long[treeSize];
        }

        long init(long[] nums, int node, int start, int end) {
            if (start == end) { // leaf 노드인 경우
                return tree[node] = nums[start];
            }

            return tree[node] = init(nums, node * 2, start, (start + end) / 2) // 자식 노드의 합을 현재 노드의 값으로 설정
                + init(nums, node * 2 + 1, (start + end) / 2 + 1, end);
        }

        void update (int node, int start, int end, int changeIdx, long diff) {
            if (changeIdx < start || end < changeIdx) { // 현재 노드의 구간 합 범위가 바뀌는 범위를 포함하지 않는 경우 그냥 리턴
                return;
            }

            tree[node] += diff; // 값 업데이트

            if (start != end) { // 자식 노드가 있는 경우 자식 노드에 대해서도 update 진행
                update(node * 2, start, (start + end) / 2, changeIdx, diff);
                update(node * 2 + 1, (start + end) / 2 + 1, end, changeIdx, diff);
            }
        }

        long sum(int node, int start, int end, int wantStart, int wantEnd) {
            if (wantEnd < start || end < wantStart) { // 구하고자 하는 구간 합 범위가(want) 현재 노드의 구간 합 범위와 겹치는 구간이 없다면 그냥 리턴
                return 0;
            }

            if (wantStart <= start && end <= wantEnd) { // 구하고자 하는 구간 합 범위가(want) 현재 노드의 구간 합 범위에 완전히 포함된다면 현재 노드값 리턴
                return tree[node];
            }

            return sum(node * 2, start, (start + end) / 2, wantStart, wantEnd) // 일부만 걸치는 경우 자식 노드에 대해 sum 계산
                + sum(node * 2 + 1, (start + end) / 2 + 1, end, wantStart, wantEnd);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        long[] nums = new long[N + 1];
        for (int i=1; i<=N; i++) {
            long num = Long.parseLong(br.readLine());
            nums[i] = num;
        }

        SegmentTree st = new SegmentTree(nums.length);
        st.init(nums, 1, 1, nums.length - 1);

        for (int i=0; i<M+K; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            long c = Long.parseLong(input[2]);

            if (a == 1) {
                st.update(1, 1, nums.length - 1, b, c - nums[b]);
                nums[b] = c;
            } else if (a == 2) {
                long sum = st.sum(1, 1, nums.length - 1, b, (int) c);
                System.out.println(sum);
            }
        }
    }
}

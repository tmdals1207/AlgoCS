import java.util.*;
import java.io.*;

public class Main {

    static int N; // 수의 개수
    static int M; // 수의 변경이 일어나는 횟수
    static int K; // 구간의 합을 구하는 횟수

    static class SegmentTree {

        long tree[];

        SegmentTree(int numsSize) {
            int h = (int) Math.ceil(Math.log(numsSize)/ Math.log(2));

            int treeSize = (int) Math.pow(2, h + 1);
            tree = new long[treeSize];
        }

        long init(long[] nums, int node, int start, int end) {
            if (start == end) {
                return tree[node] = nums[start];
            }

            return tree[node] = init(nums, node * 2, start, (start + end) / 2)
                + init(nums, node * 2 + 1, (start + end) / 2 + 1, end);
        }

        void update (int node, int start, int end, int changeIdx, long diff) {
            if (changeIdx < start || end < changeIdx) {
                return;
            }

            tree[node] += diff;

            if (start != end) {
                update(node * 2, start, (start + end) / 2, changeIdx, diff);
                update(node * 2 + 1, (start + end) / 2 + 1, end, changeIdx, diff);
            }
        }

        long sum(int node, int start, int end, int wantStart, int wantEnd) {
            if (wantEnd < start || end < wantStart) {
                return 0;
            }

            if (wantStart <= start && end <= wantEnd) {
                return tree[node];
            }

            return sum(node * 2, start, (start + end) / 2, wantStart, wantEnd)
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

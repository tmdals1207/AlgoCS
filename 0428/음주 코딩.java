import java.util.*;
import java.io.*;

public class Main {

    static int N; // 수열의 길이
    static int[] nums;

    static class SegmentTree {

        int[] tree; // 0, -1, 1 저장 - overflow를 방지하면서 부호만 나타내기 위해

        SegmentTree(int numCnt) {
            int h = (int) Math.ceil(Math.log(numCnt) / Math.log(2));
            int treeSize = (int) Math.pow(2, h + 1);
            tree = new int[treeSize];
        }

        int init(int node, int start, int end) {
            if (start == end) {
                return tree[node] = nums[start];
            }

            int mid = (start + end) / 2;
            return tree[node] = init(node * 2, start, mid) * init(node * 2 + 1, mid + 1, end);
        }

        int update(int node, int start, int end, int idx, int value) { // value = 0, -1, 1
            if (idx < start || end < idx) { // 범위를 벗어나는 경우
                return tree[node]; // 곱셈 정보를 업데이트 하기 때문에 값을 리턴
            }

            if (start == end) {
                return tree[node] = value;
            }

            int mid = (start + end) / 2;
            return tree[node] = update(node * 2, start, mid, idx, value) * update(node * 2 + 1, mid + 1, end, idx, value);
        }

        int multiply(int node, int start, int end, int multStart, int multEnd) {
            if (multEnd < start || end < multStart) {
                return 1;
            }

            if (multStart <= start && end <= multEnd) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            return multiply(node * 2, start, mid, multStart, multEnd) * multiply(node * 2 + 1, mid + 1, end, multStart, multEnd);
        }

        void printTree() {
            System.out.println(Arrays.toString(tree));
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str;
            if ((str = br.readLine()) == null) {
                break;
            }

            String[] input = str.split(" ");
            N = Integer.parseInt(input[0]); // 수열의 크기
            int K = Integer.parseInt(input[1]); // 게임의 라운드 수
            input = br.readLine().split(" ");
            nums = new int[N + 1];
            for (int i=1; i<=N; i++) {
                int num = Integer.parseInt(input[i - 1]);
                // 0, -1, 1로 저장
                if (num == 0) {
                    nums[i] = 0;
                } else {
                    nums[i] = num / Math.abs(num);
                }
            }

            SegmentTree st = new SegmentTree(N);
            st.init(1, 1, N); // 수열 nums를 바탕으로 st 초기화. tree의 0번째 노드(root)부터 채우며 시작 범위는 수열의 전 범위

            StringBuilder answer = new StringBuilder();
            // st.printTree();
            for (int k=0; k<K; k++) { // 명령어 실행
                input = br.readLine().split(" ");
                char command = input[0].charAt(0);
                if (command == 'C') { // 변경 명령
                    int idx = Integer.parseInt(input[1]);
                    int value = Integer.parseInt(input[2]);
                    
                    if (value == 0) {
                        st.update(1, 1, N, idx, 0);
                        nums[idx] = 0;
                    } else if (nums[idx] * value < 0 || nums[idx] == 0) { // 바꾸는 값이 기존 값과 부호가 다른 경우
                        st.update(1, 1, N, idx, value / Math.abs(value));
                        nums[idx] = value / Math.abs(value);
                    }
                    // st.printTree();
                } else if (command == 'P') { // 곱셈 명령
                    int multStart = Integer.parseInt(input[1]);
                    int multEnd = Integer.parseInt(input[2]);
                    int result = st.multiply(1, 1, N, multStart, multEnd);
                    if (result < 0) {
                        answer.append("-");
                    } else if (result > 0) {
                        answer.append("+");
                    } else {
                        answer.append("0");
                    }
                }
            }
            System.out.println(answer);
        }
    }
}

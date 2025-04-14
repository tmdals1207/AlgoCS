import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기 {

    static int N, M, K;
    static long[] arr, tree;

    // 세그먼트 트리 초기화: 구간 합을 트리에 저장
    static long init(int node, int start, int end) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
    }

    // 값 변경 시 차이(diff)를 이용해 트리 갱신
    static void update(int node, int start, int end, int idx, long diff) {
        if (idx < start || idx > end) return;  // 범위 밖일 경우 종료
        tree[node] += diff;                    // 현재 노드 값 갱신
        if (start != end) {                    // 리프 노드가 아니면 자식 노드도 갱신
            int mid = (start + end) / 2;
            update(node * 2, start, mid, idx, diff);
            update(node * 2 + 1, mid + 1, end, idx, diff);
        }
    }

    // 주어진 구간 [left, right] 합 구하기
    static long sum(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return 0;           // 완전히 벗어난 경우
        if (left <= start && end <= right) return tree[node]; // 완전히 포함되는 경우
        int mid = (start + end) / 2;
        return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        // 트리 높이 구하기
        int h = (int) Math.ceil(Math.log(N)/ Math.log(2));
        // 높이를 이용한 배열 사이즈 구하기
        int treeSize = (int) Math.pow(2,h+1);
        // 배열 생성
        tree = new long[treeSize];

        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init(1, 1, N); // 세그먼트 트리 초기화

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            if (cmd == 1) { // 값 Update 진행
                update(1, 1, N, a, b - arr[a]);
                arr[a] = b;
            } else {        // 구간 합 조회
                System.out.println(sum(1, 1, N, a, (int) b));
            }
        }
    }
}
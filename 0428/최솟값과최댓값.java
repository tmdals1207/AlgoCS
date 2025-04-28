import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최솟값과최댓값 {

    static int N, M;
    static long[] minTree, maxTree, arr;


    static long initMin(int node, int start, int end) {
        if (start == end) return minTree[node] = arr[start];
        int mid = (start + end) / 2;
        return minTree[node] = Math.min(initMin(node*2, start, mid), initMin(node*2+1, mid+1, end));
    }

    static long initMax(int node, int start, int end) {
        if (start == end) return maxTree[node] = arr[start];
        int mid = (start + end) / 2;
        return maxTree[node] = Math.max(initMax(node*2, start, mid), initMax(node*2+1, mid+1, end));
    }

    static long min(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return Long.MAX_VALUE;
        if (left <= start && end <= right) return minTree[node];
        int mid = (start + end) / 2;
        return Math.min(min(node*2, start, mid, left, right),
                min(node*2+1, mid+1, end, left, right));
    }

    static long max(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return Long.MIN_VALUE;
        if (left <= start && end <= right) return maxTree[node];
        int mid = (start + end) / 2;
        return Math.max(max(node*2, start, mid, left, right),
                max(node*2+1, mid+1, end, left, right));
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        minTree = new long[N*4];
        maxTree = new long[N*4];
        arr = new long[N+1];

        for (int i = 1; i < N+1; i ++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initMin(1, 1, N);
        initMax(1, 1, N);

        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            long minValue = min(1, 1, N, a, b);
            long maxValue = max(1, 1, N, a, b);

            System.out.println(minValue + " " + maxValue);
        }

    }
}

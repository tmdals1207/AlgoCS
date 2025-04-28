import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 완전이진트리 {

    static int K;
    static int[] arr;
    static ArrayList<Integer>[] tree;

    // 중위순회
    static void inOrder(int start, int end, int depth) {
        if(start > end) return;

        int mid = (start+end)/2;
        tree[depth].add(arr[mid]);

        inOrder(start, mid-1, depth+1);
        inOrder(mid+1, end, depth+1);
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        K = Integer.parseInt(br.readLine());
        int size = (int) Math.pow(2, K) - 1;
        // 완전 이진 트리의 총 노드 수
        arr = new int[size];
        tree = new ArrayList[K];

        for (int j = 0; j < K; j++) {
            tree[j] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        inOrder(0, size-1, 0);

        for (int i = 0; i < K; i++) {
            for (int val : tree[i]) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
import java.util.*;
import java.io.*;

public class Main {

    static int N; // 강의 수
    static int M; // 블루레이 개수
    static int arr[];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = Arrays.stream(arr).max().getAsInt();
        int right = Arrays.stream(arr).sum();
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = cntBlueLay(mid);
            if (cnt <= M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    static int cntBlueLay(int mid) {
        int cnt = 1;
        int sum = 0;
        for (int a : arr) {
            sum += a;
            if (sum > mid) {
                sum = a;
                cnt++;
            }
        }

        return cnt;
    }
}

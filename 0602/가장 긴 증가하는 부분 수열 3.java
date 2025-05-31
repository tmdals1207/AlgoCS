import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] arr;
    static List<Integer> tails;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        tails = new ArrayList<>();

        for (int i=0; i<N; i++) {
            int a = arr[i];
            if (tails.isEmpty() || tails.get(tails.size() - 1) < a) {
                tails.add(a);
                continue;
            }

            int idx = findIdx(a);
            tails.set(idx, a);
        }
        
        System.out.println(tails.size());
    }

    static int findIdx(int value) {
        int left = 0;
        int right = tails.size() - 1;
        int idx = tails.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (tails.get(mid) == value) {
                return mid;
            }
            if (tails.get(mid) < value) {
                left = mid + 1;
            } else {
                idx = mid;
                right = mid - 1;
            }
        }

        return idx;
    }
}

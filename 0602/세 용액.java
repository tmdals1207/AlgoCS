import java.util.*;
import java.io.*;

public class Main {

    static int N; // 용액의 개수
    static long[] values;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        values = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        Arrays.sort(values);

        long[] answer = new long[3];
        long minValue = Long.MAX_VALUE;
        outter : for (int target=0; target<N; target++) {
            int left = 0;
            int right = N - 1;
            while (left < right) {
                if (left == target) {
                    left++;
                    continue;
                }

                if (right == target) {
                    right--;
                    continue;
                }

                long tmp = values[target] + values[left] + values[right];
                if (Math.abs(tmp) < minValue) {
                    minValue = Math.abs(tmp);
                    answer[0] = values[target];
                    answer[1] = values[left];
                    answer[2] = values[right];
                    if (tmp == 0) {
                        break outter;
                    }
                }

                if (tmp > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        Arrays.sort(answer);
        for (int i=0; i<3; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}

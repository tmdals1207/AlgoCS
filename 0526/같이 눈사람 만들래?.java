import java.util.*;
import java.io.*;

public class Main {

    static int N; // 눈덩이 수
    static int[] heights;
    static int answer = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(heights);

        outter: for (int e1=0; e1<N; e1++) {
            for (int e2=e1+1; e2<N; e2++) {

                int eHeight = heights[e1] + heights[e2];
                f(eHeight, e1, e2);
                if (answer == 0) {
                    break outter;
                }
            }
        }

        System.out.println(answer);
    }

    static void f(int eHeight, int e1, int e2) {
        int left = 0;
        int right = N - 1;
        int aHeight = 0;
        int minDiff = Integer.MAX_VALUE;
    
        while (left < right) {
            if (left == e1 || left == e2) {
                left++;
                continue;
            }
            if (right == e1 || right == e2) {
                right--;
                continue;
            }
    
            aHeight = heights[left] + heights[right];
            int diff = Math.abs(eHeight - aHeight);
            if (diff == 0) {
                answer = 0;
                break;
            }
            minDiff = Math.min(minDiff, diff);
    
            if (eHeight < aHeight) {
                right--;
            } else {
                left++;
            }
        }
        
        // System.out.println("minDiff = " + minDiff);
        answer = Math.min(answer, minDiff);
    }
}

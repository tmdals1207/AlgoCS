import java.util.*;
import java.io.*;

// 문제 신박하네요
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for (int t=0; t<testcase; t++) {
            String[] input = br.readLine().split(" ");

            int l = Integer.parseInt(input[0]); // 막대의 길이
            int n = Integer.parseInt(input[1]); // 개미의 수

            int minTime = 0;
            int maxTime = 0;
            for (int i=0; i<n; i++) { // 개미 위치 입력
                int pos = Integer.parseInt(br.readLine());
                if (pos <= l / 2 && pos > minTime) { // 막대의 왼쪽편에 있으면서 왼쪽 끝으로부터 가장 멀리 떨어진 개미 찾기
                    minTime = pos;
                } else if (pos > l / 2 && l - pos > minTime) { // 막대의 오른쪽편에 있으면서 오른쪽 끝으로부터 가장 멀리 떨어진 개미 찾기
                    minTime = l - pos;
                }

                if (pos <= l / 2 && l - pos > maxTime) { // 막대의 왼쪽편에 있으면서 오른쪽 끝으로부터 가장 멀리 떨어진 개미 찾기
                    maxTime = l - pos;
                } else if (pos > l / 2 && pos > maxTime) { // 막대의 오른쪽편에 있으면서 왼쪽 끝으로부터 가장 멀리 떨어진 개미 찾기
                    maxTime = pos;
                }
            }

            System.out.println(minTime + " " + maxTime);
        }
    }
}

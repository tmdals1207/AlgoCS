import java.util.*;
import java.io.*;

public class Main {

    static int T;
    static char[] p; // 수행할 함수의 개수
    static int n; // 배열에 들어있는 수의 개수
    static int[] arr;
    static int cursor = 0;
    static int start, end;
    static StringBuilder answer;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t=0; t<T; t++) {
            boolean error = false;
            p = br.readLine().toCharArray();
            n = Integer.parseInt(br.readLine());
            arr = new int[n];

            String str = br.readLine();
            str = str.substring(1, str.length() - 1);
            String[] input = str.split(",");
            for (int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(input[i]);
            }

            start = 0;
            end = arr.length - 1;
            cursor = 0;

            for (int i=0; i<p.length; i++) {
                if (p[i] == 'R') {
                    int cnt = 1;
                    int nextIdx = i + 1;
                    while (nextIdx < p.length && p[nextIdx] == 'R') {
                        cnt++;
                        nextIdx++;
                    }
                    i = nextIdx - 1;
                    if (cnt % 2 == 0) {
                        continue;
                    }
                    reverse();
                } else if (p[i] == 'D') {
                    if (start > end) {
                        error = true;
                        break;
                    }
                    pop();
                }
            }
            
            if (error) {
                System.out.println("error");
                error = false;
                continue;
            }
            
            answer = new StringBuilder("[");
            if (cursor == start) {
                for (int i=start; i<=end; i++) {
                answer.append(arr[i]);
                    if (i == end) {
                        break;
                    }
                answer.append(",");
                }
            } else {
                for (int i=end; i>=start; i--) {
                answer.append(arr[i]);
                    if (i == start) {
                        break;
                    }
                answer.append(",");
                }
            }
            answer.append("]");
            System.out.println(answer);
            answer = null;
        }
    }

    static void reverse() {
        if (cursor == start) {
            cursor = end;
        } else {
            cursor = start;
        }
    }

    static void pop() {
        if (start == cursor) {
            cursor = ++start;
        } else {
            cursor = --end;
        }
    }
}

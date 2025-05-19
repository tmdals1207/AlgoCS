package 0519;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A - B {

    static int A, B;


    static void bfs() {
        Queue<long[]> queue = new LinkedList<>();
        queue.offer(new long[]{A, 1});

        while (!queue.isEmpty()) {
            long[] current = queue.poll();
            long num = current[0];
            long count = current[1];

            if (num == B) {
                System.out.println(count);
                return;
            }

            long next1 = num * 2;
            if (next1 <= B) {
                queue.offer(new long[]{next1, count+1});
            }

            long next2 = num * 10 + 1;
            if (next2 <= B) {
                queue.offer(new long[]{next2, count+1});
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        bfs();
    }
}
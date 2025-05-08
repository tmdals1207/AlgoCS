import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static Node[] nodes;

    static class Node {
        int a, b;

        Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];

        for (int i=0; i<N; i++) {
            String[] input = br.readLine().split(" ");
            nodes[i] = new Node(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

        int minDiff = Integer.MAX_VALUE;
        for (int number=2; number<=Math.pow(2, N); number++) {
            String binary = Integer.toBinaryString(number); // 000, 001, 010, 011, 100, 101, 110, 111 이렇게 생성
            String padded = String.format("%" + N + "s", binary).replace(' ', '0');
            int sumA = 1;
            int sumB = 0;
            for (int idx=0; idx<N; idx++) {
                char c = padded.charAt(idx);
                if (c == '1') {
                    sumA *= nodes[idx].a;
                    sumB += nodes[idx].b;
                }
            }

            minDiff = Math.min(minDiff, Math.abs(sumA - sumB));
        }

        System.out.println(minDiff);
    }
}

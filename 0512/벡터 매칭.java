import java.util.*;
import java.io.*;

// 벡터의 합을 모르면 풀 수 없는 까다로운 문제
public class Main {

    static int N;
    static Dot[] dots;
    static double answer;
    static boolean[] negative;

    static class Dot {
        int x, y;

        Dot(int x, int y) {
            this.x = x; 
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t=0; t<T; t++) {
            N = Integer.parseInt(br.readLine());
            dots = new Dot[N];
            negative = new boolean[N];
            answer = Double.MAX_VALUE;

            for (int i=0; i<N; i++) {
                String[] input = br.readLine().split(" ");
                dots[i] = new Dot(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
            }
            // 입력 끝
            
            combi(0, 0);

            System.out.println(answer);
        }
    }

    // 끝점으로 선택할 점을 조합으로 구하기
    static void combi(int start, int cnt) {
        if (cnt == N / 2) {
            answer = Math.min(calc(), answer);
            return;
        }

        for (int i=start; i<N; i++) {
            negative[i] = true;
            combi(i + 1, cnt + 1);
            negative[i] = false;
        }
    }

    static double calc() {
        int sumX = 0;
        int sumY = 0;
      
        for (int i=0; i<N; i++) {
            if (negative[i]) {
                sumX -= dots[i].x;
                sumY -= dots[i].y;
            } else {
                sumX += dots[i].x;
                sumY += dots[i].y;
            }
        }

        return Math.sqrt(Math.pow(sumX, 2) + Math.pow(sumY, 2));
    }
}

package s0512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
   * 평면에 n개의 점 존재, 해당 점을 집합 p라고 가정.
   * p의 벡터 매칭은 벡터의 집합이고, 모든 벡터는 한 점에서 시작하여 또 다른 점에서 끝나는 벡터의 집합.
   * p에 속하는 점은 모두 한 번씩 쓰여야 함
   * p의 벡터 매칭에 있는 벡터의 합의 길이의 최솟값 구하기
   * *절대.상대 오차는 10^-6까지 허용
   * ->그냥 주어진 점 2개씩 묶어서 차 계산때림 되는거 아닌가? 그러고 길이 구하기?
 */
public class Main_BJ1007 {                          //벡터 매칭
    static int[][] arr;
    static boolean[] visited;
    static int n;
    static double answer;

    private static void dfs(int idx, int cnt) {
        if(cnt == n/2){
            int []xy = new int[2];
            for(int i = 0; i < n; i++){
                xy[0] += arr[i][0];
                xy[1] += arr[i][1];
            }
            double len = Math.sqrt((double) xy[0] *xy[0] + (double) xy[1] *xy[1]);
            answer = Math.min(len, answer);
            return;
        }

        for(int i = idx; i < n; i++){
            if(!visited[idx]) {
                arr[i][0] *= -1;
                arr[i][1] *= -1;
                visited[idx] = true;            //해당 점에 -1을 곱해 빼기 판정으로 만들기
                dfs(i + 1, cnt + 1);
                visited[idx] = false;           //백트래킹(원상태로 복구)
                arr[i][0] *= -1;
                arr[i][1] *= -1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());

            visited = new boolean[n];
            arr = new int[n][2];
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                arr[j][0] = Integer.parseInt(st.nextToken());
                arr[j][1] = Integer.parseInt(st.nextToken());
            }

            answer = Double.MAX_VALUE;
            dfs(0, 0);

            System.out.println(answer);
        }

    }

}

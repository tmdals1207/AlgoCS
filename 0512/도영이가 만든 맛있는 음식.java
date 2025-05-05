package s0512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    n개의 재료, 각 재료마다 신맛 s와 쓴맛 b를 알고있음
    재료로 만든 음식의 맛은 재료들의 신맛의 곱, 쓴맛의 합
    신맛과 쓴맛의 차를 가장 적은 요리를 만들고자 함. 단, 재료는 최소 1개 이상 사용.
 */
public class Main_BJ2961 {                                  //도영이가 만든 맛있는 음식
    static int n, sol;
    static int[][] arr;


    private static void dfs(int idx, int s, int b, int cnt) {
        if(idx == n){
            if(cnt > 0) {       //아무것도 선택하지 않은 경우 제외
                sol = Math.min(sol, Math.abs(s - b));
            }
            return;
        }
    
        //idx번째 재료를 선택한 경우
        dfs(idx+1, s * arr[idx][0], b + arr[idx][1], cnt+1);

        //idx번째 재료를 선택하지 않은 경우
        dfs(idx+1, s, b, cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];            //0 = 신맛 1 = 쓴맛

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        sol = Integer.MAX_VALUE;
        dfs(0, 1, 0, 0);

        System.out.println(sol);
    }

}

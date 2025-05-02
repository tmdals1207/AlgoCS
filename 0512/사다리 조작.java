package s0512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * n개의 세로선, m개의 가로선
 * 인접한 세로선 사이에는 가로선을 놓을 수 있음(=사다리 줄 긋기), 가로선을 놓을 수 있는 위치 개수는 H,
 * 모든 세로선은 같은 위치를 가짐.
 * 사다리에 가로선을 추가해서, i번 세로선이 i번으로 나오도록 결과를 조작하고자 함
 * 이때, 추가해야하는 가로선의 개수의 최솟값 구하기
 * *입력으로 주어지는 가로선이 서로 연속하는 경우는 없다
 * 사다리는 3개까지만 놓을 수 있다고 생각->3개 넘어가거나 아예 불가능하면 -1 출력
 */
public class Main_BJ15684 {                         //사다리 조작
    static int[][] arr;
    static int n, m, h, answer;

    private static void dfs(int cnt) {
        if(cnt > 3){
            return;
        }
        if(cnt >= answer){
            return;
        }

        boolean flag = true;
        for(int i = 1; i <= n; i++){
            flag = check(i);
            if(!flag){
                break;
            }
        }

        if(flag){
            answer = cnt;
            return;
        }

        if(cnt < 3){
            for(int i =1 ; i <= h; i++){
                for(int j =1 ; j <= n; j++){
                    if(arr[i][j] != 1 && arr[i][j-1] != 1){     //사다리가 없으면 놓기
                        arr[i][j] = 1;
                        dfs(cnt + 1);
                        arr[i][j] = 0;  //백트래킹
                    }
                }
            }
        }

    }

    private static boolean check(int start) {
        int r = 1;
        int c = start;                  //세로줄 자기번호
        while(r <= h+1) {
            if(arr[r][c] == 1) { //해당 좌표에 사다리가 존재할 경우 오른쪽으로 이동
                c += 1;
            }
            else if(arr[r][c-1] == 1) { //해당 좌표의 왼쪽에 사다리가 있는 경우 왼쪽으로 이동
                c -= 1;
            }
            r += 1;
        }

        if(c == start) return true; //자기 번호에 맞게 도착한 경우
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());           //세로선
        m = Integer.parseInt(st.nextToken());           //(이미 그어진)가로선
        h = Integer.parseInt(st.nextToken());           //세로선마다 가로선을 놓을 수 있는 위치의 개수

        arr = new int[h+2][n+2];           //i번 가로선에 j와 j+1 세로선 사이에 다리가 그어짐

        for(int i =0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = 1;
        }

        answer = Integer.MAX_VALUE;
        dfs(0);

        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        } else{
            System.out.println(answer);
        }
    }

}


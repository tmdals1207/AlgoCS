package s0512;


import java.util.Scanner;

/*
   * n*n 크기 체스판에서 퀸 n개를 서로 공격하지 못하게 놓기
   * 놓을 수 있는 방법 수 구하기
 */
public class Main_BJ9663 {                                      //N-Queen
    static int[] arr;
    static int n, answer;

    private static boolean check(int i) {
        for(int j = 0; j < i; j++){
            if(arr[i] == arr[j] || i+arr[i] == arr[j]+j || i-arr[i] == j-arr[j]){       //열이 같거나 / 대각선이 같거나 \ 대각선이 같거나
                return false;
            }
        }
        return true;
    }

    private static void dfs(int cnt) {
        if(!check(cnt-1)){
            return;
        }

        if(cnt == n){
            answer++;
            return;
        }

        for(int i = 0; i < n; i++){
            arr[cnt] = i;
            dfs(cnt+1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        arr = new int[n];                                    //arr[i] = j => (i, j)에 놓는 퀸

        answer=0;
        dfs(0);

        System.out.println(answer);
    }
}

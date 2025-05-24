package s0526;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    라이언과 어피치 인형이 n개 일렬로 놓여있음
    라이언 = 1, 어피치 = 2
    라이언이 K개 이상 있는 가장 작은 연속된 인형들의 집합의 크기를 구하기
 */
public class Main_BJ15565 {                             //귀여운 라이언
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;
        int cnt = arr[0] % 2;           //0번째 인덱스부터 탐색하는데 0번 인덱스가 1이면 1, 2면 0(개수로 안 셈)
        
        while(right < n){
            if(cnt < k){                                   //조건 불만족시 탐색 범위 증가(동시에 라이언 개수 세기)
                right++;
                if(right < n && arr[right] == 1){
                    cnt++;
                }
            } else{
                min = Math.min(min, right-left+1);          //조건을 만족할때마다 최솟값 갱신
                
                if(arr[left] == 1){
                    cnt--;
                }
                left++;
            }
        }

        if(min == Integer.MAX_VALUE){
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }
}

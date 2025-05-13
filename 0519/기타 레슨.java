package s0519;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

/*
    같은 크기를 가진 m개의 블루레이에 n개의 기타 강의를 넣고자 함
    각 강의들은 다른 길이를 가지고 있으며, 블루레이에 넣을때 순서가 꼬여서는 안 됨
    이 때 가능한 블루레이의 크기 중 최소를 구하기
 */
public class Main_BJ2343 {                      //기타 레슨
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int sum = 0;
        int max = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            max = Math.max(arr[i], max);
        }

        int left =  max;                        //강의의 최댓값 보다는 커야함
        int right = sum;
        int sol = Integer.MAX_VALUE;

        while(left <= right) {
            int mid = (left + right) / 2;

            int cnt = 1;                        //이번 탐색에서 쓸 블루레이의 총 갯수
            int tmp = 0;                        
            for(int i = 0; i < n; i++){
                if(tmp + arr[i]> mid){         //이번에 담지 못하면
                    cnt++;                      //새로운 블루레이에 담아주기
                    tmp = 0;
                }
                tmp += arr[i];
            }

            if(cnt > m){
                left = mid + 1;
            } else{
                right = mid - 1;
                sol = Math.min(sol, mid);
            }
        }

        System.out.println(sol);
    }
}

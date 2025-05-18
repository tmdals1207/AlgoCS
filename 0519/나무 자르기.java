package s0519;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    m미터의 나무를 잘라서 구하고자 함.
    h만큼의 높이를 지정하면, h보다 큰 높이의 나무는 h 윗 부분이 잘리고, 낮으면 잘리지 않음
    잘린 나무만 들고갈 수 있음.
    나무를 필요한 만큼만 가져가려고 할 때, m미터의 나무를 집에 가져가기 위해 절단기에 설정할 수 있는 높이의 최댓값 구하기.
 */
public class Main_BJ2805 {                      //나무 자르기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());           //나무 개수
        int m = Integer.parseInt(st.nextToken());           //필요한 나무 길이

        int left = 0;
        int right = 0;
        int h = 0;

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            right = Math.max(arr[i], right);
        }

        while(left <= right){
            int mid = (left + right) / 2;

            long sum = 0;
            for(int i =0; i < n; i++) {
                if (arr[i] - mid > 0) {
                    sum += arr[i] - mid;
                }
            }

            if(sum >= m){
                left = mid + 1;
                h = Math.max(h, mid);
            } else {
                right = mid - 1;
            }
        }

        System.out.println(h);

    }
}

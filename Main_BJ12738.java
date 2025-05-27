package s0602;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
    LIS의 길이 구하기
    * 1 <= n <= 1000000 / -1000000000 <= Ai <= 1000000000
 */
public class Main_BJ12738 {                     //가장 긴 증가하는 부분 수열 3
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] arr = new long[n];

        for(int i = 0; i < n; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        List<Long> lis = new ArrayList<>();
        for(long num : arr){
            int idx = Collections.binarySearch(lis, num);     //이분 탐색으로 lis 리스트에 넣어야 할 위치 찾기

            if(idx < 0){                                //lis에 num이 없으면 idx는 음수로 반환
                idx = -(idx + 1);
            }

            if(idx == lis.size()){                  //idx가 lis의 끝을 가리키면 추가
                lis.add(num);
            } else{
                lis.set(idx, num);                  //그렇지 않으면 lis 안의 idx번째 값과 교체
            }
        }

        System.out.println(lis.size());
    }
}

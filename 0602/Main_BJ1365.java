package s0602;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
    전봇대가 두 줄로 늘어서 있음, 전봇대는 반대편 줄 전봇대와 연결되며 두 대 이상 다른 전봇대와 연결되지 않음
    다만 전봇대 줄이 꼬여있는걸 원치 않음. 최소한의 전선을 잘라내어 꼬인 전선이 없도록 만들고자 함.
    * 1 <= n <= 100000 / i번째 줄에 입력되는 자연수는 왼쪽에 i번째 전봇대가 오른쪽 몇 번 전봇대와 연결되는지 말함.
 */
public class Main_BJ1365 {                                  //꼬인 전깃줄
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> lis = new ArrayList<>();
        for(int num : arr){
            int idx = Collections.binarySearch(lis, num);

            if(idx < 0){
                idx = -(idx + 1);
            }

            if(idx == lis.size()){
                lis.add(num);
            } else{
                lis.set(idx, num);
            }
        }

        //lis = 꼬이지 않은 전깃줄 수 -> 전체 수에서 꼬인 부분만 빼기
        System.out.println(n - lis.size());
    }
}

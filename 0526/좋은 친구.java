package s0526;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    좋은 친구 = 자신과 반 등수 차가 k보다 작거나 같으면서 이름의 길이가 같아야 함
    n명의 학생들이 성적순으로 주어졌을 때, 좋은 친구가 몇 쌍이나 있는지 구하기
 */
public class Main_BJ3078 {                          //좋은 친구
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int [] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = br.readLine().length();
        }

        int[] cnt = new int[21];            //cnt[i] = 글자의 길이가 i다 / 2~20까지 길이가 가능하므로
        for(int i = 0; i <= k; i++){
            cnt[arr[i]]++;
        }

        long answer = --cnt[arr[0]];         //arr[0]과 착한 친구 수, 본인 제외하기 위해 --연산, 초기값

        for(int i = 1; i < n; i++){
            if(i + k < n){
                cnt[arr[i+k]]++;
            }
            answer += --cnt[arr[i]];        //arr[i]와 착한 친구 수
        }

        System.out.println(answer);
    }
}

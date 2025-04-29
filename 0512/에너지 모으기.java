package s0512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
   * 백트래킹 알고리즘
   * n개의 에너지 구슬이 일렬로 놓임, Wi만큼의 에너지를 가지고 있음
   * 처음과 끝을 제외한 구슬 x를 고른다고 하면, Wx-1 * Wx+1만큼의 에너지 생성 가능
   * 구슬을 고르면,n이 n-1로 값이 갱신되고 다시 1번부터 n번까지 번호를 매김
   * 모을 수 있는 에너지 양의 최댓값을 고르기
 */
public class Main_BJ16198 {                                         //에너지 모으기
    static List<Integer> list;
    static int max;

    //백트래킹 함수
    static void dfs(List<Integer> list, int n, int sum){
        if(list.size() == 2){
            max = Math.max(sum, max);
            return;
        }

        for(int i = 1; i < n-1; i++){
            int temp = list.get(i);
            int s = list.get(i-1) * list.get(i+1);

            list.remove(i);
            dfs(list, n-1, sum+s);
            list.add(i, temp);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }

        max = 0;
        dfs(list, n, 0);

        System.out.println(max);
    }
}

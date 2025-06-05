package s0609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
    개똥벌레가 종유석과 석순으로 가득찬 동굴에 들어감
    개똥벌레는 장애물(석순과 종유석, 번갈아가며 등장)을 피하지 않고 일직선으로 부수면서 들어감.
    동굴의 크기 n, 높이 h, 장애물의 크기가 주어질 때
    개똥벌레가 파괴해야하는 장애물의 최솟값과 최솟값에 해당하는 구간이 몇개 있는지 구하기
    * 2 <= n <= 200000 / 2 <= h <= 500000 / 장애물의 크기는 h보다 작은 양수
 */
public class Main_BJ3020 {                               //개똥벌레
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int up[] = new int[h + 1];                     //1~h 이상의 종유석 개수
        int down[] = new int[h + 1];                   //1~h 이상의 석순 개수
        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(br.readLine());
            if(i % 2 != 0){
                up[num]++;                         //종유석
            } else{
                down[num]++;                       //석순
            }
        }

        for(int i = h-1; i > 0; i--){               //누적합 구하기
            up[i] += up[i + 1];
            down[i] += down[i + 1];
        }

        int cnt = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= h; i++){
            if(down[i] + up[h - i + 1] < min){                  //최솟값 갱신(cnt도 초기화)
                cnt = 1;
                min = down[i] + up[h - i + 1];
            } else if(down[i] + up[h - i + 1] == min){          //최솟값 개수세기
                cnt++;
            }
        }

        System.out.println(min + " " + cnt);
    }
}

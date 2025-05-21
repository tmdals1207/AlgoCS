package s0526;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    n개의 눈덩이, 각 눈덩이 i의 지름은 Hi
    눈사람 하나 = 2개의 눈덩이, 단 더 큰 지름의 눈덩이가 아래로 오게
    눈사람의 키 = 두 눈덩이의 지름의 합
    n개의 눈덩이 중 4개를 골라 총 2개의 눈사람을 만들 때,
    두 눈사람의 키의 차의 최솟값을 구하기
    * 4 <= n <= 600 / 1 < Hi < 10^9
 */
public class Main_BJ20366 {                             //같이 눈사람 만들래?
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] h = new int[n];
        int min = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            h[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(h);

        for(int i = 0; i < n-1; i++){
            for(int j = i+1; j < n; j++){       //(i, j) 쌍의 눈덩이 선정
                int l = 0;                      //
                int r = n-1;                    //두번째 눈사람 탐색용 투포인터
                int man1 = h[i] + h[j];

                while(l < r){           
                    if(l == i || l == j){       //같은 눈덩이를 고르지 않도록 처리
                        l++;
                        continue;
                    }

                    if(r == i || r == j){
                        r--;
                        continue;
                    }

                    int man2 = h[l] + h[r];
                    min = Math.min(min, Math.abs(man1 - man2));

                    if(man1 > man2) {       //man2를 man1에 더 가깝게 만들기 위해 l값 증가(더 큰 눈덩이 선정)
                        l++;
                    } else {                //man1에 가까워지기 위해 r값 감소(더 작은 눈덩이 선정)
                        r--;
                    }
                }
            }
        }
        System.out.println(min);
    }
}

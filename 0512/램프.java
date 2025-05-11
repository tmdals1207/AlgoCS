package s0512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    nxm 크기의 직사각형 모양 탁자에 1x1 크기에(한 칸) 램프가 각 칸마다 존재
    각 '열' 아래에는 스위치가 존재, 스위치 온 오프로 각 열에 있는 모든 램프 끄고 키기 가능.
    행에 있는 램프다 모두 켜져있는 경우 == 행이 켜져있다.
    k번 스위치를 누를때 (같은 스위치 여러번 누르기 가능), 켜져있는 행을 최대로 하기.
    * 1 = 켜짐, 0 = 꺼짐
 */     //다시 풀기!!
public class Main_BJ1034 {                          //램프
    static String[] arr;
    static int n, m, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new String[n];                            //입력값
        int zero[] = new int[n];                        //각 행의 0의 개수
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            arr[i] = str;
            for(int j = 0; j < m; j++){
                if(str.charAt(j)-'0' == 0){
                    zero[i]++;
                }
            }
        }

        k = Integer.parseInt(br.readLine());

        int max = 0;
        for(int i = 0; i < n; i++){
            if(k < zero[i]){                //0의 개수가 스위치 누르는 횟수보다 많으면 행을 못킴
                continue;
            }
            if((k - zero[i]) % 2 != 0){     //0의 개수가 홀수면 마지막에 불킨 스위치를 눌러야해서 안됨
                continue;
            }

            int sol = 0;
            for(int j = 0; j < n; j++){
                if(arr[i].equals(arr[j])){  //행의 불켜진 모양이 같은 애들 찾아서 개수 세기
                    sol++;
                }
            }
            max = Math.max(max, sol);       //가장 많은 같은 모양의 개수를 세서 최댓값 갱신
        }

        System.out.println(max);
    }
}

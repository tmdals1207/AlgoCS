package s0421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/*
    AC-정수 배열에 연산을 하기 위해 만든 언어, R과 D 메소드가 존재
    R- 수의 순서를 뒤집는 함수, D- 첫번째 수를 버리는 함수. 비어있는 경우 D 사용시 에러 발생
    배열의 초기값과 수행할 함수가 주어질 때, 최종 결과 구하는 프로그램 작성하기
 */
public class Main_BJ5430 {                  //AC
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++){
            char[] method = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            boolean flag = false;
            boolean reverse = false;
            String str = br.readLine();

            str = str.replace("[", "");
            str = str.replace("]", "");
            String arr[] = str.split(",");

            Deque<Integer> deque = new ArrayDeque<>();          //덱 사용
            for(int j = 0; j < n; j++){
                deque.add(Integer.parseInt(arr[j]));
            }

            for(char func : method){
                if(func == 'R'){                //R 명령시 reverse 플래그 뒤집기
                    reverse = !reverse; 
                } else if(func == 'D'){
                    if(deque.isEmpty()){        //덱이 비어 있으면 에러 플래그 활성화
                        flag = true;
                        break;
                    }

                    if(reverse){                //reverse 플래그 켜져 있으면 뒤에서 하나
                        deque.pollLast();
                    } else{                     //reverse 플래그 꺼져 있으면 앞에서 하나
                        deque.pollFirst();
                    }
                } else{                         //R D 이외 명령의 경우 오류
                    flag = true;
                    break;
                }
            }

            if(flag){
                System.out.println("error");
            } else{
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                while(!deque.isEmpty()){
                    if(reverse) {              //reverse 플래그의 마지막 상황에 따라
                        sb.append(deque.pollLast());        //뒤에서부터 출력
                    } else{
                        sb.append(deque.pollFirst());       //앞에서부터 출력
                    }
                    if(deque.isEmpty()){
                        break;
                    }
                    sb.append(",");
                }
                sb.append("]");

                System.out.println(sb.toString());              //테스트 케이스 출력
            }
        }
    }
}

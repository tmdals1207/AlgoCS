package s0519;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/*
    정수 A를 B로 바꾸고자 함
    1. 2를 곱하거나,
    2. 1을 수의 가장 오른쪽에 추가한다. (A*10 + 1)
    두 가지 연산이 가능할때, B로 바꾸기 위한 연산의 최솟값 구하기
    연산의 최솟값에 1을 더해 출력하기. 불가능하면 -1 출력.
 */
public class Main_BJ16953 {                             //A → B
    static int a, b;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        a = sc.nextInt();
        b = sc.nextInt();
        int min = 0;

        while(a < b) {
            if (b % 2 == 0) {
                b = b / 2;
            } else if(b % 10 == 1){
                b = b / 10;
            } else{
                break;
            }

            min++;
        }

        if(a == b) {
            System.out.println(min + 1);
        } else {
            System.out.println(-1);
        }

        sc.close();
    }
}

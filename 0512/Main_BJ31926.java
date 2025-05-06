package s0512;

import java.util.Scanner;

/*
    daldidalgo가 n번 반복된 후, 반복이 완료되면 daldidan으로 종료함.
    ex) n=3, daldidalgodaldidalgodaldidalgodaldidan.
    매초 2개의 작업 중 하나 수행 가능.
    1. 알파벳 a~z 중 하나를 골라 지금까지 입력한 내용의 맨 뒤에 입력.
    2. 지금까지 입력한 문자열의 연속된 부분 문자열을 복사 후 입력한 내용 맨 뒤에 붙이기.
    ex) ajouapcshake = 지금까지 입력한 문자열 -> ajouapcshake, apc 붙이기 가능, aashake 붙이기 불가.
    문제에 언급된 시행 중 하나를 선택하여 매초 시행했을 때,
    n번의 daldidalgo를 입력한 후 1번의 daldidan을 입력할 수 있는 최소 시간을 출력한다.
 */
public class Main_BJ31926 {                                 //밤양갱
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        //n = 1 daldi = 5, daldi(5)dal(6) = 6, daldidal(6)g(7)o(8) = 8. daldidalgo(8)daldida(9)n(10) => 10
        //n = 2 => daldidalgo(8) daldidalgo(9) daldida n => 9 + 2 = 11
        //n = 3 => daldidalgo(8) daldidalgo(9) daldidalgodaldida(10) n => 10 + 1= 11
        //n = 4 => daldidalgo(8) daldidalgo(9) daldidalgodaldidalgo(10) daldida(11) n(12) = 10 + 2 = 12
        //n = 5 => daldidalgo(8) daldidalgo(9) daldidalgodaldidalgo(10) daldidalgodaldida(11) n(12) = 12
        //n = 6 => daldidalgo(8) daldidalgo(9) daldidalgodaldidalgo(10) daldidalgodaldidalgodaldida(11) n =>11+1 = 12
        //n = 7 => daldidalgo(8) daldidalgo(9) daldidalgodaldidalgo(10) daldidalgodaldidalgodaldidalgoaldida(11) n(12) = 12
        //n = 8 => daldidalgo(8) daldidalgo(9) daldidalgodaldidalgo(10) daldidalgodaldidalgodaldidalgodaldidalgo(11) daldida(12) n(13) =>11+2 = 13

        int sol = 8;    //daldidalgo(8)

        int i = 1;
        while(true){
            if(n == Math.pow(2, i)){    //n = 2의 제곱수
                sol = sol + i + 2;
                break;
            } else if(n < Math.pow(2, i)){ //n = 2의 제곱수가 아닐때(조건을 만족하는 제곱수들 중 가장 작은 제곱수)
                sol = sol + i + 1;
                break;
            }
            i++;
        }

        System.out.println(sol);
    }
}

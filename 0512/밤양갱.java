import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()) + 1;
        int base = 8;
        int answer = base;
        int count = 1;
        while (true) {
            int left = N - count; // 남은 daldidalgo
            if (left > count) { // 남은 daldidalgo가 지금까지 쓴 daldidalgo보다 많다면 그대로 복붙
                answer += 1;
                count *= 2;
            } else {
                answer += 2;
                break;
            }
        }
        

        System.out.println(answer);
    }
}

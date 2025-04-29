import java.util.*;
import java.io.*;

public class Main {

    static int N; // 구슬의 갯수
    static int[] weights; // 각 구슬의 무게
    static boolean[] removed; // 각 구슬이 제거되었는지 저장, 제거-true
    static int maxEnergy = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        removed = new boolean[N];
        // 입력 끝

        // 백트래킹
        f(0, 0);
        System.out.println(maxEnergy);
    }

    static void f(int cnt, int tmpEnergy) {
        if (cnt == N - 2) { // maxEnergy 업데이트
            maxEnergy = Math.max(maxEnergy, tmpEnergy);
            return;
        }
        
        for (int i=1; i<N-1; i++) {
            if (removed[i]) {
                continue;
            }
            
            removed[i] = true;
            f(cnt + 1, tmpEnergy + calcEnergy(i));
            removed[i] = false;
        }
    }

    static int calcEnergy(int idx) {
        int left = idx - 1;
        while (removed[left]) {
            left--;
        }
        
        int right = idx + 1;
        while (removed[right]) {
            right++;
        }

        return weights[left] * weights[right];
    }
}

import java.io.*;

class Solution {
	public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        String state; // 시작 상태
        int K; // 방울이 울린 횟수
        for (int tc=1; tc<=T; tc++) {
            int answer = 0;
            String[] split = br.readLine().split(" ");
            state = split[0];
            K = Integer.parseInt(split[1]);
            
            if (state.charAt(0) == 'o') {
                if (K % 2 == 0) {
                    answer = 0;
                } else {
                    answer = 1;
                }
            }
            
            if (state.charAt(1) == 'o') {
                if (K % 2 == 0) {
                    answer = 1;
                } else {
                    answer = 0;
                }
            }
            
            if (state.charAt(2) == 'o') {
                if (K == 0) {
                    answer = 2;
                }
                else if (K % 2 == 0) {
                    answer = 0;
                } else {
                    answer = 1;
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
   }
}

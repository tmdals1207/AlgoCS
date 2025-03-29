class Solution {
    
    // 1. 정삼각형에 마름모가 없는 경우
    // 2. 정삼각형에 1번 마름모가 사용된 경우
    // 3. 정삼각형에 2번 마름모가 사용된 경우
    // 4. 정삼각형에 3번 마름모가 사용된 경우
    static int[] dp1;
    static int[] dp2;
    static int[] dp3;
    static int[] dp4;
    
    public int solution(int n, int[] tops) {
        dp1 = new int[n];
        dp2 = new int[n];
        dp3 = new int[n];
        dp4 = new int[n];
        
        dp1[0] = 1;
        dp2[0] = 1;
        dp3[0] = 1;
        if (tops[0] == 1) { // 3번 마름모는 윗변에 정삼각형이 붙어 있어야 사용 가능
            dp4[0] = 1;
        }
        
        for (int i=1; i<n; i++) {
            dp1[i] = (dp1[i - 1] + dp2[i - 1] + dp3[i - 1] + dp4[i - 1]) % 10007;
            dp2[i] = (dp1[i - 1] + dp2[i - 1] + dp4[i - 1]) % 10007;
            dp3[i] = (dp1[i - 1] + dp2[i - 1] + dp3[i - 1] + dp4[i - 1]) % 10007;
            if (tops[i] == 1) {
                dp4[i] = (dp1[i - 1] + dp2[i - 1] + dp3[i - 1] + dp4[i - 1]) % 10007;
            }
        }
        
        return (dp1[n - 1] + dp2[n - 1] + dp3[n - 1] + dp4[n - 1]) % 10007;
    }
}

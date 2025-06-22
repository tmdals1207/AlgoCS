import sys
input = sys.stdin.readline

dp = [0] * 2501
dp[0] = 1

for n in range(1, len(dp)):
    for i in range(n):
        dp[n] = (dp[n] + dp[i] * dp[n - 1 - i]) % 1000000007

T = int(input())

for _ in range(T):
    L = int(input())
    
    if L % 2 != 0:
        print(0)
    else:
        print(dp[L // 2])

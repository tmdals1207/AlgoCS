import sys
input = sys.stdin.readline

MOD = 10**9 + 7
MAX_N = 2500

dp = [0] * (MAX_N + 1)
dp[0] = 1

for n in range(1, MAX_N + 1):
    for i in range(n):
        dp[n] = (dp[n] + dp[i] * dp[n - 1 - i]) % MOD

T = int(input())
for _ in range(T):
    L = int(input())
    if L % 2 != 0:
        print(0)
    else:
        print(dp[L // 2])

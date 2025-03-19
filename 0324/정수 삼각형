import sys
input = sys.stdin.readline

n = int(input())

arr = [list(map(int, input().split())) for _ in range(n)]
dp = [[0 for _ in range(n)] for _ in range(n)]

dp[-1] = arr[-1]

if n != 1:
    for i in range(n - 2, 0, -1):
        for j in range(len(arr[i])):
            dp[i][j] = max(dp[i + 1][j], dp[i + 1][j + 1]) + arr[i][j]

    print(max(dp[1][0], dp[1][1]) + arr[0][0])
else:
    print(arr[0][0])

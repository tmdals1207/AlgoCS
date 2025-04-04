import sys
input = sys.stdin.readline

N = int(input())

if N == 1:
    K = int(input())
    print(0)
else:
    dp = [[0 for _ in range(N)] for _ in range(2)]
    arr = [[] for _ in range(N - 1)]

    for i in range(N - 1):
        a, b = map(int, input().split())
        arr[i].extend([a, b])

    K = int(input())

    if N == 2:
        print(arr[0][0])
    else:
        for i in range(2):
            dp[i][1] = dp[0][0] + arr[0][0]
            dp[i][2] = min(dp[0][0] + arr[0][1], dp[0][1] + arr[1][0])

        if N > 2:
            for i in range(3, N):
                dp[0][i] = min(dp[0][i - 2] + arr[i - 2][1], dp[0][i - 1] + arr[i - 1][0])
                dp[1][i] = min(dp[0][i - 3] + K, dp[1][i - 2] + arr[i - 2][1], dp[1][i - 1] + arr[i - 1][0])

        print(min(dp[0][-1], dp[1][-1]))

import sys
input = sys.stdin.readline

N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]

dp_min = [e for e in arr[0]]
dp_max = [e for e in arr[0]]

if N == 1:
    print(max(arr[0]), min(arr[0]))

else:
    i = 1
    while i < N:
        x = [0] * 3
        y = [0] * 3
        for j in range(3):
            if j == 0:
                x[j] = min(dp_min[j] + arr[i][j], dp_min[j + 1] + arr[i][j])
                y[j] = max(dp_max[j] + arr[i][j], dp_max[j + 1] + arr[i][j])
            elif j == 2:
                x[j] = min(dp_min[j] + arr[i][j], dp_min[j - 1] + arr[i][j])
                y[j] = max(dp_max[j] + arr[i][j], dp_max[j - 1] + arr[i][j])
            else:
                x[j] = min(dp_min[j] + arr[i][j], dp_min[j + 1] + arr[i][j], dp_min[j - 1] + arr[i][j])
                y[j] = max(dp_max[j] + arr[i][j], dp_max[j + 1] + arr[i][j], dp_max[j - 1] + arr[i][j])

        i += 1

        for j in range(3):
            dp_min[j] = x[j]
            dp_max[j] = y[j]

    print(max(dp_max), min(dp_min))

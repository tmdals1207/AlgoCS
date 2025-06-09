import sys
input = sys.stdin.readline

T = int(input())

for _ in range(T):
    N = int(input())

    arr = []
    for _ in range(N):
        a, b = map(int, input().split())
        arr.append((a, b))

    arr.sort(key = lambda x: (x[0], x[1]))

    cnt = N
    min_value = arr[0][1]

    for i in range(1, len(arr)):
        min_value = min(min_value, arr[i][1])
        
        if arr[i][1] > min_value:
            cnt -= 1

    print(cnt)

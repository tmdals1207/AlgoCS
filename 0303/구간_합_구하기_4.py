import sys
input = sys.stdin.readline

N, M = map(int, input().split())
arr = list(map(int, input().split()))

arr_sum = [0] * (N + 1)
arr_sum[0] = 0
arr_sum[1] = arr[0]

for i in range(N):
    arr_sum[i + 1] = arr_sum[i] +  arr[i]

for _ in range(M):
    a, b = map(int, input().split())
    print(arr_sum[b] - arr_sum[a - 1])

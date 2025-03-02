import sys
input = sys.stdin.readline

N, M = map(int, input().split())
arr = list(map(int, input().split()))

arr_sum = [0] * (N + 1) # 누적합을 넣어둘 배열
arr_sum[0] = 0
arr_sum[1] = arr[0]

for i in range(N): # arr_sum의 (i + 1)번째에 arr의 i번째까지의 합을 넣음
    arr_sum[i + 1] = arr_sum[i] +  arr[i]

for _ in range(M): # b까지의 누적합과 (a - 1)까지의 누적합을 뺌으로써 arr의 a번째에서 b번째까지 합계를 구함
    a, b = map(int, input().split())
    print(arr_sum[b] - arr_sum[a - 1])

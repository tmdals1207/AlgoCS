import sys
input = sys.stdin.readline

N, K = map(int, input().split())
arr = list(map(int, input().split()))

index = []

for i in range(N):
    if arr[i] == 1:
        index.append(i)

cnt = len(index)

if cnt < K:
    print(-1)
else:
    result = float('inf')
    left = 0
    right = K - 1

    result = min(index[right] - index[left] + 1, result)

    while right + 1 < cnt:
        right += 1
        left += 1
        
        result = min(index[right] - index[left] + 1, result)

    print(result)

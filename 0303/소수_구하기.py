import math

def check_prime(M, N):
    arr = [False] * (N + 1)
    arr[0] = True
    arr[1] = True
    for i in range(2, int(math.sqrt(N) + 1)):
        j = i
        while i * j <= N:
            arr[i * j] = True
            j += 1
    result = []
    for i in range(len(arr)):
        if not arr[i] and i >= M:
            result.append(i)
    return result

M, N = map(int, input().split())

result = check_prime(M, N)

for ele in result:
    print(ele)

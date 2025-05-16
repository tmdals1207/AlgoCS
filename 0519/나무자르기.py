import sys, math
input = sys.stdin.readline

N, M = map(int, input().split())
arr = sorted(list(map(int, input().split())), reverse = True)
arr.append(0)

tmp_M = M
i = 0
result = 0

while tmp_M > 0:
    tmp = arr[i] - arr[i + 1]

    if tmp_M < tmp * (i + 1):
        result += math.ceil(tmp_M / (i + 1))
        break
        
    tmp_M -= tmp * (i + 1)
    result += tmp

    i += 1

print(arr[0] - result)

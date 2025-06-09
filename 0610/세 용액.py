import sys
input = sys.stdin.readline

N = int(input())
arr = sorted(list(map(int, input().split())))

i = 0
result = [float('inf'), -1, -1, -1]
while i < N:
    left = i + 1
    right = N - 1

    while left < right:
        tmp = arr[i] + arr[left] + arr[right]
        if abs(tmp) < abs(result[0]):
            result = [tmp, arr[i], arr[left], arr[right]]

        if tmp == 0:
            break

        if tmp < 0:
            left += 1
        else:
            right -= 1

    if result[0] == 0:
        break

    i += 1

for ele in sorted(result[1:]):
    print(ele, end = ' ')

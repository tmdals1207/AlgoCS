import sys
input = sys.stdin.readline

N = int(input())
arr = sorted(list(map(int, input().split())))

left = 0
right = N - 1

tmp = abs(arr[left] + arr[right])

while left < right:
    a = arr[left] + arr[right]
    for i in range(left + 1, right - 1):
        for j in range(right - 1, i, -1):
            b = arr[i] + arr[j]
            if tmp > abs(a - b):
                tmp = abs(a - b)
            if a >= b:
                break
                
    if tmp == 0:
        break

    if left == right - 1:
        right -= 1
        left = 0
    else:
        left += 1

print(tmp)

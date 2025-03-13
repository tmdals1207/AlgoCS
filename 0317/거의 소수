import math

A, B = map(int, input().split())

arr = [False] * (int(math.sqrt(B)) + 1)
total = 0

for i in range(2, len(arr)):
    if not arr[i]:
        for j in range(i * i, len(arr), i):
            arr[j] = True

        if math.log(A, i) == int(math.log(A, i)) and A != 1 and A != i:
            a = int(math.log(A - 1, i))
        else:
            a = int(math.log(A, i))
        if A < i:
            a += 1
        b = int(math.log(B, i))

        total += b - a

print(total)

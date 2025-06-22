from collections import deque
import sys
input = sys.stdin.readline

N, H = map(int, input().split())
arr = [int(input().strip()) for _ in range(N)]

even_arr = deque(sorted([arr[i] for i in range(0, len(arr), 2)]))
odd_arr = deque(sorted([(H - arr[i]) for i in range(1, len(arr), 2)]))

tmp = 0.5
cnt = len(even_arr)

result = cnt
result_cnt = 0

while tmp < H:
    while even_arr and even_arr[0] < tmp:
        even_arr.popleft()
        cnt -= 1

    while odd_arr and odd_arr[0] < tmp:
        odd_arr.popleft()
        cnt += 1

    if result == cnt:
        result_cnt += 1
    elif result > cnt:
        result = cnt
        result_cnt = 1

    tmp += 1
    
print(result, result_cnt)

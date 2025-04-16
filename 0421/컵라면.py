import sys
import heapq
input = sys.stdin.readline

N = int(input())
arr = []

for _ in range(N):
    a, b = map(int, input().split())
    arr.append((a, b))
arr.sort()

time = 1
ramen = []

ramen.append(heapq.heappop(arr)[1])

while arr:
    a, b = heapq.heappop(arr)
    if time >= a:
        k = heapq.heappop(ramen)
        if k >= b:
            heapq.heappush(ramen, k)
            continue
    else:   
        time += 1
    heapq.heappush(ramen, b)
    
print(sum(ramen))

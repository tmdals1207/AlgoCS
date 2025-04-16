import heapq
import sys
input = sys.stdin.readline

N = int(input())

queue = []

for _ in range(N):
    tmp = int(input())
    
    if tmp:    
        heapq.heappush(queue, (abs(tmp), tmp))
    else:
        if not queue:
            print(0)
        else:
            print(heapq.heappop(queue)[1])

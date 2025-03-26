import heapq
import sys
input = sys.stdin.readline

V, E = map(int, input().split())
K = int(input())

arr = [[] for _ in range(V + 1)]

for _ in range(E):
    u, v, w = map(int, input().split())
    arr[u].append((v, w))

visited = [float('INF') for _ in range(V + 1)]

queue = []
heapq.heappush(queue, (0, K))
visited[K] = 0

while queue:
    total_w, x = heapq.heappop(queue)

    if total_w > visited[x]:
        continue

    for ele in arr[x]:
        if total_w + ele[1] < visited[ele[0]]:
            visited[ele[0]] = total_w + ele[1]
            heapq.heappush(queue, (total_w + ele[1], ele[0]))

for i in range(1, len(visited)):
    if visited[i] == float('inf'):
        print('INF')
    else:
        print(visited[i])

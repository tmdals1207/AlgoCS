import heapq
import sys
input = sys.stdin.readline

def dijkstra(start, end, result = float('inf')):
    visited = [float('inf') for _ in range(N + 1)]
    visited[start] = 0
    
    queue = []
    queue.append((start, 0))

    while queue:
        x, new_w = heapq.heappop(queue)

        if x == end:
            result = min(result, new_w)
            continue

        if visited[x] < new_w:
            continue

        for ele in arr[x]:                
            if visited[ele[0]] > new_w + ele[1]:
                visited[ele[0]] = new_w + ele[1]
                heapq.heappush(queue, (ele[0], visited[ele[0]]))

    return result

N, E = map(int, input().split())
arr = [[] for _ in range(N + 1)]

for _ in range(E):
    a, b, c = map(int, input().split())
    arr[a].append((b, c))
    arr[b].append((a, c))

v1, v2 = map(int, input().split())

result_1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N)
result_2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N)

if result_1 == float('inf') or result_2 == float('inf'):
    print(-1)
else:
    print(min(result_1, result_2))

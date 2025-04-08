import heapq
import sys
input = sys.stdin.readline

def dijkstra(x, y):
    global N
    
    queue = []
    heapq.heappush(queue, (0, x))

    visited = [float('inf')] * (N + 1)
    visited[x] = 0

    while queue:
        new_w, new_x = heapq.heappop(queue)

        if new_x == y:
            return new_w

        if visited[new_x] < new_w:
            continue

        for ele in arr[new_x]:
            now_w = max(new_w, ele[0])
            if visited[ele[1]] > now_w:
                visited[ele[1]] = now_w
                heapq.heappush(queue, (now_w, ele[1]))

    return -1

N, M, T = map(int, input().split())
arr = [[] for _ in range(N + 1)]

for _ in range(M):
    a, b, c = map(int, input().split())
    arr[a].append((c, b))

for _ in range(T):
    s, e = map(int, input().split())
    print(dijkstra(s, e))

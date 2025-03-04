from collections import deque

n = int(input())

arr = []
visited = []
queue = deque()

for _ in range(n):
    arr.append([int(e) for e in input()])
    visited.append([0 for _ in range(n)])

visited[0][0] = 1    
queue.append((0, 0, 0))

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

result_arr = []

while queue:
    r, c, cnt = queue.popleft()

    if r == n - 1 and c == n - 1:
        result_arr.append(cnt)
        continue

    for i in range(4):
        nr = r + dr[i]
        nc = c + dc[i]

        if nr > -1 and nr < n and nc > -1 and nc < n and not visited[nr][nc]:
            visited[nr][nc] = 1
            if arr[nr][nc]:
                queue.appendleft((nr, nc, cnt))
            else:
                queue.append((nr, nc, cnt + 1))

print(min(result_arr))

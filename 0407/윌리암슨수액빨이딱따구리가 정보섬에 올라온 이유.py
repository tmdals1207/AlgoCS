from collections import deque
import sys
input = sys.stdin.readline

n, m = map(int, input().split())
arr = [[int(e) for e in input().strip()] for _ in range(n)]

for i in range(n):
    if 2 in arr[i]:
        for j in range(m):
            if arr[i][j] == 2:
                tak_space = (i, j, 0)

queue = deque()
queue.append(tak_space)

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

visited = [[0 for _ in range(m)] for _ in range(n)]
visited[tak_space[0]][tak_space[1]] = 1

arr[tak_space[0]][tak_space[1]] = 0
check = 0

while queue:
    r, c, cnt = queue.popleft()

    if arr[r][c] != 0:
        print("TAK")
        print(cnt)
        check = 1
        break

    for i in range(4):
        nr = r + dr[i]
        nc = c + dc[i]

        if nr > -1 and nr < n and nc > -1 and nc < m and arr[nr][nc] != 1:
            if not visited[nr][nc]:
                visited[nr][nc] = 1
                queue.append((nr, nc, cnt + 1))

if not queue and check != 1:
    print("NIE")

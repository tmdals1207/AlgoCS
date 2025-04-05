from collections import deque
import sys
input = sys.stdin.readline

N, K = map(int, input().split())

queue = deque()
queue.append((N, 0))

visited = [0] * (2 * (max(N, K) + 1) + 1)
visited[N] = 1

while queue:
    x, cnt = queue.popleft()

    if x < 0:
        continue

    if x == K:
        print(cnt)
        break

    arr = [x + 1, x - 1]

    if x > K:
        if not visited[x - 1]:
            visited[x - 1] = 1
            queue.append((x - 1, cnt + 1))
        continue

    if K - (x + 1) > 2 * x - K:
        if not visited[2 * x]:
            visited[2 * x] = 1
            queue.append((2 * x, cnt + 1))

    for ele in arr:
        if not visited[ele]:
            visited[ele] = 1
            queue.append((ele, cnt + 1))

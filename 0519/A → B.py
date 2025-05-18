from collections import deque

A, B = map(int, input().split())

queue = deque()
queue.append((A, 1))

while queue:
    x, cnt = queue.popleft()

    if x == B:
        print(cnt)
        break
    
    if x * 10 + 1 <= B:
        queue.append((x * 10 + 1, cnt + 1))
    if x * 2 <= B:
        queue.append((x * 2, cnt + 1))

    if not queue:
        print(-1)
        break

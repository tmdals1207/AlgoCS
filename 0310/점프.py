import sys
input = sys.stdin.readline

N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]
visited = [[0 for _ in range(N)] for _ in range(N)]

visited[0][0] = 1

dr = [0, 1]
dc = [1, 0]

for c in range(N):
    for r in range(N):
        if c == N -1 and r == N -1:
            break
        tmp = arr[r][c]
        for i in range(2):
            nr = r + dr[i] * tmp
            nc = c + dc[i] * tmp

            if nr > -1 and nr < N and nc > -1 and nc < N:
                visited[nr][nc] += visited[r][c]

print(visited[N - 1][N - 1])

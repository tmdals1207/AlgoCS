import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)

def dfs(r, c, cnt, per, visited):
    global result
    
    if not per:
        return

    if cnt == N:
        result += per
        return

    for i in range(4):
        nr = r + dr[i]
        nc = c + dc[i]

        if nr > -1 and nr < (2 * N + 1) and nc > -1 and nc < (2 * N + 1) and not visited[nr][nc]:
            visited[nr][nc] = 1
            dfs(nr, nc, cnt + 1, per * per_dir[i], visited)
            visited[nr][nc] = 0

N, per_e, per_w, per_s, per_n = map(int, input().split())
per_dir = [per_e / 100, per_w / 100, per_s / 100, per_n / 100]

visited = [[0 for _ in range(2 * N + 1)] for _ in range(2 * N + 1)]
result = 0

dr = [0, 0, 1, -1]
dc = [1, -1, 0, 0]

visited[N][N] = 1
dfs(N, N, 0, 1, visited)

print(result)

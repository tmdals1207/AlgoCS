import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)

def dfs(r, c):
    global cnt, idx
    
    nr = r + direction[arr[r][c]][0]
    nc = c + direction[arr[r][c]][1]

    if not visited[nr][nc]:
        visited[nr][nc] = idx
        dfs(nr, nc)
    else:
        if visited[r][c] == visited[nr][nc]:
            cnt += 1
            

N, M = map(int, input().split())
arr = [[e for e in input().strip()] for _ in range(N)]

direction = dict()
direction['U'] = [-1, 0]
direction['D'] = [1, 0]
direction['L'] = [0, -1]
direction['R'] = [0, 1]

visited = [[0 for _ in range(M)] for _ in range(N)]

cnt = 0
idx = 1

for i in range(N):
    for j in range(M):
        if not visited[i][j]:
            visited[i][j] = idx
            dfs(i, j)
            idx += 1

print(cnt)

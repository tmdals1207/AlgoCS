from itertools import combinations
from collections import deque
import copy
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]

v_co = [] # 바이러스의 좌표를 모아둘 배열
no_co = [] # 빈 칸의 좌표를 모아둘 배열
result_arr = []

for i in range(N):
    for j in range(M):
        if arr[i][j] == 2:
            v_co.append((i, j))
        elif not arr[i][j]:
            no_co.append((i, j))

for ele in combinations(no_co, 3):
    arr_copy = copy.deepcopy(arr)
    queue = deque(v_co)
    
    for i in range(3):
        a, b = ele[i]
        arr_copy[a][b] = 1

        
    visited = [[0 for _ in range(M)] for _ in range(N)]

    for i in range(len(queue)):
        a, b = queue[i]
        visited[a][b] = 1
          
    dr = [-1, 1, 0, 0]
    dc = [0, 0, -1, 1]

            
    while queue:
        r, c = queue.popleft()

        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]

            if nr > -1 and nr < N and nc > -1 and nc < M and not arr_copy[nr][nc] and not visited[nr][nc]:
                queue.append((nr, nc))
                visited[nr][nc] = 1
                arr_copy[nr][nc] = 2

    tmp = 0
    for i in range(N):
        for j in range(M):
            if not arr_copy[i][j]:
                tmp += 1     
                    
    result_arr.append(tmp)

print(max(result_arr))

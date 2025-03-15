from collections import deque
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
arr = [[e for e in input().strip()] for _ in range(N)]
PR, PC = map(int, input().split())

direction = ['U', 'R', 'D', 'L']
ch_dir_1 = {'U': 'R', 'R': 'U', 'D': 'L', 'L': 'D'}
ch_dir_2 = {'U': 'L', 'R': 'D', 'D': 'R', 'L': 'U'}

dic = {'U': (-1, 0), 'R': (0, 1), 'D': (1, 0), 'L': (0, -1)}

signal = []
queue = deque()

for d in direction:
    dr, dc = dic[d]
    queue.append(((PR - 1) + dr ,(PC - 1) + dc, d, 0))
    visited = []
    
    while queue:
        x, y, drt, time = queue.popleft()

        if x > -1 and x < N and y > -1 and y < M and arr[x][y] != 'C':
            if arr[x][y] == '/':
                if (x, y, drt) in visited:
                    signal.append((d, float('inf')))
                    break
                visited.append((x, y, drt))
                drt = ch_dir_1[drt]

            elif arr[x][y] == '\\':
                if (x, y, drt) in visited:
                    signal.append((d, float('inf')))
                    break
                visited.append((x, y, drt))
                drt = ch_dir_2[drt]
                
            dr, dc = dic[drt]
            queue.append((x + dr, y + dc, drt, time + 1))

        else:
            signal.append((d, time + 1))

max_d = ''
max_time = 0
for ele in signal:
    d, time = ele
    if max_time < time:
        max_d = d
        max_time = time
        
print(max_d)

if max_time == float('inf'):
    print('Voyager')
else:
    print(max_time)

import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)

def dfs(v):
    global k
    
    if not visited[v]:
        visited[v] = 1
        result[v] = k
        
        for ele in graph[v]:
            if not visited[ele]:
                dfs(ele)

def check_cycle(v, c):
    global check
    
    for ele in graph[v]:
        if ele != c:
            if ele in visit_v:
                check = 1
                return
            visit_v.append(ele)
            check_cycle(ele, v)
        else:
            continue

case_num = 1
        
while True:
    n, m = map(int, input().split())
    
    if n == 0 and m == 0:
        break
    
    graph = [[] for _ in range(n + 1)]
    visited = [1] + [0] * n
    
    for _ in range(m):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)

    result = [0] * (n + 1)
    k = 1
    is_cycle = 0

    for i in range(1, n + 1):
        dfs(i)
        k += 1

    list_arr = []

    for i in range(1, n + 1):
        tmp = []
        for j in range(1, n + 1):
            if result[j] == i:
                tmp.append(j)
        if tmp:
            list_arr.append(tmp)

    for ele in list_arr:
        visit_v = []
        check = 0
        check_cycle(ele[0], 0)
        if check == 1:
                is_cycle += 1

    tmp = len(set(result)) - is_cycle - 1

    print(f'Case {case_num}: ', end = '')
    case_num += 1

    if tmp == 1:
        print('There is one tree.')
    elif tmp > 1:
        print(f'A forest of {tmp} trees.')
    else:
        print('No trees.')

from collections import deque
import sys
input = sys.stdin.readline

N, K = map(int, input().split())
arr = deque([len(input().strip()) for _ in range(N)])

queue = deque([arr.popleft()])
dic = dict()
dic[queue[0]] = 1

cnt = 0

while len(arr) > 0:
    if len(queue) == K + 1:
        tmp_pop = queue.popleft()
        
        if dic[tmp_pop] == 1:
            del(dic[tmp_pop])
        else:
            dic[tmp_pop] -= 1
    
    tmp = arr.popleft()
    queue.append(tmp)
    if tmp in dic:
        cnt += dic[tmp]
        dic[tmp] += 1
    else:
        dic[tmp] = 1

print(cnt)

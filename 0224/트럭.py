from collections import deque

n, w, L = map(int, input().split())
truck = deque(list(map(int, input().split())))
result = 1

queue = deque() # 다리 위에 있는 트럭 정보
queue.append((truck.popleft(), 1))

while queue:
    sum_truck = 0
    result += 1
    
    for _ in range(len(queue)):
        a, b = queue.popleft()

        if b != w:
            sum_truck += a
            queue.append((a, b + 1))

    if truck:
        if sum_truck + truck[0] <= L :
            queue.append((truck.popleft(), 1))

print(result)

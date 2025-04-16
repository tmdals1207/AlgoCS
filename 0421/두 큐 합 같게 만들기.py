from collections import deque

def solution(queue1, queue2):
    limit = 2 * (len(queue1) + len(queue2))
    
    new_queue1 = deque(queue1)
    new_queue2 = deque(queue2)
    
    sum_queue1 = sum(new_queue1)
    sum_queue2 = sum(new_queue2)
    
    goal = (sum_queue1 + sum_queue2) // 2
    
    check_queue1 = sum_queue1
    check_queue2 = sum_queue2

    cnt = 0
    
    while sum_queue1 != goal or sum_queue2 != goal:
        if sum_queue1 > sum_queue2:
            k = new_queue1.popleft()
            new_queue2.append(k)
            sum_queue1 -= k
            sum_queue2 += k
        else:
            k = new_queue2.popleft()
            new_queue1.append(k)
            sum_queue1 += k
            sum_queue2 -= k
            
        cnt += 1
        
        if not new_queue1 or not new_queue2:
            return -1

        if cnt > limit:
            return -1

    return cnt

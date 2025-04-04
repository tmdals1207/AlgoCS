from collections import deque
import math

def solution(n, stations, w):
    answer = 0
    queue = deque(stations)

    queue.appendleft(-w)
    queue.append(n + w + 1)

    for i in range(len(queue) - 1):
        answer += math.ceil((queue[i + 1] - queue[i] -1 - 2 * w) / (2 * w + 1))
            
    return answer

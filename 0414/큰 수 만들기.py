from collections import deque

def solution(number, k):
    nums = deque([int(e) for e in number])
    stack = [nums.popleft()]

    cnt = 0
    
    while nums:
        x = stack[-1]
        y = nums.popleft()

        while x < y and stack and cnt != k:
            stack.pop()
            cnt += 1
            if stack:
                x = stack[-1]
                
        stack.append(y)

    if cnt == k:
        answer = ''.join(list(map(str, stack)))
    else:
        answer = ''.join(list(map(str, stack[:-(k - cnt)])))
        
    return answer

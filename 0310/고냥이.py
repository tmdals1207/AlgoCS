from collections import deque

N = int(input())
word = input()
arr_queue = deque([e for e in word])
max_conti = 0
input_queue = deque()

idx_list = [0]
i = 1
j = 1
idx_sum = 0
max_len = 1

input_queue.append(arr_queue.popleft())

while arr_queue:
    ele = arr_queue.popleft()

    if input_queue[-1] != ele:
        idx_list.append(j)
        
        input_queue.append(ele)
        tmp = set(input_queue)
        
        while len(tmp) > N:
            idx_sum = idx_list[i] - idx_list[i - 1]
            for _ in range(idx_sum):
                input_queue.popleft()
            i += 1
            tmp = set(input_queue)
    else:
        input_queue.append(ele)
    j += 1

    max_len = max(max_len, len(input_queue))

print(max_len)

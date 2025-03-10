from collections import deque

N = int(input())
word = input()

arr_queue = deque([e for e in word])
input_queue = deque()

idx_list = [0] # 다른 문자가 들어올 때 인덱스 저장
i = 1 # idx_list에서 사용
j = 1 # 다른 문자가 들어왔을 때의 인덱스
idx_sum = 0 # 
max_len = 1 # 문자열의 최대 길이

input_queue.append(arr_queue.popleft())

while arr_queue:
    ele = arr_queue.popleft()

    if input_queue[-1] != ele:
        idx_list.append(j)
        
        input_queue.append(ele)
        tmp = set(input_queue) # 중복된 원소 제거
        
        while len(tmp) > N: # 다른 문자가 N개 보다 작아질 때까지 반복
            idx_sum = idx_list[i] - idx_list[i - 1]
            # 다른 문자의 인덱스 차이만큼 원소 제거
            for _ in range(idx_sum):
                input_queue.popleft()
            i += 1
            tmp = set(input_queue)
    else:
        input_queue.append(ele)
    j += 1

    max_len = max(max_len, len(input_queue))

print(max_len)

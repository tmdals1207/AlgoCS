from collections import deque

for _ in range(int(input())):
    tmp = [e for e in input()]
    n = int(input())
    sen = input()
    
    if n == 0:
        arr = []
    else:
        arr = deque(list(list(map(int, sen[1:len(sen) - 1].split(',')))))

    i = 0
    error = 0
    
    for ele in tmp:
        if ele == 'R':
            i = n - 1 - i
        elif ele == 'D':
            if not arr:
                error = 1
                break
            elif i == 0:
                arr.popleft()
            else:
                arr.pop()

    if error:
        print('error')
    else:
        if i == 0:
            print('[', end = '')
            for i in range(len(arr)):
                if i == len(arr) - 1:
                    print(f'{arr[i]}', end = '')
                else:
                    print(f'{arr[i]},', end = '')
            print(']')
        else:
            print('[', end = '')
            for i in range(len(arr)):
                if i == len(arr) - 1:
                    print(f'{arr[len(arr) - 1 - i]}', end = '')
                else:
                    print(f'{arr[len(arr) - 1 - i]},', end = '')
            print(']')

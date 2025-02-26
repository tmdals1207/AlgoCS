T = int(input())

for i in range(T):
    S, n = input().split()
    n = int(n)

    for j in range(len(S)):
        if S[j] == 'o':
            tmp = j

    if n == 0:
        result = tmp
    elif (tmp + n) % 2 == 0:
        result = 0
    else:
        result = 1

    print(f'#{i + 1} {result}')

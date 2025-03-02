T = int(input())

for i in range(T):
    S, n = input().split()
    n = int(n)

    for j in range(len(S)):
        if S[j] == 'o':
            tmp = j

    # 현재 위치의 인덱스와 방울이 울린 갯수를 더했을 때,
    # 이 값이 짝수이면 0번 위치에 있을 확률이 높고, (확률이 동일할 시 가장 왼쪽에 있는 컵 선택)
    # 이 값이 홀수이면 1번 위치에 있을 확률이 높다.
    if n == 0:
        result = tmp
    elif (tmp + n) % 2 == 0:
        result = 0
    else:
        result = 1

    print(f'#{i + 1} {result}')

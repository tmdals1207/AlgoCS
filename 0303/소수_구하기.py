import math

# 에라토스테네스의 체 사용 (i(2 이상)의 배수에 해당하는 인덱스를 모두 True로 만듬)
def check_prime(M, N):
    arr = [False] * (N + 1)
    arr[0] = True
    arr[1] = True
    for i in range(2, int(math.sqrt(N) + 1)):
    # 항상 j 값은 i 값 이상이여야 하기 때문에 i에 √N 보다 큰 숫자를 대입할 필요가 없음
        j = i
        while i * j <= N:
            arr[i * j] = True
            j += 1
    result = []
    for i in range(len(arr)): # 남아있는 False에 해당하는 인덱스들은 모두 소수
        if not arr[i] and i >= M:
            result.append(i)
    return result

M, N = map(int, input().split())

result = check_prime(M, N)

for ele in result:
    print(ele)

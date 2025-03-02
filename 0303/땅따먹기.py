def solution(land):
    for i in range(1, len(land)):
        for j in range(4):
            tmp = land[i][j]
            for k in range(4):
                if j != k: # land[i][j]와, land[i][j]에 이전 행의 숫자를 더한 것을 비교함으로써 가장 높은 값을 뽑음
                    land[i][j] = max(land[i][j], tmp + land[i - 1][k])
    
    return max(land[-1])

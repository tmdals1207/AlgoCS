def solution(land):
    for i in range(1, len(land)):
        for j in range(4):
            tmp = land[i][j]
            for k in range(4):
                if j != k:
                    land[i][j] = max(land[i][j], tmp + land[i - 1][k])
    
    return max(land[-1])

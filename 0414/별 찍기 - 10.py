import math
import sys
input = sys.stdin.readline

N = int(input())

def star(x):

    if x == 1:
        return ['*']

    if x == 3:
        return ['***', '* *', '***']

    result = []
    tmp = star(x // 3)
    
    for i in range(len(tmp)):
        result.append(tmp[i] * 3)
    
    for i in range(len(tmp)):
        s = tmp[i] + ' ' * len(tmp[i]) + tmp[i]
        result.append(s)
    
    for i in range(len(tmp)):
        result.append(tmp[i] * 3)
        
    return result

print('\n'.join(star(N)))

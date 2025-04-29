import sys
input = sys.stdin.readline

def energy(ene, cnt):
    global result

    if len(ene) <= 2:
        result = max(result, cnt)
        return

    for i in range(1, len(ene) - 1):
        energy(ene[:i] + ene[i + 1:], cnt + ene[i + 1] * ene[i - 1])
    

N = int(input())
arr = list(map(int, input().split()))

result = 0
energy(arr, 0)

print(result)

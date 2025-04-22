import math
import sys
input = sys.stdin.readline

def inorder(i):
    if i < N:
        inorder(i * 2)
        new_arr.append(tree[i])
        inorder(i * 2 + 1)
    
K = int(input())
arr = list(map(int, input().split()))
new_arr = []
N = len(arr) + 1
tree = [i for i in range(len(arr) + 1)]

inorder(1)

dic = dict()
for i in range(len(arr)):
    dic[new_arr[i]] = arr[i]

check = 1
mul = 1
for i in range(1, N):
    print(dic[i], end = ' ')
    if i == check:
        print()
        mul *= 2
        check += mul

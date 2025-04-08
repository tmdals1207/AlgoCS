import math
import sys
input = sys.stdin.readline

def segment(left, right, i = 1):
    if left == right:
        tree[i] = nums[left]
        return tree[i]

    mid = (left + right) // 2
    tree[i] = segment(left, mid, i * 2) + segment(mid + 1, right, i * 2 + 1)
    return tree[i]

def search(start, end, left, right, i = 1):
    if left > end or right < start:
        return 0

    if left <= start and right >= end:
        return tree[i]

    mid = (start + end) // 2
    return search(start, mid, left, right, i * 2) + search(mid + 1, end, left, right, i * 2 + 1)

def update(start, end, idx, val, i = 1):
    if idx < start or idx > end:
        return

    tree[i] += val
    if start != end:
        mid = (start + end) // 2
        update(start, mid, idx, val, i * 2)
        update(mid + 1, end, idx, val, i * 2 + 1)

N, M, K = map(int, input().split())

nums = [int(input()) for _ in range(N)]
tree = [0] * (2 ** (math.ceil(math.log(N, 2)) + 1))

segment(0, N - 1)

for _ in range(M + K):
    a, b, c = map(int, input().split())
    if a == 1:
        update(0, N - 1, b - 1, c - nums[b - 1])
        nums[b - 1] = c
    elif a == 2:
        print(search(0, N - 1, b - 1, c - 1))

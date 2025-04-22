import math
import sys
input = sys.stdin.readline

def segment(left, right, min_max, tree, i = 1):
    if left == right:
        tree[i] = nums[left]
        return tree[i]

    mid = (left + right) // 2
    
    if not min_max:
        tree[i] = min(segment(left, mid, min_max, tree, i * 2), segment(mid + 1, right, min_max, tree, i * 2  + 1))
    else:
        tree[i] = max(segment(left, mid, min_max, tree, i * 2), segment(mid + 1, right, min_max, tree, i * 2  + 1))
    return tree[i]

def search(start, end, left, right, min_max, tree, i = 1):
    if left > end or right < start:
        if not min_max:
            return float('inf')
        else:
            return 0

    if left <= start and right >= end:
        return tree[i]

    mid = (start + end) // 2

    if not min_max:
        return min(search(start, mid, left, right, min_max, tree, i * 2), search(mid + 1, end, left, right, min_max, tree, i * 2 + 1))
    else:
        return max(search(start, mid, left, right, min_max, tree, i * 2), search(mid + 1, end, left, right, min_max, tree, i * 2 + 1))

N, M = map(int, input().split())
nums = [int(input()) for _ in range(N)]

min_tree = [0] * (2 ** (math.ceil(math.log(N, 2)) + 1))
max_tree = [0] * (2 ** (math.ceil(math.log(N, 2)) + 1))

segment(0, N - 1, 0, min_tree)
segment(0, N - 1, 1, max_tree)

for _ in range(M):
    a, b = map(int, input().split())

    print(search(0, N - 1, a - 1, b - 1, 0, min_tree), end = ' ')
    print(search(0, N - 1, a - 1, b - 1, 1, max_tree))

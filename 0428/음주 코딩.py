import math
import sys
input = sys.stdin.readline

def segment(left, right, i = 1):
    if left == right:
        tree[i] = nums[left]
        return tree[i]

    mid = (left + right) // 2
    tree[i] = segment(left, mid, i * 2) * segment(mid + 1, right, i * 2 + 1)
    return tree[i]

def search(start, end, left, right, i = 1):
    if left > end or right < start:
        return 1

    if left <= start and right >= end:
        return tree[i]

    mid = (start + end) // 2
    return search(start, mid, left, right, i * 2) * search(mid + 1, end, left, right, i * 2 + 1)

def update(start, end, idx, val, prev_val, i = 1):
    if idx < start or idx > end:
        return

    if start != end:
        mid = (start + end) // 2
        update(start, mid, idx, val, prev_val, i * 2)
        update(mid + 1, end, idx, val, prev_val, i * 2 + 1)

        if tree[i] == 0 or prev_val == 0:
            tree[i] = tree[i * 2] * tree[i * 2 + 1]
        else:
            tree[i] *= val * prev_val
    else:
        tree[i] = val

while True:
    try:
        N, K = map(int, input().split())

        nums = []
        for ele in list(map(int, input().split())):
            if ele == 0:
                nums.append(ele)
            else:
                nums.append(ele // abs(ele))
        tree = [1] * (2 ** (math.ceil(math.log(N, 2)) + 1))

        segment(0, N - 1)

        for _ in range(K):
            tmp = input().split()
            if tmp[0] == 'C':
                i, V = map(int, tmp[1:])
                if V == 0:
                    update(0, N - 1, i - 1, 0, nums[i - 1])
                else:
                    V //= abs(V)
                    update(0, N - 1, i - 1, V, nums[i - 1])
                nums[i - 1] = V
            else:
                i, j = map(int, tmp[1:])
                x = search(0, N - 1, i - 1, j - 1)
                if x > 0:
                    print('+', end = '')
                elif x < 0:
                    print('-', end = '')
                else:
                    print(0, end = '')
        print()
    except:
        break

n = int(input())
arr = list(map(int, input().split()))

max_val = arr.copy()

for i in range(1, n):
    max_val[i] = max(max_val[i],max_val[i - 1] + arr[i])
	# i번째 값과 i-1번째까지의 최댓값에 i번째 값을 더한 것을 비교

print(max(max_val))

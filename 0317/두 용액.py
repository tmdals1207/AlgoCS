N = int(input())
arr = list(map(int, input().split()))
arr.sort()

left = 0
right = N - 1
tmp_back = float('inf')

memory = (left, right)

while left < right:
    tmp = arr[left] + arr[right]
    
    if abs(tmp) < abs(tmp_back):
        tmp_back = tmp
        memory = (left, right)

    if tmp > 0:
        right -= 1
    elif tmp < 0:
        left += 1
    else:
        break
    
left, right = memory
print(arr[left], arr[right])

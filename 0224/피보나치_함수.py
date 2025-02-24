t = int(input())

num = []
for i in range(t):
    num.append(int(input()))

for i in range(t):
    n = num[i]
    a, b = 1, 0
    for i in range(n):
        a, b = b, a + b
    print(a, b)

def gcd(x, y):
    new_x = x
    new_y = y

    while new_y != 0:
        new_x, new_y = new_y, new_x % new_y

    return x // new_x, y // new_x
    
e_a, e_b = map(int, input().split())

a, b = map(int, input().split())
x_0 = int(input())

L = a * x_0 + b

print(L)

if a == 0:
    print(0, 0)
else:
    result = gcd(e_a, abs(a) * e_b)

    if (result[0] > 10 ** 8) or (result[1] > 10 ** 8):
        print(0, 0)
    else:
        print(result[0], result[1])

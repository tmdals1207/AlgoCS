N = int(input())

if N == 1:
    print(3)
elif N == 2:
    print(7)
else:
    tmp_1 = 1
    tmp_2 = 3

    for i in range(2, N):
        tmp_3 = 1 + tmp_1 + 2 * tmp_2
        tmp_1 = tmp_2
        tmp_2 = tmp_3
        
    print((tmp_3 * 2 + 1) % 9901)

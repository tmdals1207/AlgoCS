import sys
input = sys.stdin.readline

dic = dict()

while True:
    tmp = input().strip()
    if tmp == '':
        break
    if tmp in dic:
        dic[tmp] += 1
    else:
        dic[tmp] = 1

for ele in sorted(dic.keys()):
    print(ele, end = '')
    print(f'{dic[ele] / sum(dic.values()) * 100: .4f}')

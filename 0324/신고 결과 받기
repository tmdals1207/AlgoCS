def solution(id_list, report, k):
    answer = []
    dic = dict()
    for ele in id_list:
        dic[ele] = [0, 0] # 신고 횟수, 신고 당한 횟수

    for li in report:
        a, b = li.split()
        if b not in dic[a]:
            dic[a].append(b)
            dic[a][0] += 1
            dic[b][1] += 1

    for ele in id_list:
        tmp = 0
        for p in dic[ele][2:]:
            if dic[p][1] >= k:
                tmp += 1
        answer.append(tmp)
    
    return answer

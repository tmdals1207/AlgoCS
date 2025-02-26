def solution(n, info):
    global list_arr, score_arr

    list_arr = [] # 최종 n점 과녁에 맞춘 화살 수 배열의 배열
    score_arr = [] # 최종 점수를 담을 배열
    result_arr = [0] * 11 # n점 과녁에 맞춘 화살 수 배열
    
    dfs(0, result_arr, n, info)

    if list_arr:
        tmp = max(score_arr)
        tmp_result = [] # 점수가 가장 높은 배열의 배열
        for i in range(len(score_arr)):
            if tmp == score_arr[i]:
                tmp_result.append(list_arr[i])

        while len(tmp_result) > 1: # 각 배열의 뒷 index부터 확인해서 작은 것 부터 pop
            for j in range(len(tmp_result[0]) - 1, 0, -1):
                value = [] # 각 배열의 j번째 값을 넣을 배열

                for i in range(len(tmp_result)):
                    value.append(tmp_result[i][j])

                for i in range(len(tmp_result)):
                    if max(value) > tmp_result[i][j]:
                        tmp_result.remove(tmp_result[i])
                        if len(tmp_result) == 1:
                            break

        result = tmp_result[0] # 마지막으로 남은 배열 출력
        
    else:
        result = [-1]
    return result


def dfs(i, result_arr, n, info):
    global list_arr, score_arr

    if n == 0 or i == 11:
        tmp_list = [0] * 11 # 얻거나 뺏긴 점수를 입력할 배열
        
        if n > 0: # 쏘지 않은 화살이 있다면 마지막에 다 소모
            result_arr[-1] += n

        for x in range(11): # 점수 세기
            if result_arr[x] > info[x]:
                tmp_list[x] = 10 - x
            elif info[x] != 0:
                tmp_list[x] = x - 10

        if sum(tmp_list) > 0: # 이긴 경우에만 배열에 추가
            list_arr.append(result_arr[:])
            score_arr.append(sum(tmp_list))
            
        result_arr[-1] = 0 # 백트래킹
        return

    if n > info[i]:
        result_arr[i] = info[i] + 1
        dfs(i + 1, result_arr, n - result_arr[i], info)
        result_arr[i] = 0
    dfs(i + 1, result_arr, n, info)

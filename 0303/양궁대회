list_arr = [] # 최종 n점 과녁에 맞춘 화살 수 배열
score_arr = [] # 최종 점수를 담을 리스트

def solution(n, info):
    global list_arr, score_arr

    list_arr = [] # 초기화
    score_arr = [] # 초기화
    
    result_arr = [0] * 11 # n점 과녁에 맞춘 화살 수 배열
    dfs(0, result_arr, n, info)

    if list_arr:
        tmp = max(score_arr)
        tmp_result = []
        for i in range(len(score_arr)):
            if tmp == score_arr[i]:
                tmp_result.append(list_arr[i])

        while len(tmp_result) > 1:
            for j in range(len(tmp_result[0]) - 1, 0, -1):
                value = []

                for i in range(len(tmp_result)):
                    value.append(tmp_result[i][j])

                for i in range(len(tmp_result)):
                    if max(value) > tmp_result[i][j]:
                        tmp_result.remove(tmp_result[i])
                        if len(tmp_result) == 1:
                            break

        result = tmp_result[-1]
        

    else:
        result = [-1]
    return result

def dfs(i, result_arr, n, info):
    global list_arr, score_arr

    if n == 0 or i == 11:
        tmp_list = [0] * 11
        
        if n > 0:
            result_arr[-1] += n

        for x in range(11):
            if result_arr[x] > info[x]:
                tmp_list[x] = 10 - x
            elif info[x] != 0:
                tmp_list[x] = x - 10

        if sum(tmp_list) > 0:
            list_arr.append(result_arr[:])
            score_arr.append(sum(tmp_list))
            
        result_arr[-1] = 0
        return

    if n > info[i]:
        result_arr[i] = info[i] + 1
        dfs(i + 1, result_arr, n - result_arr[i], info)
        result_arr[i] = 0
    dfs(i + 1, result_arr, n, info)

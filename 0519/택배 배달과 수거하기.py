def solution(cap, n, deliveries, pickups):
    i = n - 1
    j = n - 1
    result = 0

    while i > -1 or j > -1:
        deli_cap = 0
        pick_cap = 0

        deli_r = 0
        pick_r = 0

        while deli_cap < cap and i > -1:
            tmp = deliveries[i]

            if tmp:
                deli_r = max(deli_r, i)

                if deli_cap + tmp <= cap:
                    deli_cap += tmp
                    deliveries[i] = 0
                    i -= 1
                    
                else:
                    r = cap - deli_cap
                    deli_cap += r
                    deliveries[i] -= r
                    break
            else:
                i -= 1

        while pick_cap < cap and j > -1:
            tmp = pickups[j]
            
            if tmp:
                pick_r = max(pick_r, j)

                if pick_cap + tmp <= cap:
                    pick_cap += tmp
                    pickups[j] = 0
                    j -= 1
                else:
                    r = cap - pick_cap
                    pick_cap += r
                    pickups[j] -= r
                    break
            else:
                j -= 1
                
        if deli_r > -1 or pick_r > -1:
            if deli_r == 0 and pick_r == 0 and deli_cap == 0 and pick_cap == 0:
                result += 0
            else:
                result += (max(deli_r, pick_r) + 1) * 2
    
    return result

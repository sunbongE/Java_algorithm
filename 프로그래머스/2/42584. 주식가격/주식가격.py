
def solution(prices):
    L=len(prices)
    ans = [0] * L
    # for문 2중을 돌면서 cnt +=1 하고 현재값보다 작으면 break
    for i in range(L):
        for j in range(i+1,L):
            ans[i] += 1
            if prices[i] > prices[j]:
                break
                
    return ans
class Solution {
    public int solution(int n) {
        int answer = 1;
        int sum = 1;
        
        int left=1, right=1;
        
        while(left < right || right < n){
            
            if(sum > n){ // 타겟보다 크면 왼쪽거 빼야함.
                sum -= left++;
                
            }else{ // 타겟보다 작으면 다음 수를 더해본다.
                if(sum==n) answer++; // 같은 경우만.
                sum += ++right;
            }
        }
        
        return answer;
    }
}
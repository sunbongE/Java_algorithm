class Solution {
    public int solution(int n) {
        int answer = 0;
        int cur = n ;
        int target = getOneCnt(cur);
        int oneCnt=-1;
        while(target != oneCnt){
            oneCnt = getOneCnt(++cur);
        }
        
        return cur;
    }
    
    public int getOneCnt(int num){
        int result = 1 ;
        while(num>1){
            if(num%2==1){
                result++;
            }
            num/=2;
        }
        return result;
    }
}
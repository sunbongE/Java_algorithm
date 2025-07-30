/*
n진수로 변환 후, 0으로 split

에라토스테네스채 -> 소수 판별 -> 0이 있는지 확인 boolean[]

소수 P가 여러개인 경우 고려해서 각 자리를 구해야함? 맞나?
210002100021 -> 이러면, P로 split하면 결과 구할 수 있다고 생각함.

startsWith -> P0 인지 확인
endsWith -> 0P 인지 확인
split으로 나뉜 요소들 순회 for i in 0~n-1까지
i, i+1 요소 탐색 i의 마지막이 0이고, i+1의 첫번째가 0인지 확인해서 P가 0P0의 형태였는지 확인.

조건에 맞는거 찾고 개수 올림.

*/
import java.util.*;
class Solution {
    static HashSet<String> set;
    public int solution(int n, int k) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        set = new HashSet<>();
        
        while(n>0){
            sb.append(n%k);
            n/=k;
        }
        sb.reverse();
        String result = sb.toString();
        String[] nums = result.split("0");
        // 소수인지 확인
        for(int i=0; i<nums.length; i++){
            if(nums[i].isEmpty() ) continue;
            if(isPrime(Long.parseLong(nums[i])) && set.add(nums[i])){
                System.out.println(nums[i]);
               
                // 확인된 소수 P에 대해 점수 확인.
                answer+=checkPoint(result, nums[i]);            
            }
        }
        
        return answer;
    }
    
    // 소수인경우 return false;
    public boolean isPrime(long num){
        if(num < 2) return false;
        
        for(int i=2; i<=Math.sqrt(num);i++){
            if(num%i == 0) return false;
        }
        return true;
    }

    
    public int checkPoint(String result, String P){
        int point = 0;
        String[] sp = result.split(P);
        
        // P만 있는거
        if(result.equals(P)) return 1;
        
        // P로 시작한경우 -> P0
        if(result.startsWith(P)){
            if(sp[1].charAt(0)=='0') point++;
        }
        // P로 끝나는 경우 -> 0P
        if(result.endsWith(P) ){
            int endIdx = sp.length-1;
            if(sp[endIdx].charAt(sp[endIdx].length()-1)=='0') point++;
        }
        // 사이 값들
        for(int i=0;i<sp.length-1;i++){
            if(sp[i].isEmpty() || sp[i+1].isEmpty()) continue;
            // 100 P 011 이상태 확인.
            if(sp[i].charAt(sp[i].length()-1)=='0' && sp[i+1].charAt(0)=='0') point++;
        }

        // System.out.println("P : "+P+", point :"+point);

        return point;
    }
}
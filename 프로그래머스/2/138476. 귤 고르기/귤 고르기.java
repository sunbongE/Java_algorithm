import java.util.*;

/**
상자에 담을 수 있는 같은 종류의 귤을 최대한 담는다.
담긴 귤의 종류의 개수를 리턴하면 된다.

**/
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        int len = tangerine.length;
        
        HashMap<Integer,Integer> map= new HashMap<>();
        
        for(int i=0; i<len;i++){
            map.put(tangerine[i], map.getOrDefault(tangerine[i],0)+1);
        }
        
        ArrayList<Integer> li = new ArrayList<>(map.values());
        
        Collections.sort(li, Collections.reverseOrder());
        
        for(Integer cnt : li){
            answer++;
            k-=cnt;
            if(k <= 0) break;
        }
        
        
        
        return answer;
    }
    
    
}
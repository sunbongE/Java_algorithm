import java.util.*;

class Solution {
    static PriorityQueue<Integer> pq ;
    
    public long solution(int n, int[] works) {
        pq = new PriorityQueue<>( (a,b) -> {return b-a;} );
        long answer = 0;
        int len = works.length;
        
        for(int i=0;i<len;i++){
            pq.offer(works[i]);
        }

        while(n-- > 0 && !pq.isEmpty()){
            Integer tmp = pq.poll()-1;
            
            if(tmp>0) pq.offer(tmp);
        }
        
        while(!pq.isEmpty()){
            long w = (long) pq.poll();
            answer += (w*w);
        }       
        
        return answer;
    }
}
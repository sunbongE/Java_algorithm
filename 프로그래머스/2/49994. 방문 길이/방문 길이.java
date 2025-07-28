import java.util.*;
class Solution {
    static int ci=0, cj=0,pi=0,pj=0;
    static int[] di = {-1,1,0,0}, dj = {0,0,-1,1};
    public int solution(String dirs) {
        int answer = 0;

        // 방문 기록을 어떤걸로 할까.
        // 배열로하기에는 공간낭비가 심해서 해쉬셋으로 하는 것이 좋을 듯.
        HashSet<String> set = new HashSet<>();
        
        for(int i=0; i<dirs.length(); i++ ){
            char c = dirs.charAt(i);
            if(move(c)){
               String path = "";
                if (ci < pi || (ci == pi && cj < pj)) {
                    path = ci + "," + cj + "," + pi + "," + pj;
                } else {
                    path = pi + "," + pj + "," + ci + "," + cj;
                }
                if (set.add(path)) answer++;
            }
        }
        return answer;
    }
    // 0~9 : 10개, 11~20 : 10개 
    public boolean move(char c){
        boolean moved = false;
        int ni=ci, nj=cj;
        
        if(c == 'U'){
            ni = ci+di[0];    
        }else if(c == 'D'){
            ni = ci+di[1];
        }else if(c == 'R'){
            nj = cj+dj[2];
        }else{
            nj = cj+dj[3];
        }
        
        if(!isOut(ni,nj)){ // 움직일 수 있는 거리라면,
            // 이전 기록. 
            pi = ci;
            pj = cj;
            
            ci = ni;
            cj = nj;
            
            moved = true;
        }
        
        return moved;
    }
    
    public boolean isOut(int ni, int nj){
        return ni < -5 || ni > 5 || nj < -5 || nj > 5;
    }
}
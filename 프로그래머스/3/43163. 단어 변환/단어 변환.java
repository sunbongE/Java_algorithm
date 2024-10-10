import java.util.*;
class Solution {
    static int changeCnt, matchedCnt,answer;
    static boolean v[], matched[];
    static String start, end, data[];
    public int solution(String begin, String target, String[] words) {
        answer = 0;
        
        // target으로 변환될 수 있는지를 확인한다.
        boolean isValidData = checkWords(target,words);
        if(!isValidData) return answer;
        
        start = begin;
        end = target;
        data = words;
        
        changeCnt = matchedCnt = 0;
        
        v = matched = new boolean[words.length];
        answer = Integer.MAX_VALUE;
        

        // dfs를 사용해서 각 단어를 살펴본다.
        
        dfs(0, data.length, 0, start);
                
        return answer;
    }
    
    private void dfs(int n, int N, int cnt,String tmp){
        if(n>=N){ // 전부 둘러봤다면
            // 현재 cnt한거랑 정답이 될거 둘중 작은거를 answer에 갱신한다.
            if(tmp.equals(end)){
                answer = (answer > cnt) ? cnt : answer;
            }
            return; // 리턴
        }
        
        if(cnt > answer) return;
        
        
        for(int i = 0; i<data.length; i++){
            if(v[i]) continue; // 이미 바뀐 이력이 있으면 넘어가기.
            
            // 지금 단어랑 바뀔 단어의 변화되는 알파벳 수가 1개인지 확인한다.
            int changAlpaCnt = getChangeCnt(data[i],tmp);
            if(changAlpaCnt != 1) continue; // 바뀌는 단어의 개수가 1개가 아니면 넘어가
  
               
            // 현재 단어로 변경한다.
            v[i] = true;
            if(data[i].equals(end)){
                dfs(N, N, cnt+1, data[i]);
            }else{
                dfs(n+1, N, cnt+1, data[i]);
            }
            v[i] = false;
            
            // 변경안한다.         
            // dfs(n+1, N, cnt, tmp);
        
        }

        
        return;
        
    }
               

    
    private Integer getChangeCnt(String next, String tmp){
        // 다른 자리수 개수 리턴
        int cnt=0;
        for(int i=0; i<tmp.length(); i++){
            if(next.charAt(i) != tmp.charAt(i)) cnt++;
        }
        return cnt;
    }
    
    private boolean checkWords(String target, String[] words){
        for(String word : words){
            if(word.equals(target)){
                return true;
            }
        }
        return false;
    }
}
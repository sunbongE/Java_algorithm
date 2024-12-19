import java.util.*;
class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        boolean isF=true;
        
        for(int i=0; i<s.length(); i++){
            
            char tmp=s.charAt(i);
            if(tmp == ' '){
                isF=true;
                answer.append(tmp);
                continue;
            }
            if(isF){
                if(Character.isAlphabetic(tmp) && Character.isLowerCase(tmp)){
                    tmp = Character.toUpperCase(tmp);
                }
                isF=false;
            }else{
                if(Character.isAlphabetic(tmp)){
                    tmp = Character.toLowerCase(tmp);
                }
            }   
            answer.append(tmp);
        }
        
        return answer.toString();
    }
}
import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        sb.append(0);
        int cnt = t*m; 
        int num = 1;
        StringBuilder result ;
        while(cnt-- > 0){
            
            int copyNum = num++;
            result = new StringBuilder();
            
            while(copyNum > 0){
                int n1 = copyNum%n;
                copyNum /= n;
                result.append((n1 < 10) ? n1 : getAlpa(n1) );
                
            }
            sb.append(result.reverse().toString());
            
        }
        // System.out.println(sb);
        result = new StringBuilder();
        
        String s = sb.toString();
        
        for(int i=p-1; i<s.length();i+=m){
            result.append(s.charAt(i));
            if(result.length() == t) break;
        }
        answer = result.toString();
        return answer;
    }
    
    public String getAlpa(int num){
        
        switch (num){
            case 10 : 
                return "A";
            case 11:
                return "B";
            case 12 : 
                return "C";
            case 13:
                return "D";
            case 14 : 
                return "E";
            case 15:
                return "F";
                
        }
        return "-1";
    }
}
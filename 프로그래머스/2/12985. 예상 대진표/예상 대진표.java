import java.util.*;
class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
       
        for(int r=0; r<n;r+=2){
            a = a/2+a%2;
            b = b/2+b%2;
            answer++;
            if(a == b) break;
        }

        return answer;
    }
}
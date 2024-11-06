import java.util.*;
class Solution {
    /**
    길이가 2이상의 짝수다.
    모든 수열 집합의 교집합은 1개 이상이다.
    같은 집합의 앞뒤 수는 다르다.
    리턴 : 가장 긴 스타 수열의 길이 || 없으면 0
    n이 50만으로 조합, dfs 불가능.
    반복문으로 끝낼 수 있는 문제같다.
    짝수번째에 올 수 있는 값은 뭐든 상관 없다만
    홀수번째 오는 값은 무조건 교집합내에 있어야한다.
    그럼 교집합이 되는 것을 골라야하는데
    1번의 반복으로 불가능한게 모든 경우를 따저볼 수 없기 때문이다.
    
    가장 많이 등장하는 수를 기준으로 앞뒤로 사용할 수 있는 값이 잇는지 확인하고,
    사용하면 사용체크 후 cnt++, 
    **/
    public int solution(int[] a) {
        int answer = -1;
        
        // 길이 1인거 처리
        if(a.length == 1) return 0;

        // 가장 많이 사용되는 값을 기록한다.
        int[] cntArr = new int[a.length];
        for(int i=0;i<a.length;i++){
            cntArr[a[i]]++;
        }
        // System.out.println(Arrays.toString(cntArr));
        
        // 가장 많이 사용된 수
        int manyCnt = -1;
        int secondManyCnt = -1;
        // int manyNum = -1; // 가장 많이 사용된 수
        List<Integer> targets = new ArrayList<>(); // 가장 많이 사용된 수가 여러개인경우 사용될것.
        
        for(int i=0;i<cntArr.length;i++){
            if(manyCnt < cntArr[i]){
                secondManyCnt = manyCnt;
                manyCnt = cntArr[i]; // 많이 사용된 개수 찾기.
                // manyNum = i;
            }else{
                secondManyCnt = Math.max(secondManyCnt,cntArr[i]);
            }
            
        }
        
        for(int i=0;i<cntArr.length;i++){
            if(manyCnt == cntArr[i] || secondManyCnt== cntArr[i]){ // 가장 많이 사용된 개수인 수 찾기.
                targets.add(i);
            }
        }
        // System.out.println(targets);System.out.println(secondManyCnt);
        
        
        int maxLen=0;
        for(Integer manyNum:targets){
        boolean[] v = new boolean[a.length];
        int len = 0;    
        
        // 가장 많이 사용된 수를 기준으로 앞뒤 중 사용할 수 있는거 사용(우선순위 앞)
        for(int i=0;i<a.length;i++){
            
            if(a[i] == manyNum){
                // 앞에꺼 확인
                if(isIn(i-1,a.length) && !v[i-1] && a[i-1]!=manyNum){
                    v[i-1] = true;
                    len+=2;
                }else if(isIn(i+1,a.length) && !v[i+1] && a[i+1] != manyNum){
                // 앞에 없으면 뒤에꺼 확인
                    v[i+1] = true;
                    len+=2;
                }
            }
        }
            maxLen = Math.max(maxLen,len);
        }
        
        if(maxLen > 2){
            answer = maxLen;
        }
        
        // System.out.println(manyIdx);
        
        return answer;
    }
    
    private boolean isIn(int idx,int len){
        return 0<= idx && idx < len;
    }
}
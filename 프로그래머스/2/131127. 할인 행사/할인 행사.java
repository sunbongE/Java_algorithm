/*
1. 10개씩 투포인터 > 정렬
2. 10개 배열에 원하는 과일이 몇개 들었는지 확인 : 이분탐색
3. 찾은 개수가 원하는거 개수보다 전부 크거나 같으면 cnt++
4. 아니면 다음 슬라이딩 윈도우 배열 가져와서 반복
슬라이딩 윈도우 시 O(n)
이분탐색 O(logN)
총 NlogN
n = 10만

*/
import java.util.*;
class Solution {
    private static String[] tp;
    private static HashMap<String, Integer> map;
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        tp = new String[10];
        
              
        
        // System.out.println(tp);
        
        
        for(int i = 0; i<=discount.length-10; i++){
            tp = Arrays.copyOfRange(discount,i,i+10);
            Arrays.sort(tp);
            // System.out.println(Arrays.toString(tp));
            answer += check(want,number);
        }
        
        
        return answer;
    }
    
    private int check(String[] want, int[] number){
        map = new HashMap<>();
        for(int i=0; i<tp.length;i++){
            map.put(tp[i],i);
        }
        
              
        for(int i=0;i<want.length;i++){
            int count=0;
            String target = want[i];
            // System.out.println("target : "+target);
            if(!map.containsKey(target)) return 0;
            int s = 0;
            int e = tp.length-1;
            boolean isNotFind=true;
            while(s<=e){
                
                int mid = (s+e)/2;
                // uper
                if(map.get(tp[mid]) <= map.get(target)){
                    if(tp[mid].equals(target)) isNotFind=false;
                    s = mid+1;
                }else{
                    e=mid-1;
                }
            }
            if(isNotFind) return 0 ; // 배열에 원하는 원소가 없는 경우.
            count=s;
            // System.out.println("count : "+count);
            s = 0;
            e = tp.length;
            while(s<=e){
                
                int mid = (s+e)/2;
                // lower
                if(map.get(tp[mid]) < map.get(target)){
                    s = mid+1;
                }else{
                    e=mid-1;
                }
            }
            count-=s;
            // System.out.println("count2: "+count);
            if(count < number[i]) return 0;// 원하는 원소의 개수가 적은경우.
        }
        
        return 1; // 원하는 원소가 개수까지 포함해서 있는경우.
    }
}






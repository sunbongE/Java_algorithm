/*
s를 일단 배열 형태로 변경
1. } 기준으로 나눔
2. 나눈걸 문자열 배열에 기록
3. 들어간 배열 순회하며, 중괄호와 쉼표 삭제.
4. 
*/
import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        boolean isOpen      =false;
        
        ArrayList<ArrayList<Integer>> li = new ArrayList<>();
        for(int i=0; i<1000000;i++){
            li.add(new ArrayList<>());
        }

        int idx=0;
        String snum = "";
        
        for(int i=0; i<s.length();i++){
            char cur = s.charAt(i);
            
            if(cur == '{'  ) { 
                isOpen = true;
            } else if(cur == '}' ) { // 닫는데 숫자가 있으면 
                if(!snum.equals("")) {
                    li.get(idx).add(Integer.parseInt(snum));
                    snum="";
                }
                isOpen = false;
                idx++;
            } else if( cur == ',') { 
                if(!snum.equals("")){
                    li.get(idx).add(Integer.parseInt(snum)); // 이전까지 기록된 숫자 넣기
                    snum = ""; // 초기화
                }
            } else {
                if(isOpen) snum+=cur;
            }
        }
        
        Collections.sort(li, Comparator.comparingInt(List::size));
        
        ArrayList<Integer> result = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for(int i =0 ; i< li.size(); i++) {
            if(li.get(i).isEmpty()) continue;
            for(Integer num : li.get(i)){
                if(set.add(num)){
                    result.add(num);
                }
            }
        }
        // System.out.println(result);
        answer = new int[result.size()];
        for(int i=0; i<result.size();i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}
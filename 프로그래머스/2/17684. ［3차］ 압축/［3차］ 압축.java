import java.util.*;
class Solution {
//    public static void main(String[] args) {
//        Solution s = new Solution();
//        int[] answer = s.solution("ABABABABABABABAB");
////        int[] answer = s.solution("KAKAO");
//
//            System.out.println(Arrays.toString(answer) );
//
//
//        System.out.println(map);
//    }
    static HashMap<String,Integer> map;
    public int[] solution(String msg) {
        int[] answer = {};
        ArrayList<Integer> li = new ArrayList<>();
        map = new HashMap<>();

        for(int i=1; i<27;i++){
            char c = (char)('A'+i-1);
            map.put(""+c,i);
        }

        int idx = 27;
        int s=0,e=1;

        String tmp = ""+msg.charAt(s);
        for(int i=s+1; i < msg.length();i++){
            if(map.containsKey(tmp + msg.charAt(i))){
                tmp += msg.charAt(i);    // 있으면, 다음 긴 문자열 찾음.
            }else{
                //없으면,
                li.add(map.get(tmp));
                map.put((tmp+msg.charAt(i)),idx++);
                s = i;
                tmp = ""+msg.charAt(s);
            }
        }
        li.add(map.get(tmp));



        answer = new int[li.size()];
        for(int i=0;i<li.size();i++){
            answer[i] = li.get(i);
        }
        return answer;
    }
}
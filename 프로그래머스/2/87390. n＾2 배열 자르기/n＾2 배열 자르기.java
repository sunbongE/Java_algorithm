/*
left -> right까지 순회 데이터 : target이라할때,
target을 2차원 배열에서 i,j 값을 찾고, Math.max(i,j)+1값이 그 자리 값이다.

i = target/n
j = target - (i*n)

*/
class Solution {
    public int[] solution(int n, long left, long right) {
        int size = (int) (right-left);
        int[] answer = new int[size + 1];
        
        long  target = left;
        int idx=0;
        while(target <= right){
            int i = (int) (target/ n);
            int j = (int) (target - (i* n));
            
            int value = Math.max(i,j)+1;
            answer[idx++] = value;
            target++;
        }
        
        return answer;
    }
}
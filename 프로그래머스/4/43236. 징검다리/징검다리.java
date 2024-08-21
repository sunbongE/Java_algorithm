import java.util.Arrays;
class Solution {
    int[] Rocks;
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        Rocks = new int[rocks.length+2];
        Rocks[0]=0;
        Rocks[Rocks.length-1]=distance;
        for(int i=1;i<Rocks.length-1;i++){
            Rocks[i]=rocks[i-1];
        }
        // System.out.println(Arrays.toString(Rocks));
        
        
        int min = 1;
        int max = distance;
        
        while(min<=max){
            // 다리의 간격
            int mid = (min+max)/2;
            
            // 제거된 다리 개수 구하기
            int rmCnt = getRemoveRocksCnt(mid);

            if (rmCnt <= n) { 
                // 제거한 바위의 수가 n 이하인 경우, 더 큰 간격을 찾기 위해 min을 늘림
                min = mid + 1;
            } else {
                // 제거한 바위의 수가 너무 많은 경우, 더 작은 간격을 찾기 위해 max를 줄임
                max = mid - 1;
            }

            
        }
        
        return min;
    }
    
    private int getRemoveRocksCnt(int mid){
        int len = Rocks.length;
        int next=1;
        int pre=0;
        int rmCnt = 0;
        while(next<len){
            if(mid < (Rocks[next] - Rocks[pre])){ // 다리 간격이 작으면 제거
                pre = next;
                next++;
            }else{ // 같거나 크면 제거안함
                next++;
                rmCnt++;
            }
        }
        return rmCnt;
        
    }
}
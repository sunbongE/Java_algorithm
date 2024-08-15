class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        boolean[] check  = new boolean[46];
        for(int i = 0 ; i< win_nums.length ; i++){
            check[win_nums[i]] = true;
        }
        
        int cntZero =0;
        int inCnt=0;
        for(int num : lottos){
            if(num == 0) cntZero++;
            else{
                if(check[num]){
                    inCnt++;
                }
            }
        }
        int[] result = new int[2];
        
        result[0] = mkGrade((inCnt+cntZero)); // 최대 등급
        result[1] = mkGrade(inCnt); // 최소 등급

        return result;
    }
    
    public int mkGrade(int cnt){
        switch(cnt){
        case 6:
                return 1;
        case 5:
                return 2;
        case 4: 
                return 3; 
        case 3:
                return 4;
            case 2:
                return 5;
            default:
                return 6;
                
        }
        
    }
}

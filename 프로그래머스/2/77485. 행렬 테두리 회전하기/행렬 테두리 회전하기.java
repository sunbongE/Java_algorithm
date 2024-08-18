import java.util.*;
class Solution {
    int[] dr = {0,1,0,-1};
    int[] dc = {1,0,-1,0};
    int[][] map;
    final int MAX_NUM = 101;
    public int[] solution(int rows, int columns, int[][] queries) {
        int size = queries.length;
        int[] answer = new int[size];
        
        map = makeMap(rows, columns);
        // for(int[] arr : map){
        //     System.out.println(Arrays.toString(arr));
        // }
        
        int r1,c1,r2,c2,tmp;
        for(int idx=0;idx<size;idx++){
            r1 = queries[idx][0]-1;
            c1 = queries[idx][1]-1;
            r2 = queries[idx][2]-1;
            c2 = queries[idx][3]-1;
            // System.out.println(r1+", "+c1+", "+r2+", "+c2);
            int minNum = turn(r1,c1,r2,c2);
            answer[idx] = minNum;
        }
        
        
        return answer;
    }
    
    private int turn(int r1, int c1, int r2, int c2){
        int tmp = map[r1][c1];  // 다음값 기록용
        int ins = tmp;  // 삽입값 기록용
        int minNum = ins; // 최소값
        int directoin=0;
        int curR = r1;
        int curC = c1;
        int nextR, nextC;
        
        while(directoin<4){
            nextR = curR+dr[directoin];
            nextC = curC+dc[directoin];
            // 다음 값 기록.
            tmp = map[nextR][nextC];

            // 이동한 값 최소값 갱신
            minNum = Math.min(minNum, ins);
            
            // 현재값을 다음 값으로 이동.
            map[nextR][nextC] = ins;
            
            // 다음에 삽입할 값 저장.
            ins = tmp;
            
            // 방향 전환
            if(check(nextR,nextC,r1,c1,r2,c2)){
                directoin++;
            }
            
            // 현재값을 다음 값으로 이동.
            curR = nextR;
            curC = nextC;
            
            
        }
        return minNum;
    }
    
    private boolean check(int nextR, int nextC, int r1, int c1, int r2, int c2){
        // 다음 값이 처음 시작한 값이면
        if(nextR == r1 && nextC == c1){ // 왼쪽 위 도달
            return true;
        }else if(nextR == r1 && nextC == c2){ // 오른쪽 위 도달
            return true;
        }else if(nextR == r2 && nextC == c2){ // 오른쪽 아래 도달
            return true;
        }else if(nextR == r2 && nextC == c1){ // 왼쪽 아래 
            return true;
        }else{ // 그 외 방향유지
            return false;
        }
    }
    
    private int[][] makeMap(int row, int col){
        int[][] result = new int[row][col];
        
        int num = 1;
        
        for(int r=0;r<row;r++){
            for(int c=0;c<col;c++){
                result[r][c] = num++;
            }
        }
        return result;
    }
}
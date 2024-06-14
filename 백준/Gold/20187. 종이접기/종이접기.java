import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 반드시 가로,세로 k번씩은 접는다.
 * 최소 크기가 2*2가 된다는 말이다.
 * 그리고 다 펼친 모습은 반드시 정사각형이 되어야한다.
 * 그럼 작은 정사각형(2*2)를 먼저 구하고 이를 반복하면 문제가 풀린다.
 *
 * 1. 2*2 정사각형 값을 구하고.
 * 2. N번 반복 출력.
 * 출력 과정에서는 StringBuilder 사용하자.
 */
public class Main {

    static boolean[] check = new boolean[4];
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        String[] commands = bf.readLine().split(" ");


        int[] startXY = getStartIdx(commands);
        int startX = startXY[0];
        int startY = startXY[1];



        int target = Integer.parseInt(bf.readLine());
        check[target] = true;
        int[][] smallResult =new int[2][2];
        smallResult[startX][startY] = target;
        smallResult[(startX+1)%2][startY] = upDown(target);
        smallResult[startX][(startY+1)%2] = leftRight(target);


        for (int i =0;i<4;i++){
            if(!check[i]) smallResult[(startX+1)%2][(startY+1)%2] = i;
        }

        String result1="";
        String result2="";

        for (int i = 0; i < 2; i++) {
                result1 += (smallResult[0][i]+" ");
        }
        for (int i = 0; i < 2; i++) {
                result2 += (smallResult[1][i]+" ");
        }

        for (int k = 0; k < N; k++) {

            for (int i=0;i<N;i++){
                sb.append(result1);
            }
            sb.append("\n");
            for (int i=0;i<N;i++){
                sb.append(result2);
            }
            sb.append("\n");
        }



//        System.out.println(result1);
//        System.out.println(result2);
        System.out.println(sb);
    }

    private static int[] getStartIdx(String[] commands) {
        int[] startIdx=new int[2];
        Arrays.fill(startIdx,-1);

        for (int i = commands.length-1; i >= 0 ; i--) {
            if(commands[i].equals("U")||commands[i].equals("D")){
                if(startIdx[0]!=-1) continue;
                startIdx[0] = (commands[i].equals("U")) ? 0 : 1;
            }else{
                if(startIdx[1]!=-1) continue;
                startIdx[1] = (commands[i].equals("L")) ? 0 : 1;
            }

        }
        return startIdx;
    }

    // 가로로 접는 경우.
    private static int upDown(int cur){
        check[(cur+2)%4]=true;
        return (cur+2)%4;
    }

    private static int leftRight(int cur){
        if(cur==0||cur==2){
            check[cur+1] = true;
            return cur+1;
        }else{
            check[cur-1] = true;
            return cur-1;
        }
    }
}

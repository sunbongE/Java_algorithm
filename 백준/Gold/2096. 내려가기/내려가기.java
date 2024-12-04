
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int size = n+2;
        StringTokenizer st;
        int[][] arr = new int[size][5];
        int[][] maxDp = new int[size][5];
        int[][] minDp = new int[size][5];

        for (int i = 0; i < minDp.length; i++) {
            Arrays.fill(minDp[i],Integer.MAX_VALUE);
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
                minDp[i][j] = num;
                maxDp[i][j] = num;
            }
        }

        for (int i = 2; i < size-1; i++) {
            for (int j = 1; j < 4; j++) {
                maxDp[i][j] = Math.max(maxDp[i-1][j-1],Math.max(maxDp[i-1][j],maxDp[i-1][j+1])) + arr[i][j];
                minDp[i][j] = Math.min(minDp[i-1][j-1],Math.min(minDp[i-1][j],minDp[i-1][j+1])) + arr[i][j];
            }
        }

        int max =0 ;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < maxDp[0].length; i++) {
            max = Math.max(max, maxDp[size-2][i]);
            min = Math.min(min, minDp[size-2][i]);
        }
        System.out.println(max+" "+min);


//        for (int[] ints : minDp) {
//            System.out.println(Arrays.toString(ints));
//        }

    }
}
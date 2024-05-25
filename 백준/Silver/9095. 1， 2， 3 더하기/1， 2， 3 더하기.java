import java.util.Arrays;
import java.util.Scanner;

/**
 * dp풀이
 * 규칙성 찾기
 * 1 = 1
 * 2 = 1+1, 2
 * 3 = 1+1+1, 2+1, 1+2, 3
 * 4 = 1+3, 1+1+2, 2+2, 1+1+1+1, 2+1+1, 1+2+1, 3+1
 *
 * 점화식
 * dp[i] = dp[i-3] + dp[i-2]+ dp[i-1]
 *
 *
 *
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] dp= new int[12];
        dp[1]=1;
        dp[2]=2;
        dp[3]=4;
        for (int i = 4; i<12;i++){
            dp[i] = dp[i-3] +dp[i-2] +dp[i-1];
//            System.out.println(Arrays.toString(dp));
        }
        int T = sc.nextInt();
        for (int t=0; t<T;t++){
            int n = sc.nextInt();
            System.out.println(dp[n]);
        }
    }
}

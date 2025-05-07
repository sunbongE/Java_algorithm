import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 상근이는 길을 걷다가 신기한 기계를 발견했다. 기계는 매우 매우 큰 화면과 버튼 하나로 이루어져 있다.
 *
 * 기계를 발견했을 때, 화면에는 A만 표시되어져 있었다. 버튼을 누르니 글자가 B로 변했다. 한 번 더 누르니 BA로 바뀌고,
 * 그 다음에는 BAB, 그리고 BABBA로 바뀌었다. 상근이는 화면의 모든 B는 BA로 바뀌고, A는 B로 바뀐다는 사실을 알게되었다.
 *
 * 버튼을 K번 눌렀을 때, 화면에 A와 B의 개수는 몇 개가 될까?
 * 첫째 줄에 K (1 ≤ K ≤ 45)가 주어진다.
 *
 *
 * 0. A     [1,0]
 * 1. B     [0,1]
 * 2. BA    [1,1]
 * 3. BAB   [1,2]
 * 4. BABBA [2,3]
 * 5. [3,5]
 * 6. [5,8]
 * 7. [8,13]
 * 8. [13,21]
 * 9. [21, 34]
 * 10. [34,55]
 * 10 -> 34 55
 *
 * */
public class Main {

    static int n, dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //       BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1][2];
        dp[0][0] = 1;
        dp[1][1] = 1;
        if(n <= 1){
            System.out.println(dp[n][0]+" "+dp[n][1]);
            return;
        }

        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-2][0];
            dp[i][1] = dp[i-1][1] + dp[i-2][1];
        }

        System.out.println(dp[n][0]+" "+dp[n][1]);

    }


}

import java.io.*;
import java.util.Arrays;

/**
 * lcs를 구하는 배열 2개
 * 1. 최대 부분 공통 수열의 길이를 구하는 배열
 * 2. 1을 구하는 과정에서 어떤 위치에서 결과를 내었는지 기록하는 배열
 * <p>
 * 메소드 1 (a.len, b.len, 2번배열, a)
 * 1번 배열의 값을 역추척하여 lcs 결과값을 구하는 메소드
 */
public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // dp 점화식 적용을 위해 0번은 0으로 초기화한다.
        String s1 = bf.readLine();
        String s2 = bf.readLine();

        char[] a = charArraySetting(s1);
        char[] b = charArraySetting(s2);
//        System.out.println("Arrays.toString(a) = " + Arrays.toString(a));
//        System.out.println("Arrays.toString(a) = " + Arrays.toString(b));
        int[][] resultArray = lcs(a, b);
//        System.out.println("===resultArr===");
//        for (int[] ints : resultArray) {
//            System.out.println(Arrays.toString(ints));
//        }
        String result = get_lcs(a.length-1, b.length-1, resultArray, a);
        sb.append(result);
        System.out.println(sb);

    }

    private static String get_lcs(int i, int j, int[][] resultArray, char[] a) {
        if (i == 0 || j == 0) return "";

        if (resultArray[i][j] == 1) {
            return get_lcs(i - 1, j - 1, resultArray, a) + a[i];
        } else if (resultArray[i][j] == 2) {
            return get_lcs(i, j - 1, resultArray, a);
        } else {
            return get_lcs(i - 1, j, resultArray, a);

        }

    }

    private static int[][] lcs(char[] a, char[] b) {
        int m = a.length;
        int n = b.length;

        int dp[][] = new int[m][n]; // 1번째배열
        int result[][] = new int[m][n]; // 2번째 배열

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i] == b[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result[i][j] = 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    result[i][j] = (dp[i - 1][j] < dp[i][j - 1]) ? 2 : 3;
                }
            }
        }
//        System.out.println("===dp===");
//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }
        sb.append(dp[m-1][n-1] + "\n");
        return result;
    }

    private static char[] charArraySetting(String s) {
        int len = s.length();
        char[] result = new char[len + 1];
        for (int i = 0; i < len; i++) {
            result[i + 1] = s.charAt(i);
        }
        return result;
    }
}


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n+1][m+1];

        String data;
        for (int i = 1; i <= n; i++) {
            data = br.readLine();
            for (int j = 1; j <= m; j++) {
                int c = data.charAt(j-1)-'0';
                arr[i][j] = c;
            }
        }

        int max=0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(arr[i][j] != 1) continue;
                arr[i][j] = Math.min(arr[i-1][j], Math.min(arr[i-1][j-1], arr[i][j-1]))+1;
                max = Math.max(arr[i][j],max);
            }
        }

        System.out.println(max*max);

 


    }
}
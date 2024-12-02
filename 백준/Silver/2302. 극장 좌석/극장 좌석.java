import java.io.*;
import java.util.*;

public class Main {

    static int dp[] = new int [41];		public static void main(String[] args) throws Exception{		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		int T = Integer.parseInt(br.readLine());		dp[0] = 1;		dp[1] = 1; 		dp[2] = 2;		for(int i = 3; i<=T; i++){			dp[i] = dp[i-1]+dp[i-2];		}		int n = Integer.parseInt(br.readLine());		int ans = 1;		int start =0; 		int stop =0;		for(int i=0; i<n; i++){			stop = Integer.parseInt(br.readLine());			ans *= dp[stop-start-1];			start = stop;		}		ans *= dp[T-stop];		System.out.println(ans);	} }

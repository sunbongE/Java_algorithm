import java.io.*;
import java.util.*;

/**
 *
 * 3
 * 0
 * */
public class Main {
    static int N,K;
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   //      BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] prefixSum = new int[N+1];
        for (int i = 1; i <=N; i++) {
            prefixSum[i] = prefixSum[i-1]+arr[i-1];
        }

//        System.out.println(Arrays.toString(prefixSum));
        int max = -987654321;

        for (int i = K; i <= N; i++) {
            max = Math.max(max,prefixSum[i]-prefixSum[i-K]);
        }
        System.out.println(max);
    }

}

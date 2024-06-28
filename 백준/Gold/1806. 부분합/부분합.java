import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,S;
    static long sumArr[],arr[] ;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new long[N+1];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        int start=0;
        int end =0;
        long sum=0;
        int ans = Integer.MAX_VALUE;
        while(start<= N && end <= N){ // 두 포인터가 범위 내
            if(sum>=S && ans > end-start){
                ans = end-start;
            }

            if(sum<S){
                sum+= arr[end++];
            }else{
                sum -= arr[start++];
            }

        }

        System.out.println((ans==Integer.MAX_VALUE)?0:ans);
    }


}

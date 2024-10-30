import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int s=0, e =n-1;
        long ans = Long.MAX_VALUE;
        while(s<e){// 같은 수끼리 더하면 안되니까 '<=' 말고 '<'
            long sum = (long)arr[s]+arr[e];

//            System.out.println("s : "+s+", e : "+e+", sum : "+sum);

            if(sum < 0){
                s++;
            }else{
                e--;
            }

            if(Math.abs(sum) < Math.abs(ans)){
                ans = sum;
            }

        }

        System.out.println(ans);
    }
}

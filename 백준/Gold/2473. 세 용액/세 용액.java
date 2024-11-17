import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long minValue = Long.MAX_VALUE;
    static int n,arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int[] ans = new int[3];

        // 3가지 용액의 합이 0에 가까운거.
        for (int i = 0; i < n; i++) {
            int cur = arr[i];
            int sIdx = i+1;
            int eIdx = n-1;

            while(sIdx < eIdx){

                long sum = (long) cur + arr[sIdx]+arr[eIdx];
//                System.out.println(sum+", "+minValue);
                if(Math.abs(sum) < minValue){
//                    System.out.println("안오나");
                    minValue = Math.abs(sum);
                    ans[0] = cur; ans[1] = arr[sIdx]; ans[2] = arr[eIdx];
                }

                if(sum < 0){
                    sIdx++;
                }else{
                    eIdx--;
                }

            }
        }
        Arrays.sort(ans);
        StringBuilder sb = new StringBuilder();
        for (int an : ans) {
            sb.append(an).append(" ");
        }
        System.out.println(sb);
        br.close();
    }
}

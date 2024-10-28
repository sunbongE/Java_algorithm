
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        if(N<2) {
            System.out.println(0);
            return;
        }

        Arrays.sort(arr);

        int cnt = 0;
        for (int i=0; i<N;i++) {
            if(isGood(0,N-1,arr[i],i,arr)){
//                System.out.println(arr[i]);
                cnt++;
            };
        }

        System.out.println(cnt);
    }

    // 두 수의 합이 v가 되는지 확인한다.
    private static boolean isGood(int s, int e, int v,int curIdx, int[] arr){

        while(s<e){
            if(s == curIdx ){
                s++;
            }else if(e == curIdx){
                e--;
            }
            if(s==e) return false;

            long sum = (long)arr[s]+arr[e];
//            System.out.println("sum : "+sum+" v : "+v);
            if(sum == v){
//                System.out.println(s+", "+e+", "+curIdx+", sum : "+sum+" v : "+v);
                return true;
            }else if (sum < v){
                // 목표값보다 작으면 아래를 키워
                s++;
            }else{
                e--;
            }
        }

        return false;
    }
}


import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
    static int n,S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        int[] data = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            data[i] =Integer.parseInt(st.nextToken());
        }
//        System.out.println(Arrays.toString(data));

        int s=0;
        int e=0;
        int cnt = Integer.MAX_VALUE;
        long sum=0;
        // 두 포인터가 범위에 있을때 반복.
        while(s<=n && e <= n){
            if(sum >= S && cnt > e-s){
                cnt = e-s;
            }
            if(sum<S){ // 값이 작으면 e가 있는 위치의 값을 sum에 더하고 다음 위치로 이동하낟.
                sum+=data[e++];
            }else{ // 값이 크면 s에 있는 위치를 sum에서 빼준다.
                sum-=data[s++];

            }
        }

        System.out.println((cnt==Integer.MAX_VALUE)?0:cnt);


    }

}

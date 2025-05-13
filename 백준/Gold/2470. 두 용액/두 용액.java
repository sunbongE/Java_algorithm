import java.io.*;
import java.util.*;

/**
 *
 * 3
 * 0
 * */
public class Main {
    static int N,arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  //      BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        long ans = (long) Integer.MAX_VALUE;
        Integer a=null,b=null;
        int s=0,e=N-1;
        while(s<e){
            long sum =(long)arr[s] + (long)arr[e];

            if( Math.abs(sum) < ans ) {
                ans = Math.abs(sum);
                a = arr[s];
                b = arr[e];
            }

            if(sum == 0 ){
                a = arr[s];
                b = arr[e];
                break;
            }else if ( sum < 0 ){
                s++;
            }else{
                e--;
            }
        }
        System.out.println(a+" "+b);

    }

}

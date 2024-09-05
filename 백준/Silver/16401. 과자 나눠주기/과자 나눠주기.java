import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M,arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        // 시작과 끝 범위 설정.
        int left = 1;
        int right = arr[M-1];


        while(left<=right){
            int mid = (left+right)/2;
            int cnt = isValid(mid);
            if(cnt >= N){
                left=mid+1;
            }else{
                right = mid-1;
            }
//            System.out.println(left+", "+right);
        }
        System.out.println(right);

    }

    private static int isValid(int num){
        int cnt=0;
        for (int target : arr) {
            cnt += target/num;
        }
        return cnt;
    }
}

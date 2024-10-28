
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N,C, arr[];
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int s = 0,e=arr[N-1],m;
//        int ans = 0 ;
        while(s<=e){
            m = (s+e)/2;

            int cnt = check(arr,m);
//            System.out.println("s : "+s+", e : "+e + ", m : "+m+", cnt : "+cnt);

            if(cnt < C){// 개수가 작으면 범위를 좁힌다.
                e = m-1;
            }else{

                s=m+1;
            }
        }

        System.out.println(s-1);
    }

    // 주어진 거리로 설치할 수 있는 공유기 개수를 반환하는 함수
    private static int check(int[] arr, int distance) {
        int cnt = 1;           // 첫 번째 집에 공유기를 설치하고 시작
        int cur = arr[0];      // 첫 설치 집 위치

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - cur >= distance) {  // 현재 집에서 distance 이상의 거리가 있으면
                cnt++;                       // 공유기 설치
                cur = arr[i];                // 마지막 설치 위치 갱신
            }
        }
        return cnt;
    }
}

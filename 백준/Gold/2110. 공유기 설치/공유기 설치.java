import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static int n,m,arr[],ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 //       BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());    // 집 개수
        m = Integer.parseInt(st.nextToken());    // 공유기 개수
        arr = new int[n];                       // 집 위치 배열

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        binarySearch();
        System.out.println(ans);
    }

    private static void binarySearch(){
        int distance = 1;
        int s = 1, e = arr[n-1]-arr[0];

        while(s<=e){
            int mid = (s+e)/2;

            // mid = 임시로 설치해볼 거리.
            int tmpCnt = getValidCnt(mid);

            if(tmpCnt<m){ // m개보다 적음 : 거리를 줄인다.
                e = mid-1;
            }else{ // m개보다 많음
                distance = mid; // 설치할 공유기 개수보다 많이 설치할 수 있는거는 노상관
                s = mid+1;
            }
        }
        ans = distance;
    }

    private static int getValidCnt(int mid) {
//        System.out.println("mid : "+mid);
        // 첫번째 집에 공유기 설치
        int cnt = 1;
        // 처음 시작위치.
        int cur = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if(arr[i]-cur>=mid){
//                System.out.println(arr[i]-cur);
                cur = arr[i];
                cnt++;
            }
        }
        return cnt;
    }
}

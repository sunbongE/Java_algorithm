import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int l=0; int maxL = 0;
        for (int i = 0; i < n; i++) {
            // 히스토 그램이 더 크면 계단 높이 올리기.
            if(l+1 <= A[i]){
                l++;
            }else{
                l = A[i];
            }
            // 작으면 계단 높이를 현재 히스토 그램 높이에 맞추기.


            maxL = Math.max(l,maxL);

        }
        System.out.println(maxL);



    }
}
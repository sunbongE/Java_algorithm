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

        int l=1; int maxL = 0;
        for (int i = 0; i < n; i++) {
            if(l > A[i]) l = A[i];
            else l++;
            maxL = Math.max(l,maxL);

        }
        System.out.println(maxL);



    }
}
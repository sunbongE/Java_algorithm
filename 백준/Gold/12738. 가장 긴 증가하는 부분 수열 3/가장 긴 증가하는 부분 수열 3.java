import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class Main {
    static int n,m,arr[],ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 //       BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n+1];

        ArrayList<Integer> li = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        li.add(arr[0]);
        for (int i = 1; i < n; i++) {
            int cur = arr[i];
            int idx = Collections.binarySearch(li,cur);
            if(idx < 0 ) {
                idx = -idx - 1;
            }

            if(li.size() == idx) li.add(cur);
            else li.set(idx,cur);
//            System.out.println(idx);

        }
        System.out.println(li.size());
        }
}

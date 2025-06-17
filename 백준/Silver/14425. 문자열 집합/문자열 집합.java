import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  //      BufferedReader br = new BufferedReader(new FileReader("test.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashSet<String> S = new HashSet<>();
        for (int i = 0; i < n; i++) {
            S.add(br.readLine());
        }

        int cnt=0;

        for (int i = 0; i < m; i++) {
            if(S.contains(br.readLine())) cnt++;
        }

        System.out.println(cnt);
    }
}

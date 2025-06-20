import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  //      BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        int testCase = Integer.parseInt(br.readLine());
        String[] data;
        StringBuilder ans = new StringBuilder();

        while(testCase-- > 0 ) {
            int n = Integer.parseInt(br.readLine());
            data = new String[n];

            for (int i = 0; i < n; i++) {
                data[i] = br.readLine();
            }

            boolean isYes = true;
            Arrays.sort(data);
//            System.out.println(Arrays.toString(data));
                for (int i = 0; i < n - 1; i++) {

                    if (data[i + 1].startsWith(data[i])) {
                        ans.append("NO");
                        isYes=false;
                        break;
                    }
                }

            if(isYes) ans.append("YES");
            ans.append("\n");
        }

        System.out.println(ans);
    }
}

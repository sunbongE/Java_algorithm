import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long sum = 0, xor = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case (1):
                    int addX = Integer.parseInt(st.nextToken());
                    operations(cmd,addX);
                    break;
                case (2):
                    int removeX = Integer.parseInt(st.nextToken());
                    operations(cmd,removeX);
                    break;
                case (3):
                    sb.append(sum);
                    sb.append("\n");
                    break;
                case (4):
                    sb.append(xor);
                    sb.append("\n");
                    break;

            }
        }
        System.out.println(sb);
    }

    private static void operations(int cmd, int x) {
        if (cmd == 1) {
            sum += x;
        } else {
            sum -= x;
        }
        xor ^= x;
    }


}

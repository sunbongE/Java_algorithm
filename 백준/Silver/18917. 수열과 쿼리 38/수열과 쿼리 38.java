import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {
    static long sum = 0, xor = 0;
    static List<Integer> nums = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        
        nums.add(0);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case (1):
                    int addX = Integer.parseInt(st.nextToken());
//                    addQuery(addX);
                    operations(cmd,addX);
                    break;
                case (2):
                    int removeX = Integer.parseInt(st.nextToken());
                    operations(cmd,removeX);
//                    removeQuery(removeX);
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

//    private static void addQuery(int x){
//        nums.add(x);
//    }
//    // M이 최대 50만 이니까 그냥 순회해서 지원도 시간초과안될듯
//    private static void removeQuery(int x){
//        for (int i = 0; i < nums.size(); i++) {
//            if(nums.get(i) == x){
//                nums.remove(i);
//                return;
//            }
//        }
//    }

}

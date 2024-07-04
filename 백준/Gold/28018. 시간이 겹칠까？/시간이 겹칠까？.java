import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

// 누적합.
public class Main {
      public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
//        int table[] = new int[10];
        int table[] = new int[1000010];

        StringTokenizer st;

        for (int i = 0; i<N;i++){
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            table[start] ++;
            table[end+1] --;
        }
//        System.out.println(Arrays.toString(table));

        for (int i = 1; i < table.length; i++) {
            table[i] += table[i-1];
//        System.out.println(table[i]);
        }
//        System.out.println(Arrays.toString(table));

        int Q =  Integer.parseInt(bf.readLine());

//        int[] queryArr = new int[Q];

        st = new StringTokenizer(bf.readLine());
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<Q;i++){
            int query = Integer.parseInt(st.nextToken());
            sb.append(table[query]+"\n");
            //            queryArr[i] = query;
        }
        System.out.println(sb);


    }
}

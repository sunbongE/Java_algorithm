import java.io.*;
import java.util.*;

/**
 *
 * 3
 * 0
 * */
public class Main {
    static int tc,cnt;
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  //       BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        tc = Integer.parseInt(br.readLine());
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < tc; t++) {
            int len = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[len];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(st.nextToken())-1;
            }

            cnt = len;
            boolean[] v = new boolean[len];

            for (int i = 0; i < len; i++) {
                if(v[i]) continue;
                ArrayList<Integer> tmp = new ArrayList<>();
                cnt-=dfs(arr,v,i,tmp);
            }

            sb.append(cnt).append("\n");
        }
        System.out.println(sb);

    }

    private static int dfs(int[] arr, boolean[] v,int cur, ArrayList<Integer> tmp) {
        v[cur]=true;
        tmp.add(cur);
        int next = arr[cur];
        if(!v[next]) {
            return dfs(arr,v,next,tmp);
        }else{
            if(!tmp.contains(next)) return 0;
            return tmp.size()-tmp.indexOf(next);
        }

    }

}

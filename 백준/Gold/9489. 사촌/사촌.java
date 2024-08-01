
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if(n==0&&k==0){
                break;
            }

            int targetIdx = 0;
            int[] arr = new int[n+1];
            int[] parent = new int[n+1];
            int parentNumber = -1;
            parent[0] = -1;
            arr[0] = -1;

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n;i++){
                arr[i] = Integer.parseInt(st.nextToken());
                if(arr[i] == k ) targetIdx = i;
                if(arr[i] != arr[i-1]+1) parentNumber++;
                parent[i] = parentNumber;
            }
            int answer = 0 ;
            for (int i = 1; i <=n; i++) {
                if(parent[i] != parent[targetIdx] && parent[parent[i]] == parent[parent[targetIdx]]){
                    answer++;
                }
            }
//            System.out.println("parent => "+ Arrays.toString(parent));
//            System.out.println("arr => "+ Arrays.toString(arr));
            sb.append(answer+"\n");
        }
        System.out.println(sb);
    }
}

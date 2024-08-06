
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<ArrayList<Integer>> adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        adjList = new ArrayList<>();
        for (int i=0;i<=n;i++){
            adjList.add(new ArrayList<>());
        }

        StringTokenizer st;


        int a,b;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        StringBuilder sb = new StringBuilder();

        int q = Integer.parseInt(br.readLine());
        int t,k;
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            if(t==1){ // 단절점이니
                if(adjList.get(k).size() >=2){
                    sb.append("yes\n");
                }else{
                    sb.append("no\n");
                }
            }else{  // 단절선이니
                sb.append("yes\n");

            }

        }
        System.out.println(sb);
    }
}

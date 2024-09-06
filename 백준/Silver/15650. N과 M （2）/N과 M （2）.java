import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M,combi[];
    static boolean[] isSelected;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        combi = new int[M];
        isSelected = new boolean[N+1];
        getCombination(0,1);

        System.out.println(sb);
    }

    private static void getCombination(int cnt,int num){
        if(cnt == M){
            for (int selected : combi) {
                sb.append(selected+" ");
            }
            sb.append("\n");
            return;
        }

        if(num<=N){

            combi[cnt] = num;
            getCombination(cnt+1,num+1);
            getCombination(cnt,num+1);


        }

    }
}

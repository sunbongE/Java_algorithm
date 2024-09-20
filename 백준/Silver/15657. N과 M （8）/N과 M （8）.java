import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int M,N,result[];
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] data = new int[N];
        for(int i=0;i<N;i++){
            data[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(data);
        result = new int[M];
        getDuplicationPermutation(data,0);
        System.out.println(sb);

    }

    private static void getDuplicationPermutation(int[] data,int cnt){
        if(cnt==M){
//            System.out.println(Arrays.toString(result));
            if(isValid(result)){
                for (int num : result) {
                    sb.append(num+" ");
                }
                sb.append("\n");
            }
            return;
        }

        for (int idx = 0; idx < N; idx++) {
            result[cnt] = data[idx];
            getDuplicationPermutation(data,cnt+1);
        }

    }

    // 비내림차순 검증.
    private static boolean isValid(int[] permutation){
        if (permutation.length <= 1) return true;
        for (int i = 1; i < M; i++) {
            if(permutation[i-1]>permutation[i]) return false;
        }

        return true;
    }
}

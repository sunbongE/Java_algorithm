import java.io.*;
import java.util.*;
/**
 * n 개의 차는 오름차순의 위치로 나온다.
 * 그래서 i번 위치 차가 이동할 수 있는 위치 범위에 있는지 확인하는 것이 필요한데
 * i-1위치의 값과 X[i] + H[i] 혹은 X[i] - H[i] 이게 범위내에 있는지 확인..
 *
 * */
public class Main {
    static int n, m, parents[]; // 각 원소의 부모원소 저장.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 //       BufferedReader br = new BufferedReader(new FileReader(new File("./test.txt")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());    // 원소 마지막 번호
        m = Integer.parseInt(st.nextToken());    // 연산 횟수

        parents = initParents();
        StringBuilder sb = new StringBuilder();

        for (int w = 0; w < m; w++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(command == 0){
                union(a,b);
            }else{
                if(find(a) == find(b)) sb.append("yes\n");
                else sb.append("no\n");
            }
        }
        System.out.println(sb);
    }

    // 두 집합 합치기.
    private static boolean union(int a, int b){
        int parentA = find(parents[a]);
        int parentB = find(parents[b]);
        if(parentA == parentB) return false;
        parents[parentB] = parentA;

        return  true;
    }

    private static int find(int c){
        if(parents[c] == c) return c;
        return parents[c]=find(parents[c]);
    }

    private static int[] initParents() {
        int[] result = new int[n+1];
        for (int i = 0; i < result.length; i++) {
            result[i]=i;
        }
        return result;
    }
}

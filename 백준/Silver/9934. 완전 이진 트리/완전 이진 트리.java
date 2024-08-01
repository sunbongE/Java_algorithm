import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static ArrayList<ArrayList<Integer>> tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int k = Integer.parseInt(br.readLine());
        int size =(int) Math.pow(2,k)-1;
        arr = new int[size];

        tree = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        build(0,size-1,0);

//        System.out.println(tree);
        for (ArrayList<Integer> integers : tree) {
            for (Integer data : integers) {
                sb.append(data+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void build(int start, int end, int depth) {
        if(start>end) return;

        int mid = (start+end)/2;
        tree.get(depth).add(arr[mid]);

        // 왼쪽
        build(start,mid-1,depth+1);
        // 오른쪽
        build(mid+1,end,depth+1);
    }
}

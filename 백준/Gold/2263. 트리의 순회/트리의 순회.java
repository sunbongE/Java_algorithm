
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, inorder[], postorder[], inorderIdx[];
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        inorder = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        postorder = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        // inorder index
        inorderIdx = new int[n + 1]; // 수의 인덱스 정보를 기록
        for (int i = 1; i <= n; i++) {
            inorderIdx[inorder[i]] = i;
        }

//        System.out.println(Arrays.toString(inorderIdx));

        sb = new StringBuilder();
        sol(1, n, 1, n);
        System.out.println(sb);
    }

    private static void sol(int is, int ie, int ps, int pe) {
        if (is > ie || ps > pe) return;
        int root = postorder[pe]; // 후위 순회의 마지막 값은 트리의 루트이다.
        int rootIdx = inorderIdx[root]; // 루트의 인덱스
        sb.append(root).append(" ");    // 루트 먼저 출력

        int sizeL = rootIdx - is; // 왼쪽 트리의 크기

        sol(is, rootIdx - 1, ps, ps + sizeL - 1);
        sol(rootIdx+1,ie,ps+sizeL,pe-1);

    }
}

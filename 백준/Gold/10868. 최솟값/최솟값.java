
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static long[] segmentTree;
    static long[] inputArray;
    static int treeSize;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inputArray = new long[N];
        for (int i = 0; i < N; i++) {
            inputArray[i] = Long.parseLong(bf.readLine());
        }

        // 트리의 높이 구하기 N보다 크거나 같은 가장 작은 2의 제곱수를 찾음
        int k = 0;
        while (Math.pow(2, k) < N) {
            k++;
        }
        // 트리 크기 구하기
        treeSize = (int) Math.pow(2, k) * 2;

        // 트리 구축
        segmentTree = new long[treeSize];
        buildSegmentTree(1, 0, N - 1);
//        System.out.println(Arrays.toString(segmentTree));
        // 쿼리 처리
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            sb.append(query(1, 0, N - 1, a, b)).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static long buildSegmentTree(int node, int start, int end) {
        if (start == end) {
            return segmentTree[node] = inputArray[start];
        }

        int mid = (start + end) / 2;
        long leftMin = buildSegmentTree(node * 2, start, mid);
        long rightMin = buildSegmentTree(node * 2 + 1, mid + 1, end);

        return segmentTree[node] = Math.min(leftMin, rightMin);
    }

    private static long query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return Long.MAX_VALUE;
        }
        if (left <= start && end <= right) {
            return segmentTree[node];
        }

        int mid = (start + end) / 2;
        long leftMin = query(node * 2, start, mid, left, right);
        long rightMin = query(node * 2 + 1, mid + 1, end, left, right);

        return Math.min(leftMin, rightMin);
    }
}

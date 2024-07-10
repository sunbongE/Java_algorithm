import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * query
 * 1 i v : i번째를 v로 바꾼다.
 * 2 : 가장 작은 값의 인덱스를 출력한다. 여러개인 경우 인덱스가 작은 것을 출력
 *
 * data structure
 * segment tree
 */
public class Main{
    static int[][] segment; // 세그먼트 트리 배열
    static int N; // 입력 배열의 크기

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // 입력 배열의 크기 N을 읽어들임
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());

        // 세그먼트 트리의 크기를 결정
        int k = 0;
        while(Math.pow(2, k) < N){
            k++;
        }

        // 세그먼트 트리의 실제 크기 계산
        int treeSize = (int) Math.pow(2, k + 1);
        segment = new int[treeSize][2]; // [[value1, index1], [value2, index2]]

        // 입력값을 배열에 저장
        int[] inputNums = new int[N];
        for (int i = 0; i < N; i++) {
            inputNums[i] = Integer.parseInt(st.nextToken());
        }

        // 세그먼트 트리 구축
        buildMinSegment(1, 0, N - 1, inputNums);

        StringBuilder sb = new StringBuilder();

        // 쿼리 수 M을 읽어들임
        int M = Integer.parseInt(bf.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            String queryType = st.nextToken();
            if(queryType.equals("1")){
                // 쿼리 타입이 1인 경우: i번째 값을 v로 바꿈
                int idx = Integer.parseInt(st.nextToken()) - 1; // 1-based index이므로 0-based로 변환
                int val = Integer.parseInt(st.nextToken());
                update(1, 0, N - 1, idx, val);
//                System.out.println("==================================");
//                showTree(segment);
            } else {
                // 쿼리 타입이 2인 경우: 가장 작은 값의 인덱스를 출력
                sb.append(segment[1][1] + 1).append("\n"); // 0-based index를 1-based로 변환하여 출력
            }
        }

        // 최종 결과 출력
        System.out.print(sb.toString());
    }

    // 세그먼트 트리에서 idx 위치의 값을 val로 변경하고 부모 노드를 다시 업데이트
    private static void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            // 리프 노드인 경우 해당 노드의 값을 업데이트
            segment[node][0] = val;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) {
                // 변경할 인덱스가 왼쪽 자식 범위에 있는 경우
                update(node * 2, start, mid, idx, val);
            } else {
                // 변경할 인덱스가 오른쪽 자식 범위에 있는 경우
                update(node * 2 + 1, mid + 1, end, idx, val);
            }
            // 자식 노드 값을 비교하여 부모 노드 값을 업데이트
            int[] left = segment[node * 2];
            int[] right = segment[node * 2 + 1];
            if (left[0] == right[0]) {
                segment[node] = (left[1] < right[1]) ? left : right;
            } else if (left[0] < right[0]) {
                segment[node] = left;
            } else {
                segment[node] = right;
            }
        }
    }

    // 세그먼트 트리 구축
    private static int[] buildMinSegment(int node, int start, int end, int[] inputNums) {
        if (start == end) { // 리프 노드인 경우
            segment[node][0] = inputNums[start];
            segment[node][1] = start;
            return segment[node];
        }

        int mid = (start + end) / 2;
        int[] leftMin = buildMinSegment(node * 2, start, mid, inputNums);
        int[] rightMin = buildMinSegment(node * 2 + 1, mid + 1, end, inputNums);

        // 자식 노드 값을 비교하여 부모 노드 값을 설정
        if (leftMin[0] == rightMin[0]) { // 값이 같으면 인덱스가 작은 쪽 선택
            segment[node] = (leftMin[1] < rightMin[1]) ? leftMin : rightMin;
        } else if (leftMin[0] < rightMin[0]) {
            segment[node] = leftMin;
        } else {
            segment[node] = rightMin;
        }
        return segment[node];
    }
    private static void showTree(int[][] tree){
        for (int[] ints : tree) {
            System.out.println(Arrays.toString(ints));
        }
    }
}

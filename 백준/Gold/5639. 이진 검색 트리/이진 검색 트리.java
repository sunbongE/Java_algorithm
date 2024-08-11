import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] tree; // 이진 검색 트리의 값을 저장할 배열
    static final int treeSize = 10001; // 트리 배열의 최대 크기 (10000 + 1)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        tree = new int[treeSize]; // 트리 배열을 초기화

        int idx = 0; // 트리에 저장할 인덱스를 초기화
        while (true) {
            String inp = br.readLine(); // 입력을 한 줄씩 읽어옴
            if (inp == null  || inp.equals("")) break; // 빈 줄이 입력되면 종료
            int val = Integer.parseInt(inp); // 입력된 값을 정수로 변환
            tree[idx++] = val; // 트리에 값을 저장하고 인덱스를 증가시킴
        }

        // 후위 순회(post-order traversal)를 시작 (트리의 루트 노드부터 끝까지)
        postOrder(0, idx-1);

    }

    // 후위 순회 메서드 (start: 시작 인덱스, end: 끝 인덱스)
    private static void postOrder(int start, int end) {
        // 시작 인덱스가 끝 인덱스보다 크면 종료 (탐색 범위를 벗어남)
        if (start > end) return;

        int mid = start + 1; // 중간 지점을 설정 (루트 다음부터 시작)

        // 루트의 오른쪽 서브트리를 찾기 위해 탐색
        while (mid <= end && tree[mid] < tree[start]) {
            mid++;
        }

        // 왼쪽 서브트리에 대해 후위 순회
        postOrder(start+1, mid-1);
        // 오른쪽 서브트리에 대해 후위 순회
        postOrder(mid, end);
        // 루트 노드를 출력 (후위 순회이므로 서브트리들을 모두 방문한 후에 출력)
        System.out.println(tree[start]);
    }
}

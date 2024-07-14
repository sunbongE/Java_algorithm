import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  세그먼트 트리에 들어갈 값을 인덱스로 할거임.
 *
 */
public class Main {

    static int[] tree, inputArr;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());


        inputArr = new int[N+1];
        inputArr[0]=INF;
        tree = new int[N*4];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= N; i++) {
            inputArr[i] = Integer.parseInt(st.nextToken());
        }

        buildTree(1,1,N);
//        System.out.println(Arrays.toString(tree));
//        System.out.println(Arrays.toString(inputArr));


        int M = Integer.parseInt(bf.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(cmd == 1){ // a번째를 v로 변경.
                inputArr[a] = b;
                changeQuery(a,b,1,1,N);
            }else{ // a~b 범위에서 최소인거 인덱스 가져오기.
                int result = getMinQuery(1,1,N,a,b);
                sb.append(result+"\n");
            }
        }
        System.out.println(sb);
    }

    private static int getMinQuery(int node, int start, int end, int left, int right) {
        // 범위 벗어남.
        if( right < start || left > end) return 0;
        // 범위 내에 있는 경우.
        if(left <= start && end <= right) return tree[node];
        int mid = (start+end)/2;
        int leftIdx = getMinQuery(node*2, start, mid,left,right);
        int rightIdx = getMinQuery(node*2+1, mid+1,end,left,right);

        return merge(leftIdx,rightIdx);

    }

    private static int changeQuery(int idx, int v, int node, int start, int end) {
        if(start > idx || idx > end){ // 범위 밖은 넘어감.
            return tree[node];
        }

        if(start == end ){ // 변경하려는 위치 찾음. 값이 아닌 인덱스를 넣어야하는데 인덱스는 변화가 없기 때문에 이게 필요없게 느껴짐
            return tree[node] = idx;
        }

        int mid = (start+end)/2;
        int leftIdx = changeQuery(idx, v, node*2, start, mid);
        int rightIdx = changeQuery(idx, v, node*2+1,mid+1,end);

        return tree[node] = merge(leftIdx,rightIdx);
    }

    private static int buildTree(int node, int start, int end) {

        // 리프 노드일때 값 갱신.
        if(start == end){
            return tree[node] = start; // 리프노드에 인덱스를 넣은거임.
        }

        int mid = (start+end)/2;

        int leftIdx = buildTree(node*2,start,mid);
        int rightIdx = buildTree(node*2+1,mid+1,end);

        return tree[node] = merge(leftIdx,rightIdx);
    }

    // 더 작은 값 인덱스 반환.
    private static int merge(int leftIdx, int rightIdx){
        if(inputArr[leftIdx] == inputArr[rightIdx]){
            return Math.min(leftIdx, rightIdx);
        }
        return (inputArr[leftIdx] < inputArr[rightIdx]) ? leftIdx:rightIdx;
    }
}

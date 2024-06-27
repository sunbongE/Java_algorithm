import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 박태호
 * 
 *         <pre>
 * 	풀이
 * 	세그먼트 트리 사용
 * 	
 * 	업데이트가 빈번하고 이렇게 수가 많은 경우 세그먼트 트리의 구간합 특성을 이용하여 
 * 	보다 빠르게, 적은 연산으로 값을 수정하고 찾아낼 수 있다.
 * 	
 * 	[트리생성]
 * 	1. 트리초기화
 * 	2. 트리의 크기 : 2^k * 2
 * 	3. k 구하기: 2^k >= N
 * 	4. 시작 인덱스 구하기 : 2^k
 *  5. 트리에 값 삽입. 
 *  6. 시작 인덱스에 주어진 값을 차례대로 삽입.
 *  7. 1~2^k 까지는 구간합을 가진 노드를 표현한다.
 *  8. 방법: tree[index/2]+=tree[index] => index/2 == 0이면 멈춤.
 *  
 *  [구간합 구하기]
 *  0. 시작하는 인덱스와 끝나는 인덱스 구하기.
 *  1. (찾고싶은 인덱스 + 2^k-1) :  찾고싶은 인덱스 == 시작인덱스 or 끝인덱스
 *  2. 구간합을 가진 노드. (주어진 값 + 2^k-1) % 2 == 0;
 *  3. 시작인덱스가 왼쪽 값이면 바로 부모 노드로 이동.
 *  3-1. 오른쪽 값이면 현재 값을 킵하고 (시작인덱스+1)/2 옆칸에 있는 부모인덱스로 이동.
 *  4. 끝 인덱스가 오른쪽 값이면 : 끝 인덱스/2
 *  4-1. 왼쪽 값이면 현재 값 선택, => 끝인덱스-1 /2 옆집의 부모 인덱스로 이동.
 *  5. 시작인덱스와 끝 인덱스가 같다면 멈추게된다.
 *  6. 선택된 값과 부모 인덱스를 모두 합해주면 답이된다.
 *  
 *  
 *  [업데이트하기]
 *  0. a가 1인경우 b번째 값을 c으로 변경한다.
 *  1. b번째 인덱스 구하기 : b+2^k-1
 *  2. b번째 값을 c으로 변경
 *  3. 부모 노드로 이동하면서 차이를 더해줘 구간합을 다시 정의 : diff == 변경될 값 - 이전값
 *  3-1. tree[index] += diff 
 *  4. 0이면 종료 
 * 
 *         </pre>
 *
 */
public class Main {
    static int N, M, K, start;
    static long segmentTree[];

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 트리 크기 구하기.
        int k = 0;
        while (Math.pow(2, k) <= N) {
            k++;
        }
        int treeSize = (int) Math.pow(2, k) * 2;
        segmentTree = new long[treeSize];

        // 시작 인덱스
        start = (int) Math.pow(2, k);
        long in;
        for (int i = 0; i < N; i++) {
            in = Long.parseLong(bf.readLine());
            segmentTree[start + i] = in;
        }

        // 트리 세팅 1~시작 인덱스 전까지 구간 합 삽입.
        treeInit();
        // 변경 횟수.
        int L = M + K;
        int b,a;
        long  c;
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(bf.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Long.parseLong(st.nextToken());
            if (a == 1) {// 값을 변경.
                update(b, c);
            } else { // 구간 합 출력.
                long result = sum(b, (int) c);
                sb.append(result).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void update(int index, long num) {
        // 변경할 인덱스 구하기
        index = index + start - 1;
        num -= segmentTree[index];
//        segmentTree[index] = num;
        while (index != 0) {
            // 부모의 구간 합 재설정.
            segmentTree[index] += num;
            index /= 2;
        }
    }

    // 구간 합 구하기.
//    private static long sum(int startIdx, int endIdx) {
//        long sum = 0;
//        // 시작 인덱스 구하기.
//        startIdx = startIdx + start - 1;
//        // 끝 인덱스 구하기.
//        endIdx = endIdx + start - 1;
//
//        // 시작과 끝 인덱스가 같으면 멈춘다.
//        while (!(startIdx == endIdx)) {
//            // 시작 인덱스 %2가 0이면 선택 없이 부모 노드로 간다.
//            if (startIdx % 2 == 0) {
//                startIdx /= 2;
//            } else {
//                // 아니면 현재 값을 sum에 더해주고 옆집 부모로 이동한다.
//                // 현재값 선택
//                sum += segmentTree[startIdx];
//                // 오른쪽 옆집 부모 노드로 이동.
//                startIdx = (startIdx + 1) / 2;
//            }
//
//            if (endIdx % 2 == 1) {
//                // 끝 인덱스 %2가 1이면 선택 없이 부모 노드로 간다.
//                endIdx /= 2;
//            } else {
//                // 아니면 현재 값을 sum에 더해주고 옆집 부모로 이동한다.
//                sum += segmentTree[endIdx];
//                endIdx = (endIdx - 1) / 2;
//            }
//        }
//        sum += segmentTree[endIdx];
//        return sum;
//    }
    private static long sum(int startIdx, int endIdx) {
        long sum = 0;
        
        startIdx = startIdx + start - 1; // 시작인덱스
        endIdx = endIdx + start - 1;

        while (startIdx < endIdx) {
            if (startIdx % 2 == 1) {
                sum += segmentTree[startIdx];
                startIdx++;
            }
            if (endIdx % 2 == 0) {
                sum += segmentTree[endIdx];
                endIdx--;
            }
            startIdx /= 2;
            endIdx /= 2;
        }
        if(startIdx == endIdx)sum += segmentTree[endIdx];

        return sum;
    }

    // 트리 구간 합 세팅.
    private static void treeInit() {
    	// 부모로 올라가면서 구간합을 세팅한다. 14,15를 각각 2로 나누면 7으로 부모가 같다.
        for (int i = segmentTree.length - 1; i / 2 > 0; i--) {
            segmentTree[i / 2] += segmentTree[i];
        }
    }
}

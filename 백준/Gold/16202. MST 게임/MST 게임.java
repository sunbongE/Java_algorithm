import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * kruskal알고리즘으로 풀 예정(간선중심)
 * 1. 간선객체 생성. Edge
 * 2. union find 구현
 * 3. 입력, edgeList.add(간선정보)
 * 4. 간선의 비용 오름차순 정렬 => 생략.
 * 5. make();
 * 6. 정점수 - 1 번
 * 7. union(from,to) => true라면 비용 누적합, count ++ ;
 * 8. if(++count == 간선수-1) break;
 * 9. 위 과정을
 */
public class Main {

    // 1
    private static class 간선 implements Comparable<간선> {
        int from, to, 가중치;

        public 간선(int from, int to, int 가중치) {
            this.from = from;
            this.to = to;
            this.가중치 = 가중치;
        }

        @Override
        public int compareTo(간선 o) {
            return Integer.compare(this.가중치, o.가중치); // asc
        }


    }

    static int N, M, K, parents[];
    static 간선[] edgeArr;
//    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        edgeArr = new 간선[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            // 간선정보 추가.
            edgeArr[i] = new 간선(from, to, i+1);
        }

        StringBuilder sb = new StringBuilder();


        int k = 0;
        while (k< K) {
            long result = 0;
            int count = 0;
            // mst구하기.
            make(N); // 5
            for (간선 edge : edgeArr) {
                if (edge == null) continue;

                if (union(edge.from, edge.to)) {
                    result += edge.가중치;
                    if (++count == N - 1) {
                        break;
                    }
                }
            }

            // count != M 스패닝트리가 안되는것.
            if (count != N - 1) {
                for (int j = 0; j < (K - k ); j++) {
                    sb.append("0 ");
                }
                break;
            }

            // 가장 작은 가중치 간선 제거.
            edgeArr[k] = null;


            sb.append(result + " ");
            k++;
        }

        System.out.println(sb);

    }

    // 2 구현.
    private static void make(int n) {
        parents = new int[n + 1];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    private static int find(int v) {
        if (parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

}

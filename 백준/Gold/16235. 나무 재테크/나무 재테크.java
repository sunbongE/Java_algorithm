import java.io.*;
import java.util.*;

/**
 * 봄
 * 나무는 위치 중복 허용.
 * 같은 위치의 나무 중 나이가 어린 나무부터 토지 양분을 나이만큼 흡수하고 나이가 1 증가
 * 나이만큼 양분을 못먹으면 죽는다.
 *
 * 여름
 * 죽은 나무는 토지에 양분이 된다. 양분 = 나이 / 2
 *
 * 가을
 * 나이가 5배수인 나무는 인접한(8방향)에 나이 1짜리 나무가 생긴다.
 *
 * 겨울
 * 양분을 추가한다. 각 칸에 추가되는 양분의 양은 A[r][c]로 입력받을거임.
 *
 * k년 지난 후 살아있는 나무의 수를 구해라.
 *
 *
 * 시뮬레이션
 * - bfs -> while > for(q.size()) => 이런식으로 년을 구분할 수 있다.
 * - - 그럼 while 조건이 k-- > 0 이거임.
 * - bfs에서 사용되는 큐는 우선순위큐를 사용한다.PriorityQueue<>()
 *
 * */
public class Main {
    // 8방향
    static int[] di = {-1,-1,-1,0,0,1,1,1};
    static int[] dj = {-1,0,1,-1,1,-1,0,1};
    static int n,m,k,A[][], land[][];
    static PriorityQueue<Tree> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //    BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        land = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(land[i],5);
        }
        // 채워넣을 양분 정보
        A = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 단일인터페이스 람다로 정의
        pq = new PriorityQueue<>((a,b)->{
            return a.age-b.age;
        });

        // 나무 입력.
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int ti = Integer.parseInt(st.nextToken())-1;
            int tj = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());
            pq.offer(new Tree(ti,tj,age));
        }

        while(k-- > 0){
            ArrayList<Tree> tmp = new ArrayList<>();

            // 봄 : 나이먹기
            int len = pq.size();
            for (int i = 0; i < len; i++) {
                Tree tree = pq.poll();
                if(land[tree.ci][tree.cj] < tree.age){ // 양분못먹으면 죽음
                    tree.isDie = true;
                }else{ // 양분 먹이기
                    land[tree.ci][tree.cj] -= tree.age;
                    tree.age+=1;
                }
                tmp.add(tree);
            }
            // 여름 : 죽은 나무가 양분으로 변함. += 나이/2
            for(Tree tree : tmp){
                if(tree.isDie){
                    land[tree.ci][tree.cj] += tree.age/2;
                }
            }

            // 가을 : 나이가 5배수인 나무 주변에 1짜리 나무가 생긴다.
            for(Tree tree : tmp){
                if(tree.isDie) continue;
                pq.offer(tree);
                if(tree.age % 5 != 0) continue;
                int ci = tree.ci;
                int cj = tree.cj;
                for (int i = 0; i < 8; i++) {
                    int ni = ci + di[i];
                    int nj = cj + dj[i];
                    if(isOut(ni,nj)) continue;
                    pq.offer(new Tree(ni,nj,1));
                }
            }
            // 겨울 : 땅에 양분을 추가한다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    land[i][j] += A[i][j];
                }
            }

        }

        System.out.println(pq.size());

    }

    private static boolean isOut(int ni, int nj) {
        return 0 > ni || 0 > nj || ni >= n || nj >= n;
    }


    static class Tree{
        int ci,cj,age;
        boolean isDie; //  죽은 나무인지, 이미 양분이 된건지
        public Tree(int ci, int cj, int age){
            this.ci = ci;
            this.cj = cj;
            this.age = age;
        }
    }


}

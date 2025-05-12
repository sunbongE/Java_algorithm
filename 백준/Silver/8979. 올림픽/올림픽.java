import java.io.*;
import java.util.*;

public class Main {
    static int N,K;
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        PriorityQueue<Ctry> pq = new PriorityQueue<>(
                (a,b) -> {
                    if(a.G != b.G ) return b.G- a.G;
                    else if(a.S != b.S) return b.S - a.S;
                    else if(a.D != b.D) return b.D - a.D;
                    else return 0;
                }
        );

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int C,G,S,D;
            C = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            pq.offer(new Ctry(C,G,S,D));
        }

        int no=1;
        Ctry before = null;
        while(!pq.isEmpty()){
            Ctry cur = pq.poll();
            if(before != null && isSameScore(before,cur)) cur.no = before.no; // 메달 동일
            else{ // 다른 경우
                cur.no = no;
            }

            no++;
            before = cur;
            if(cur.C == K ) {
                System.out.println(cur.no);
                break;
            }

        }

    }

    private static boolean isSameScore(Ctry before, Ctry cur) {
        if(before.G != cur.G || before.S != cur.S || before.D != cur.D  ) return false;
        return true;
    }

    static class Ctry{
        int C;
        int G,S,D;
        int no;
        public Ctry(int C,int G, int S, int D){
            this.C=C;
            this.G=G;
            this.S=S;
            this.D=D;
        }


         
    }
}

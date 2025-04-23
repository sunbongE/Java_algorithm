import java.io.*;
import java.util.*;
/**
 * D: D 는 n을 두 배로 바꾼다. 결과 값이 9999 보다 큰 경우에는 10000 으로 나눈 나머지를 취한다. 그 결과 값(2n mod 10000)을 레지스터에 저장한다.
 * S: S 는 n에서 1 을 뺀 결과 n-1을 레지스터에 저장한다. n이 0 이라면 9999 가 대신 레지스터에 저장된다.
 * L: L 은 n의 각 자릿수를 왼편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d2, d3, d4, d1이 된다.
 * R: R 은 n의 각 자릿수를 오른편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d4, d1, d2, d3이 된다.
 * */
public class Main {
    static int t,ans=0, MOD = 10000;
    public static void main(String[] args) throws IOException {

 //       BufferedReader br = new BufferedReader(new FileReader("test.txt")); // 입력 파일 열기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(bfs(a,b));
            if(t!=0) sb.append("\n");
        }
        System.out.println(sb);


    }

    private static String bfs(int a, int b) {
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(a,""));
        boolean[] v = new boolean[10001];
        //v[0]=true;
        v[a]=true;
        //int cnt=0;
        while (!q.isEmpty()){
            Info cur = q.poll();
//            System.out.println(cur.val);
          //  System.out.println(cnt++);
            // 정답 체크
            if(cur.val == b) return cur.cmd;
            // 4가지 방법 다 적용해보기

            // 2배하고 10000로 나눈 나머지.
            int D = cur.val*2%MOD;
            if(!v[D]){
                v[D]=true;
                q.offer(new Info(D,cur.cmd+"D"));
            }

            // -1
            int S = cur.val == 0? 9999 : cur.val-1;
            if(!v[S]){
                v[S]=true;
                q.offer(new Info(S, cur.cmd+"S"));
            }

            // 1234 -> 2341
            int L = cur.val%1000*10+cur.val/1000;
            if(!v[L]){
                v[L]=true;
                q.offer(new Info(L, cur.cmd+"L"));
            }

            // 1234 -> 4123
            int R = cur.val % 10*1000+cur.val/10;
            if(!v[R]){
                v[R]=true;
                q.offer(new Info(R, cur.cmd+"R"));
            }



        }


        return null;
    }

    static class Info{
        int val;
        String cmd="";
        public Info(int val, String cmd){
            this.val = val;
            this.cmd = cmd;
        }
    }
}

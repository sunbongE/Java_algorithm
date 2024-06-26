/**
 * 상하좌우 4방향으로 이동할 수 있다
 * 하지만 다른 문제와 다르게 각각의 구슬은 벽에 부딪히기 전까지 계속
 * 굴러가야한다.
 * <p>
 * 조건
 * 1. 10번 이내면 값 출력 아니면 -1
 * 2. 빨간 구슬만 구멍으로 가야함.
 * 3. 파란구슬이 빠지면 -1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M,oi=-1,oj=-1,ans=Integer.MAX_VALUE;
    // 상 우 하 좌
    static int[] di = {-1, 0,1, 0};
    static int[] dj = {0, 1, 0, -1};
    static boolean isInBlue = false, isInRed=false;
    static char matrix[][], copyMatrix[][] ;

    private static class Ball {
        int ci, cj, commandCnt;
        boolean kind;

        public Ball(boolean kind, int ci, int cj, int commandCnt) {
            this.kind = kind;
            this.ci = ci;
            this.cj = cj;
            this.commandCnt = commandCnt;
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new char[N][M];

        // 빨간, 파란 구슬위치
        int ri = -1, rj = -1, bi = -1, bj = -1;
        // 목표지점 지점 위치


        for (int i = 0; i < N; i++) {
            String inp = bf.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = inp.charAt(j);
                switch (matrix[i][j]) {
                    case 'R':
                        ri = i;
                        rj = j;
                        matrix[i][j]='.';
                        break;
                    case 'B':
                        bi = i;
                        bj = j;
                        matrix[i][j]='.';
                        break;
                    case 'O':
                        oi = i;
                        oj = j;
                        break;
                    default:
                        break;
                }
            }
        }

        ans = bfs(oi, oj, ri, rj, bi, bj);
        System.out.println((ans==Integer.MAX_VALUE)? -1 : ans);
    }

    private static int bfs(int oi, int oj, int ri, int rj, int bi, int bj) {

        int commandCount = 0;
        Queue<Ball> redQ = new LinkedList<>();
        Queue<Ball> blueQ = new LinkedList<>();

        // 방문은 두 색깔로 나눠서 체크하는데 맞는거같아.
        // 3차원까지 갈필요없이 int값으로 기억한다.
        boolean[][][][] visited = new boolean[N][M][N][M];

        redQ.offer(new Ball(true, ri, rj, commandCount)); //  빨
        blueQ.offer(new Ball(false, bi, bj, commandCount)); // 파


        while (!blueQ.isEmpty() || !redQ.isEmpty()) {
            // 4방향으로 움직였으면 개수를 올린다.

            Ball curRed = redQ.poll();
            Ball curBlue = blueQ.poll();

            for (int dir = 0; dir < 4; dir++) {
                isInRed=false;
                isInBlue=false;
                // 여기서 안가도되는 방향을 찾아주면 될거같음

                copyMatrix = copy();
                Ball movedRed = null;
                Ball movedBlue = null;
                // 방향을 정해서 움직여본다.
                // 어떤 공을 먼저 움직일지 결정한다.
                if(dir ==0 ){ // 위로 움직이는 경우는 두 공 중에 더 위에 있는 공을 먼저 움직인다.
                    if(curRed.ci < curBlue.ci){
                        movedRed = oneMove(curRed,dir);
                        movedBlue = oneMove(curBlue,dir);
                    }else{
                        movedBlue = oneMove(curBlue,dir);
                        movedRed = oneMove(curRed,dir);
                    }
                } else if (dir == 1) { // 오른쪽으로 움직이는건 더 오른쪽에 있는애가 먼저 움직인다.
                    if(curRed.cj > curBlue.cj){
                        movedRed = oneMove(curRed,dir);
                        movedBlue = oneMove(curBlue,dir);
                    }else{
                        movedBlue = oneMove(curBlue,dir);
                        movedRed = oneMove(curRed,dir);
                    }
                }else if (dir == 2) { // 아래로 움직이는건 더 아래에 있는애가 먼저 움직인다.
                    if(curRed.ci > curBlue.ci){
                        movedRed = oneMove(curRed,dir);
                        movedBlue = oneMove(curBlue,dir);
                    }else{
                        movedBlue = oneMove(curBlue,dir);
                        movedRed = oneMove(curRed,dir);
                    }
                }else{ // 왼쪽으로 움직이는건 더 왼쪽에 있는애가 먼저 움직인다.
                    if(curRed.cj < curBlue.cj){
                        movedRed = oneMove(curRed,dir);
                        movedBlue = oneMove(curBlue,dir);
                    }else{
                        movedBlue = oneMove(curBlue,dir);
                        movedRed = oneMove(curRed,dir);
                    }
                }

                if (movedRed.commandCnt>10 || movedBlue.commandCnt>10) return ans;

//                System.out.println("=================="+movedBlue.commandCnt+", "+dir+"==================");
//                showM(copyMatrix);
//                System.out.println("red : "+redV[movedRed.ci][movedRed.cj][dir]);
//                System.out.println("blue : "+blueV[movedBlue.ci][movedBlue.cj][dir]);

                // 공이 빠진거있는지 확인해본다.
                if (isInRed&& !isInBlue) {
                    return Math.min(movedRed.commandCnt,ans);
                }

                //  둘중하나라도 방문이 0이면 넣고 방문체크
                if(!visited[movedRed.ci][movedRed.cj][movedBlue.ci][movedBlue.cj]&&!isInBlue){
//                    redV[movedRed.ci][movedRed.cj][dir] = movedRed.commandCnt;
//                    blueV[movedBlue.ci][movedBlue.cj][dir] = movedBlue.commandCnt;
                    visited[movedRed.ci][movedRed.cj][movedBlue.ci][movedBlue.cj]=true;
                    redQ.offer(movedRed);
                    blueQ.offer(movedBlue);
//                    System.out.println("넣었따.");
                }
            }




        }

        return -1;
    }

    // 움직인 공을 반환한다. 하지만 안움직였으면 반환안해. **안움직여도 반환해야함**
    private static Ball oneMove(Ball curBall, int dir) {
        int ci = curBall.ci;
        int cj = curBall.cj;

        while(true){
//            System.out.println("ci, cj = "+ci+", "+cj+" dir==>  "+dir);
            int ni = ci+di[dir];
            int nj = cj+dj[dir];
//            System.out.println(copyMatrix[ni][nj]+", "+copyMatrix[ci][cj]);

            if(isIn(ni,nj) && copyMatrix[ni][nj] != '#'){
                if(copyMatrix[ni][nj] == 'O'){ // 목표점에 도달했을 때 어떤 공이 들어간건지 알려준다.
                    if(curBall.kind) { // 빨간공이 들어간거
                        ci = ni;
                        cj = nj;
                        isInRed=true;
                        break;
                    }else{              // 파란공이 들어간거
                        ci = ni;
                        cj = nj;
                        isInBlue=true;
                        break;
                    }
                }else if(copyMatrix[ni][nj] == 'R' || copyMatrix[ni][nj] == 'B'){
                    // 이미 다른 공이 있으면 멈춘다.
                    break;
                }else if(copyMatrix[ni][nj]=='.'){
//                    System.out.println("여기는 안오나?");
                    // 이동 가능한 위치라면
                    ci = ni;
                    cj = nj;
                }
            }else{
                break;
            }
        }
        if(curBall.kind && copyMatrix[ci][cj]!='O'){
            copyMatrix[ci][cj] = 'R';
        }else if(!curBall.kind && copyMatrix[ci][cj]!='O'){
            copyMatrix[ci][cj] = 'B';
        }
//        if(!curBall.kind){
//            System.out.println("B가 어디갔지?    => "+ci+","+cj+"    값=> "+copyMatrix[ci][cj]);
//        }
        return new Ball(curBall.kind, ci,cj, curBall.commandCnt+1);


    }
    private static boolean isIn(int ni, int nj){
        return 0<= ni && ni <N && 0<= nj && nj <M;
    }

    private static char[][] copy(){
        char[][] c = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                c[i][j] = matrix[i][j];
            }

//            System.out.println(Arrays.toString(c[i]));
        }
        return c;
    }

    private static void showM(char[][] matrix){
        for (char[] chars : matrix) {
            System.out.println(Arrays.toString(chars));
        }
    }

}

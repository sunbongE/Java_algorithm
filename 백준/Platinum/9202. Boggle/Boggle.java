import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 가로, 세로, 대각선 인접한 글자를 합하여 사전에있는 문자열을 만들 수 있는지 확인하는거
 * 찾은것중 -> 가장 긴 문자열과 찾은 단어수, 총점 출력
 * <p>
 * 점수
 * 길이 1~2 = 0점
 * 길이 3~4 = 1점
 * 길이 5 = 2점
 * 길이 6 = 3점
 * 길이 7 = 5점
 * 길이 8 = 11점
 * <p>
 * 각 보드에서 사전에 있는 단어를 찾는거임.
 * 풀이
 * 1. 사전에 있는 모든 문자열은 1번째에서 찾고 답기록
 * 2. 다음 번째 보드에서 찾고 답 기록 반복
 * <p>
 * 문자열을 뽑아서 찾을건데 어떻게 찾아야 효율적인가
 * 1. 첫 문자부터 차례대로 같아야하는 특성을 이용해서 풀이를 해본다.
 * 2. ICPC가 찾는거라면 첫번째를 찾아서 거기서부터 가로,세로,대각 방향으로 문자탐색한다.
 * 3. 탐색한 문자는 두번째에 들어가야하니까 두번째랑 같은 문자열이 아닌경우는 리턴해야겠네
 * 4. 이게 빽트레킹이 맞나 싶다.
 * 5. 가지치기를 하면 되는구나
 * 6. 그럼 각 IDX번째에 맞지 않은 문자들은 가지치기를 하므로 불필요한 연산을 줄일 수 있겠군요.
 * <p>
 * 이걸로 풀어본다.
 */
public class Main {
    static int maxLen=-1, maxScore=0,findCnt;
    static String findString;
    // 상 하 좌 우 / 대각 : 좌상, 우상, 좌하, 우하 (8방향) 
    static int[] di = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dj = {0, 0, -1, 1, -1, 1, -1, 1};

    static final int SIZE = 4;
    static boolean isFind, visited[][];
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] dataList = new String[N];
        for (int i = 0; i < N; i++) {
            String data = br.readLine();
            dataList[i] = data;
        }

        br.readLine(); // 빈줄 처리

        int boardCnt = Integer.parseInt(br.readLine());

        char[][][] board = new char[boardCnt][SIZE][SIZE];
        // 보드 입력

        for (int k = 0; k < boardCnt; k++) {

            for (int i = 0; i < SIZE; i++) {
                String s = br.readLine();
                for (int j = 0; j < SIZE; j++) {
                    board[k][i][j] = s.charAt(j);
                }
            }
            if(k < boardCnt-1){
                br.readLine(); // 빈줄 처리.
            }
        }


        // 각 보드에서 문자열 찾아볼것.
        for (int k = 0; k < boardCnt; k++) {
            // 각 보드마다 초기화
            maxLen=0;
            maxScore=0;
            findString="";
            findCnt=0;

            for (int i = 0; i <dataList.length ; i++) { // 문자열 리스트에서 찾을 문자열 뽑아오기.
                String cur = dataList[i];


                // 현재 보드에서 이 문자를 찾을 시작 위치를 불러온다.
                Queue<int[]> startXY = getStartXY(cur.charAt(0), board[k]);

                isFind = false;
                while(!startXY.isEmpty()){
                    int[] start = startXY.poll();



                    if(cur.length()==1){
                        isFind = true;
                        break;
                    }

                    visited = new boolean[SIZE][SIZE];
                    visited[start[0]][start[1]] = true; // 시작위치 방문처리.

                    findCur(cur, board[k],1,  start[0],start[1]);
                    if(isFind) break;
                }

                // 찾았다면 여기서 점수는 += | 길이는 더 길다면 갱신, 길이가 갱신되면 문자열 갱신.
                if(isFind){
                    findCnt++;
                    maxScore += getScore(cur); // 점수 갱신

                    if(maxLen <= cur.length()){
//                        System.out.println("findString = "+findString+" cur = "+cur);
                        if(maxLen == cur.length()){
                            findString = (findString.compareTo(cur) < 0 ) ? findString : cur;
                        }else{
                            findString = cur;
                        }
                        maxLen = cur.length();

                    }
                }
            }

            sb.append(maxScore+" "+findString+" "+findCnt+"\n");

        }
        System.out.println(sb);

    }



    private static void findCur(String target, char[][] board,int idx, int ci, int cj) {

        if(isFind) return;

        if(idx == target.length()){
            isFind=true;
            return;
        }

        // 8방향으로 탐색해본다.
        int ni,nj;
        for (int dir = 0; dir < 8; dir++) {
            ni = ci + di[dir];
            nj = cj + dj[dir];
//
//            System.out.println("찾을거 = "+target);
//            System.out.println(ni+", "+nj+" | idx = "+idx);
//            if(isIn(ni,nj)){
//                System.out.println(board[ni][nj]+" == "+target.charAt(idx));
//            }

            // 다음 자리의 문자와 찾은 문자가 같으면 넣어준다.
            if(isIn(ni,nj) && !visited[ni][nj] && board[ni][nj] == target.charAt(idx)){
                visited[ni][nj] = true;
                findCur(target,board,idx+1,ni,nj);
                visited[ni][nj] = false;
            }

        }


    }
    private static Queue<int[]> getStartXY(char c, char[][] chars) {
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if(chars[i][j]==c) q.offer(new int[] {i,j});
            }
        }



        return q;

    }

    // * 길이 1~2 = 0점
// * 길이 3~4 = 1점
// * 길이 5 = 2점
// * 길이 6 = 3점
// * 길이 7 = 5점
// * 길이 8 = 11점
    private static int getScore(String cur) {
        int len = cur.length();
        if(len<=2){
            return 0;
        }else if (len<=4){
            return 1;
        } else if (len==5) {
            return 2;
        } else if (len==6) {
            return 3;
        } else if (len==7) {
            return 5;
        } else if (len==8) {
            return 11;
        }
        return 0;
    }

    private static boolean isIn(int ni, int nj){
        return 0<= ni && ni < SIZE && 0<= nj && nj < SIZE;
    }

}
/**
 * ACMA
 * APCA
 * TOGI
 * NEST
 * <p>
 * PCMM
 * RXAI
 * ORCN
 * GPCG
 * <p>
 * ICPC
 * GCPC
 * ICPC
 * GCPC
 *
 * for (char[][] chars : board) {
 *             for (char[] aChar : chars) {
 *                 System.out.println(Arrays.toString(aChar));
 *             }
 *         }
 *
 *
 */
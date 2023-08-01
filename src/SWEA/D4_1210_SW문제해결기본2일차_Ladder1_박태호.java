package SWEA;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class D4_1210_SW문제해결기본2일차_Ladder1_박태호 {
	static int ans;
	static int startX;
	static int startY;
	static int nx;
	static int ny;
	static int cx;
	static int cy;
	static int N=100;
	static int[] dx = {-1,0,0};
	static int[] dy = {0,-1,1};
	static int idx=0;
	static int[][] map;
	
	public static void sol(int sx, int sy, int idx) {
		// 종료조건
		if(sx==0) {
			ans = sy;
			return;
		}
		// 다음 위치를 구해준다.
		nx = sx+dx[idx]; ny = sy+dy[idx];
		if(idx == 0) { // 위로 직진 중일 때
			//좌
			cx = nx+dx[1];  
			cy = ny+dy[1];
			if((0<=cx&&cx<100)&&(0<=cy&&cy<100)&&(map[cx][cy]!=0)) {
				idx =1;
				sol(cx,cy,idx);
//				return ;
			}
			
			//우 확인
			cx = nx+dx[2];  
			cy = ny+dy[2];
			if((0<=cx&&cx<100)&&(0<=cy&&cy<100)&&(map[cx][cy]!=0)) {
				idx =2;
				sol(cx,cy,idx);
//				return ;
			}
			if(idx==0) {
				sol(nx,ny,idx);
			}
		}else { // 옆방향으로 이동중인경우.
			// 위에 길이 있는지 확인 있으면 idx=0으로 바꾸고 가고 없으면 다음 위치 호출
			cx = nx+dx[0]; cy = ny+dy[0];
			if((0<=cx&&cx<100)&&(0<=cy&&cy<100)&&map[cx][cy]==1) { // 범위 체크, 위
				idx=0;
				sol(nx,ny,idx); // 위에 길이 뚫리면 위로 이동.
			}else {
				
				sol(nx,ny,idx); // 위에 길이 뚫리면 위로 이동.
			}
				
			//			return;
		}
		
	}
	
	public static void findStart() {
		// 시작 위치 찾기.
			for (int i = N-1; i >= N-1; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j]==2) {
						startX=i;startY=j; // 시작위치 찾기.
						return;
					}
				}
				
			}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[N][N];;
		for (int tc = 1; tc <= 10; tc++) {
			String _ = br.readLine(); // 테케입력받은거 무시.
			for (int i = 0; i < N; i++) {
				String target = br.readLine().replaceAll(" ", "");
				for (int j = 0; j < N; j++) {
					map[i][j] = Character.getNumericValue(target.charAt(j));
				}
			}
			// 시작위치 찾기.
			findStart();
			sol(startX,startY,idx);
			System.out.printf("#%d %d%n",tc,ans);
			
			
			 // 2차원 배열 선언..
			// 배열에 값입력//
			
		}
	}

}
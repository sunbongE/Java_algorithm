package BJ;
/**
 * @author 박태호
 * <pre>
 * 입력받은 문자열의 길이, 현재 재귀깊이, 몇번 변하는지, 비교할 문자 를
 * 매개변수로 sol함수에 넘겨주면
 * sol함수는 범위 내에서 문자열의 위치를 비교하여 몇번의 변화가 있는지 cnt에 기록을한다.
 * 그리고 문자열 길이 만큼 돌았다면 리턴
 * </pre>
 */
import java.util.Scanner;

public class SWEA_1289_원재의메모리복구하기 {
	static String inp;
    public static int sol(int N,int n,int cnt, char change ) {

    	if(n>N) { // 범위 벗어나면 리턴
    		return cnt;
    	}
    	
    	if(inp.charAt(n)!=change) { // 문자가 다르고
    		if(change=='0') { // 비교한 문자가 0 일때 1, 
    			change='1';
    		}else {//1 -> 0 으로
    			change='0';
    		}
    		cnt = sol(N,n+1,cnt+1,change); // 호출횟수, 변화한 수를 1업해서 호출
    	}else {
    		cnt = sol(N,n+1,cnt,change);// 호출횟수 1업해서 호출
    	}
        
        return cnt;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            inp= sc.next();
            int ans = sol(inp.length()-1,0,0,'0');
            System.out.printf("#%d %d%n",tc,ans,'0');
        }
    }

}
package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author qkrxo
 * <pre>
 * 공
 * 
 * 풀이
 * 공을 가진 컵의 번호를 tmp에 넣고 
 * 각 줄에 입력으로 오는 번호를 비교해서
 * tmp의 번호가 있는 입력이 오면 tmp가 아닌 다른 번호를 tmp에 할당한다.
 * </pre>
 */
public class BJ_1547 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int tmp = 1;
		String[] INP;
		for(int i=0;i<N;i++) {
			INP= br.readLine().split(" ");
			int x = Integer.parseInt(INP[0]);
			int y = Integer.parseInt(INP[1]);
			
			if(x==tmp) {
				tmp=y;
			}else if(y==tmp) {
				tmp = x;
			}
		}

		System.out.println(tmp);
		br.close();
	}

}
